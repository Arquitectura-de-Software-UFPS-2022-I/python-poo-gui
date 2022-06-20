package ufps.arqui.python.poo.gui.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Clase de utilidad para mover o actualizar el archivo scan.py dentro del directorio
 * raiz del proyecto
 * 
 * @author Sachikia
 */
public class ConfScanFile {
    private static final String FILE_SCAN = "scan.py";
    
    /**
     * Crea el archivo scan.py en el directorio especificado.
     *
     * En caso de que exista se borra y se vuelve a crear, con el fin <br>
     * de evitar problemas por cualquier manipulacion del usuario en el archivo
     *
     * @param directorio
     * @throws IOException 
     */
    public static void actualizarArchivoScan(File directorio) throws IOException{
        File file = new File(directorio.getAbsolutePath() + File.separator + FILE_SCAN);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        
        InputStream in = ConfScanFile.class.getClassLoader().getResourceAsStream(FILE_SCAN);
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
 
        byte[] buffer = new byte[1024];
        int lengthRead;
        while ((lengthRead = in.read(buffer)) > 0) {
            out.write(buffer, 0, lengthRead);
            out.flush();
        }
        in.close();
        out.close();
    }
}
