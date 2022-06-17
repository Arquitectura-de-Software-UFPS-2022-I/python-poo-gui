package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import ufps.arqui.python.poo.gui.controllers.IEditorTextoController;
import ufps.arqui.python.poo.gui.utils.ViewTool;

/**
 *
 * @author joser
 */
public class EditorTexto implements Observer{
    private IEditorTextoController controller;

    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JButton btnNewClass;
    private JButton btnSave;
    private JButton btnClose;

    public EditorTexto(IEditorTextoController controller) {
        this();
        this.controller = controller;
    }
    
    public EditorTexto() {
        this.frame = new JFrame("Editor de Texto");
        
        this.tabbedPane = new JTabbedPane();
        this.btnClose = new JButton("Cerrar");
        this.btnNewClass = new JButton("Nueva Clase");
        this.btnSave = new JButton("Guardar");
        
        this.init();
    }

    private void init() {
        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());
        
         JPanel p = new JPanel(new GridBagLayout());
         ViewTool.insert(p,btnNewClass, 0,0,0,0,1,1,GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
         ViewTool.insert(p,btnSave,     1,0,1,0,1,1,GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
         ViewTool.insert(p,btnClose,    2,0,1,0,1,1,GridBagConstraints.NONE, GridBagConstraints.LINE_END, null, 0, 0);
         
         
         ViewTool.insert(container, p, 0, 0, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, null, 0, 0);
        
         ViewTool.insert(container, this.tabbedPane, 0, 1, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, null, 0, 0);
         
         this.btnClose.addActionListener(e -> {
             this.tabbedPane.remove(this.tabbedPane.getSelectedIndex());
         });
         
         this.btnSave.addActionListener(e -> {
             EditorArchivoContenido eac = (EditorArchivoContenido)this.tabbedPane.getSelectedComponent();
             
             this.controller.guardarArchivo(eac.getPath(), eac.getTxtArea().getText());
         });
         
        this.frame.setPreferredSize(new Dimension(500, 700));
        this.test();
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
    
    private void test(){
        EditorArchivoContenido eac = new EditorArchivoContenido("C:path1/to/project/file.py", "rafael es gay");
        this.tabbedPane.add(eac.getTitle(), eac);
        this.tabbedPane.add("Dos ventana", new EditorArchivoContenido("C:path2", "el parlon"));
    }
    
    public static void main(String[] args) {
        new EditorTexto();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.toString().equals("openFile")){
            
        } else if(arg.toString().equals("añadiClase")){
            
        }
    }
}
