package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import ufps.arqui.python.poo.gui.controllers.IEditorTextoController;
import ufps.arqui.python.poo.gui.models.Directorio;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelMenuClase;

/**
 * Clase Editor de texto En esta clase se podra visualizar los contenidos de
 * cada clase y a si mismo tambien se podra editar.
 *
 * @author Rafael Peña
 */
public class EditorTexto {

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

    public EditorTexto(IEditorTextoController controller) throws Exception {
        this("");
        this.controller = controller;
    }

    public EditorTexto(String nombre) throws Exception {
        this.frame = new JFrame("Editor de Texto");
        this.tabbedPane = new JTabbedPane();
        this.btnClose = new JButton("Cerrar");
        this.btnSave = new JButton("Guardar");
        this.btnNewClass = new JButton("Nueva Clase");
        this.modalCrearClase = new ModalCrearClase();
        this.init(nombre);
    }

    private void init(String nombre) {
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

        this.btnClose.addActionListener(e -> {
            this.tabbedPane.remove(this.tabbedPane.getSelectedIndex());
        });

        this.btnSave.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                try {
                    File f = buscar(nombre);
                    String ruta = f.getAbsolutePath();
                    EditorArchivoContenido eac = new EditorArchivoContenido();
                    eac.guardar(ruta);
                } catch (Exception ex) {
                    Logger.getLogger(PanelProyecto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        this.btnNewClass.addActionListener(e -> {
            this.modalCrearClase.setVisible(true);
        });

        this.frame.setPreferredSize(new Dimension(500, 700));
        this.test(nombre);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setVisible(true);
    }

    private void test(String nombre) {
        File f = buscar(nombre);
        String ruta = f.getAbsolutePath();
        EditorArchivoContenido eac = new EditorArchivoContenido(ruta, informacion(ruta));
        this.tabbedPane.add(eac.getTitle(), eac.getPanel());
//        String ruta = new File(".").getAbsolutePath();
//        ruta = ruta.replace('.', 's') + nombre;
//        this.tabbedPane.add("Dos ventana", new EditorArchivoContenido("C:path2", informacion("")).getPanel());
    }

    private static File buscar(String archivoABuscar) {
        String path = new File(".").getAbsolutePath();
                path = path.replace('.', 's') + "rc\\main\\resources\\";

        
        System.out.println("---------------------------"+path);
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();

                if (files.equalsIgnoreCase(archivoABuscar)) {
                    return listOfFiles[i];
                }
            }
        }
        System.out.println("Fin");
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

//    public static void main(String[] args) throws Exception {
//        new EditorTexto("src\\main\\resources\\scan.py");
//    }
//    @Override
//    public void update(Observable o, Object arg) {
//        if (arg.toString().equals("openFile")) {
//            //this.tabbedPane.add();
//        } else if (arg.toString().equals("añadiClase")) {
//
//        }
//    }
    public void modalCrearClase(String name) throws IOException {
        this.controller.crearClase(name);
    }
}
