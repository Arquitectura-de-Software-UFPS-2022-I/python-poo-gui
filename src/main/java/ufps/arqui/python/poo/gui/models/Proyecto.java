package ufps.arqui.python.poo.gui.models;

import com.google.gson.Gson;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.utils.AdministrarArchivo;
import ufps.arqui.python.poo.gui.utils.ConfScanFile;
import ufps.arqui.python.poo.gui.utils.TerminalInteractiva;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

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
     * Comando para inicializar python.
     */
    private String comandoPython;

    /**
     * Archivo de propiedades del proyecto.
     */
    private Properties fileProperties = new Properties();

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
    
    /**
     * Editor de texto, encargado de administrar las operaciones sobre los archivos <br>
     * del proyecto
     */
    private Editor editor;
    
    /**
     * Instancia de la terminal interactiva.
     */
    private final TerminalInteractiva terminalInteractiva;

    public Proyecto(TerminalInteractiva terminalInteractiva, Editor editor) {
        this.terminalInteractiva = terminalInteractiva;
        this.editor = editor;
    }

    /**
     * Escanea el proyecto en busca de clases declaradas en todos los
     * directorios y subdirectorios
     *
     * @throws ufps.arqui.python.poo.gui.exceptions.Exceptions
     */
    public void escanearProyecto() throws Exceptions {
        if (this.directorioRaiz == null) {
            throw new Exceptions("El proyecto no ha sido seleccionado", null);
        }
        // Crear directorio src
        File file = new File(this.directorioRaiz + File.separator+ "src");
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
        this.terminalInteractiva.ingresarComando("scanner_project()");
//        this.terminalInteractiva.inicializarTerminal(this.directorioRaiz, new String[]{"scan.py"});
    }

    /**
     * Lista las clases correspondientes a un directorio.
     *
     * Se toma la ruta relativa y se concatena a la ruta del proyecto para asi
     * obtener la ruta absooluta del directorio en el cual se extraeran las
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

    public String getNombre() {
        return nombre;
    }

    public String getComandoPython() {
        return comandoPython;
    }

    public String getDirectorio() {
        return directorioRaiz != null ? directorioRaiz.getAbsolutePath():"";
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.update("nombre");
    }

    /**
     * Reinicia todo el proyecto.
     */
    public void resetearProyecto() {
        this.nombre = null;
        this.comandoPython = null;
        this.fileProperties = new Properties();
        this.directorioTrabajo = null;
        this.directorioRaiz = null;
    }

    public void setComandoPython(String comandoPython) {
        this.comandoPython = comandoPython;
        this.update("comandoPython");
    }

    public void setDirectorioRaiz(File directorioRaiz) throws Exceptions {
        this.directorioRaiz = directorioRaiz;
        this.directorioTrabajo = new Directorio(new File(this.directorioRaiz.getAbsolutePath() + File.separator + "src"));

        // Crear archivo properties
        try {
            File properties = new File(this.directorioRaiz.getAbsolutePath() + File.separator + "project.properties");
            if (!properties.exists() && this.nombre == null) {
                throw new Exceptions("El directorio seleccionado no contiene ningún proyecto.", null);
            }
            if (properties.exists() && this.nombre != null) {
                throw new Exceptions("El directorio seleccionado ya contiene un proyecto.", null);
            }
            if (!properties.exists()) {
                properties.createNewFile();
                FileOutputStream out = new FileOutputStream(properties);
                this.fileProperties.setProperty("NAME", this.nombre);
                this.fileProperties.setProperty("PYTHON", this.comandoPython);
                this.fileProperties.setProperty("DIR", this.directorioRaiz.getAbsolutePath());
                fileProperties.store(out, null);
                out.close();
            } else{
                FileInputStream in = new FileInputStream(properties);
                this.fileProperties.load(in);
                this.nombre = this.fileProperties.getProperty("NAME");
                this.comandoPython = this.fileProperties.getProperty("PYTHON");
                in.close();
            }
        } catch (IOException e) {
            throw new Exceptions("No se ha podido acceder al archivo de configuración", e);
        }

        this.terminalInteractiva.inicializarTerminal(this.directorioRaiz, this.comandoPython, new String[]{"scan.py"});
        this.update("directorio");
        this.escanearProyecto();
    }

    public Directorio getDirectorioTrabajo() {
        return directorioTrabajo;
    }

    /**
     * Actualiza el modelo y notifica a los observaciones del Mundo a que se a
     * realizado un cambio
     *
     * @param type representa el tipo de cambio realizado.
     */
    private void update(String type) {
        super.setChanged();
        super.notifyObservers(type);
    }

    @Override
    public void update(Observable o, Object arg) {
        //Proyecto solo esta pendiente de la terminal, por lo tanto solo va ser notificado por esta misma
        if (arg instanceof Mensaje) {
            Mensaje m = (Mensaje) arg;
            Gson gson = new Gson();
            if (m.getTipo().esDirectorio()) {
                try {
                    this.directorioTrabajo = gson.fromJson(m.getLinea(), Directorio.class);
                    super.setChanged();
                    super.notifyObservers(obtenerClasesDesde(this.directorioTrabajo));
                    this.update("directoriosTrabajo");
                } catch (Exception e) {
                }
            }
            if (m.getTipo().esImports()) {
                try {
                    String[] importaciones = gson.fromJson(m.getLinea(), String[].class);
                    for (String impor: importaciones) {
                        this.terminalInteractiva.ingresarComando(impor);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void eliminarArchivo(String relativePath) throws Exceptions {
        File file = new File(this.directorioTrabajo.getDirectorio().getAbsolutePath() + File.separator + relativePath);
        if (file.getAbsolutePath().equals(this.directorioTrabajo.getDirectorio().getAbsolutePath())) {
            throw new Exceptions("No se puede eliminar el Directorio de Trabajo", null);
        }
        AdministrarArchivo.eliminarArchivo(file);

        this.setChanged();
        this.update("archivoBorrado");
    }
    
    /**
     * Abre un <code>ArchivoPython</code> que corresponda a la ruta relativa pasada como
     * parametro
     * @param relativaPathFile Ruta relativa del archivo a abrir
     * @throws Exceptions 
     */
    public void abrirArchivo(String relativaPathFile) throws Exceptions{
        relativaPathFile = this.directorioRaiz.getAbsolutePath() + File.separator + relativaPathFile;
        ArchivoPython archivoPython = this.directorioTrabajo.getArchivo(relativaPathFile);
        this.editor.abrirArchivo(archivoPython);
    }
    
    /**
     * Cierra un <code>ArchivoPython</code> que corresponda a la ruta absoluta pasada como
     * parametro
     * @param absolutePathFile Ruta absoluta del archivo a abrir
     * @throws Exceptions 
     */
    public void cerrarArchivo(String absolutePathFile) throws Exceptions{
        ArchivoPython archivoPython = this.directorioTrabajo.getArchivo(absolutePathFile);
        this.editor.cerrarArchivo(archivoPython);
    }
    
    /**
     * Guarda el contenido en un <code>ArchivoPython</code> que corresponda con <br>
     * la ruta absoluta pasada como parametro
     * @param absolutePathFile Ruta absoluta del archivo
     * @param contenido Contenido nuevo a escribir en el archivo
     * @throws Exceptions 
     */
    public void guardarArchivo(String absolutePathFile, String contenido) throws Exceptions {
        ArchivoPython archivoPython = this.directorioTrabajo.getArchivo(absolutePathFile);
        this.editor.guardarArchivo(archivoPython, contenido);
        this.escanearProyecto();
    }

    /**
     * Crea una clase con el nombre <code>nombre</code> en el archivo python que <br>
     * corresponda con la ruta absoluta
     * @param absolutePath Ruta absoluta del archivo al cual se le añadira una nueva clase
     * @param nombre Nombre de la clase a ser añadida
     */
    public void crearClase(String absolutePath, String nombre) throws Exceptions {
        ArchivoPython archivoPython = this.directorioTrabajo.getArchivo(absolutePath);
        
        String dirWork = this.directorioRaiz.getAbsolutePath();
        String module = absolutePath.replace(dirWork, "").substring(1).replaceAll("\\\\", ".").replace(".py", "");
        
        this.editor.crearClase(archivoPython, module, nombre);
        
        super.setChanged();
        super.notifyObservers(obtenerClasesDesde(this.directorioTrabajo));
    }
}
