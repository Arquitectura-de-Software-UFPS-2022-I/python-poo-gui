package ufps.arqui.python.poo.gui.models;

import com.google.gson.Gson;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.utils.ConfScanFile;
import ufps.arqui.python.poo.gui.utils.TerminalInteractiva;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Modelo para la gestión del proyecto del usuario.
 *
 * Aquí se almacenará la información del proyecto, para que los demás
 * componentes puedan interacturar con el proyecto, como por ejemplo lista de
 * clases con sus metodos y parametros; datos del proyecto; datos del mundo como
 * los objetos.
 *
 * @author Omar Ramón Montes
 */
public class Proyecto extends Observable implements Observer {

    /**
     * Nombre del proyecto.
     */
    private String nombre;

    /**
     * Ubicación del directorio donde se ejecutará la aplicación.
     *
     */
    private File directorioRaiz;

    /**
     * Directtorio de trabajo, donde el usuario tendra su proyecto.
     *
     * El directorio debe llamarse src, y debe estár dentro del directorio raiz.
     */
    private Directorio directorioTrabajo;

    private TerminalInteractiva terminalInteractiva;

    public Proyecto(TerminalInteractiva terminalInteractiva) {
        this.terminalInteractiva = terminalInteractiva;
    }

    /**
     * Escanea el proyecto en busca de clases declaradas en todos los directorios y subdirectorios
     *
     * @throws IOException
     */
    public void escanearProyecto() throws Exceptions {
        if (this.directorioRaiz == null) {
            throw new Exceptions("El proyecto no ha sido seleccionado", null);
        }
        // Crear directorio src
        File file = new File(this.directorioRaiz + "/src");
        if (!file.exists()) {
            file.mkdir();
        }

        //Si el archivo scan no esta en la raiz del proyecto, lo crea
        try {
            ConfScanFile.actualizarArchivoScan(this.directorioRaiz);
        } catch (IOException e) {
            throw new Exceptions("No se ha podido actualizar el archivo scan", e);
        }
        this.directorioTrabajo = new Directorio(file);
        this.terminalInteractiva.inicializarTerminal(this.directorioRaiz, new String[]{"scan.py"});
    }

    /**
     * Lista las clases correspondientes a un directorio.
     *
     * Se toma la ruta relativa y se concatena a la ruta del proyecto para
     * asi obtener la ruta absooluta del directorio en el cual se extraeran las
     * clases
     *
     * @param relativePath
     */
    public void obtenerClasesDesde(String relativePath) {
        String absolutePath = this.directorioTrabajo.getDirectorio().getAbsolutePath()
                + (!relativePath.isEmpty() ? File.separator : "") + relativePath;

        List<ClasePython> classes = this.obtenerClasesDesde(
                this.obtenerDirectorio(directorioTrabajo, absolutePath));

        this.setChanged();
        this.notifyObservers(classes);
    }

    /**
     * Lista las clases correspondientes a un directorio.
     *
     * @param directorio
     * @return listado de clases de python de un directivo dado.
     */
    private List<ClasePython> obtenerClasesDesde(Directorio directorio) {
        List<ClasePython> clases = new ArrayList<>();
        this.obtenerClasesDesde(directorio, clases);
        return clases;
    }

    /**
     * Lista las clases correspondientes a un directorio de forma recursiva.
     *
     * @param directorio
     * @param clases
     */
    private void obtenerClasesDesde(Directorio directorio, List<ClasePython> clases) {
        for (ArchivoPython archivo : directorio.getArchivos()) {
            clases.addAll(archivo.getClases());
        }
        for (Directorio subdir : directorio.getDirectorios()) {
            this.obtenerClasesDesde(subdir, clases);
        }
    }

    /**
     * Obtiene un directorio mediante una ruta absoluta de forma recursiva.
     *
     * @param dir
     * @param absolutePath
     * @return
     */
    private Directorio obtenerDirectorio(Directorio dir, String absolutePath) {
        if (dir.getDirectorio().getAbsolutePath().equals(absolutePath)) {
            return dir;
        }
        Directorio directorio = null;
        for (Directorio subdir : dir.getDirectorios()) {
            directorio = this.obtenerDirectorio(subdir, absolutePath);
            if (directorio != null) {
                break;
            }
        }
        return directorio;
    }

    /**
     * Crea un directorio en la ubiacion y el nombre que se le indica.
     * 
     * @param ubicacionDirectorio
     * @param nombreDirectorio 
     */
    public void crearDirectorio(String ubicacionDirectorio, String nombreDirectorio) {
        File file = new File(ubicacionDirectorio + "/" + nombreDirectorio);
        if (!file.exists()) {
            file.mkdir();
        } else {
            //..
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.update("nombre");
    }

    public File getDirectorioRaiz() {
        return directorioRaiz;
    }

    public void setDirectorioRaiz(File directorioRaiz) throws Exceptions {
        this.directorioRaiz = directorioRaiz;
        this.update("directorio");
        this.escanearProyecto();
    }

    public Directorio getDirectorioTrabajo() {
        return directorioTrabajo;
    }

    private void update(String type) {
        super.setChanged();
        super.notifyObservers(type);
    }

    @Override
    public String toString() {
        return "Proyecto{" + "nombre=" + nombre + ", directorioRaiz=" + directorioRaiz + ", directorioTrabajo=" + directorioTrabajo + '}';
    }

    @Override
    public void update(Observable o, Object arg) {
        //Proyecto solo esta pendiente de la terminal, por lo tanto solo va ser notificado por esta misma
        if (arg instanceof Mensaje) {
            Mensaje m = (Mensaje) arg;
            Gson gson = new Gson();
            try {
                this.directorioTrabajo = gson.fromJson(m.getLinea(), Directorio.class);
                super.setChanged();
                super.notifyObservers(obtenerClasesDesde(this.directorioTrabajo));
                this.update("directoriosTrabajo");
            } catch (Exception e) {
            }
        }
    }
}
