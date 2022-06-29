package ufps.arqui.python.poo.gui.exceptions;

/**
 * Clase para el manejo de errores.
 *
 * @author Manuel
 */
public class Exceptions extends Exception {


    public Exceptions() {
    }

    public Exceptions(String message) {
        super(message);
    }

    public Exceptions(String msg, Exception e) {
       super(msg);
       if (e != null) {
           e.printStackTrace();
       }
    }
    
}
