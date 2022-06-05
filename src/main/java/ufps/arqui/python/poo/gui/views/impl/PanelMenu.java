/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.arqui.python.poo.gui.views.impl;

import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.models.Proyecto;
import ufps.arqui.python.poo.gui.views.IPanelMenu;

/**
 *
 * @author USUARIO
 */
public class PanelMenu implements IPanelMenu {
    
    private final IMenuController controller;    
    // elementos de GUI
    private final JButton btnAbrirProyecto;
    private final JPanel panel;
    private final JLabel lblContenido;
    
    public PanelMenu(IMenuController controller) {
        this.controller = controller;
        
        this.panel = new JPanel();
        this.btnAbrirProyecto = new JButton("Abrir");
        this.lblContenido = new JLabel("Hola mundo");
        
        this.panel.add(this.btnAbrirProyecto);
        this.panel.add(this.lblContenido);
        
        this.init();        
    }

    private void init() {
        this.btnAbrirProyecto.addActionListener(e -> {     
            // TODO: ejecutar código para obtener el nombre y directorio
            
            // Pasar el nombre y directorio al controlador
            controller.abrirProyecto("?", "?");
        });
        this.panel.setVisible(true);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Proyecto) {
            this.lblContenido.setText(((Proyecto) o).toString());
        }
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }
    
}
