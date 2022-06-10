package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.views.IPanelTerminal;

import javax.swing.JPanel;
import java.util.Observable;

/**
 * Panel para visualizar el cuadro de texto para ingresar comandos.
 *
 * Se podrá ingresar comandos, interacturando con los objetos del panel del
 * mundo y visualizar su respuesta al momento de ejecutarlo.
 *
 * @author Omar Ramón Montes
 */
public class PanelTerminal implements IPanelTerminal {

    private final ITerminalController controller;

    public PanelTerminal(ITerminalController controller) {
        this.controller = controller;
    }

    @Override
    public JPanel getPanel() {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
