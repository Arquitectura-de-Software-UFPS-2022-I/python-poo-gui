package ufps.arqui.python.poo.gui.utils.impl;

import ufps.arqui.python.poo.gui.utility.ViewTool;

import java.awt.*;

/**
 * Clase para la configuración de Grillas.
 */
public class ConfGrid {

    private Container contenedor;
    private Component componente;
    private Insets insets = new Insets(0, 0, 0, 0);
    private int gridx;
    private int gridy;
    private double weightx;
    private double weighty;
    private int gridwidth = 1;
    private int gridheight = 1;
    private int fill = GridBagConstraints.NONE;
    private int anchor = GridBagConstraints.CENTER;;
    private int ipadx;
    private int ipady;

    public ConfGrid(Container contenedor, Container componente) {
        this.contenedor = contenedor;
        this.componente = componente;
    }

    public Container getContenedor() {
        return contenedor;
    }

    public void setContenedor(Container contenedor) {
        this.contenedor = contenedor;
    }

    public Component getComponente() {
        return componente;
    }

    public void setComponente(Component componente) {
        this.componente = componente;
    }

    public Insets getInsets() {
        return insets;
    }

    public void setInsets(int top, int left, int bottom, int right) {
        this.insets = new Insets(top, left, bottom, right);
    }

    public int getGridx() {
        return gridx;
    }

    public void setGridx(int gridx) {
        this.gridx = gridx;
    }

    public int getGridy() {
        return gridy;
    }

    public void setGridy(int gridy) {
        this.gridy = gridy;
    }

    public double getWeightx() {
        return weightx;
    }

    public void setWeightx(double weightx) {
        this.weightx = weightx;
    }

    public double getWeighty() {
        return weighty;
    }

    public void setWeighty(double weighty) {
        this.weighty = weighty;
    }

    public int getGridwidth() {
        return gridwidth;
    }

    public void setGridwidth(int gridwidth) {
        this.gridwidth = gridwidth;
    }

    public int getGridheight() {
        return gridheight;
    }

    public void setGridheight(int gridheight) {
        this.gridheight = gridheight;
    }

    public int getFill() {
        return fill;
    }

    public void setFill(int fill) {
        this.fill = fill;
    }

    public int getAnchor() {
        return anchor;
    }

    public void setAnchor(int anchor) {
        this.anchor = anchor;
    }

    public int getIpadx() {
        return ipadx;
    }

    public void setIpadx(int ipadx) {
        this.ipadx = ipadx;
    }

    public int getIpady() {
        return ipady;
    }

    public void setIpady(int ipady) {
        this.ipady = ipady;
    }
}
