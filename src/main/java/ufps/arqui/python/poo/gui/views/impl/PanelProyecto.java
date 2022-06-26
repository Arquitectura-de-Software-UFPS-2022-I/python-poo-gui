package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.utils.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelProyecto;

import javax.swing.*;
import java.util.Observable;
import java.util.Set;
import ufps.arqui.python.poo.gui.models.ClasePython;
import ufps.arqui.python.poo.gui.utils.ViewTool;

/**
 * Panel del proyecto para visualizar las clases del proyecto, así como sus
 * relaciones.
 *
 * En la interfaz tambien tendrán los botones para crear una nueva clase y crear
 * una relación de herencia.
 *
 * @author Omar Ramón Montes
 */
public class PanelProyecto implements IPanelProyecto {

    private final IProyectoController controller;
    private final JPanel panel;
    private final Set<PanelClass> classPanels = new HashSet<>();

    int x = 0, y = 0;

    public PanelProyecto(IProyectoController controller) {
        this.controller = controller;
        this.panel = new JPanel(new GridBagLayout()) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                cambiaAUltimaUbicacion();
                paintLinesHierarchy(g);
            }
        };

        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {
        PanelClass pc1 = new PanelClass("scan.py", this.panel);
        PanelClass pc2 = new PanelClass("SoloTest 2", this.panel);
        pc1.getPanel().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                System.out.println(pc1.getPanel().getName());
               EditorTexto editor = new EditorTexto( "rc\\main\\resources\\scan.py");
            }

        });

        pc1.añadirHerencia(pc2);
        this.classPanels.add(pc1);
        this.classPanels.add(pc2);

        ConfGrid config = new ConfGrid(panel, pc1.getPanel());
        config.setWeightx(1);
        config.setWeighty(1);
        config.setIpadx(100);
        config.setIpady(40);

        ViewTool.insert(config);

        config = new ConfGrid(panel, pc2.getPanel());
        config.setGridx(1);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setIpadx(100);
        config.setIpady(40);

        ViewTool.insert(config);

//        ViewTool.insert(this.panel, pc1.getPanel(), 0, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER, null, 100, 40);
//        ViewTool.insert(this.panel, pc2.getPanel(), 1, 0, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER, null, 100, 40);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof List) {
            this.paintPanelClasses((List<ClasePython>) arg);
        }
    }

    /**
     * Ubica y muestra los paneles(clases) que se desean dibujar, ademas se
     * relacionan los paneles que dispongan de herencia.
     *
     * @param classes listado de clases del archivo.
     */
    private void paintPanelClasses(List<ClasePython> classes) {
        Map<String, PanelClass> panels = new HashMap<>();

        this.classPanels.clear();
        this.panel.removeAll();

        this.x = 0;
        this.y = 0;
        for (ClasePython clase : classes) {
            PanelClass pcBase = panels.getOrDefault(clase.getNombre(), new PanelClass(clase.getNombre(), this.panel));

            this.classPanels.add(pcBase);
            panels.put(clase.getNombre(), pcBase);

            for (ClasePython herencia : clase.getHerencia()) {
                PanelClass pcHerencia = panels.getOrDefault(herencia.getNombre(), new PanelClass(herencia.getNombre(), this.panel));
                this.classPanels.add(pcHerencia);
                panels.put(herencia.getNombre(), pcHerencia);
                pcBase.añadirHerencia(pcHerencia);
            }

            this.inicializarConfig(pcBase);
        }
        for (PanelClass pc : classPanels) {
            if (!pc.estaDibujado()) {
                inicializarConfig(pc);
            }
        }

        this.panel.validate();
        this.panel.repaint();
    }

    private void inicializarConfig(PanelClass pc) {
        ConfGrid config = new ConfGrid(this.panel, pc.getPanel());
        config.setGridx(x);
        config.setGridy(y);
        config.setWeightx(1);
        config.setWeighty(1);
        config.setIpadx(100);
        config.setIpady(40);

        ViewTool.insert(config);

//        ViewTool.insert(this.panel, pc.getPanel(), x, y, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER, null, 100, 40);
        pc.setEstaDibujado(Boolean.TRUE);
        if (this.x++ > 3) {
            this.x = 0;
            this.y++;
        }
    }

    /**
     * Dibuja las lineas de herencia entre los paneles(clases) respectivos
     *
     * @param g
     */
    private void paintLinesHierarchy(Graphics g) {
        for (PanelClass pc : classPanels) {
            Point p1 = pc.getLocation();
            for (PanelClass pc1 : pc.getHerencia()) {
                Point p2 = pc1.getLocation();
                int xC1 = p1.x + (pc.getPanel().getWidth() / 2);
                int yC1 = p1.y + (pc.getPanel().getHeight() / 2);
                int xC2 = p2.x + (pc1.getPanel().getWidth() / 2);
                int yC2 = p2.y + (pc.getPanel().getHeight() / 2);
                g.drawLine(xC1, yC1, xC2, yC2);

                int x = (xC1 + xC2) / 2;
                int y = (yC1 + yC2) / 2;

                this.dibujarLineaConFlecha(g, xC1, yC1, x, y, 20, 20);
            }
        }
    }

    /**
     * Draw an arrow line between two points.
     *
     * @param g the graphics component.
     * @param x1 x-position of first point.
     * @param y1 y-position of first point.
     * @param x2 x-position of second point.
     * @param y2 y-position of second point.
     * @param d the width of the arrow.
     * @param h the height of the arrow.
     */
    private void dibujarLineaConFlecha(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawPolygon(xpoints, ypoints, 3);
    }

    /**
     * Cambia la posición de los paneles a su ultima ubicación
     */
    private void cambiaAUltimaUbicacion() {
        for (PanelClass pc : this.classPanels) {
            pc.cambiaAUltimaUbicacion();
        }
    }
}
