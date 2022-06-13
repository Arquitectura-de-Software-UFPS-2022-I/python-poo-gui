package ufps.arqui.python.poo.gui.views;

import java.util.Observer;

/**
 * Interfaz del menú para visualizar las opciones del proyecto.
 * 
 * En el menú se podrán realizar las diferentes opciones del sistema,
 * como por ejemplo guardar el proyecto, crear uno nuevo, ver el manual, etc.
 * @author Omar Ramón Montes
 */
public interface IPanelMenu extends Observer, IPanelView {
    
    /**
     * El modal para crear proyecto llama a este metodo del PanelMenu cuando
     * el usuario termino de diligienciar los campos para crear un proyecto
     * @param name
     * @param path 
     */
    public void modalCrearProyectoEvento(String name, String path);
}
