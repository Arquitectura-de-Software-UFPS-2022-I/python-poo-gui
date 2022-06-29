package ufps.arqui.python.poo.gui.models;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.utils.AdministrarArchivo;

/**
 * Clase del modelo encargada de gestionar los archivos abiertos en el editor de
 * texto por el usuario, y realizar las correspondientes acciones sobre los mismos
 * archivos
 * @author Sachikia
 */
public class Editor extends Observable{
    private final Set<ArchivoPython> archivosAbiertos = new HashSet<>();
    private ArchivoPython ultimoArchivoAbierto;

    public Editor() {
    }
    
    /**
     * Abre un <code>ArchivoPython</code>
     * Si actualmente ya estaba abierto, no lo vuelve a leer
     * @param archivo
     * @throws Exceptions 
     */
    public void abrirArchivo(ArchivoPython archivo) throws Exceptions{
        this.ultimoArchivoAbierto = archivo;
        
        if(this.estaAbierto(archivo)){
            this.update("estaAbierto");
        }else{
            this.archivosAbiertos.add(archivo);
            this.ultimoArchivoAbierto.leerContenido();
            this.update("archivoAbierto");
        }
    }
    
    private void update(String type){
        this.setChanged();
        this.notifyObservers(type);
    }
    
    /**
     * Verifica si el archivo python pasado como parametro se encuentra abierto
     * @param archivoPython
     * @return 
     */
    public boolean estaAbierto(ArchivoPython archivoPython){
        return this.archivosAbiertos.contains(archivoPython);
    }

    public Set<ArchivoPython> getArchivosAbiertos() {
        return archivosAbiertos;
    }
    
    public ArchivoPython getUltimoArchivoAbierto() {
        return ultimoArchivoAbierto;
    }

    /**
     * Cierra el archivo python
     * @param archivoPython 
     */
    public void cerrarArchivo(ArchivoPython archivoPython) {
        this.archivosAbiertos.remove(archivoPython);
        if(this.ultimoArchivoAbierto != null && this.ultimoArchivoAbierto.equals(archivoPython)){
            this.ultimoArchivoAbierto = null;
        }
    }

    public void guardarArchivo(ArchivoPython archivoPython, String contenido) throws Exceptions {
        AdministrarArchivo.escribirArchivo(archivoPython.getArchivo(), contenido);
        archivoPython.setContenido(contenido);
        this.ultimoArchivoAbierto = archivoPython;
        
        this.update("actualizacionArchivo");
    }
}
