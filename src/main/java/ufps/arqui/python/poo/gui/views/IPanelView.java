package ufps.arqui.python.poo.gui.views;

import ufps.arqui.python.poo.gui.exceptions.Exceptions;

import javax.swing.*;
import java.awt.*;

/**
 * Interfaz todos los paneles del proyecto.
 *
 * @author Omar Ramón Montes
 */
public interface IPanelView {

    /**
     * Obtiene el Jpanel contenedor.
     * @return JPanel contenedor.
     */
    JPanel getPanel();

    /**
     * Debe inicializar los componentes que tendrá en panel.
     */
    void inicializarContenido();

    /**
     * Metodo para mostrar cuadro de error en los paneles.
     * @param e Excepción generada.
     */
    default void mostrarError(Component contenedor, Exceptions e){
        JOptionPane.showMessageDialog(contenedor, e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
    };

    /**
     * Mostrar cuandro de dialogo con mensaje informativo.
     * @param titulo titulo del mensaje
     * @param contenido Contenido del mensaje
     * @param contenedor contenedor para mostrar el dialogo
     */
    default void mostrarMensaje(String titulo, String contenido, Component contenedor){
        JOptionPane.showMessageDialog(contenedor, contenido, titulo, JOptionPane.INFORMATION_MESSAGE);
    };
}
