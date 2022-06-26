package ufps.arqui.python.poo.gui.views.impl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.views.IPanelMenuClase;

/**
 *
 * @author Sachikia
 */
public class ModalCrearClase {
    private IPanelMenuClase panelMenu;
    private JFrame frame;
    private JTextField txtName;
    private JButton btnAceptar;
    private JButton btnCancel;

    public ModalCrearClase(IPanelMenuClase panel) throws Exception {
        this.panelMenu = panelMenu;
        this.frame = new JFrame("Nueva Clase");
        this.txtName = new JTextField();
        this.btnAceptar = new JButton("Aceptar");
        this.btnCancel = new JButton("Cancelar");
        this.init();
        this.addEvents();
    }

    private void init() throws Exception {
        JPanel panelForm = new JPanel(new GridBagLayout());
        JLabel lblName = new JLabel("Nombre");
        
        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());
        
        ViewTool.insert(panelForm, lblName, 0, 0, 0, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 0);
        ViewTool.insert(panelForm, this.txtName, 1, 0, 1, 0, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, new Insets(0, 0, 5, 5), 0, 10);
        
        JPanel panelOptions = new JPanel(new GridBagLayout());

        ViewTool.insert(panelOptions, this.btnAceptar, 0, 0, 1, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LAST_LINE_END, new Insets(0, 0, 0, 10), 0, 0);
        ViewTool.insert(panelOptions, this.btnCancel, 1, 0, 0, 0, 1, 1, GridBagConstraints.NONE, GridBagConstraints.LAST_LINE_END, null, 0, 0);
        ViewTool.insert(container, panelForm, 0, 0, 1, 0, 3, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, new Insets(10, 10, 10, 10), 0, 0);
        ViewTool.insert(container, panelOptions, 0, 1, 1, 1, 3, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_END, new Insets(0, 0, 10, 10), 0, 0);

        this.frame.setPreferredSize(new Dimension(500, 200));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    private void addEvents() {
        // Evento del menú para cerrar la modal al darle click primario.
        this.btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    cerrarModal();
                }
            }
        });
        
        this.btnAceptar.addActionListener(e -> {
            try{
                this.panelMenu.modalCrearClase(this.txtName.getText());
                this.cerrarModal();
            }catch(IOException err){
                JOptionPane.showMessageDialog(this.panelMenu.getPanel(), "Error al crear la clase: "+ err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void cerrarModal() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
    
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
   
}
