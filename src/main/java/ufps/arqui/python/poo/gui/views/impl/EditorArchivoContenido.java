package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *Clase Editor Archivo Contenido
 * Clase encargada de recibir la ruta del la clase para despues tomar su contenido y llenarlo en text area, para mostrar.
 * Tambien toma el nombre de clase desde la ruta.
 * 
 * @author Rafael Peña
 */
public class EditorArchivoContenido {
    private JTabbedPane tabbedPane;
    private String cotenidoInicial;
    private String titulo;
    private String ruta;
    private JTextArea txtArea;
    private NumeroLinea numero;
    private JScrollPane jsCroll;
    
    public EditorArchivoContenido(String path, String contenido, JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        this.cotenidoInicial = contenido;
        
        this.ruta = path.replace('\\', '/');
        
        this.txtArea = new JTextArea();
        this.txtArea.setTabSize(2);
        this.txtArea.setText(contenido);
        
        this.jsCroll = new JScrollPane();
        this.numero = new NumeroLinea(txtArea);
        
        this.jsCroll.setRowHeaderView(numero);
        this.jsCroll.setViewportView(txtArea);
        
        String pathArray[] = this.ruta.split("/");
        this.titulo = pathArray[pathArray.length - 1];
        
        this.addEvent();
    }

    private void addEvent(){
        this.txtArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                this.validateContent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                this.validateContent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                this.validateContent();
            }
            
            private void validateContent(){
                if(!cotenidoInicial.equals(txtArea.getText())){
                    tabbedPane.setBackgroundAt(tabbedPane.getSelectedIndex(), Color.GRAY);
                }else{
                    tabbedPane.setBackgroundAt(tabbedPane.getSelectedIndex(), new Color(242, 242, 242));
                }
            }
        });
    }
    
    public JScrollPane getPanel() {
        return this.jsCroll;
    }

    public String getPath() {
        return ruta;
    }

    public void setPath(String path) {
        this.ruta = path;
    }

    public JTextArea getTxtArea() {
        return txtArea;
    }

    public void setTxtArea(JTextArea txtArea) {
        this.txtArea = txtArea;
    }

    public String getTitle() {
        return titulo;
    }

    public void setTitle(String title) {
        this.titulo = title;
    }
    
    public void setContenido(String contenido){
        this.txtArea.setText(contenido);
        this.cotenidoInicial = contenido;
        tabbedPane.setBackgroundAt(tabbedPane.getSelectedIndex(), new Color(242, 242, 242));
    }
    
    public String getContenido(){
        return this.txtArea.getText();
    }
}
