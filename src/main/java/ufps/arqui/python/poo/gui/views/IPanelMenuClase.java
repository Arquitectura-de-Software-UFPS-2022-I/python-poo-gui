package ufps.arqui.python.poo.gui.views;

import java.io.IOException;
import java.util.Observer;

/**
 * Interfaz del menú para visualizar las opciones del proyecto.
 * 
 * En el menú se podrán realizar las diferentes opciones del sistema,
 * como por ejemplo guardar el proyecto, crear uno nuevo, ver el manual, etc.
 * @author Omar Ramón Montes
 */
public interface IPanelMenuClase extends Observer, IPanelView {
    
    /**
     * El modal para crear una clase llama a este metodo del PanelMenu cuando el usuario termino de
     * diligienciar los campos para crear una clase.
     *
     * @param nombre nombre del proyecto.
     */
    void modalCrearProyecto(String nombre)  throws IOException;
}
