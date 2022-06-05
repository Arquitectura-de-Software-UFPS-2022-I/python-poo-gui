package ufps.arqui.python.poo.gui;

import ufps.arqui.python.poo.gui.views.IMainView;
import ufps.arqui.python.poo.gui.views.impl.MainView;

/**
 * Clase Main para la inicialización del proyecto.
 * 
 * Clase iniciadora del sistema.
 * @author Omar Ramón Montes
 */
public class PythonPooGui {
    
    public static void main(String[] args) {
        
        // Iniciar el sistema con un nombre 
        IMainView main = new MainView("App");
        
    }
    
}
