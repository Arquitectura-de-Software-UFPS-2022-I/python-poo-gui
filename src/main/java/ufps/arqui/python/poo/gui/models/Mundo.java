package ufps.arqui.python.poo.gui.models;

import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.utils.TerminalInteractiva;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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

    private List<Mensaje> salidas2 = new ArrayList<>();

    private TerminalInteractiva terminalInteractiva;

    public Mundo(TerminalInteractiva terminalInteractiva) {
        this.terminalInteractiva = terminalInteractiva;
    }

    /**
     * Obtiene la lista de mensajes que se han ingresados nuevos y limpia la lista para posteriores mensajes.
     *
     * @return Listado de mensajes retornados por la terminal.
     */
    public List<Mensaje> getSalidas() {
        List<Mensaje> salidas_ = new ArrayList<>(salidas);
        this.salidas.clear();
        return salidas_;
    }

    /**
     * Obtiene la lista de mensajes que se han ingresados nuevos y limpia la lista para posteriores mensajes.
     *
     * @return Listado de mensajes retornados por la terminal.
     */
    public List<Mensaje> getSalidas2() {
        List<Mensaje> salidas_ = new ArrayList<>(salidas2);
        this.salidas2.clear();
        return salidas_;
    }

    /**
     * Adiciona un nuevo comando para poder ejecutarlo en python.
     * @param entrada comando de python
     * @throws Exceptions En caso de que la terminal no esté inicializada.
     */
    public void nuevaEntrada(String entrada) throws Exceptions {
        Mensaje mensaje = new Mensaje(entrada, TipoMensaje.COMANDO);
        this.entradas.add(mensaje);
        this.terminalInteractiva.ingresarComando(entrada);
        this.nuevaSalida(mensaje);
    }

    /**
     * Ingresa nueva salida, para poder ser visualizada desde la interfaz.
     * @param mensaje mensaje de salida, representa una linea y puede ser de varios tipos.
     */
    public void nuevaSalida(Mensaje mensaje) {
        this.salidas.add(mensaje);
        this.salidas2.add(mensaje);
        this.update("nuevaSalida");
    }

    /**
     * Resetea la terminal, para borrar todas aquellas instancias creadas y empezar desde cero.
     * @throws Exceptions En caso de que la terminal no esté inicializada.
     */
    public void reiniciarTerminal() throws Exceptions{
        this.terminalInteractiva.reiniciarTerminal();
        this.salidas.clear();
    }

    public String getComando(int indice) {
        int i = this.entradas.size()-indice-1;
        if (i >= 0 && i < this.entradas.size()) {
            return this.entradas.get(i).getLinea();
        }
        return null;
    }

    /**
     * Actualiza el modelo y notifica a los observaciones del Mundo a que se a realizado un cambio
     * @param type representa el tipo de cambio realizado.
     */
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
