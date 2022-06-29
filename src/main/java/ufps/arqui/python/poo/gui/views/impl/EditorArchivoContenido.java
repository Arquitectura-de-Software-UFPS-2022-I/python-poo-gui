package ufps.arqui.python.poo.gui.views.impl;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
            e.printStackTrace();
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
