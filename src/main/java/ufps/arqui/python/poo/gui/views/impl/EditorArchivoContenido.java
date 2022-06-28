package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 *Clase Editor Archivo Contenido
 * Clase encargada de recibir la ruta del la clase para despues tomar su contenido y llenarlo en text area, para mostrar.
 * Tambien toma el nombre de clase desde la ruta.
 * 
 * @author Rafael Peña
 */
public class EditorArchivoContenido {

    private String title;
    private String path;
    private JTextArea txtArea;
    private NumeroLinea numero;
    private JScrollPane jsCroll;

    public EditorArchivoContenido(){
        
    }
    public EditorArchivoContenido(String path, String contenido) {
        try {

            this.path = path.replace('\\', '/');
            this.txtArea = new JTextArea();
            this.txtArea.setText(contenido);
            this.jsCroll = new JScrollPane();
            this.numero = new NumeroLinea(txtArea);
            this.jsCroll.setRowHeaderView(numero);
            this.jsCroll.setViewportView(txtArea);
            String pathArray[] = this.path.split("/");
            this.title = pathArray[pathArray.length - 1];

        } catch (Exception e) {
        }

    }
    public void guardar(String ruta){
         try
        {    
            String txt="\n"+"----";
            System.out.println(this.txtArea.getText()+".-----");
            String filePath = ruta;
          byte[] byteArr = txt.getBytes();
            Files.write(Paths.get(filePath), byteArr, StandardOpenOption.APPEND);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }

    public JScrollPane getPanel() {
        return this.jsCroll;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public JTextArea getTxtArea() {
        return txtArea;
    }

    public void setTxtArea(JTextArea txtArea) {
        this.txtArea = txtArea;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
