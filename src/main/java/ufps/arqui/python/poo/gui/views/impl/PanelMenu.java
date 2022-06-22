package ufps.arqui.python.poo.gui.views.impl;

import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Proyecto;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
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
    }

    @Override
    public void inicializarContenido() {
        ConfGrid config = new ConfGrid(panel, btnAbrirProyecto);
        config.setAnchor(GridBagConstraints.LINE_START);

        ViewTool.insert(config);

        config = new ConfGrid(panel, btnNuevoProyecto);
        config.setGridx(1);
        config.setAnchor(GridBagConstraints.LINE_START);

        ViewTool.insert(config);

        config = new ConfGrid(panel, btnAyuda);
        config.setGridx(2);
        config.setWeightx(1);
        config.setAnchor(GridBagConstraints.LINE_START);

        ViewTool.insert(config);
//        ViewTool.insert(this.panel, this.btnAbrirProyecto,  0, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
//        ViewTool.insert(this.panel, this.btnNuevoProyecto,  1, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);
//        ViewTool.insert(this.panel, this.btnAyuda,          2, 0, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LINE_START, null, 0, 0);

        this.btnNuevoProyecto.addActionListener(e -> {
            this.modalCrearProyecto.setVisible(true);
        });

        this.panel.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void modalCrearProyecto(String name, String path){
        try {
            this.controller.crearProyecto(name, path);
        } catch (Exceptions ex) {
            mostrarError(ex);
        }
    }
}
