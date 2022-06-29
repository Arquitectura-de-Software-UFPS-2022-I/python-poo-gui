package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelMenu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;


import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Mundo;
import ufps.arqui.python.poo.gui.models.Mundo.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelMenu;


/**
 *
 * @author Sachikia
 */
public class ModalCrearProyecto {

    private IPanelMenu panelMenu;
    
    private String lastDir;
    private String selectDir;
    
    private JFrame frame;
    private JTextField txtName;
    private JTextField txtPath;
    private JTextField txtPythonVersions ;

    private JButton btnChoose;
    private JButton btnAceptar;
    private JButton btnCancel;

    public ModalCrearProyecto(IPanelMenu panelMenu) {
        this.panelMenu = panelMenu;
        this.frame = new JFrame("Nuevo Proyecto");

        this.txtName = new JTextField();
        this.txtPath = new JTextField();
        this.txtPythonVersions = new JTextField("python");

        this.btnChoose = new JButton("Buscar");
        this.btnAceptar = new JButton("Aceptar");
        this.btnCancel = new JButton("Cerrar");

        this.inicializar();
        this.agregarEventos();
    }

    private void inicializar() {
        JPanel panelForm = new JPanel(new GridBagLayout());

        JLabel lblName = new JLabel("Nombre");

        JLabel lblPath = new JLabel("Localización");
        JLabel lblPythonVersion = new JLabel("Python");

        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());

        ConfGrid config = new ConfGrid(panelForm, lblName);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.LINE_END);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, this.txtName);
        config.setGridx(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblPath);
        config.setGridy(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.LINE_END);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, txtPath);
        config.setGridx(1);
        config.setGridy(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblPythonVersion);
        config.setGridy(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.LINE_END);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, txtPythonVersions);
        config.setGridx(1);
        config.setGridy(3);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, btnChoose);
        config.setGridx(2);
        config.setGridy(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 0);

        ViewTool.insert(config);

        JPanel panelOptions = new JPanel(new GridBagLayout());

        config = new ConfGrid(panelOptions, btnAceptar);
        config.setWeightx(1);
        config.setAnchor(GridBagConstraints.LAST_LINE_END);
        config.setInsets(0, 0, 0, 10);

        ViewTool.insert(config);

        config = new ConfGrid(panelOptions, btnCancel);
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

        this.frame.setPreferredSize(new Dimension(500, 225));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    private void agregarEventos() {

        // Obtener directorio del proyecto.
        this.btnChoose.addActionListener(e -> {
            this.askForDirectory();
        });

        // Evento del menú para cerrar la modal al darle click primario.
        this.btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    cerrarModal();
                }
            }
        });

        // Aceptar los datos para crear el proyecto.
        this.btnAceptar.addActionListener(e -> {
            this.panelMenu.modalCrearProyecto(this.txtName.getText(), this.txtPath.getText(), this.txtPythonVersions.getText());
        });
    }

    /**
     * Cerrar modal.
     */
    private void cerrarModal() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }

    /**
     * Obtener directorio de trabajo.
     */
    private void askForDirectory(){
        JFileChooser chooser = new JFileChooser();
        if(this.lastDir != null) chooser.setCurrentDirectory(new File(this.lastDir));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        
        this.selectDir = chooser.getSelectedFile() != null ? chooser.getSelectedFile().getAbsolutePath() : this.selectDir;
        this.lastDir = chooser.getCurrentDirectory().getAbsolutePath();
        this.txtPath.setText(this.selectDir);
    }

    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
}
