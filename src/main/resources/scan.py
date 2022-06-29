import importlib
import inspect
import json
import os
import sys
import re


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
    def __init__(self, file, name):
        self.file = file
        self.name = name
        self.clases = []

    def __get_list_classes_json(self):
        list_classes_json = []
        for clase in self.clases:
            list_classes_json.append(clase.to_json())
        return list_classes_json

    def to_json(self):
        return {
            "archivoStr": self.file,
            "module": self.name,
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

    def get_names_modules(self):
        list_mudules = []
        for file in self.archivos:
            list_mudules.append(file.name)

        for folder in self.directorios:
            list_mudules += folder.get_names_modules()
        return list_mudules

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

#Obtiene el archivo las lineas de inicio y fin de una clase
def get_lines_class(class_name):
    file = inspect.getsourcefile(class_name)
    codigo, index = inspect.findsource(class_name)
    index_last = index
    for ind_linea in range(index+1, len(codigo)):
        index_last += 1
        if codigo[ind_linea].startswith('class '):
            break
    data = {'inicio': str(index), 'fin': str(index_last-1), 'archivo': str(file).replace("'", ""), 'clase': class_name.__name__}
    print("get_lines_class:"+json.dumps(json.loads(str(data).replace("'", '"'))))

#List all python files in current folder and subdirectories and save in a list
def list_files(path):
    files = []
    dir_names = []
    for root, dirs, filenames in os.walk(path):
        dir_names += ['{}\\{}'.format(root, dir_name) for dir_name in dirs if dir_name != "__pycache__"]
        for filename in filenames:
            if filename.endswith(".py") and filename != "__init__.py" and filename != "scan.py":
                files.append(os.path.join(root, filename))
    return [files, dir_names]

def list_all_instancias(local_val: dict):
    classes = [cls for cls in inspect.getmembers(sys.modules[__name__], inspect.isclass) if cls[1].__module__ != '__main__']
    classes_values = []
    for key, value in local_val.items():
        for cls in classes:
            if isinstance(value, cls[1]):
                attr_value = {'attrs': [], 'methods': [], 'name_class': cls[0], 'name': key}
                # attribute is a string representing the attribute name
                for attribute in dir(local_val[key]):
                    # Get the attribute value
                    attribute_value = getattr(local_val[key], attribute)
                    # Check that it is callable
                    if callable(attribute_value):
                        # Filter all dunder (__ prefix) methods
                        if attribute.startswith('__') == False:
                            attr_value['methods'].append({
                                'name': attribute,
                                'args': [arg for arg in inspect.getfullargspec(attribute_value).args if arg != 'self']
                            })
                for atkey in local_val[key].__dict__.keys():
                    attr_value['attrs'].append({
                        'key': atkey,
                        'value': str(local_val[key].__dict__[atkey]),
                        'type': str(type(local_val[key].__dict__[atkey])).replace("'", "")
                    })
                classes_values.append(attr_value)
                break

    print("list_all_instancias:"+json.dumps(json.loads(str(classes_values).replace("'", '"'))))

def list_all_python_class_with_hierarchy(list_of_files_folder):
    list_of_files = list_of_files_folder[0]
    list_of_folder = list_of_files_folder[1]
    dict_folders_class = {}
    dict_folders_list = []

    dict_folders = {}
    dict_modules = []
    dict_class = []

    # src por default, cuando el directorio está vacio, o no hay archivos.
    folder = 'src'
    dict_folders[folder] = {}
    directorio = Directorio(folder)
    dict_folders_class[folder] = directorio
    dict_folders_list.append(directorio)
    for folder in list_of_folder:
        if folder not in dict_folders:
            dict_folders[folder] = {}
            directorio = Directorio(folder)
            dict_folders_class[folder] = directorio
            dict_folders_list.append(directorio)
    errores = []
    for file in list_of_files:
        ## Eliminar modulos previamente importados.
        exec('if "{0}" in sys.modules.keys():del sys.modules["{0}"]'.format(file[:-3].replace("\\", ".")))

        folder = "\\".join(file.split("\\")[:-1])
        directorio = dict_folders_class[folder]
        module = file.split("\\")[-1].split(".")[0]
        path_module = file[:-3].replace("\\", ".")

        if module not in dict_modules:
            archivo = directorio.get_file(module)
            if archivo is None:
                archivo = ArchivoPython(module, path_module)
                directorio.archivos.append(archivo)

        try:
            #name -> nombre de clase
            #cls -> tipo de clase
            for name, cls in inspect.getmembers(importlib.import_module(file[:-3].replace("\\", ".")), inspect.isclass):
                #module -> archivo python
                #class_name -> nombre de clase
                module = str(cls).split("'")[1].split(".")[-2]
                path_module = ".".join(str(cls).split("'")[1].split(".")[:-1])

                #Evita repetir el archivo debido al import
                if module not in dict_modules:
                    archivo = directorio.get_file(module)
                    if archivo is None:
                        archivo = ArchivoPython(module, path_module)
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
        except Exception as err:
            errores.append(str(err))
    return [dict_folders_class, errores]


def scanner_project():
    dict_folders_class = list_all_python_class_with_hierarchy(list_files("src"))
    errores = dict_folders_class[1]
    if errores:
        for error in errores:
            print("errores_importacion:{}".format(error))
    dict_folders_class = dict_folders_class[0]
    src = dict_folders_class["src"]
    del dict_folders_class["src"]
    for val in dict_folders_class.values():
        src.push_folder(val, val.directorio.split("\\")[1:])
    src.set_absolute_path(os.getcwd())
    module_names = src.get_names_modules()
    import_modules = []
    for module in module_names:
        import_modules.append("from {} import *".format(module))
    print("get_directorio_trabajo:"+json.dumps(json.loads(str(src).replace("'", '"'))))
    print("import_modules:"+json.dumps(json.loads(str(import_modules).replace("'", '"'))))
