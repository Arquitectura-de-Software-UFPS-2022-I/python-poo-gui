package ufps.arqui.python.poo.gui.views;

import java.util.Observer;

/**
 * Interfaz de la terminal para visualizar el cuadro de texto para ingresar comandos.
 * 
 * Se podrá ingresar comandos, interacturando con los objetos del panel del 
 * mundo y visualizar su respuesta al momento de ejecutarlo.
 * 
 * @author Omar Ramón Montes
 */
public interface IPanelTerminal extends Observer, IPanelView {

    /**
     * Metodo invocado por la terminal interactiva, cuando se registra una nueva salida de la terminal.
     * @param salida salida de la terminal
     * @param error permite saber si la salida, es un error.
     */
    void nuevaSalida(String salida, boolean error);
}
