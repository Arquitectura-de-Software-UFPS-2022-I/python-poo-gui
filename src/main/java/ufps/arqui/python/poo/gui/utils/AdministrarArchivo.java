package ufps.arqui.python.poo.gui.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AdministrarArchivo {

    /**
     * Elimina un archivo o directorio
     * @param archivo Archivo a eliminar
     * @throws Exceptions 
     */
    public static void eliminarArchivo(File archivo) throws Exceptions {
        if (!archivo.exists()) {
            throw new Exceptions(archivo.getAbsolutePath() + " No existe", null);
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
    
    /**
     * Abre un <code>File</code> y obtiene su contenido
     * @param archivo Archivo a abrir y leer
     * @param stringBuilder Contenido del archivo abierto
     * @throws Exceptions 
     */
    public static void abrirArchivo(File archivo, StringBuilder stringBuilder) throws Exceptions{
        if (!archivo.exists()) {
            throw new Exceptions(archivo.getAbsolutePath() + " No existe", null);
        }
        
        try (BufferedReader buffer = new BufferedReader(new FileReader(archivo))) {
            String line = "";
            while((line = buffer.readLine()) != null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        }catch(IOException E){
            throw new Exceptions("Ocurrio un problema al momento de leer el archivo.", null);
        }
    }
    
    /**
     * Escribe el contenido pasado como parametro al archivo
     * @param archivo Archivo sobre el cual se va a escribir
     * @param contenido Contenido que se escribira en el archivo
     * @param adjuntar Inidica si se adjunta el contenido al archivo o si se sobreescribe
     * @throws Exceptions 
     */
    public static void escribirArchivo(File archivo, String contenido, boolean adjuntar) throws Exceptions{
        try{
            FileWriter fw = new FileWriter(archivo, adjuntar);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            
            out.println(contenido);
            out.close();
            
        }catch(Exception e){
            throw new Exceptions("No se ha podido escribir en el archivo", null);
        }
    }
}
