package ufps.arqui.python.poo.gui.views.impl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Editor;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelView;

/**
 * Clase Editor de texto En esta clase se podra visualizar los contenidos de
 * cada clase y a si mismo tambien se podra editar.
 *
 * @author Rafael Peña
 */
public class EditorTexto implements IPanelView, Observer{
    
    private Map<String, EditorArchivoContenido> pestañasAbiertas = new HashMap<>();
    private IProyectoController controller;
    private ModalCrearClase modalCrearClase;

    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JTextArea textAreaErrores;
    private JButton btnNewClass;
    private JButton btnSave;
    private JButton btnClose;

    public EditorTexto(IProyectoController controller) throws Exception {
        this.controller = controller;
        
        this.frame = new JFrame("Editor de Texto");
        this.tabbedPane = new JTabbedPane();
        this.btnClose = new JButton("Cerrar");
        this.btnClose.setToolTipText("Alt + K");
        this.btnSave = new JButton("Guardar");
        this.btnSave.setToolTipText("Alt + S");
        this.btnNewClass = new JButton("Nueva Clase");
        this.btnNewClass.setToolTipText("Alt + C");
        this.textAreaErrores = new JTextArea();
        this.modalCrearClase = new ModalCrearClase(this);
        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {
        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());
        JPanel p = new JPanel(new GridBagLayout());

        ConfGrid config = new ConfGrid(p, btnNewClass);
        config.setGridx(0);
        config.setGridy(0);
        config.setWeightx(0);
        config.setWeighty(0);
        config.setGridwidth(1);
        config.setGridheight(1);
        config.setFill(GridBagConstraints.NONE);
        config.setAnchor(GridBagConstraints.LINE_START);
        config.setIpadx(0);
        config.setIpady(0);
        ViewTool.insert(config);

        config = new ConfGrid(p, btnSave);
        config.setGridx(1);
        config.setGridy(0);
        config.setWeightx(1);
        config.setWeighty(0);
        config.setGridwidth(1);
        config.setGridheight(1);
        config.setFill(GridBagConstraints.NONE);
        config.setAnchor(GridBagConstraints.LINE_START);
        config.setIpadx(0);
        config.setIpady(0);
        ViewTool.insert(config);

        config = new ConfGrid(p, btnClose);
        config.setGridx(2);
        config.setGridy(0);
        config.setWeightx(1);
        config.setWeighty(0);
        config.setGridwidth(1);
        config.setGridheight(1);
        config.setFill(GridBagConstraints.NONE);
        config.setAnchor(GridBagConstraints.LINE_END);
        config.setIpadx(0);
        config.setIpady(0);
        ViewTool.insert(config);

        config = new ConfGrid(container, p);
        config.setGridx(0);
        config.setGridy(0);
        config.setWeightx(1);
        config.setWeighty(0);
        config.setGridwidth(1);
        config.setGridheight(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setIpadx(0);
        config.setIpady(0);
        ViewTool.insert(config);

        config = new ConfGrid(container, this.tabbedPane);
        config.setGridx(0);
        config.setGridy(1);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setGridwidth(1);
        config.setGridheight(1);
        config.setFill(GridBagConstraints.BOTH);
        config.setAnchor(GridBagConstraints.CENTER);
        config.setIpadx(0);
        config.setIpady(0);
        ViewTool.insert(config);
        
        this.textAreaErrores.setEditable(false);
        config = new ConfGrid(container, this.textAreaErrores);
        config.setGridx(0);
        config.setGridy(2);
        config.setWeightx(1);
        config.setWeighty(0);
        config.setGridwidth(1);
        config.setGridheight(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.CENTER);
        config.setIpadx(0);
        config.setIpady(100);
        ViewTool.insert(config);

        this.btnSave.setMnemonic(KeyEvent.VK_S);
        this.btnSave.addActionListener(e -> {
            String key_path = this.getKeyComponent(this.tabbedPane.getSelectedComponent());
            try{
                this.controller.guardarArchivo(key_path, this.pestañasAbiertas.get(key_path).getContenido());
            }catch(Exceptions err){
                mostrarError(frame.getContentPane(), err);
            }
        });

        this.btnClose.setMnemonic(KeyEvent.VK_K);
        this.btnClose.addActionListener(e -> {
            this.cerrarPestaña();
        });

        this.btnNewClass.setMnemonic(KeyEvent.VK_C);
        this.btnNewClass.addActionListener(e -> {
            this.modalCrearClase.setVisible(true);
        });

        this.frame.setPreferredSize(new Dimension(800, 700));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Cierra la pestaña actual del editor de texto
     */
    private void cerrarPestaña(){
        Component component = this.tabbedPane.getSelectedComponent();
        String key_value = this.getKeyComponent(component);
        this.pestañasAbiertas.remove(key_value);
        this.tabbedPane.remove(component);
        this.frame.setVisible(this.tabbedPane.getComponentCount() > 0);
        
        try{
            this.controller.cerrarArchivo(key_value);
        }catch(Exceptions e){
            mostrarError(frame, e);
        }
    }
    
    private String getKeyComponent(Component component){
        for(String key: this.pestañasAbiertas.keySet()){
            if(this.pestañasAbiertas.get(key).getPanel().equals(component)){
                return key;
            }
        }
        
        return null;
    }

    public void modalCrearClase(String name) {
        String path_key = this.getKeyComponent(this.tabbedPane.getSelectedComponent());
        try{
            this.controller.crearClase(path_key, name);
        }catch(Exceptions e){
            mostrarError(this.frame.getContentPane(), e);
        }
    }

    @Override
    public JPanel getPanel() {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.toString().equals("archivoAbierto")){
            Editor editor = (Editor)o;
            EditorArchivoContenido eac = new EditorArchivoContenido(
                    editor.getUltimoArchivoAbierto().getArchivo().getAbsolutePath(), 
                    editor.getUltimoArchivoAbierto().getContenido().toString(),
                    this.tabbedPane
            );
            
            this.tabbedPane.add(editor.getUltimoArchivoAbierto().getArchivo().getName(), eac.getPanel());
            this.tabbedPane.setSelectedComponent(eac.getPanel());
            this.pestañasAbiertas.put(editor.getUltimoArchivoAbierto().getArchivo().getAbsolutePath(), eac);
            this.frame.setVisible(true);
            
        }else if(arg.toString().equals("estaAbierto")){
            Editor editor = (Editor)o;
            EditorArchivoContenido eac = this.pestañasAbiertas.get(editor.getUltimoArchivoAbierto().getArchivo().getAbsolutePath());
            this.tabbedPane.setSelectedComponent(eac.getPanel());
            this.frame.setVisible(true);
            
        }else if(arg.toString().equals("actualizacionArchivo")){
            Editor editor = (Editor)o;
            EditorArchivoContenido eac = this.pestañasAbiertas.get(editor.getUltimoArchivoAbierto().getArchivo().getAbsolutePath());
            eac.setContenido(editor.getUltimoArchivoAbierto().getContenido().toString());
        }
    }
}
