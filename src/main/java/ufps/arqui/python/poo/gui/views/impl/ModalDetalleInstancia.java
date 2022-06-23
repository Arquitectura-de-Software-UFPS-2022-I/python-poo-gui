package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.models.AttrInstancia;
import ufps.arqui.python.poo.gui.models.MethodInstancia;
import ufps.arqui.python.poo.gui.models.MundoInstancia;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.utils.ViewTool;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class ModalDetalleInstancia {

    private JFrame frame;
    private JButton btnCancel;

    private final MundoInstancia instancia;

    public ModalDetalleInstancia(MundoInstancia instancia) {
        this.frame = new JFrame("Detalle de Instancia");
        this.instancia = instancia;

        this.btnCancel = new JButton("Cancelar");
        this.init();
    }

    private void init() {
        JPanel panelForm = new JPanel(new GridBagLayout());

        JLabel lblName = new JLabel("Nombre");
        JLabel lblPath = new JLabel("Clase");
        JLabel txtName = new JLabel(this.instancia.getName());
        txtName.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));
        JLabel txtNameClass = new JLabel(this.instancia.getName_class());
        txtNameClass.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));

        Container container = this.frame.getContentPane();
        container.setLayout(new GridBagLayout());

        ConfGrid config = new ConfGrid(panelForm, lblName);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, txtName);
        config.setGridx(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, lblPath);
        config.setGridy(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        ViewTool.insert(config);

        config = new ConfGrid(panelForm, txtNameClass);
        config.setGridx(1);
        config.setGridy(1);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setInsets(0, 0, 5, 5);
        config.setIpady(10);
        ViewTool.insert(config);

        JPanel panelOptions = new JPanel(new GridBagLayout());

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

        JPanel panelAttr = new JPanel(new GridBagLayout());
        JPanel panelInfo = new JPanel(new GridLayout(1, 3));

        JLabel lblKey = new JLabel("Nombre");
        JLabel lblVal = new JLabel("Valor");
        JLabel lblType = new JLabel("Tipo");

        this.customLabel(lblKey, Color.BLACK, Color.LIGHT_GRAY, 14, true);
        this.customLabel(lblVal, Color.BLACK, Color.LIGHT_GRAY, 14, true);
        this.customLabel(lblType, Color.BLACK, Color.LIGHT_GRAY, 14, true);

        panelInfo.add(lblKey);
        panelInfo.add(lblVal);
        panelInfo.add(lblType);

        int i = 0;
        config = new ConfGrid(panelAttr, panelInfo);
        config.setGridy(i++);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);

        ViewTool.insert(config);

        for (AttrInstancia attr: this.instancia.getAttrs()) {

            panelInfo = new JPanel(new GridLayout(1, 3));
            lblKey = new JLabel(attr.getKey());
            lblVal = new JLabel(attr.getValue());
            lblType = new JLabel(attr.getType());

            this.customLabel(lblKey, Color.BLACK, Color.WHITE, 14, true);
            this.customLabel(lblVal, Color.BLACK, Color.WHITE, 14, true);
            this.customLabel(lblType, Color.BLACK, Color.WHITE, 14, true);

            panelInfo.add(lblKey);
            panelInfo.add(lblVal);
            panelInfo.add(lblType);

            config = new ConfGrid(panelAttr, panelInfo);
            config.setGridy(i++);
            config.setWeightx(1);
            config.setFill(GridBagConstraints.HORIZONTAL);

            ViewTool.insert(config);
        }

        config = new ConfGrid(container, panelAttr);
        config.setGridy(1);
        config.setWeightx(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 10, 10, 10);

        ViewTool.insert(config);

        panelAttr = new JPanel(new GridBagLayout());
        panelInfo = new JPanel(new GridLayout(1, 2));

        lblKey = new JLabel("Nombre");
        lblVal = new JLabel("Parametros");

        this.customLabel(lblKey, Color.BLACK, Color.LIGHT_GRAY, 14, true);
        this.customLabel(lblVal, Color.BLACK, Color.LIGHT_GRAY, 14, true);

        panelInfo.add(lblKey);
        panelInfo.add(lblVal);

        i = 0;
        config = new ConfGrid(panelAttr, panelInfo);
        config.setGridy(i++);
        config.setWeightx(1);
        config.setFill(GridBagConstraints.HORIZONTAL);

        ViewTool.insert(config);

        for (MethodInstancia attr: this.instancia.getMethods()) {

            panelInfo = new JPanel(new GridLayout(1, 3));
            lblKey = new JLabel(attr.getName());
            lblVal = new JLabel(Arrays.toString(attr.getArgs()));

            this.customLabel(lblKey, Color.BLACK, Color.WHITE, 14, true);
            this.customLabel(lblVal, Color.BLACK, Color.WHITE, 14, true);

            panelInfo.add(lblKey);
            panelInfo.add(lblVal);

            config = new ConfGrid(panelAttr, panelInfo);
            config.setGridy(i++);
            config.setWeightx(1);
            config.setFill(GridBagConstraints.HORIZONTAL);

            ViewTool.insert(config);
        }

        config = new ConfGrid(container, panelAttr);
        config.setGridy(2);
        config.setWeightx(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(10, 10, 10, 10);

        ViewTool.insert(config);

        config = new ConfGrid(container, panelOptions);
        config.setGridy(3);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setGridwidth(3);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);
        config.setInsets(0, 0, 10, 10);

        ViewTool.insert(config);

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

        this.frame.setPreferredSize(new Dimension(550, 450));
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
    }

    private void cerrarModal() {
        this.frame.setVisible(false);
        this.frame.dispose();
    }

    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    private void customLabel(JLabel lbl, Color foreground, Color background, int fontSize, boolean border) {
        lbl.setOpaque(true);
        lbl.setForeground(foreground);
        lbl.setBackground(background);
        lbl.setFont(new Font("Serif", Font.PLAIN, fontSize));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        if (border) {
            lbl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }
}
