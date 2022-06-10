package ufps.arqui.python.poo.gui.views.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.models.Proyecto;
import ufps.arqui.python.poo.gui.utility.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelMenu;

/**
 * Panel para visualizar las opciones del proyecto.
 * 
 * En el menú se podrán realizar las diferentes opciones del sistema,
 * como por ejemplo guardar el proyecto, crear uno nuevo, ver el manual, etc.
 * 
 * @author Omar Ramón Montes
 */
public class PanelMenu implements IPanelMenu {
    
    private final IMenuController controller;    
    
    private final JPanel panel;
    
    // elementos de GUI
    private final JButton btnAbrirProyecto;
    private final JButton btnNuevoProyecto;
    private final JButton btnAyuda;
    
    public PanelMenu(IMenuController controller) throws Exception {
        this.controller = controller;
        
        this.panel = new JPanel(new GridBagLayout());
        
        this.btnAbrirProyecto = new JButton("Abrir proyecto");
        this.btnNuevoProyecto = new JButton("Nuevo proyecto");
        this.btnAyuda = new JButton("Ayuda");
        
        this.init();
    }

    private void init() throws Exception {
        this.btnAbrirProyecto.addActionListener(e -> {
            
        });
        
        this.btnNuevoProyecto.addActionListener(e -> {
            
        });
        
        this.btnAyuda.addActionListener(e -> {
            
        });
        
        ViewTool.insert(this.panel, this.btnAbrirProyecto,  0, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
        ViewTool.insert(this.panel, this.btnNuevoProyecto,  1, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
        ViewTool.insert(this.panel, this.btnAyuda,          2, 0, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
        
        this.panel.setVisible(true);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        // Validar que el objeto observable sea Proyecto.
        if (o instanceof Proyecto) {
            // Actualizar contenido del label, cuando el modelo cambie.
        }
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
    
}
