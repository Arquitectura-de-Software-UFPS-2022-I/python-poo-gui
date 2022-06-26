package ufps.arqui.python.poo.gui.controllers;

import ufps.arqui.python.poo.gui.exceptions.Exceptions;

/**
 * Controlador del menú superior del proyecto.
 *
 * Donde el usuario puede manipular el proyecto y ver la ayuda.
 * @author Omar Ramón Montes
 */
public interface IMenuController {

    /**
     * Debe permitir crear un nuevo proyecto.
     * 
     * Se debe de pedir el directorio y el nombre
     * @param nombre representa el nombre del proyecto.
     * @param directorio representa el directorio raiz donde se guardo.
     */
    void crearProyecto(String nombre, String directorio)  throws Exceptions;
    
    /**
     * Debe permitir abrir un nuevo proyecto previamente guardado.
     * 
     * Se debe de pedir el directorio y el nombre
     * @param nombre representa el nombre del proyecto.
     * @param directorio representa el directorio raiz donde se guardo.
     */
    void abrirProyecto(String nombre, String directorio) throws Exceptions;
    
    /**
     * Debe permitir visualiza el manual de usuario del sistema.
     * 
     * El manual de usuario debe estar en formato pdf.
     */
    void visualizarManualUsuario() throws Exceptions;
    
}
