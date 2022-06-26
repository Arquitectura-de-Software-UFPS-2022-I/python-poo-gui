package ufps.arqui.python.poo.gui.controllers.impl;

import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Proyecto;

import java.io.File;

/**
 * Controlador del menú superior del proyecto.
 *
 * Implementación del controlador menú
 * @author Omar Ramón Montes
 */
public class MenuController implements IMenuController{
    
    private final Proyecto proyecto;

    public MenuController(Proyecto proyecto) {
        this.proyecto = proyecto;
    }   

    @Override
    public void abrirProyecto(String nombre, String directorio) throws Exceptions {
        File dir = new File(directorio);
        if (dir.exists() && dir.isDirectory()) {
            this.proyecto.setNombre(nombre);
            this.proyecto.setDirectorioRaiz(dir);
        } else {
            throw new Exceptions("El directorio no existe", null);
        }
    }

    @Override
    public void crearProyecto(String nombre, String directorio) throws Exceptions{
        this.proyecto.setNombre(nombre);
        this.proyecto.setDirectorioRaiz(new File(directorio));
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
