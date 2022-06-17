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
     * Representación del archivo python en String.
     * Es necesario para parsear el json
     */
    private String archivoStr;
    
    /**
     * Representación del archivo python.
     */
    private File archivo;
    
    /**
     * Listado de clases que contiene el archivo.
     */
    private List<ClasePython> clases;

    public File getArchivo() {
        if(this.archivo == null && this.archivoStr != null)
            this.archivo = new File(this.archivoStr);
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
