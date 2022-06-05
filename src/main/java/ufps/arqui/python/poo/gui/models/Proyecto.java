package ufps.arqui.python.poo.gui.models;

import java.util.Observable;

/**
 * Modelo para la gestión del proyecto del usuario.
 * 
 * Aquí se almacenará la información del proyecto, para que los demás 
 * componentes puedan interacturar con el proyecto, como por ejemplo
 * lista de clases con sus metodos y parametros; datos del proyecto; datos del 
 * mundo como los objetos.
 * @author Omar Ramón Montes
 */
public class Proyecto extends Observable{
    
    /**
     * Nombre del proyecto.
     */
    private String nombre;
    
    /**
     * Ruta del directorio raiz donde se guarda el proyecto.
     */
    private String directorio;

    public String getNombre() {
        return nombre;
    }

    public String getDirectorio() {
        return directorio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        super.setChanged();
        super.notifyObservers(this.nombre);
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
        super.setChanged();
        super.notifyObservers(this.directorio);
    } 

    @Override
    public String toString() {
        return "{" + "nombre=" + nombre + ", directorio=" + directorio + '}';
    }
    
    
    
}
