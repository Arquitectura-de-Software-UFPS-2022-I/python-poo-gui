package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import ufps.arqui.python.poo.gui.utils.ViewTool;

/**
 *
 * @author joser
 */
public class EditorArchivoContenido extends JPanel{
    
    private String title;
    private String path;
    private JTextArea txtArea;

    public EditorArchivoContenido(String path, String contenido) {
        super.setLayout(new GridBagLayout());
        
        this.path = path;
        this.txtArea = new JTextArea(contenido);
        String pathArray[] = this.path.split("/");
        this.title = pathArray[pathArray.length-1];
        
        this.init();
    }

    private void init() {
        ViewTool.insert(this, this.txtArea, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH, GridBagConstraints.CENTER, null, 0, 0);
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
