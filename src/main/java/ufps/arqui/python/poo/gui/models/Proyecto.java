package ufps.arqui.python.poo.gui.models;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import ufps.arqui.python.poo.gui.utils.impl.ConfScanFile;
import ufps.arqui.python.poo.gui.utils.impl.TerminalInteractiva;

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
public class Proyecto extends Observable implements Observer{

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

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.update("nombre");
    }

    public void setDirectorioRaiz(File directorioRaiz) throws IOException {
        this.directorioRaiz = directorioRaiz;
        // TODO: al cambiar de directorio, inicializar el directorio de trabajo,
        // Validar si existe, y realizar la lectura de los archivos, sino, registrarlo.
        this.update("directorio");
        this.scanearProyecto();
    }
    
    public void scanearProyecto() throws IOException{
        if(this.directorioRaiz==null)
            throw new IOException("El proyecto no ha sido seleccionado");
        //Si el archivo scan no esta en la raiz del proyecto, lo crea
        ConfScanFile.putScanFileIn(this.directorioRaiz);
        
        TerminalInteractiva terminal = new TerminalInteractiva();
        terminal.addObserver(this);
        terminal.inicializarTerminal(this.directorioRaiz, new String[]{"python", "scan.py"});
    }

    public String getNombre() {
        return nombre;
    }

    public File getDirectorioRaiz() {
        return directorioRaiz;
    }

    public Directorio getDirectorioTrabajo() {
        return directorioTrabajo;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        //Proyecto solo esta pendiente de la terminal, por lo tanto solo va ser notificado por
        //esta misma
        Mensaje m = (Mensaje)arg;
        Gson gson = new Gson();
        this.directorioTrabajo = gson.fromJson(m.getLine(), Directorio.class);
        super.setChanged();
        super.notifyObservers(getClassesFrom(this.directorioTrabajo));
    }
    
    public List<ClasePython> getClassesFrom(Directorio directorio){
        List<ClasePython> clases = new ArrayList<>();
        this.getClassesFrom(this.directorioTrabajo, clases);
        return clases;
    }
    
    private void getClassesFrom(Directorio directorio, List<ClasePython> clases){
        for(ArchivoPython archivo: directorio.getArchivos()){
            clases.addAll(archivo.getClases());
        }
        for(Directorio subdir: directorio.getDirectorios()){
            this.getClassesFrom(subdir, clases);
        }
    }
    
    private void update(String type) {
        super.setChanged();
        super.notifyObservers(type);
    }

    @Override
    public String toString() {
        return "Proyecto{" + "nombre=" + nombre + ", directorioRaiz=" + directorioRaiz + ", directorioTrabajo=" + directorioTrabajo + '}';
    }

}
