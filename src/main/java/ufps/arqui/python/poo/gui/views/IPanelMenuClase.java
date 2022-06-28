package ufps.arqui.python.poo.gui.views;

import java.io.IOException;
import java.util.Observer;

/**
 * Interfaz del menú para visualizar la opcion de crear una clase.
 * 
 * @author Rafael Peña
 */
public interface IPanelMenuClase extends Observer, IPanelView {
    
    /**
     * El modal para crear una clase llama a este metodo del PanelMenuClase cuando el usuario termino de
     * diligienciar los campos para crear una clase.
     *
     * @param nombre nombre de la clase.
     */
    void modalCrearClase(String nombre)  throws IOException;
}
