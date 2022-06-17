package ufps.arqui.python.poo.gui.models;

/**
 *
 * @author Sachikia
 */
public class Posicion {
    private int x;
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
