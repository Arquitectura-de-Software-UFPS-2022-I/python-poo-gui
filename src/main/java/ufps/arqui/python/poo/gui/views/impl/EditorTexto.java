package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import ufps.arqui.python.poo.gui.controllers.IEditorTextoController;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;

/**
 *
 * @author joser
 */
public class EditorTexto implements Observer{
    private IEditorTextoController controller;
    private ModalCrearClase modalCrearClase;

    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JButton btnNewClass;
    private JButton btnSave;
    private JButton btnClose;
    private JLabel label;
    private JButton btncrearClass;
    private JButton btncancelarNewClass;

    public EditorTexto(IEditorTextoController controller) {
        this("");
        this.controller = controller;
    }

    public EditorTexto(String nombre) {
        this.frame = new JFrame("Editor de Texto");
        this.tabbedPane = new JTabbedPane();
        this.btnClose = new JButton("Cerrar");
        this.btnSave = new JButton("Guardar");
        this.btnNewClass = new JButton("Nueva Clase");
        this.init(nombre);
    }

    private void init(String nombre) {
        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());
        
      
         

        JPanel p = new JPanel(new GridBagLayout());
        ViewTool.insert(p, btnNewClass, 0, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
        ViewTool.insert(p, btnSave, 1, 0, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
        ViewTool.insert(p, btnClose, 2, 0, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_END, null, 0, 0);

        ViewTool.insert(container, p, 0, 0, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, null, 0, 0);

        ViewTool.insert(container, this.tabbedPane, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, null, 0, 0);
        this.btnClose.addActionListener(e -> {
            this.tabbedPane.remove(this.tabbedPane.getSelectedIndex());
        });

        this.btnSave.addActionListener(e -> {
//             EditorArchivoContenido eac = (EditorArchivoContenido)this.tabbedPane.getSelectedComponent();      
//             this.controller.guardarArchivo(eac.getPath(), eac.getTxtArea().getText());
        });
        //NEW--------
        this.btnNewClass.addActionListener(e -> {
            this.modalCrearClase.setVisible(true);
        });
        //NEW ----------

        this.frame.setPreferredSize(new Dimension(500, 700));
        this.test(nombre);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setVisible(true);
    }

    private void test(String nombre) {
                    String ruta= new File(".").getAbsolutePath();             
                    ruta=ruta.replace('.','s')+nombre;
        EditorArchivoContenido eac = new EditorArchivoContenido(ruta, informacion(ruta));
        this.tabbedPane.add(eac.getTitle(), eac.getPanel());
        this.tabbedPane.add("Dos ventana", new EditorArchivoContenido("C:path2", informacion("")).getPanel());
    }
 //toca acomodar de acuerdo a la arquitectura
    public String informacion(String ruta) {
        String info = "";
        try {
            InputStream ins = new FileInputStream(ruta);
            Scanner obj = new Scanner(ins);
            while (obj.hasNextLine()) {
                info = info + obj.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
        }
        return info;
    }

//    public static void main(String[] args) {
//        new EditorTexto();
//    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg.toString().equals("openFile")) {
            //this.tabbedPane.add();
        } else if (arg.toString().equals("añadiClase")) {

        }
    }

    public void modalCrearClase(String name) throws IOException {
        this.controller.crearClase(name);
    }
}
