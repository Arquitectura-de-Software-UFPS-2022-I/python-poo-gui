package ufps.arqui.python.poo.gui.controllers.impl;

import java.io.IOException;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
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
    public void scanearProyecto() throws IOException {
        this.proyecto.scanearProyecto();
    }

    @Override
    public void obtenerClasesDesde(String relativePath) {
        this.proyecto.obtenerClasesDesde(relativePath);
    }

}
