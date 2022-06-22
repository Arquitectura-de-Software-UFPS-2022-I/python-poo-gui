import importlib
import inspect
import json
import os


class ClasePython:
    def __init__(self, nombre, path_module):
        self.nombre = nombre
        self.path_module = path_module
        self.herencia = []
    
    def __get_list_herencia_json(self):
        list_herencia_json = []
        for herencia in self.herencia:
            list_herencia_json.append(herencia.to_json())
        return list_herencia_json

    def to_json(self):
        return {
            "nombre": self.nombre,
            "pathModule": self.path_module,
            "herencia": self.__get_list_herencia_json()
        }
    
    def __str__(self) -> str:
        return str(self.to_json())


class ArchivoPython:
    def __init__(self, file):
        self.file = file
        self.clases = []

    def __get_list_classes_json(self):
        list_classes_json = []
        for clase in self.clases:
            list_classes_json.append(clase.to_json())
        return list_classes_json

    def to_json(self):
        return {
            "archivoStr": self.file,
            "clases": self.__get_list_classes_json()
        }
    
    def __str__(self) -> str:
        return str(self.to_json())

class Directorio:
    def __init__(self, directorio):
        self.directorio = directorio
        self.name = directorio.split("\\")[-1]
        self.archivos = []
        self.directorios = []
    
    def set_absolute_path(self, absolute_path):
        self.directorio = absolute_path + "\\" + self.directorio
        for file in self.archivos:
            file.file = self.directorio + "\\" + file.file + ".py"

        for folder in self.directorios:
            folder.set_absolute_path(absolute_path)

    def push_folder(self, folder, folder_level):
        if len(folder_level) == 1:
            self.directorios.append(folder)
        else:
            for subfolder in self.directorios:
                if subfolder.name == folder_level[0]:
                    del folder_level[0]
                    subfolder.push_folder(folder, folder_level)

    def get_file(self, name):
        for archivo in self.archivos:
            if archivo.file == name:
                return archivo
    
    def __get_list_files_json(self):
        list_files = []
        for archivo in self.archivos:
            list_files.append(archivo.to_json())
        return list_files

    def __get_list_folders_json(self):
        list_folders = []
        for directorio in self.directorios:
            list_folders.append(directorio.to_json())
        return list_folders

    def to_json(self):
        return {
            "directorioStr": self.directorio,
            "archivos": self.__get_list_files_json(),
            "directorios": self.__get_list_folders_json()
        }

    def __str__(self) -> str:
        return str(self.to_json())

#List all python files in current folder and subdirectories and save in a list
def list_files(path):
    files = []
    for root, dirs, filenames in os.walk(path):
        print('list_files', root, dirs, filenames)
        for filename in filenames:
            if filename.endswith(".py") and filename != "__init__.py" and filename != "scan.py":
                files.append(os.path.join(root, filename))
    return files

def list_all_python_class_with_hierarchy(list_of_files):
    dict_folders_class = {}
    dict_folders_list = []

    dict_folders = {}
    dict_modules = []
    dict_class = []
    for file in list_of_files:
        #name -> nombre de clase
        #cls -> tipo de clase	
        for name, cls in inspect.getmembers(importlib.import_module(file[:-3].replace("\\", ".")), inspect.isclass):
            folder = "\\".join(file.split("\\")[:-1])
            #print(folder)
            #print(cls, name)
            if folder not in dict_folders:
                dict_folders[folder] = {}
                
                directorio = Directorio(folder)
                dict_folders_class[folder] = directorio
                dict_folders_list.append(directorio)

            directorio = dict_folders_class[folder]

            #module -> archivo python
            #class_name -> nombre de clase
            module = str(cls).split("'")[1].split(".")[-2]
            path_module = ".".join(str(cls).split("'")[1].split(".")[:-1])
            
            #Evita repetir el archivo debido al import
            if module not in dict_modules:
                archivo = directorio.get_file(module)
                if archivo is None:
                    archivo = ArchivoPython(module)
                    directorio.archivos.append(archivo)

            if module not in dict_folders[folder]:
                if module not in dict_modules:
                    dict_modules.append(module)
                    dict_folders[folder][module] = []
            
            clase_path = cls.__module__ + "." + name
            if clase_path not in dict_class:
                dict_class.append(clase_path)
                clase_python = ClasePython(name, path_module)
                if len(cls.__bases__) != 1 or cls.__bases__[0].__name__ != "object":
                    for type_class in cls.__bases__:
                        class_split = str(type_class).split("'")[1].split(".")
                        path_mod_herencia, class_name = ".".join(class_split[:-1]), class_split[-1]

                        clase_python.herencia.append(ClasePython(class_name, path_mod_herencia))
                archivo.clases.append(clase_python)

    return dict_folders_class

dict_folders_class = list_all_python_class_with_hierarchy(list_files("src"))
src = dict_folders_class.get("src")
if src:
    del dict_folders_class["src"]
    for val in dict_folders_class.values():
        src.push_folder(val, val.directorio.split("\\")[1:])
    src.set_absolute_path(os.getcwd())

    #print(json.dumps(json.loads(str(src).replace("'", '"')), indent=3))
    print(json.dumps(json.loads(str(src).replace("'", '"'))))