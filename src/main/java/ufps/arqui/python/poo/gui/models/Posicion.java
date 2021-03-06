package ufps.arqui.python.poo.gui.models;

/**
 * Representa los puntos cardinales de un panel.
 *
 * @author Sachikia
 */
public class Posicion {

    /**
     * Posición en el plano horizontal.
     */
    private int x;

    /**
     * Posición en el plano vertical.
     */
    private int y;

    public Posicion() {
    }

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
