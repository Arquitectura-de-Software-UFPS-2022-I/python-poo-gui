package ufps.arqui.python.poo.gui.controllers.impl;

import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Implementación del Controlador de la terminal del proyecto.
 *
 * Donde el usuario puede ejecutar metodos de python.
 * @author Omar Ramón Montes
 */
public class TerminalController implements ITerminalController {

    private final Proyecto proyecto;

    public TerminalController(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

}
