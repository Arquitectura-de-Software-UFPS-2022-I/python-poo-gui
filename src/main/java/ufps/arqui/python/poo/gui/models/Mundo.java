package ufps.arqui.python.poo.gui.models;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.utils.TerminalInteractiva;

/**
 * Clase modelo donde se guardarán los datos de las instancias y comandos del entorno del usuario.
 */
public class Mundo extends Observable implements Observer{

    /**
     * Cola de comandos para ejecutar en el shell.
     */
    private List<Mensaje> entradas = new ArrayList<>();

    /**
     * Resultado de la terminal.
     */
    private List<Mensaje> salidas = new ArrayList<>();
    
    /**
     * Terminal interactiva
     */
    private TerminalInteractiva interactiveShell;

    public Mundo() throws Exceptions {
        this.interactiveShell = new TerminalInteractiva();
        
        // Solo para testear
        this.interactiveShell.inicializarTerminal(new File("."), new String[]{"python", "-i", "-q"});
        
        this.interactiveShell.addObserver(this);
    }

    public List<Mensaje> getEntradas() {
        return entradas;
    }

    public List<Mensaje> getSalidas() {
        List<Mensaje> salidas_ = new ArrayList<>(salidas);
        this.salidas.clear();
        return salidas_;
    }

    public void limpiarSalidas() {
        salidas.clear();
    }

    public void nuevaEntrada(String entrada) throws Exceptions {
        this.entradas.add(new Mensaje(entrada, TipoMensaje.ERROR));
        this.interactiveShell.ingresarComando(entrada);
        
        this.nuevaSalida(new Mensaje(entrada, TipoMensaje.COMANDO));
    }

    public void nuevaSalida(Mensaje mensaje) {
        this.salidas.add(mensaje);
        this.update("nuevaSalida");
    }
    
    public void reiniciarTerminal() throws Exceptions{
        this.interactiveShell.reiniciarTerminal();
        this.entradas.clear();
        this.salidas.clear();
    }

    private void update(String type) {
        super.setChanged();
        super.notifyObservers(type);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Mensaje){
            Mensaje m = (Mensaje)arg;
            this.nuevaSalida(m);
        }
    }
}
