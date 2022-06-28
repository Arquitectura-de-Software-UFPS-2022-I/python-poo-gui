package ufps.arqui.python.poo.gui.models;

/**
 * Modelo para representación de los mensajes de entrada y salida de la terminal interactiva.
 *
 * Cada mensaje representa una linea.
 * @author Sachikia
 */
public class Mensaje {

    /**
     * Tipo de mensaje, ya que según el tipo se cambia la configuración de entilos.
     */
    private TipoMensaje tipo;

    /**
     * Mensaje ya sea de entrada o salida de la terminal
     */
    private String linea;

    public Mensaje(String linea, TipoMensaje tipo) {
        this.linea = linea.replaceAll(">>>","");
        this.tipo = tipo;
    }

    public TipoMensaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensaje tipo) {
        this.tipo = tipo;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Mensaje{");
        sb.append("tipo=").append(tipo);
        sb.append(", linea='").append(linea).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
