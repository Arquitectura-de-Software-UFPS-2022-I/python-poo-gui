package ufps.arqui.python.poo.gui.models;

import java.io.File;
import java.util.ArrayList;
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
    private List<ClasePython> clases = new ArrayList<ClasePython>();

    /**
     * Verifica si la clase que se quiere agregar ya existe
     */
    public boolean claseExiste(String nombreClase) {
        boolean respuesta = false;
        for (int i = 0; i < this.clases.size(); i++) {
            if (this.clases.get(i).getNombre().equals(nombreClase)) {
                respuesta = true;
            }
        }
        return respuesta;
    }

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
