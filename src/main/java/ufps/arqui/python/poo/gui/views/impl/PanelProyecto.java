package ufps.arqui.python.poo.gui.views.impl;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.utils.impl.ConfGrid;
import ufps.arqui.python.poo.gui.views.IPanelProyecto;

import javax.swing.*;
import java.util.Observable;
import ufps.arqui.python.poo.gui.models.ClasePython;
import ufps.arqui.python.poo.gui.utility.ViewTool;

/**
 * PAnel del proyecto para visualizar las clases del proyecto, así como sus relaciones.
 *
 * En la interfaz tambien tendrán los botones para crear una nueva clase
 * y crear una relación de herencia.
 * @author Omar Ramón Montes
 */
public class PanelProyecto implements IPanelProyecto {

    private final IProyectoController controller;
    private final JPanel panel;
    private final List<PanelClass> classPanels = new ArrayList<>();

    public PanelProyecto(IProyectoController controller) {
        this.controller = controller;
        this.panel = new JPanel(new GridBagLayout());

        this.inicializarContenido();
    }
    
    @Override
    public void inicializarContenido() {
        PanelClass pc1 = new PanelClass("SoloTest 1", this.panel);
        PanelClass pc2 = new PanelClass("SoloTest 2", this.panel);

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
        if(arg instanceof List){
            this.classPanels.clear();
            List<ClasePython> clases = (List<ClasePython>)arg;
            this.panel.removeAll();
            int x = 0, y = 0;
            for(ClasePython clase: clases){
                PanelClass pc = new PanelClass(clase.getNombre(), this.panel);
                this.classPanels.add(pc);
                ViewTool.insert(this.panel, pc.getPanel(), x, y, 1, 1, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER, null, 100, 40);
                if(x++ > 3){
                    x = 0;
                    y++;
                }
            }
            this.panel.revalidate();
            this.panel.repaint();
        }
    }
}
