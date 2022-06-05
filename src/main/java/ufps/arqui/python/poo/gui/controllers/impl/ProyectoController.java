package ufps.arqui.python.poo.gui.controllers.impl;

import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Implementación dl Controlador del mundo del proyecto.
 *
 * Donde se administra las clases creadas por el usuario.
 * @author Omar Ramón Montes
 */
public class ProyectoController implements IProyectoController {

    private final Proyecto proyecto;

    public ProyectoController(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

}
