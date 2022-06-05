package ufps.arqui.python.poo.gui.views;

import javax.swing.*;
import java.util.Observer;

/**
 * Interfaz de la terminal para visualizar el cuadro de texto para ingresar comandos.
 * 
 * Se podrá ingresar comandos, interacturando con los objetos del panel del 
 * mundo y visualizar su respuesta al momento de ejecutarlo.
 * 
 * @author Omar Ramón Montes
 */
public interface IPanelTerminal extends Observer {

    JPanel getPanel();
}
