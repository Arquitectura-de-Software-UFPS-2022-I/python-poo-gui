package ufps.arqui.python.poo.gui.controllers;

import java.io.IOException;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;

import java.io.IOException;
import ufps.arqui.python.poo.gui.models.ArchivoPython;

/**
 * Controlador del mundo del proyecto.
 *
 * Donde se administra las clases creadas por el usuario.
 * @author Omar Ramón Montes
 */
public interface IProyectoController {
    /**
     * Escanea el proyecto en busca de clases declaradas en todos los directorios y subdirectorios
     * @throws ufps.arqui.python.poo.gui.exceptions.Exceptions
     */
    void escanearProyecto() throws Exceptions;
    
    /**
     * Lista las clases correspondientes a un directorio <br>
     * Se toma la ruta relativa y se concatena a la ruta del proyecto para <br>
     * asi obtener la ruta absooluta del directorio en el cual se extraeran las clases
     * @param relativePath Ruta relativa del directorio sobre el cual se desean listar clases
     * @throws ufps.arqui.python.poo.gui.exceptions.Exceptions
     */
    void obtenerClasesDesde(String relativePath) throws Exceptions;

    /**
     * Elimina Archivos y directorios de un directorio dado.
     * Se toma la ruta relativa y se concatena a la ruta del proyecto para asi
     * obtener la ruta absooluta del directorio en el cual se desean eliminar los archivos
     * @param relativePath Ruta relativa del directorio sobre el cual se desea eliminar archivos
     * @throws Exceptions
     */
    void eliminarArchivo(String relativePath) throws Exceptions;
    
    /**
     * Debe buscar un <code>ArchivoPython</code> que corresponda con el <code>path</code> <br>
     * pasado como parametro y abrirlo
     * @param path Ruta del archivo a buscar
     * @throws Exceptions 
     */
    void abrirArchivo(String path) throws Exceptions;
    
    /**
     * Debe buscar un <code>ArchivoPython</code> que corresponda con el <code>path</code> <br>
     * pasado como parametro y cerrarlo
     * @param path Ruta del archivo a buscar
     * @throws Exceptions 
     */
    void cerrarArchivo(String path) throws Exceptions;
    
    /**
     * Debe Guardar el contenido de la clase.
     * Para eso se tiene en cuenta el path (ruta la ubicacion de la clase) y el contenido que llego o se edito
     * 
     * Se debe de pedir el directorio y el nombre
     * @param path representa el nombre del proyecto.
     * @param contenido representa el directorio raiz donde se guardo.
     */
    void guardarArchivo(String path, String contenido) throws Exceptions;

    /**
     * Crear un nuevo archivo en el directorio indicado
     *
     * Se debe de pedir el directorio y el nombre
     * @param path representa el directorio relativo del archivo nuevo.
     * @param nombre representa el nombre del archivo nuevo.
     */
    void crearArchivo(String path, String nombre) throws Exceptions;
    
    /**
    * Creacion de clase.
    * Para eso se tiene en cuenta el path (ruta la ubicacion de la clase) y el contenido que llego o se edito.
    * 
    * Se debe de pedir el directorio y el nombre
    * @param absolutePath Ruta absoluta del archivo en que se debe crear la clase
    * @param nombre representa el nombre de la clase que se desea crear.
    * @throws ufps.arqui.python.poo.gui.exceptions.Exceptions
    */
    void crearClase(String absolutePath, String nombre) throws Exceptions;
}
