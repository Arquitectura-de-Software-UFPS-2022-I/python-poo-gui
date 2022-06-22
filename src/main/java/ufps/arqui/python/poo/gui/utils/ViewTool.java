package ufps.arqui.python.poo.gui.utils;

import java.awt.*;

/**
 * Herramienta para configurar la vista con grillas.
 * @author Sachikia
 */
public class ViewTool {
    private static final GridBagConstraints GBC = new GridBagConstraints();
    /*
        Documentación para aprender GridBagLayout: 
        https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
     */
    @Deprecated
    public static void insert(Container contenedor, Component componente, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, int anchor, Insets insets, int ipadx, int ipady) {
        if(insets == null){
            insets = new Insets(0, 0, 0, 0);
        }
        ViewTool.GBC.gridx = gridx;
        ViewTool.GBC.gridy = gridy;
        ViewTool.GBC.weightx = weightx;
        ViewTool.GBC.weighty = weighty;
        ViewTool.GBC.gridwidth = gridwidth;
        ViewTool.GBC.gridheight = gridheight;
        ViewTool.GBC.fill = fill;
        ViewTool.GBC.anchor = anchor;
        ViewTool.GBC.insets = insets;
        ViewTool.GBC.ipadx = ipadx;
        ViewTool.GBC.ipady = ipady;
        contenedor.add(componente, ViewTool.GBC);
        reset();
    }

    public static void insert(ConfGrid configGrid) {

        ViewTool.GBC.gridx = configGrid.getGridx();
        ViewTool.GBC.gridy = configGrid.getGridy();
        ViewTool.GBC.weightx = configGrid.getWeightx();
        ViewTool.GBC.weighty = configGrid.getWeighty();
        ViewTool.GBC.gridwidth = configGrid.getGridwidth();
        ViewTool.GBC.gridheight = configGrid.getGridheight();
        ViewTool.GBC.fill = configGrid.getFill();
        ViewTool.GBC.anchor = configGrid.getAnchor();
        ViewTool.GBC.insets = configGrid.getInsets();
        ViewTool.GBC.ipadx = configGrid.getIpadx();
        ViewTool.GBC.ipady = configGrid.getIpady();
        configGrid.getContenedor().add(configGrid.getComponente(), ViewTool.GBC);
    }
    
    private static void reset(){
        ViewTool.GBC.anchor = GridBagConstraints.CENTER;
        ViewTool.GBC.fill = GridBagConstraints.NONE;
        ViewTool.GBC.gridheight = 1;
        ViewTool.GBC.gridwidth = 1;
        ViewTool.GBC.insets.bottom = 0;
        ViewTool.GBC.insets.top = 0;
        ViewTool.GBC.insets.left = 0;
        ViewTool.GBC.insets.right = 0;
        ViewTool.GBC.weightx = 0;
        ViewTool.GBC.weighty = 0;
        ViewTool.GBC.ipadx = 0;
        ViewTool.GBC.ipady = 0;
    }
}