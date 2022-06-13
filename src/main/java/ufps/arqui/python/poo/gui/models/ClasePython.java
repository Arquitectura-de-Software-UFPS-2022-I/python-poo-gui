package ufps.arqui.python.poo.gui.models;

import java.util.List;

/**
 * Modelado de clase de python en java.
 *
 * @author Omar Ramón Montes
 */
public class ClasePython {

    /**
     * Nombre de la clase Python.
     */
    private String nombre;

    /**
     * Listado de clases de las cuales hereda la clase actual.
     */
    List<ClasePython> herencia;
    
    /**
     * Posición en la cual la clase SERA o FUE dibujada en la vista 
     */
    private Posicion posicion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ClasePython> getHerencia() {
        return herencia;
    }

    public void setHerencia(List<ClasePython> herencia) {
        this.herencia = herencia;
    }

    public void addHerencia(ClasePython clase) {
        this.herencia.add(clase);
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "ClasePython{" + "nombre=" + nombre + '}';
    }

}
