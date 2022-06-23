package ufps.arqui.python.poo.gui.controllers;

import ufps.arqui.python.poo.gui.exceptions.Exceptions;

/**
 * Controlador del mundo del proyecto.
 *
 * Donde se administra las variables creadas por el usuario.
 * @author Omar Ramón Montes
 */
public interface IMundoController {

    void eliminarInstancia(String name) throws Exceptions;
    
}
