package ufps.arqui.python.poo.gui.views.impl;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelMenuClase;

/**
 *
 * @author Rafael Peña
 */
public class ModalCrearClase {
    private IPanelMenuClase panelMenu;
    
    private JFrame frame;
    private JTextField txtName;
    private JButton btnAceptar;
    private JButton btnCancel;

    public ModalCrearClase(IPanelMenuClase panelMenu) throws Exception {
        this.panelMenu = panelMenu;
        this.frame = new JFrame("Nuevo clase");
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

        ConfGrid config = new ConfGrid(panelForm, lblName);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, this.txtName);
        config.setGridx(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);
        ViewTool.insert(config);

        JPanel panelOptions = new JPanel(new GridBagLayout());
        config = new ConfGrid(panelOptions, btnAceptar);
        config.setWeightx(1);
        config.setAnchor(GridBagConstraints.LAST_LINE_END);
        config.setInsets(0, 0, 0, 10);
        ViewTool.insert(config);

        config = new ConfGrid(panelOptions, btnCancel);
        config.setGridx(1);
        config.setAnchor(GridBagConstraints.LAST_LINE_END);
        ViewTool.insert(config);

        config = new ConfGrid(container, panelForm);
        config.setWeightx(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 10, 10, 10);
        ViewTool.insert(config);

        config = new ConfGrid(container, panelOptions);
        config.setGridy(1);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(0, 0, 10, 10);
        ViewTool.insert(config);

        
        this.frame.setPreferredSize(new Dimension(500, 200));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    private void addEvents() {
        DocumentListener docEvent = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                eventChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                eventChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                eventChange();
            }

            public void eventChange() {
                //lblAbsolutePath.setText(txtPath.getText() +"/"+ txtName.getText());
            }
        };

        this.txtName.getDocument().addDocumentListener(docEvent);


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
                this.panelMenu.modalCrearProyecto(this.txtName.getText());
                this.cerrarModal();
            }catch(IOException err){
                JOptionPane.showMessageDialog(this.panelMenu.getPanel(), "Error al crear el proyecto: "+ err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
