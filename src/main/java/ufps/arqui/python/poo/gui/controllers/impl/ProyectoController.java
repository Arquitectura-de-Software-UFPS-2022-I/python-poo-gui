package ufps.arqui.python.poo.gui.controllers.impl;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ufps.arqui.python.poo.gui.controllers.IProyectoController;
import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.models.Proyecto;

/**
 * Implementación dl Controlador del mundo del proyecto.
 *
 * Donde se administra las clases creadas por el usuario.
 * 
 * @author Omar Ramón Montes
 */
public class ProyectoController implements IProyectoController {

	//private final Proyecto proyecto;

     public ProyectoController() {
		
		
	}
	public ProyectoController(Proyecto proyecto) {
		//this.proyecto = proyecto;
	}
	

	public static void main(String[] args) {

		Proyecto proyectico = new Proyecto();
		proyectico.setNombre("pruebaPython");
		proyectico.setDirectorio("C:\\Users\\FREDDY\\Desktop");
		
		ProyectoController project = new ProyectoController();
		project.crearProyecto(proyectico);
		

	}

	public boolean crearProyecto(Proyecto proyecto) {
		
		String directorios = proyecto.getDirectorio() +"/" + proyecto.getNombre()+"/src/main/python";

		if (new File(directorios).mkdirs()) {
			System.out.println("directorios creados");
			return true;
		}
		else {
			System.out.println("directorio ya existe");
			return false;
		}

	}
  
}
