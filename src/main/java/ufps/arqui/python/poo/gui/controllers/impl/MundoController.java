package ufps.arqui.python.poo.gui.controllers.impl;

import ufps.arqui.python.poo.gui.controllers.IMundoController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Mundo;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Controlador del mundo del proyecto.
 *
 * Implementación del controlador mundo
 * @author Omar Ramón Montes
 */
public class MundoController implements IMundoController {

    private final Proyecto proyecto;
    private final Mundo mundo;

    public MundoController(Proyecto proyecto, Mundo mundo) {
        this.proyecto = proyecto;
        this.mundo = mundo;
    }

    @Override
    public void eliminarInstancia(String name) throws Exceptions {
        this.mundo.nuevaEntrada("del "+name);
    }
}
