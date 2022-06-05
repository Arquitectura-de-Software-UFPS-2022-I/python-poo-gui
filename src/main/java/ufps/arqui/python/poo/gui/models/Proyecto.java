package ufps.arqui.python.poo.gui.models;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Omar Ramón Montes
 */
public class Proyecto extends Observable{
    
    private String nombre;
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
