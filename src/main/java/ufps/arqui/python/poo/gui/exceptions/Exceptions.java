package ufps.arqui.python.poo.gui.exceptions;

/**
 *
 * @author Manuel
 */
public class Exceptions extends Exception {

    public Exceptions(String msg, Exception e) {
       super(msg);
       if (e != null) {
           e.printStackTrace();
       }
    }
    
}
