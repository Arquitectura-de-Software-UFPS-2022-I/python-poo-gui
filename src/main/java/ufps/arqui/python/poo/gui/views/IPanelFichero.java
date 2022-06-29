package ufps.arqui.python.poo.gui.views;

import java.util.Observer;

/**
 * Interfaz lateral del proyecto, donde el usuario puede gestionar sus archivos.
 *
 * @author Sachikia
 */
public interface IPanelFichero extends Observer, IPanelView {

    /**
     * Crear un nuevo fichero en el directorio seleccionado.
     * @param nombre
     * @param directorioSeleccionado
     */
    void crearFichero(String nombre, String directorioSeleccionado);
    
}
