package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ufps.arqui.python.poo.gui.PythonPooGui;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelMenu;

/**
 *Clase para mostrar ayuda al usuario
 * @author FREDDY
 */
public class ModalAyuda {
     private IPanelMenu panelMenu;

    private JFrame frame;    
    private JLabel lblAbsolutePath;
    private JButton btnAceptar;

    public ModalAyuda(IPanelMenu panelMenu) throws Exception {
        this.panelMenu = panelMenu;
        this.frame = new JFrame("Ayuda");      
        this.lblAbsolutePath = new JLabel();
        this.btnAceptar = new JButton("Manual de Usuario");
        this.init();
        this.addEvents();
    }

    /**
     * Creacion y ubicacion de los labels
     */
    private void init() throws Exception {
        JPanel panelForm = new JPanel(new GridBagLayout());

        JLabel lblInfo = new JLabel("Información Basica");
        JLabel lblName = new JLabel("Nombre: BluePy");
        JLabel lblVer = new JLabel("Versión: 1.0.0");
        JLabel lblAut = new JLabel("Autores: Grupo De Arquitectura");
        JLabel lblUlt = new JLabel("Ultima Modificación: --/--/----");
        
        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());

        ConfGrid config = new ConfGrid(panelForm, lblInfo);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblName);
        config.setGridy(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblVer);
        config.setGridy(2);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);
        //
        config = new ConfGrid(panelForm, lblAut);
        config.setGridy(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);
        //
        config = new ConfGrid(panelForm, lblUlt);
        config.setGridy(4);
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

        this.frame.setPreferredSize(new Dimension(300, 230));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    /**
     * Action Listener para que el boton llame el metodo de mostrar el manual de usuario
     */
    private void addEvents() {
        this.btnAceptar.addActionListener(e -> {
            try{
                mostrarPDF();
            }catch(Exception err){
                JOptionPane.showMessageDialog(null, "Error al abrir el PDF: "+ err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void cerrarModal() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }

    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
    
/**
 * Metodo para mostrar el PDf manual de usuario
 */  
    public void mostrarPDF(){
        try {
                 File path = new File(PythonPooGui.class.getClassLoader().getResource("Manual_test.pdf").getFile()); 
                 Desktop.getDesktop().open(path);
           }catch (IOException ex) {
                ex.printStackTrace();
        }
    }
}
