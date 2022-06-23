package ufps.arqui.python.poo.gui.controllers.impl;

import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Mundo;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Controlador de la terminal del proyecto.
 *
 * Implementación del Controlador de la terminal.
 * @author Omar Ramón Montes
 */
public class TerminalController implements ITerminalController {

    private final Proyecto proyecto;
    private final Mundo mundo;

    public TerminalController(Proyecto proyecto, Mundo mundo) {
        this.proyecto = proyecto;
        this.mundo = mundo;
    }

    @Override
    public void ejecutarComando(String commando) throws Exceptions {
        this.mundo.nuevaEntrada(commando);
    }

    @Override
    public String getComando(int indice) {
        return this.mundo.getComando(indice);
    }

    @Override
    public void reiniciarTerminal() throws Exceptions {
        this.mundo.reiniciarTerminal();
    }
}
