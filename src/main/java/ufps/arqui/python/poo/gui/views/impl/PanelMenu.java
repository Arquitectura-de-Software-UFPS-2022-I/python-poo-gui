package ufps.arqui.python.poo.gui.views.impl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import java.util.Observable;
import javax.swing.*;

import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.models.Proyecto;
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
    private JButton btnAbrir;
    private JTextField txtUrl;
    private JLabel lblContenido;

    public PanelMenu(IMenuController controller) {
        this.controller = controller;

        this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {

        this.btnAbrir = new JButton("Cargar");
        this.txtUrl = new JTextField();
        this.lblContenido = new JLabel("Proyecto: Sin seleccionar");
        this.txtUrl.setText("");
        this.txtUrl.setPreferredSize(new Dimension(250, 30));

        // Evento del boton para cargar el directorio que está dentro del input.
        this.btnAbrir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO: ejecutar código para obtener el nombre y directorio.
                try {
                    controller.abrirProyecto("Nombre1", txtUrl.getText());
                    JOptionPane.showMessageDialog(null, "Directorio seleccionado", "Hecho", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.panel.add(btnAbrir);
        this.panel.add(txtUrl);
        this.panel.add(lblContenido);
        this.panel.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        // Validar que el objeto observable sea Proyecto.
        if (o instanceof Proyecto) {
            // Actualizar contenido del label, cuando el modelo cambie.
            this.lblContenido.setText("Proyecto: "+((Proyecto) o).getNombre());
        }
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

}
