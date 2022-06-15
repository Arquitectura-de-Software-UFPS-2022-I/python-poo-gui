package ufps.arqui.python.poo.gui.models;

import java.io.File;
import java.util.List;

/**
 * Modelado de archivo de python.
 *
 * @author Omar Ramón Montes
 */
public class ArchivoPython {

    /**
     * Representación del archivo python.
     */
    private File archivo;

    /**
     * Listado de clases que contiene el archivo.
     */
    private List<ClasePython> clases;

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public List<ClasePython> getClases() {
        return clases;
    }

    public void setClases(List<ClasePython> clases) {
        this.clases = clases;
    }

    public void addClase(ClasePython clase) {
        this.clases.add(clase);
    }

}
