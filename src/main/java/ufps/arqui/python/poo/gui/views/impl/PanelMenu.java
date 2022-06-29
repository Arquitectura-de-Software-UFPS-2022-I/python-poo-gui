package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;

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
    private final ModalAbrirProyecto modalAbrirProyecto;
    private ModalAyuda modalAyuda;

    public PanelMenu(IMenuController controller) {
        this.controller = controller;

        this.panel = new JPanel(new GridBagLayout());

        this.btnAbrirProyecto = new JButton("Abrir proyecto");
        this.btnAbrirProyecto.setToolTipText("Alt + O");
        this.btnNuevoProyecto = new JButton("Nuevo proyecto");
        this.btnNuevoProyecto.setToolTipText("Alt + P");
        this.btnAyuda = new JButton("Acerca de");
        this.btnAyuda.setToolTipText("Alt + A");

        this.modalCrearProyecto = new ModalCrearProyecto(this);
        this.modalAbrirProyecto = new ModalAbrirProyecto(this);

        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {
        ConfGrid config = new ConfGrid(panel, btnAbrirProyecto);
        config.setAnchor(GridBagConstraints.LINE_START);
        config.setInsets(10, 10, 10, 10);

        ViewTool.insert(config);

        config = new ConfGrid(panel, btnNuevoProyecto);
        config.setGridx(1);
        config.setAnchor(GridBagConstraints.LINE_START);
        config.setInsets(10, 10, 10, 10);

        ViewTool.insert(config);

        config = new ConfGrid(panel, btnAyuda);
        config.setGridx(2);
        config.setWeightx(1);
        config.setAnchor(GridBagConstraints.LINE_START);
        config.setInsets(10, 10, 10, 10);

        ViewTool.insert(config);

        // Abrir modal para crear un nuevo proyecto.
        this.btnNuevoProyecto.setMnemonic(KeyEvent.VK_P);
        this.btnNuevoProyecto.addActionListener(e -> {
            this.modalCrearProyecto.setVisible(true);
        });

        // Abrir modal para abrir un proyecto existente.
        this.btnAbrirProyecto.setMnemonic(KeyEvent.VK_O);
        this.btnAbrirProyecto.addActionListener(e -> {
            this.modalAbrirProyecto.setVisible(true);
        });

        // Abrir modal para visualizar ayuda.
        this.btnAyuda.setMnemonic(KeyEvent.VK_A);
        this.btnAyuda.addActionListener(e -> {
            this.modalAyuda = new ModalAyuda(this);
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
    public void modalCrearProyecto(String name, String path, String comandoPython) {
        try {
            this.controller.crearProyecto(name, path, comandoPython);
            mostrarMensaje("Proyecto creado", "El proyecto ha sido creado exitosamente.", this.panel);
        } catch (Exceptions ex) {
            mostrarError(this.panel, ex);
        }
    }

    @Override
    public void modalAbrirProyecto(String path) {
        try {
            this.controller.abrirProyecto(path);
            mostrarMensaje("Proyecto abierto", "El proyecto ha sido abierto exitosamente.", this.panel);
        } catch (Exceptions ex) {
            mostrarError(this.panel, ex);
        }
    }

    @Override
    public String gerNombreProyecto() {
        return this.controller.gerNombreProyecto();
    }

    @Override
    public String getPythonProyecto() {
        return this.controller.getPythonProyecto();
    }

    @Override
    public String getDireccionProyecto() {
        return this.controller.getDireccionProyecto();
    }
}
