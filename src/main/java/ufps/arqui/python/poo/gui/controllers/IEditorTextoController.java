package ufps.arqui.python.poo.gui.controllers;

import java.io.IOException;

/**
 *
 * @author Rafael Peña
 */
public interface IEditorTextoController {
    public void guardarArchivo(String path, String contenido);
    void crearClase(String nombre)  throws IOException;

}


