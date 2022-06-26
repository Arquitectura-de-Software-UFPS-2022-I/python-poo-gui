package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import ufps.arqui.python.poo.gui.utils.ViewTool;
import ufps.arqui.python.poo.gui.utils.ConfGrid;

/**
 * PanelClass componente que se dibujara en el area de proyecto como Clase
 *
 * @author Sachikia
 */
public class PanelClass {

    /**
     * JPanel en el cual sera dibujado y arratrado este PanelClass
     */
    private JPanel parent;

    /**
     * JPanel representación visual de una clase de python
     */
    private JPanel panel;

    /**
     * JLabel representación visual del nombre de una clase de python
     */
    private JLabel lblName;

    /**
     * Listado de paneles de herencia, se usa para posteriormente saber hacia
     * que panel dibujar la flecha
     */
    private List<PanelClass> herencia = new ArrayList<>();

    /**
     * Indica si este panel ya ha sido dibujado
     */
    private Boolean estaDibujado;

    /**
     * Ubicación actual de este PanelClass en el JPanel parent donde esta siendo
     * dibujado
     */
    private Point location;

    /**
     * Ultima ubicación registrada del PanelClass luego de haber sido arrastrado
     */
    private Point lastLocation;

    private EditorTexto editor;

    public PanelClass(String name, JPanel parent) {
        this.panel = new JPanel(new GridBagLayout());
        this.estaDibujado = false;
        this.parent = parent;
        this.lblName = new JLabel(name);
        this.location = new Point();
        this.lastLocation = new Point();

        this.init();
        this.draggableMode();
    }

    private void init() {
        this.panel.setBackground(Color.CYAN);
        this.panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.lblName.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        ConfGrid config = new ConfGrid(panel, lblName);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);

        ViewTool.insert(config);
//        ViewTool.insert(this.panel, this.lblName, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, null, 0, 0);
    }

    /**
     * Añade un panel que representa una clase de la cual this heredo Recordar
     * que esta clase(PanelClass) es la forma visual de una clase de python
     *
     * @param panelClass PanelClass relacionado a this por herencia en clases de
     * python
     */
    public void añadirHerencia(PanelClass panelClass) {
        this.herencia.add(panelClass);
    }

    /**
     * Añade los eventos para arrastrar la clase(PanelClass)
     */
    private void draggableMode() {
        this.panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent ev) {
                location.move(ev.getX(), ev.getY());
            }
        });

        this.panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                //Calculo para pasar del puntero en la screen al panel
                int calX = evt.getXOnScreen() - parent.getLocationOnScreen().x;
                int calY = evt.getYOnScreen() - parent.getLocationOnScreen().y;

                int xt = calX - location.x;
                int yt = calY - location.y;

                lastLocation.move(xt, yt);
                panel.setLocation(lastLocation);
                parent.repaint();
            }
        });
    }

//    public void openEditor() {
//        this.panel.addMouseListener(new MouseAdapter() {
//            public void mouseCliked(MouseEvent ev) {
//                //editor
//                System.out.println("Estoy en el evento Cliked");
//                new EditorTexto();
//            }
//        });
//    }

    public JPanel getPanel() {
        return this.panel;
    }

    public List<PanelClass> getHerencia() {
        return herencia;
    }

    /**
     * Obtiene la ubicación de este panel relativa al panel padre que lo
     * contiene
     *
     * @return
     */
    public Point getLocation() {
        int calX = panel.getLocationOnScreen().x - parent.getLocationOnScreen().x;
        int calY = panel.getLocationOnScreen().y - parent.getLocationOnScreen().y;

        return new Point(calX, calY);
    }

    /**
     * Cambia la posición de este panel a la ultima ubicación en la que estuvo
     */
    public void cambiaAUltimaUbicacion() {
        if (this.lastLocation.x != 0) {
            this.panel.setLocation(this.lastLocation);
        }
    }

    public Point getLastLocation() {
        return this.lastLocation;
    }

    public Boolean estaDibujado() {
        return estaDibujado;
    }

    public void setEstaDibujado(Boolean isDraw) {
        this.estaDibujado = isDraw;
    }
}
