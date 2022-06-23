package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.models.AttrInstancia;
import ufps.arqui.python.poo.gui.models.MethodInstancia;
import ufps.arqui.python.poo.gui.models.MundoInstancia;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * Clase para visualizar la modal con el detalle de una instancia.
 *
 * @author Omar Ramón Montes
 */
public class ModalDetalleInstancia {

    private JFrame frame;
    private final MundoInstancia instancia;

    public ModalDetalleInstancia(MundoInstancia instancia) {
        this.frame = new JFrame("Detalle de Instancia");
        this.instancia = instancia;

        this.init();
    }

    private void init() {
        JPanel panelForm = new JPanel(new GridBagLayout());
        JTable tableAtributos = new JTable();
        JTable tableMetodos = new JTable();

        JScrollPane scrollPaneAttr = new JScrollPane();
        JScrollPane scrollPaneMethod = new JScrollPane();

        tableAtributos.setModel(new AttrTablaInstancia(instancia.getAttrs()));
        tableMetodos.setModel(new MethodTablaInstancia(instancia.getMethods()));

        scrollPaneAttr.setViewportView(tableAtributos);
        scrollPaneMethod.setViewportView(tableMetodos);

        JLabel lblName = new JLabel("Nombre");
        JLabel lblPath = new JLabel("Clase");
        JLabel txtName = new JLabel(this.instancia.getName());
        JLabel txtNameClass = new JLabel(this.instancia.getName_class());

        txtName.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));
        txtNameClass.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));

        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());

        ConfGrid config = new ConfGrid(panelForm, lblName);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, txtName);
        config.setGridx(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblPath);
        config.setGridy(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, txtNameClass);
        config.setGridx(1);
        config.setGridy(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);
        ViewTool.insert(config);

        config = new ConfGrid(container, panelForm);
        config.setWeightx(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 10, 10, 10);

        ViewTool.insert(config);

        config = new ConfGrid(container, scrollPaneAttr);
        config.setGridy(1);
        config.setWeightx(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 10, 10, 10);
        config.setIpady(70);

        ViewTool.insert(config);

        config = new ConfGrid(container, scrollPaneMethod);
        config.setGridy(2);
        config.setWeightx(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 10, 10, 10);
        config.setIpady(70);

        ViewTool.insert(config);

        this.frame.setPreferredSize(new Dimension(550, 350));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    /**
     * Hacer o no visible la modal.
     * @param visible verdadero si quiere visualizar o falso en caso contrario.
     */
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

}
