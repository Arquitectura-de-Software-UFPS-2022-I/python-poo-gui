package ufps.arqui.python.poo.gui.controllers;

import ufps.arqui.python.poo.gui.exceptions.Exceptions;

/**
 * Controlador de la terminal del proyecto.
 *
 * Donde el usuario puede ejecutar metodos de python.
 * @author Omar Ramón Montes
 */
public interface ITerminalController {

    /**
     * Metodo para ejecutar comando en python.
     * @param commando comando de python para ejecutar.
     */
    void ejecutarComando(String commando) throws Exceptions;

    /**
     * Obtener comando según la posición.
     * @param indice indice de la lista.
     */
    String getComando(int indice);

    /**
     * Metodo para reiniciar la terminal
     * @throws java.io.IOException En caso de que la terminal no pudo ser inicializada.
     */
    void reiniciarTerminal() throws Exceptions;
}
