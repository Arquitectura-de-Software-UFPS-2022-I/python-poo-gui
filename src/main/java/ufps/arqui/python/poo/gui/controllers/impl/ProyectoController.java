package ufps.arqui.python.poo.gui.controllers.impl;

import java.nio.file.Files;
import java.util.Arrays;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.ArchivoPython;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Controlador del proyecto.
 *
 * Implementación del controlador proyecto.
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
        return this.proyecto.getDirectorioTrabajo().getArchivos().stream()
                .filter(archivo -> nombre.equals(archivo.getArchivo().getName().split("\\.")[0].split("\\(")[0]))
                .findAny()
                .orElse(null);
    }
}
