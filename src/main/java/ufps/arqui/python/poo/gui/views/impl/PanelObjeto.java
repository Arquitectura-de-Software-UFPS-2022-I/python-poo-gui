package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.controllers.IMundoController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.MundoInstancia;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelObjeto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Implementación interface Panel Objeto para visualizar las instancias de clases.
 *
 * @author Omar Ramón Montes
 */
public class PanelObjeto implements IPanelObjeto {

    private JPanel panel;
    private JLabel lblName;
    private JLabel lblClass;
    private final ModalDetalleInstancia modal;
    private JPopupMenu menuInstancia;
    private JMenuItem eliminarInstancia;
    private final MundoInstancia instancia;
    private final IMundoController controller;

    public PanelObjeto(MundoInstancia instancia, IMundoController controller) {
        this.panel = new JPanel(new GridBagLayout());
        this.lblName = new JLabel(instancia.getName());
        this.lblClass = new JLabel("<"+instancia.getName_class()+">");

        this.modal = new ModalDetalleInstancia(instancia);
        this.instancia = instancia;
        this.controller = controller;

        this.inicializarContenido();
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void inicializarContenido() {

        this.menuInstancia = new JPopupMenu();
        this.eliminarInstancia = new JMenuItem("Eliminar");
        this.menuInstancia.add(this.eliminarInstancia);

        this.panel.setBackground(new Color(94, 186, 125));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.lblName.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        this.lblClass.setForeground(Color.WHITE);

        ConfGrid config = new ConfGrid(panel, lblName);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);

        ViewTool.insert(config);

        config = new ConfGrid(panel, lblClass);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setGridy(1);
        config.setFill(GridBagConstraints.CENTER);
        config.setAnchor(GridBagConstraints.CENTER);

        // Evento de menú para eliminar la instancia
        this.eliminarInstancia.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    try {
                        controller.eliminarInstancia(instancia.getName());
                    } catch (Exceptions ex) {
                        mostrarError(ex);
                    }
                }
            }
        });

        // Evento para cada una de las instancias.
        this.panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    modal.setVisible(true);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    menuInstancia.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        ViewTool.insert(config);
    }
}
