package ufps.arqui.python.poo.gui.controllers.impl;

import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Controlador del menú superior del proyecto.
 * 
 * 
 * @author Omar Ramón Montes
 */
public class MenuController implements IMenuController{
    
    private final Proyecto proyecto;

    public MenuController(Proyecto proyecto) {
        this.proyecto = proyecto;
    }   

    @Override
    public void abrirProyecto(String nombre, String directorio) {
        this.proyecto.setNombre(nombre);
        this.proyecto.setDirectorio(directorio);
    }

    @Override
    public void visualizarManualUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return proyecto.toString();
    }
    
}
