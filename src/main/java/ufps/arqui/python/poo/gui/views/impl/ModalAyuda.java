package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.PythonPooGui;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase para mostrar ayuda al usuario
 *
 * @author FREDDY
 */
public class ModalAyuda {
    private IPanelMenu panelMenu;

    private JFrame frame;
    private JLabel lblAbsolutePath;
    private JButton btnAceptar;

    public ModalAyuda(IPanelMenu panelMenu) {
        this.panelMenu = panelMenu;
        this.frame = new JFrame("Ayuda");
        this.lblAbsolutePath = new JLabel();
        this.btnAceptar = new JButton("Manual de Usuario");

        this.inicializar();
        this.agregarEventos();
    }

    /**
     * Creacion y ubicacion de los labels
     */
    private void inicializar() {
        JPanel panelForm = new JPanel(new GridBagLayout());

        JLabel lblPInfo = new JLabel("Información Proyecto");
        lblPInfo.setFont(new Font("Verdana", Font.BOLD, 16));
        JLabel lblPNom = new JLabel("Nombre: "+panelMenu.gerNombreProyecto());
        JLabel lblPPyt = new JLabel("Python: "+panelMenu.getPythonProyecto());
        JLabel lblPDir = new JLabel("Directorio: "+panelMenu.getDireccionProyecto());

        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());

        ConfGrid config = new ConfGrid(panelForm, lblPInfo);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblPNom);
        config.setGridy(2);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblPPyt);
        config.setGridy(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblPDir);
        config.setGridy(4);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        JLabel lblInfo = new JLabel("Información BluePy");
        lblInfo.setFont(new Font("Verdana", Font.BOLD, 16));
        JLabel lblVer = new JLabel("Versión: 1.0.0");
        JLabel lblAut = new JLabel("Autores: Grupo De Arquitectura");
        JLabel lblUlt = new JLabel("Ultima Modificación: 30/06/2022");

        config = new ConfGrid(panelForm, lblInfo);
        config.setGridy(5);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblVer);
        config.setGridy(6);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblAut);
        config.setGridy(7);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);

        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblUlt);
        config.setGridy(8);
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

        this.frame.setPreferredSize(new Dimension(400, 280));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    /**
     * Action Listener para que el boton llame el metodo de mostrar el manual de usuario
     */
    private void agregarEventos() {
        this.btnAceptar.addActionListener(e -> {
            try {
                mostrarPDF();
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Error al abrir el PDF: " + err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Metodo para mostrar el PDf manual de usuario
     */
    public void mostrarPDF() {
        try {
            File path = new File(PythonPooGui.class.getClassLoader().getResource("Manual de Usuario Blue Py.pdf").getFile());
            Desktop.getDesktop().open(path);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
