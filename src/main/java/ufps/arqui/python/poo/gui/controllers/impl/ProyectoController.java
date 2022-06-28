package ufps.arqui.python.poo.gui.controllers.impl;


import java.io.IOException;



import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.IOException;



import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.ArchivoPython;
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

    @Override
    public void crearClase(String nombre, ArchivoPython archivo) throws Exceptions {
        archivo.crearClase(nombre);
    }

    @Override
    public ArchivoPython obtenerArchivo(String nombre) throws Exceptions {
        return this.proyecto.getDirectorioTrabajo().getArchivoPorNombre(nombre);
    }
    public void eliminarArchivo(String relativePath) throws Exceptions {
        this.proyecto.eliminarArchivo(relativePath);
    }

}
