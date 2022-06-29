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
    public void abrirProyecto(String directorio) throws Exceptions {
        if (directorio == null || directorio.trim().isEmpty()) {
            throw new Exceptions("El directorio del proyecto está vacio", null);
        }
        File dir = new File(directorio);
        if (dir.exists() && dir.isDirectory()) {
            this.proyecto.resetearProyecto();
            this.proyecto.setDirectorioRaiz(dir);
        } else {
            throw new Exceptions("El directorio no existe", null);
        }
    }

    @Override
    public void crearProyecto(String nombre, String directorio, String comandoPython) throws Exceptions{

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exceptions("El nombre del proyecto está vacio", null);
        }
        if (directorio == null || directorio.trim().isEmpty()) {
            throw new Exceptions("El directorio del proyecto está vacio", null);
        }
        if (comandoPython == null || comandoPython.trim().isEmpty()) {
            throw new Exceptions("El comando de python está vacio", null);
        }
        this.proyecto.setNombre(nombre);
        this.proyecto.setComandoPython(comandoPython);
        this.proyecto.setDirectorioRaiz(new File(directorio));
    }

    @Override
    public String toString() {
        return proyecto.toString();
    }

    @Override
    public String gerNombreProyecto() {
        return this.proyecto.getNombre();
    }

    @Override
    public String getPythonProyecto() {
        return this.proyecto.getComandoPython();
    }

    @Override
    public String getDireccionProyecto() {
        return this.proyecto.getDirectorio();
    }
}
