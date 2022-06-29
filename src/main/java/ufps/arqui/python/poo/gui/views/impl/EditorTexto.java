package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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
    private JButton btnNewClass;
    private JButton btnSave;
    private JButton btnClose;

    public EditorTexto(IProyectoController controller) throws Exception {
        this.controller = controller;
        
        this.frame = new JFrame("Editor de Texto");
        this.tabbedPane = new JTabbedPane();
        this.btnClose = new JButton("Cerrar");
        this.btnSave = new JButton("Guardar");
        this.btnNewClass = new JButton("Nueva Clase");
        this.modalCrearClase = new ModalCrearClase();
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
        
        this.btnSave.addActionListener(e -> {
            String key_path = this.getKeyComponent(this.tabbedPane.getSelectedComponent());
            try{
                this.controller.guardarArchivo(key_path, this.pestañasAbiertas.get(key_path).getContenido());
            }catch(Exceptions err){
                mostrarError(err);
            }
        });

        this.btnClose.addActionListener(e -> {
            this.cerrarPestaña();
        });

        this.btnNewClass.addActionListener(e -> {
            this.modalCrearClase.setVisible(true);
        });

        this.frame.setPreferredSize(new Dimension(500, 700));
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
            mostrarError(e);
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

    public void modalCrearClase(String name) throws IOException {
        this.controller.crearClase(name);
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
