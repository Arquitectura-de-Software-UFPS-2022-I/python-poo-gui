package ufps.arqui.python.poo.gui.controllers;

/**
 * Controlador de la terminal del proyecto.
 *
 * Donde el usuario puede ejecutar metodos de python.
 * @author Omar Ramón Montes
 */
public interface ITerminalController {

    /**
     * Metodo para ejecutar comando en python.     *
     * @param commando comando de python para ejecutar.
     */
    void ejecutarComando(String commando);

    /**
     * Añadir nueva salida de la termianl.
     * @param salida salida recibida por la terminal.
     * @param error
     */
    void nuevaSalida(String salida, boolean error);

}
