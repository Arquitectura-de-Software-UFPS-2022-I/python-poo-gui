package ufps.arqui.python.poo.gui.models;

import java.io.File;
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
