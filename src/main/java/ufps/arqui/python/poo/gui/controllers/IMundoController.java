package ufps.arqui.python.poo.gui.controllers;

import ufps.arqui.python.poo.gui.exceptions.Exceptions;

/**
 * Controlador del mundo del proyecto.
 *
 * Donde se administra las variables creadas por el usuario.
 * @author Omar Ramón Montes
 */
public interface IMundoController {

    /**
     * Eliminar instancia creada por el usuario.
     * @param name nombre de la variable registrada por el usuario
     * @throws Exceptions En caso de que la terminal no esté inicializada.
     */
    void eliminarInstancia(String name) throws Exceptions;
    
}
