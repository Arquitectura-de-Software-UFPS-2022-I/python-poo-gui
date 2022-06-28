package ufps.arqui.python.poo.gui.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Clase para el manejo del portapapeles del sistema operativo.
 *
 * @author Omar Ramón Montes
 */
public class Portapapeles {

    /**
     * Permite pegar en el portapapeles del usuario un contenido.
     * @param texto texto a copiar en el portapapeles
     */
    public static void pegar(String texto) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection ss = new StringSelection(texto);
        cb.setContents(ss, ss);
    }
}
