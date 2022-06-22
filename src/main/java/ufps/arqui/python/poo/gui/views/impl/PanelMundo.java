package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.controllers.IMundoController;
import ufps.arqui.python.poo.gui.views.IPanelMundo;

import javax.swing.*;
import java.util.Observable;

/**
 * Panel para visualizar el mundo el proyecto, es decir todas las intancias.
 *
 * En el panel mundo se visualizarán las instancias de las clases creadas,
 * con sus respectivos nombres y datos adicionales
 * @author Omar Ramón Montes
 */
public class PanelMundo implements IPanelMundo {

    private final IMundoController controller;
    private final JPanel panel;
    private JButton btnAbrir;

    public PanelMundo(IMundoController controller) {
        this.controller = controller;
        this.panel = new JPanel();

        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {
        this.btnAbrir = new JButton("Mundo");
        this.panel.add(btnAbrir);
        this.panel.setVisible(true);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
