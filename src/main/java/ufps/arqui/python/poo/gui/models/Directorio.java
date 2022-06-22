package ufps.arqui.python.poo.gui.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para representar el paquete de los ficheros de python.
 *
 * @author Omar Ramón Montes
 */
public class Directorio {

    /**
     * Representación de un directorio en String. Es necesario para parsear el
     * json
     */
    private String directorioStr;

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
    private List<ArchivoPython> archivos = new ArrayList<>();
    
    /**
     * Crea un archivo .py tomando como ruta el directorio en el que se encuentra.
     * 
     * @param nombreArchivo 
     */
    public void crearArchivo(String nombreArchivo) {
        File file = new File(directorio.getAbsolutePath() + "/" + nombreArchivo + ".py");
        try {
            if (!file.exists()) {
                file.createNewFile();
                ArchivoPython ap = new ArchivoPython();
                ap.setArchivo(file);
                addArchivo(ap);
            } else {
                //..
            }
        } catch (Exception e) {
            //..
        }
    }

    public File getDirectorio() {
        if (this.directorio == null && this.directorioStr != null) {
            this.directorio = new File(this.directorioStr);
        }
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
