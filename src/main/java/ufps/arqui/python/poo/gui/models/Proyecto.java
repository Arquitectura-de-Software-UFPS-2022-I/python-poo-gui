package ufps.arqui.python.poo.gui.models;

import java.io.File;
import java.util.Observable;

/**
 * Modelo para la gestión del proyecto del usuario.
 *
 * Aquí se almacenará la información del proyecto, para que los demás
 * componentes puedan interacturar con el proyecto, como por ejemplo lista de
 * clases con sus metodos y parametros; datos del proyecto; datos del mundo como
 * los objetos.
 *
 * @author Omar Ramón Montes
 */
public class Proyecto extends Observable {

    /**
     * Nombre del proyecto.
     */
    private String nombre;

    /**
     * Ubicación del directorio donde se ejecutará la aplicación.
     *
     */
    private File directorioRaiz;

    /**
     * Directtorio de trabajo, donde el usuario tendra su proyecto.
     *
     * El directorio debe llamarse src, y debe estár dentro del directorio raiz.
     */
    private Directorio directorioTrabajo;

    public void setNombre(String nombre) {
        this.nombre = nombre;
        super.setChanged();
        super.notifyObservers(this.nombre);
    }

    public void setDirectorioRaiz(File directorioRaiz) {
        this.directorioRaiz = directorioRaiz;
        // TODO: al cambiar de directorio, inicializar el directorio de trabajo,
        // Validar si existe, y realizar la lectura de los archivos, sino, 
        // registrarlo.
        super.setChanged();
        super.notifyObservers(this.directorioRaiz);
    }

    public String getNombre() {
        return nombre;
    }

    public File getDirectorioRaiz() {
        return directorioRaiz;
    }

    public Directorio getDirectorioTrabajo() {
        return directorioTrabajo;
    }
    
    

    @Override
    public String toString() {
        return "Proyecto{" + "nombre=" + nombre + ", directorioRaiz=" + directorioRaiz + ", directorioTrabajo=" + directorioTrabajo + '}';
    }

}
