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

/**
 *
 * @author Sachikia
 */
public class ModalAbrirProyecto {
    private IPanelMenu panelMenu;

    private String lastDir;
    private String selectDir;

    private JFrame frame;
    private JTextField txtPath;
    private JLabel lblAbsolutePath;
    private JButton btnChoose;
    private JButton btnAceptar;
    private JButton btnCancel;

    public ModalAbrirProyecto(IPanelMenu panelMenu) throws Exception {
        this.panelMenu = panelMenu;
        this.frame = new JFrame("Abrir Proyecto");

        this.txtPath = new JTextField();
        this.lblAbsolutePath = new JLabel();
        this.btnChoose = new JButton("Buscar");

        this.btnAceptar = new JButton("Aceptar");
        this.btnCancel = new JButton("Cancelar");

        this.init();
        this.addEvents();
    }
    /**
     * Creacion y ubicacion de los labels
     */
    private void init() throws Exception {
        JPanel panelForm = new JPanel(new GridBagLayout());

        JLabel lblName = new JLabel("");
        JLabel lblPath = new JLabel("Localización");
        JLabel lblFullPath = new JLabel("Path");

        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());

        ConfGrid config = new ConfGrid(panelForm, lblName);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);
     
        config = new ConfGrid(panelForm, lblPath);
        config.setGridy(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
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

        config = new ConfGrid(panelForm, btnChoose);
        config.setGridx(2);
        config.setGridy(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 0);

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

        this.frame.setPreferredSize(new Dimension(500, 200));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    private void addEvents() {
 
        /**
         * llamado del metodo abrir directorio
         */
        this.btnChoose.addActionListener(e -> {
            this.askForDirectory();
        });

        /**
         * Evento del menú para cerrar la modal al darle click primario.
         */
        this.btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    cerrarModal();
                }
            }
        });
        
        this.btnAceptar.addActionListener(e -> {
            try{
                //this.panelMenu.mo
                this.panelMenu.modalAbrirProyecto("",this.txtPath.getText());
                this.cerrarModal();
            }catch(IOException err){
                JOptionPane.showMessageDialog(this.panelMenu.getPanel(), "Error al abrirel proyecto: "+ err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void cerrarModal() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
    
    /**
     * Metodo para abrir la direccion del proyecto
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
