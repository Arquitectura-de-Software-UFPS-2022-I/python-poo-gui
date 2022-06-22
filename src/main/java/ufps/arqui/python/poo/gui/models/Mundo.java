package ufps.arqui.python.poo.gui.models;

import java.io.File;
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
    private TerminalInteractiva terminalInteractiva;

    public Mundo() throws Exceptions {
        this.terminalInteractiva = new TerminalInteractiva();
        
        // Solo para testear
        this.terminalInteractiva.inicializarTerminal(new File("."), new String[]{"-i", "-q"});

        this.terminalInteractiva.addObserver(this);
    }

    public List<Mensaje> getSalidas() {
        List<Mensaje> salidas_ = new ArrayList<>(salidas);
        this.salidas.clear();
        return salidas_;
    }

    public void nuevaEntrada(String entrada) throws Exceptions {
        Mensaje mensaje = new Mensaje(entrada, TipoMensaje.COMANDO);
        this.entradas.add(mensaje);
        this.terminalInteractiva.ingresarComando(entrada);
        this.nuevaSalida(mensaje);
    }

    public void nuevaSalida(Mensaje mensaje) {
        this.salidas.add(mensaje);
        this.update("nuevaSalida");
    }
    
    public void reiniciarTerminal() throws Exceptions{
        this.terminalInteractiva.reiniciarTerminal();
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
