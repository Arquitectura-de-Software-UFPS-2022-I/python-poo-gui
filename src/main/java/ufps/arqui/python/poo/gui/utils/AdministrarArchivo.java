package ufps.arqui.python.poo.gui.utils;

import ufps.arqui.python.poo.gui.exceptions.Exceptions;

import java.io.File;

public class AdministrarArchivo {

    public static void eliminarArchivo(File archivo) throws Exceptions {
        if (!archivo.exists()) {
            throw new Exceptions(archivo.getAbsolutePath() + "No existe", null);
        }
        File[] archivos = archivo.listFiles();
        if (archivos != null) {
            for (File f : archivos) {
                if (f.isDirectory()) {
                    eliminarArchivo(f);
                } else {
                    f.delete();
                }
            }
        }
        archivo.delete();
    }

}
