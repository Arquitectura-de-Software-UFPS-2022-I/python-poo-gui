package ufps.arqui.python.poo.gui;

import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.controllers.IMundoController;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.controllers.impl.MenuController;
import ufps.arqui.python.poo.gui.controllers.impl.MundoController;
import ufps.arqui.python.poo.gui.controllers.impl.ProyectoController;
import ufps.arqui.python.poo.gui.controllers.impl.TerminalController;
import ufps.arqui.python.poo.gui.models.Proyecto;
import ufps.arqui.python.poo.gui.views.*;
import ufps.arqui.python.poo.gui.views.impl.*;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Clase Main para la inicialización del proyecto.
 * 
 * Clase iniciadora del sistema.
 * @author Omar Ramón Montes
 */
public class PythonPooGui {
    
    public static void main(String[] args) throws MalformedURLException, Exception {
        // Modelo
        Proyecto modelo = new Proyecto();

        // Inicializar el menú.
        IMenuController menuController = new MenuController(modelo);
        IPanelMenu panelMenu = new PanelMenu(menuController);

        // Inicializar el terminal.
        ITerminalController terminalController = new TerminalController(modelo);
        IPanelTerminal panelTerminal = new PanelTerminal(terminalController);

        // Inicializar el mundo.
        IMundoController mundoController = new MundoController(modelo);
        IPanelMundo panelMundo = new PanelMundo(mundoController);

        // Inicializar el proyecto.
        IProyectoController proyectoController = new ProyectoController(modelo);
        IPanelProyecto panelProyecto = new PanelProyecto(proyectoController);

        // Agregar Observadores del modelo
        modelo.addObserver(panelMenu);
        modelo.addObserver(panelTerminal);
        modelo.addObserver(panelMundo);
        modelo.addObserver(panelProyecto);

        // Crear splash al momento de iniciar el sistema.
        JWindow window = new JWindow();
        window.setSize(460, 344);
        window.setLocationRelativeTo(null);
        window.getContentPane().add(new JLabel("", new ImageIcon(new URL("https://c.tenor.com/fdNuq0ikCLwAAAAC/blue-octopus.gif")), SwingConstants.CENTER));
        window.setVisible(true);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Iniciar de ventana principal.
        IMainView main = new MainView("POO Con Python", panelMenu, panelMundo, panelProyecto, panelTerminal);

        // Ejecutar interfaz
        main.init();
        
        // Cerrar splash cuando la interfaz ya es visible.
        window.dispose();
    }
    
}
