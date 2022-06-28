package ufps.arqui.python.poo.gui.models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelado de archivo de python.
 *
 * @author Omar Ramón Montes
 */
public class ArchivoPython {

    /**
     * Representación del archivo python en String. Es necesario para parsear el
     * json
     */
    private String archivoStr;

    /**
     * Representación del archivo python.
     */
    private File archivo;

    /**
     * Listado de clases que contiene el archivo.
     */
    private List<ClasePython> clases = new ArrayList<>();

    /**
     * Crear una clase en el atchivo actual.
     * 
     * @param nombre 
     */
    public void crearClase(String nombre) {
        boolean existe = false;
        for (int i = 0; i < this.clases.size(); i++) {
            if (this.clases.get(i).getNombre().equals(nombre)) {
                existe = true;
                break;
            }
        }
        try {
            if (!existe) {
                ClasePython clasePython = new ClasePython();
                clasePython.setNombre(nombre);
                addClase(clasePython);
                FileWriter fw = new FileWriter(archivo.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("class " + nombre + ":\n\tpass\n");
                bw.close();
            }
        } catch (Exception e) {
            //..
        }
    }

    public File getArchivo() {
        if (this.archivo == null && this.archivoStr != null) {
            this.archivo = new File(this.archivoStr);
        }
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
