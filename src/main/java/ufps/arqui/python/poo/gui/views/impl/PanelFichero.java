package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JPanel;
import ufps.arqui.python.poo.gui.utility.ViewTool;
import ufps.arqui.python.poo.gui.utils.impl.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelFichero;

/**
 *
 * @author Sachikia
 */
public class PanelFichero implements IPanelFichero{
    
    private final JPanel panel;
    
    // elementos de GUI
    private JButton btnNewFile;
    
    public PanelFichero() throws Exception {
        this.panel = new JPanel(new GridBagLayout());
        
        this.btnNewFile = new JButton("Nuevo archivo");
        
        this.inicializarContenido();
    }
    
    @Override
    public void inicializarContenido() {
        ConfGrid config = new ConfGrid(panel, btnNewFile);
        config.setWeighty(1);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 0, 0, 0);

        ViewTool.insert(config);
//        ViewTool.insert(this.panel, this.btnNewFile, 0, 0, 0, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.PAGE_START, new Insets(10, 0, 0, 0), 0, 0);
    }
    
    @Override
    public void update(Observable o, Object arg) {
    }
    
    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
