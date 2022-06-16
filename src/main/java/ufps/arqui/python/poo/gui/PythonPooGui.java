package ufps.arqui.python.poo.gui;

import java.io.File;
import ufps.arqui.python.poo.gui.controllers.IMenuController;
import ufps.arqui.python.poo.gui.controllers.IMundoController;
import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.controllers.impl.MenuController;
import ufps.arqui.python.poo.gui.controllers.impl.MundoController;
import ufps.arqui.python.poo.gui.controllers.impl.ProyectoController;
import ufps.arqui.python.poo.gui.controllers.impl.TerminalController;
import ufps.arqui.python.poo.gui.models.Mundo;
import ufps.arqui.python.poo.gui.models.Proyecto;
import ufps.arqui.python.poo.gui.views.*;
import ufps.arqui.python.poo.gui.views.impl.*;

import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.net.URL;

/**
 * Clase Main para la inicialización del proyecto.
 * 
 * Clase iniciadora del sistema.
 * @author Omar Ramón Montes
 */
public class PythonPooGui {
    
    public static void main(String[] args) throws Exception {
        // Modelo
        Proyecto modelo = new Proyecto();
        Mundo mundo = new Mundo();

        // Inicializar el menú.
        IMenuController menuController = new MenuController(modelo);
        IPanelMenu panelMenu = new PanelMenu(menuController);

        // Inicializar el terminal.
        ITerminalController terminalController = new TerminalController(modelo, mundo);
        IPanelTerminal panelTerminal = new PanelTerminal(terminalController);

        // Inicializar el mundo.
        IMundoController mundoController = new MundoController(modelo);
        IPanelMundo panelMundo = new PanelMundo(mundoController);

        // Inicializar el proyecto.
        IProyectoController proyectoController = new ProyectoController(modelo);
        IPanelProyecto panelProyecto = new PanelProyecto(proyectoController);
        
        IPanelFichero panelFichero = new PanelFichero(proyectoController);
        
        IPanelMundoTerminal panelMundoTerminal = new PanelMundoTerminal(panelMundo, panelTerminal);
        IPanelFicheroProyecto panelFicheroProyecto = new PanelFicheroProyecto(panelFichero, panelProyecto);
        
        // Agregar Observadores del modelo
        modelo.addObserver(panelMenu);
        modelo.addObserver(panelFichero);
        modelo.addObserver(panelTerminal);
        modelo.addObserver(panelMundo);
        modelo.addObserver(panelProyecto);
        modelo.addObserver(panelTerminal);
        mundo.addObserver(panelTerminal);

        // Crear splash al momento de iniciar el sistema.
        JWindow window = new JWindow();
        window.setSize(460, 344);
        window.setLocationRelativeTo(null);
        window.getContentPane().add(new JLabel("", new ImageIcon(new URL("https://c.tenor.com/fdNuq0ikCLwAAAAC/blue-octopus.gif")), SwingConstants.CENTER));
        window.setVisible(true);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Cerrar splash cuando la interfaz ya es visible.
        window.dispose();

        // Iniciar de ventana principal.
        IMainView main = new MainView("POO Con Python", panelMenu, panelFicheroProyecto, panelMundoTerminal);

        
//        Selecciona el proyecto desde aca para testear
//        modelo.setDirectorioRaiz(new File("C:\\Users\\dunke\\Downloads\\workdirectory"));
    }
    
}
