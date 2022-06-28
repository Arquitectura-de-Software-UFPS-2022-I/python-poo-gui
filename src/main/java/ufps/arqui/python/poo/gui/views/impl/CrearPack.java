package ufps.arqui.python.poo.gui.views.impl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelFichero;

public class CrearPack {
    private PanelFichero panelFichero;
    
    private String lastDir;
    private String selectDir;
    
    private JFrame frame;
    private JTextField txtName;
    private JTextField txtPath;
    private JLabel lblAbsolutePath;
    private JButton btnGuardar;
    private JButton btnCancelar;

    public CrearPack(PanelFichero panelFichero) throws Exception {
        this.panelFichero = panelFichero;
        this.frame = new JFrame("Crear Paquete");

        this.txtName = new JTextField();
        this.txtPath = new JTextField();
        this.lblAbsolutePath = new JLabel();

        this.btnGuardar = new JButton("Guardar");
        this.btnCancelar = new JButton("Cancelar");

        this.init();
        this.addEvents();
    }
    
    private void init() throws Exception {
        JPanel panelForm = new JPanel(new GridBagLayout());

        JLabel lblName = new JLabel("Nombre del Paquete");
        JLabel lblFullPath = new JLabel("Path");

        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());

        ConfGrid config = new ConfGrid(panelForm, lblName);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, this.txtName);
        config.setGridx(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblFullPath);
        config.setGridy(2);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblAbsolutePath);
        config.setGridx(1);
        config.setGridy(2);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);
//        ViewTool.insert(panelForm, lblName, 0, 0, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
//        ViewTool.insert(panelForm, this.txtName, 1, 0, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 10);
//        ViewTool.insert(panelForm, lblPath, 0, 1, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
//        ViewTool.insert(panelForm, this.txtPath, 1, 1, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 10);
//        ViewTool.insert(panelForm, this.btnChoose, 2, 1, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 0), 0, 0);
//        ViewTool.insert(panelForm, lblFullPath, 0, 2, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
//        ViewTool.insert(panelForm, this.lblAbsolutePath, 1, 2, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);

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
//        ViewTool.insert(panelOptions, this.btnAceptar, 0, 0, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LAST_LINE_END, new Insets(0, 0, 0, 10), 0, 0);
//        ViewTool.insert(panelOptions, this.btnCancel, 1, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LAST_LINE_END, null, 0, 0);
//        ViewTool.insert(container, panelForm, 0, 0, 1, 0, 3, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, new Insets(10, 10, 10, 10), 0, 0);
//        ViewTool.insert(container, panelOptions, 0, 1, 1, 1, 3, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_END, new Insets(0, 0, 10, 10), 0, 0);

        this.frame.setPreferredSize(new Dimension(500, 200));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    private void addEvents() {
        DocumentListener docEvent = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                eventChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                eventChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                eventChange();
            }

            public void eventChange() {
                lblAbsolutePath.setText(txtPath.getText() +"/"+ txtName.getText());
            }
        };
 

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
            this.panelFichero.crearPack(this.txtName.getText(), this.txtPath.getText());
            this.cerrarModal();
        });
    }

    private void cerrarModal() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
    

    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
}
