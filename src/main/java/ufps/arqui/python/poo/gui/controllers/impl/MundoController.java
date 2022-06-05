package ufps.arqui.python.poo.gui.controllers.impl;

import ufps.arqui.python.poo.gui.controllers.IMundoController;
import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Implementación del Controlador del mundo del proyecto.
 *
 * Donde se administra las variables creadas por el usuario.
 * @author Omar Ramón Montes
 */
public class MundoController implements IMundoController {

    private final Proyecto proyecto;

    public MundoController(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

}
