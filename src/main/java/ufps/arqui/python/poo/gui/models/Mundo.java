package ufps.arqui.python.poo.gui.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Clase modelo donde se guardarán los datos de las instancias y comandos del entorno del usuario.
 */
public class Mundo extends Observable {

    /**
     * Cola de comandos para ejecutar en el shell.
     */
    private List<String> entradas = new ArrayList<>();

    /**
     * Resultado de la terminal.
     */
    private List<String> salidas = new ArrayList<>();

    public List<String> getEntradas() {
        return entradas;
    }

    public List<String> getSalidas() {
        List<String> salidas_ = new ArrayList<>(salidas);
        this.salidas.clear();
        return salidas_;
    }

    public void limpiarSalidas() {
        salidas.clear();
    }

    public void nuevaEntrada(String entrada) {
        this.entradas.add(entrada);
        this.salidas.add(entrada);
        this.update("nuevaEntrada");
    }

    public String getEntrada() {
        if (this.entradas.isEmpty()) {
            return null;
        }
        String comando = this.entradas.remove(0);
        this.update("getEntrada");
        return comando;
    }

    public void nuevaSalida(String salida) {
        this.salidas.add(salida);
        this.update("nuevaSalida");
    }

    private void update(String type) {
        super.setChanged();
        super.notifyObservers(type);
    }
}
