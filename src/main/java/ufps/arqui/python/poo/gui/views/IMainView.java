package ufps.arqui.python.poo.gui.views;

/**
 * Interfaz Vista principal, donde se concentra la inicialización de cada parte 
 * del proyecto.
 * 
 * @author Omar Ramón Montes
 */
public interface IMainView {
    
    IPanelMenu getPanelMenu();

    IPanelFicheroProyecto getPanelFicheroProyecto();

    IPanelMundoTerminal getPanelMundoTerminal();
}
