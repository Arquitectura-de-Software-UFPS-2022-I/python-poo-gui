package ufps.arqui.python.poo.gui.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Clase para representar el paquete de los ficheros de python.
 *
 * @author Omar Ramón Montes
 */
public class Directorio {

    /**
     * Representación de un directorio.
     */
    private File directorio;

    /**
     * Listado de directorios dentro del directorio actual.
     */
    private List<Directorio> directorios;

    /**
     * Listado de archivos python dentro del directorio actual.
     */
    private List<ArchivoPython> archivos;

    /**
     * Método para crear un archivo .py y crear clases en el.
     */
    public void agregarClase(String nombreArchivo, String nombreClase) {
        File archivo = new File(nombreArchivo + "src/fichero.py");

        try {
            // Verifica si el archivo ya existe
            if (!archivo.exists()) {
                archivo.createNewFile();
                ArchivoPython ap = new ArchivoPython();
                ap.setArchivo(archivo);
                addArchivo(ap);
            }
            
            // Agrega la clase al archivo .py
            for (int i = 0; i < this.archivos.size(); i++) {
                if (this.archivos.get(i).getArchivo().getPath().equals(archivo.getPath())) {
                    if (!(this.archivos.get(i).claseExiste(nombreClase))) {
                        ClasePython clasePython = new ClasePython();
                        clasePython.setNombre(nombreClase);
                        this.archivos.get(i).addClase(clasePython);
                        FileWriter fw = new FileWriter(archivo.getAbsoluteFile(), true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write("class " + nombreClase + " :\n\tpass\n");
                        bw.close();
                    }
                }
            }
        } catch (Exception e) {
            //...
        }
    }

    public File getDirectorio() {
        return directorio;
    }

    public void setDirectorio(File directorio) {
        this.directorio = directorio;
    }

    public List<Directorio> getDirectorios() {
        return directorios;
    }

    public void setDirectorios(List<Directorio> directorios) {
        this.directorios = directorios;
    }

    public void addDirectorio(Directorio directorio) {
        this.directorios.add(directorio);
    }

    public List<ArchivoPython> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<ArchivoPython> archivos) {
        this.archivos = archivos;
    }

    public void addArchivo(ArchivoPython archivo) {
        this.archivos.add(archivo);
    }

    @Override
    public String toString() {
        return "Directorio{" + "directorio=" + directorio + '}';
    }
}
