package ufps.arqui.python.poo.gui.controllers;

import java.io.IOException;

/**
 * Controlador del mundo del proyecto.
 *
 * Donde se administra las clases creadas por el usuario.
 * @author Omar Ramón Montes
 */
public interface IProyectoController {
    /**
     * Escanea el proyecto en busca de clases declaradas en todos los directorios y subdirectorios
     * @throws IOException Excepción en caso del
     */
    void escanearProyecto() throws IOException;
    
    /**
     * Lista las clases correspondientes a un directorio <br>
     * Se toma la ruta relativa y se concatena a la ruta del proyecto para <br>
     * asi obtener la ruta absooluta del directorio en el cual se extraeran las clases
     * @param relativePath Ruta relativa del directorio sobre el cual se desean listar clases
     */
    void obtenerClasesDesde(String relativePath);
}
