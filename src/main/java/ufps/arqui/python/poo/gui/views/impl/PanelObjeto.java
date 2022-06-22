package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.models.MundoInstancia;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelObjeto;

import javax.swing.*;
import java.awt.*;

/**
 * Implementación interface Panel Objeto para visualizar las instancias de clases.
 */
public class PanelObjeto implements IPanelObjeto {

    private JPanel panel;
    private JLabel lblName;
    private JLabel lblClass;

    public PanelObjeto(MundoInstancia instancia) {
        this.panel = new JPanel(new GridBagLayout());
        this.lblName = new JLabel(instancia.getName());
        this.lblClass = new JLabel("<"+instancia.getName_class()+">");

        this.inicializarContenido();
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void inicializarContenido() {
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

        ViewTool.insert(config);
    }
}
