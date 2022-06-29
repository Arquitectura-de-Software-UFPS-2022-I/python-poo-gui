package ufps.arqui.python.poo.gui.views.impl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;

import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelFichero;

/**
 * Modal para crear fichero.
 *
 * @author Omar Ramón Montes
 */
public class ModalCrearFichero {
    private IPanelFichero panelFichero;
    private String relativeUrl;

    private JFrame frame;
    private JTextField txtName;
    private JLabel lblUrl;

    private JButton btnGuardar;
    private JButton btnCancelar;

    public ModalCrearFichero(IPanelFichero panelFichero) {
        this.panelFichero = panelFichero;
        this.frame = new JFrame("Crear Fichero");

        this.txtName = new JTextField();
        this.txtName.setToolTipText("Ingresar la extensión .py para crear un archivo python");
        this.lblUrl = new JLabel();

        this.btnGuardar = new JButton("Guardar");
        this.btnCancelar = new JButton("Cerrar");

        this.inicializar();
        this.agregarEventos();
    }

    private void inicializar() {
        JPanel panelForm = new JPanel(new GridBagLayout());

        JLabel lblName = new JLabel("Nombre");
        JLabel lblRuta = new JLabel("Ruta");

        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());

        ConfGrid config = new ConfGrid(panelForm, lblRuta);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.LINE_END);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblUrl);
        config.setGridx(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblName);
        config.setGridy(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.LINE_END);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, this.txtName);
        config.setGridy(1);
        config.setGridx(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);

        ViewTool.insert(config);

        JPanel panelOptions = new JPanel(new GridBagLayout());

        config = new ConfGrid(panelOptions, btnGuardar);
        config.setWeightx(1);
        config.setAnchor(GridBagConstraints.LAST_LINE_END);
        config.setInsets(0, 0, 0, 10);

        ViewTool.insert(config);

        config = new ConfGrid(panelOptions, btnCancelar);
        config.setGridx(1);
        config.setAnchor(GridBagConstraints.LAST_LINE_END);

        ViewTool.insert(config);

        config = new ConfGrid(container, panelForm);
        config.setWeightx(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 10, 10, 10);

        ViewTool.insert(config);

        config = new ConfGrid(container, panelOptions);
        config.setGridy(1);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(0, 0, 10, 10);

        ViewTool.insert(config);

        this.frame.setPreferredSize(new Dimension(500, 170));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    private void agregarEventos() {
        this.btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    cerrarModal();
                }
            }
        });

        this.btnGuardar.addActionListener(e -> {
            this.panelFichero.crearFichero(this.txtName.getText(), this.relativeUrl);
            this.cerrarModal();
        });
    }

    private void cerrarModal() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }

    public void setVisible(String path) {
        this.relativeUrl = path;
        this.lblUrl.setText(this.relativeUrl);
        this.frame.setVisible(true);
    }
}
