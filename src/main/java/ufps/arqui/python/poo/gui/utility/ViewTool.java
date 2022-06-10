package ufps.arqui.python.poo.gui.utility;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 *
 * @author Sachikia
 */
public class ViewTool {
    public static final GridBagLayout GBL = new GridBagLayout();
    private static final GridBagConstraints GBC = new GridBagConstraints();
    
    /*
        Documentación para aprender GridBagLayout: 
        https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
     */
    public static void insert(Container contenedor, Component componente, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, int anchor, Insets insets, int ipadx, int ipady) throws Exception{
        if(!(contenedor.getLayout() instanceof GridBagLayout))
            throw new Exception("El contenerdor ["+contenedor.getClass()+"] no tiene establecido el layout aceptado ["+GridBagLayout.class+"]");
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