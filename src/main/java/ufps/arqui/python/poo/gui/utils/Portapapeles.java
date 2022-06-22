package ufps.arqui.python.poo.gui.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Portapapeles {

    public static void pegar(String texto) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection ss = new StringSelection(texto);
        cb.setContents(ss, ss);
    }
}
