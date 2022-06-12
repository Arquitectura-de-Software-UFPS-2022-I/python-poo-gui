package ufps.arqui.python.poo.gui.views.impl;

import java.awt.*;
import java.util.Observable;
import javax.swing.*;

import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.models.Proyecto;
import ufps.arqui.python.poo.gui.utility.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelMenu;

/**
 * Panel para visualizar las opciones del proyecto.
 * <p>
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
    
    private final ModalCrearProyecto modalCrearProyecto;
    
    public PanelMenu(IMenuController controller) throws Exception {
        this.controller = controller;
        
        this.panel = new JPanel(new GridBagLayout());
        
        this.btnAbrirProyecto = new JButton("Abrir proyecto");
        this.btnNuevoProyecto = new JButton("Nuevo proyecto");
        this.btnAyuda = new JButton("Ayuda");
        
        this.modalCrearProyecto = new ModalCrearProyecto(this);
        
        this.inicializarContenido();
        this.addEvents();
    }

    @Override
    public void inicializarContenido() {
        ViewTool.insert(this.panel, this.btnAbrirProyecto,  0, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
        ViewTool.insert(this.panel, this.btnNuevoProyecto,  1, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
        ViewTool.insert(this.panel, this.btnAyuda,          2, 0, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
        
        this.panel.setVisible(true);
    }
    
    private void addEvents(){
        this.btnAbrirProyecto.addActionListener(e -> {
            
        });
        
        this.btnNuevoProyecto.addActionListener(e -> {
            this.modalCrearProyecto.setVisible(true);
        });
        
        this.btnAyuda.addActionListener(e -> {
            
        });
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

    @Override
    public void modalCrearProyectoEvento(String name, String path) {
        this.controller.crearProyecto(name, path);
    }
}
