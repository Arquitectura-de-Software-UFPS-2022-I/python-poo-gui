package ufps.arqui.python.poo.gui.controllers.impl;

import ufps.arqui.python.poo.gui.controllers.IMundoController;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Controlador del mundo del proyecto.
 *
 * Implementación del controlador mundo
 * @author Omar Ramón Montes
 */
public class MundoController implements IMundoController {

    private final Proyecto proyecto;

    public MundoController(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

}
