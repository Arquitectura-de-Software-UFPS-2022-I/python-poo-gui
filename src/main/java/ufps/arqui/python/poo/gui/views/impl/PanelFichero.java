package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.utility.ViewTool;
import ufps.arqui.python.poo.gui.utils.impl.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelFichero;

/**
 *
 * @author Sachikia
 */
public class PanelFichero implements IPanelFichero{
    private IProyectoController controller;
    private final JPanel panel;
    
    // elementos de GUI
    private JButton btnNewFile;
    private JButton btnCheckout;
    
    public PanelFichero(IProyectoController controller) throws Exception {
        this.controller = controller;
        this.panel = new JPanel(new GridBagLayout());
        
        this.btnNewFile = new JButton("Nuevo archivo");
        this.btnCheckout = new JButton("Checkout");
        
        this.inicializarContenido();
    }
    
    @Override
    public void inicializarContenido() {
        this.btnCheckout.addActionListener(e -> {
            try{
                this.controller.scanearProyecto();
            }catch(IOException err){
                JOptionPane.showMessageDialog(null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        ConfGrid config = new ConfGrid(panel, btnNewFile);
        config.setWeighty(0);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 0, 0, 0);

        ViewTool.insert(config);
        ViewTool.insert(this.panel, this.btnCheckout, 0, 1, 0, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, new Insets(10, 0, 0, 0), 0, 0);
    }
    
    @Override
    public void update(Observable o, Object arg) {
    }
    
    @Override
    public JPanel getPanel() {
        return this.panel;
    }
}
