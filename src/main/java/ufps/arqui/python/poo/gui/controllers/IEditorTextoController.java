package ufps.arqui.python.poo.gui.controllers;

import java.io.IOException;

/**
 *
 * @author joser
 */
public interface IEditorTextoController {
    public void guardarArchivo(String path, String contenido);
    
      public  void crearClase(String nombre)  throws IOException;

}
