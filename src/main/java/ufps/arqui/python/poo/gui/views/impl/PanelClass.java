package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import ufps.arqui.python.poo.gui.utility.ViewTool;
import ufps.arqui.python.poo.gui.utils.impl.ConfGrid;

/**
 * PanelClass componente que se dibujara en el area de proyecto como Clase
 * 
 * @author Sachikia
 */
public class PanelClass implements MouseListener{
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

        ConfGrid config = new ConfGrid(panel, lblName);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setFill(GridBagConstraints.HORIZONTAL);
        config.setAnchor(GridBagConstraints.PAGE_START);

        ViewTool.insert(config);
//        ViewTool.insert(this.panel, this.lblName, 0, 0, 1, 1, 1, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.PAGE_START, null, 0, 0);
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

    @Override
    public void mouseClicked(MouseEvent e) {
       
//        JOptionPane.showMessageDialog(null, "pene");
//        System.out.println("hola1");
//       
    }

    @Override
    public void mousePressed(MouseEvent e) {
//       JOptionPane.showMessageDialog(null, "pene gordo");
        System.out.println("hola2");
        JFrame r = new JFrame();
        r.setBounds(100, 100, 180, 180);
        JPanel b = new JPanel();
        
        JButton be = new JButton();
         JButton beb = new JButton();
        be.setText("Añadir");
        beb.setText("Eliminar");
        r.add(b);
        b.add(be);
        b.add(beb);
        r.setVisible(true);
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//       JOptionPane.showMessageDialog(null, "pene");
//        System.out.println("hola3");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//       JOptionPane.showMessageDialog(null, "pene");
//        System.out.println("hola4");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        JOptionPane.showMessageDialog(null, "pene");
//        System.out.println("hola5");
    }
}
