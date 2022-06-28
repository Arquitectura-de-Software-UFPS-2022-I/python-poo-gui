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
 *
 * @author FREDDY
 */
public class ModalAyuda {
     private IPanelMenu panelMenu;

    private String lastDir;
    private String selectDir;

    private JFrame frame;
    //private JTextField txtName;
    
    private JLabel lblAbsolutePath;
    private JButton btnAceptar;

    public ModalAyuda(IPanelMenu panelMenu) throws Exception {
        this.panelMenu = panelMenu;
        this.frame = new JFrame("Ayuda");

        //this.txtName = new JTextField();
        
        this.lblAbsolutePath = new JLabel();


        this.btnAceptar = new JButton("Manual de Usuario");


        this.init();
        this.addEvents();
    }

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
//        ViewTool.insert(panelForm, lblName, 0, 0, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
//        ViewTool.insert(panelForm, this.txtName, 1, 0, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 10);
//        ViewTool.insert(panelForm, lblPath, 0, 1, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
//        ViewTool.insert(panelForm, this.txtPath, 1, 1, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 10);
//        ViewTool.insert(panelForm, this.btnChoose, 2, 1, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 0), 0, 0);
//        ViewTool.insert(panelForm, lblFullPath, 0, 2, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
//        ViewTool.insert(panelForm, this.lblAbsolutePath, 1, 2, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);

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
//        ViewTool.insert(panelOptions, this.btnAceptar, 0, 0, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LAST_LINE_END, new Insets(0, 0, 0, 10), 0, 0);
//        ViewTool.insert(panelOptions, this.btnCancel, 1, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LAST_LINE_END, null, 0, 0);
//        ViewTool.insert(container, panelForm, 0, 0, 1, 0, 3, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, new Insets(10, 10, 10, 10), 0, 0);
//        ViewTool.insert(container, panelOptions, 0, 1, 1, 1, 3, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_END, new Insets(0, 0, 10, 10), 0, 0);

        this.frame.setPreferredSize(new Dimension(300, 230));
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
               // lblAbsolutePath.setText(txtPath.getText() +"/"+ txtName.getText());
            }
        };

        //this.txtName.getDocument().addDocumentListener(docEvent);
        //this.txtPath.getDocument().addDocumentListener(docEvent);



        // Evento del menú para cerrar la modal al darle click primario.
        
        
        this.btnAceptar.addActionListener(e -> {
            try{
                //this.panelMenu.mo
                mostrarPDF();
            }catch(Exception err){
                //JOptionPane.showMessageDialog(null, "Error al abrirel proyecto: "+ err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        //this.btnAyuda.addActionListener(e -> {
        //            this.modalAyuda.setVisible(true);
        //        });
        //    try {
        //         File path = new File ("carpeta/tuArchivo.pdf");
        //         Desktop.getDesktop().open(path);
        //    }catch (IOException ex) {
        //         ex.printStackTrace();
        //}
    }

    private void cerrarModal() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
    
   

    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
    public void mostrarPDF(){
        try {
                 File path = new File(PythonPooGui.class.getClassLoader().getResource("Manual_test.pdf").getFile()); 
                 Desktop.getDesktop().open(path);
           }catch (IOException ex) {
                ex.printStackTrace();
        }
    }
}
