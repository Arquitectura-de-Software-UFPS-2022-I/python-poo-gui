/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.arqui.python.poo.gui.controllers;

/**
 *
 * @author Omar Ramón Montes
 */
public interface IMenuController {

    /**
     * Debe permitir abrir un nuevo proyecto previamente guardado.
     * 
     * Se debe de pedir el directorio y el nombre
     * @param nombre representa el nombre del proyecto.
     * @param directorio representa el directorio raiz donde se guardo.
     */
    void abrirProyecto(String nombre, String directorio);
    
    /**
     * Debe permitir visualiza el manual de usuario del sistema.
     * 
     * El manual de usuario debe estar en formato pdf.
     */
    void visualizarManualUsuario();
    
}
