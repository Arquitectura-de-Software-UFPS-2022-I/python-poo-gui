package ufps.arqui.python.poo.gui.controllers.impl;

import java.io.IOException;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Controlador del proyecto.
 *
 * Implementación del controlador proyecto.
 *
 * @author Omar Ramón Montes
 */
public class ProyectoController implements IProyectoController {

    private final Proyecto proyecto;

    public ProyectoController(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public void escanearProyecto() throws Exceptions {
        this.proyecto.escanearProyecto();
    }

    @Override
    public void obtenerClasesDesde(String relativePath) {
        this.proyecto.obtenerClasesDesde(relativePath);
    }

    public void eliminarArchivo(String relativePath) throws Exceptions {
        this.proyecto.eliminarArchivo(relativePath);
    }

    @Override
    public void abrirArchivo(String path) throws Exceptions{
        this.proyecto.abrirArchivo(path);
    }

    @Override
    public void cerrarArchivo(String path) throws Exceptions {
        this.proyecto.cerrarArchivo(path);
    }
    
    @Override
    public void guardarArchivo(String path, String contenido) throws Exceptions {
        this.proyecto.guardarArchivo(path, contenido);
    }

    @Override
    public void crearArchivo(String path, String nombre) throws Exceptions {
        this.proyecto.crearArchivo(path, nombre);
    }

    @Override
    public void crearClase(String absolutePath, String nombre) throws Exceptions {
        this.proyecto.crearClase(absolutePath, nombre);
    }
}
