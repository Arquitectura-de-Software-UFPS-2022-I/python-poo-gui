package ufps.arqui.python.poo.gui.controllers;

import java.io.IOException;

/**
 *Controlador del Editor de texto.
 * 
 * 
 * @author Rafael Peña 
 */
public interface IEditorTextoController {
    
    /**
     * Debe Guardar el contenido de la clase.
     * Para eso se tiene en cuenta el path (ruta la ubicacion de la clase) y el contenido que llego o se edito
     * 
     * Se debe de pedir el directorio y el nombre
     * @param path representa el nombre del proyecto.
     * @param contenido representa el directorio raiz donde se guardo.
     */
    public void guardarArchivo(String path, String contenido);
    
     /**
     * Creacion de clase.
     * Para eso se tiene en cuenta el path (ruta la ubicacion de la clase) y el contenido que llego o se edito.
     * 
     * Se debe de pedir el directorio y el nombre
     * @param nombre representa el nombre de la clase que se desea crear.
     */
      public  void crearClase(String nombre)  throws IOException;

}
