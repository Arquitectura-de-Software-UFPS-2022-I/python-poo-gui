package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import ufps.arqui.python.poo.gui.utility.ViewTool;

/**
 * PanelClass componente que se dibujara en el area de proyecto como Clase
 * 
 * @author Sachikia
 */
public class PanelClass {
    private JPanel parent;
    private JPanel panel;
    private JLabel lblName;
    private int x, y;

    public PanelClass(String name, JPanel parent) {
        this.panel = new JPanel(new GridBagLayout());
        this.parent = parent;
        this.lblName = new JLabel(name);

        this.init();
        this.draggableMode();
    }

    private void init() {
        this.panel.setBackground(Color.CYAN);
        this.panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        this.lblName.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        
        ViewTool.insert(this.panel, this.lblName, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, null, 0, 0);
    }

    private void draggableMode() {
        this.panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent ev) {
                x = ev.getX();
                y = ev.getY();
            }
        });

        this.panel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                //Calculo para pasar del puntero en la screen al panel
                int calX = evt.getXOnScreen() - parent.getLocationOnScreen().x;
                int calY = evt.getYOnScreen() - parent.getLocationOnScreen().y;
                
                int xt = calX - x;
                int yt = calY - y;
                panel.setLocation(xt, yt);
            }
        });
    }
    
    public JPanel getPanel(){
        return this.panel;
    }
    
    public Point getLocation(){
        int calX = panel.getLocationOnScreen().x - parent.getLocationOnScreen().x;
        int calY = panel.getLocationOnScreen().y - parent.getLocationOnScreen().y;
        
        return new Point(calX, calY);
    }
}
