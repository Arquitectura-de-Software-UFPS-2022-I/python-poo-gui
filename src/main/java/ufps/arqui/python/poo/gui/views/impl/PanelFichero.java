package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Proyecto;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelFichero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Observable;

/**
 * Implementación de Interfaz lateral del proyecto, donde el usuario puede
 * gestionar sus archivos.
 *
 * @author Sachikia
 */
public class PanelFichero implements IPanelFichero {

    private IProyectoController controller;
    private final JPanel panel;

    // elementos de GUI
    private JButton btnNuevoArchivo;
    private JButton btnVerificar;
    private JButton btnEliminar;
    private ArbolDinamico arbol;
    private ModalCrearFichero modalCrearFichero;

    public PanelFichero(IProyectoController controller) {
        this.controller = controller;
        this.panel = new JPanel(new GridBagLayout());

        this.btnNuevoArchivo = new JButton("Nuevo archivo");
        this.btnNuevoArchivo.setToolTipText("Alt + N");
        this.btnVerificar = new JButton("Verificar");
        this.btnVerificar.setToolTipText("Alt + V");
        this.btnEliminar = new JButton("Eliminar");
        this.btnEliminar.setToolTipText("Alt + D");
        this.arbol = new ArbolDinamico(controller);
        this.modalCrearFichero = new ModalCrearFichero(this);

        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {

        this.btnNuevoArchivo.setMnemonic(KeyEvent.VK_N);
        this.btnNuevoArchivo.addActionListener(e -> {
            String path = this.arbol.getCurrentPath();
            if (!path.endsWith(".py")) {
                this.modalCrearFichero.setVisible(getFolderPath(this.arbol.getCurrentPath()));
            } else {
                mostrarError(this.panel, new Exceptions("Debe seleccionar un directorio para crear un archivo.", null));
            }
        });

        this.btnVerificar.setMnemonic(KeyEvent.VK_V);
        this.btnVerificar.addActionListener(e -> {
            try {
                this.controller.escanearProyecto();
            } catch (Exceptions ex) {
                mostrarError(this.panel, ex);
            }
        });

        this.btnEliminar.setMnemonic(KeyEvent.VK_D);
        this.btnEliminar.addActionListener(e -> {
            String path = this.arbol.getCurrentPath();
            if (!path.isEmpty()) {
                int option = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el " + esArchivo(path) + getFileName(path) + "?", "Eliminar archivo", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    try {
                        this.controller.eliminarArchivo(path);
                        JOptionPane.showMessageDialog(null, "Archivo eliminado correctamente");
                    } catch (Exceptions ex) {
                        mostrarError(this.panel, ex);
                    }
                }
            }
        });

        ConfGrid config = new ConfGrid(panel, btnNuevoArchivo);
        config.setWeighty(0);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 0, 0, 0);

        ViewTool.insert(config);

        config = new ConfGrid(panel, btnVerificar);
        config.setGridy(1);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 0, 0, 0);

        ViewTool.insert(config);

        config = new ConfGrid(panel, btnEliminar);
        config.setGridy(2);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 0, 0, 0);

        ViewTool.insert(config);

        config = new ConfGrid(panel, this.arbol.getPanel());
        config.setGridy(3);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setFill(GridBagConstraints.BOTH);
        config.setAnchor(GridBagConstraints.PAGE_END);
        config.setInsets(10, 0, 0, 0);

        ViewTool.insert(config);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String update = arg.toString();
            if (update.equals("directoriosTrabajo")) {
                Proyecto proyecto = (Proyecto) o;
                this.arbol.populate(proyecto.getDirectorioTrabajo());
            } else if (update.equals("archivoBorrado")) {
                this.arbol.removeCurrentNode();
            }
        }
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void crearFichero(String nombre, String directorioSeleccionado) {
        try {
            this.controller.crearArchivo(directorioSeleccionado, nombre);
        } catch (Exceptions e) {
            mostrarError(panel, e);
        }
    }

    /**
     * Verifica si el nombre del archivo corresponde a un fichero python.
     * @param path nombre del archivo
     * @return True si es un fichero con extensión .py
     */
    public String esArchivo(String path) {
        if (path.endsWith(".py")) {
            return "archivo ";
        } else {
            return "directorio ";
        }
    }

    private String getFileName(String path) {
        String[] parts = path.split("\\\\");
        return parts[parts.length - 1];
    }

    private String getFolderPath(String path) {
        return "src"+ File.separator +path.split("\\\\")[0];
    }

}
