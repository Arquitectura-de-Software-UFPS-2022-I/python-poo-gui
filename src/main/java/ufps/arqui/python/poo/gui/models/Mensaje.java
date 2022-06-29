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
        if (linea.startsWith("list_all_instancias:")) {
            this.linea = this.linea.replaceAll("list_all_instancias:", "");
            this.tipo = TipoMensaje.INSTANCIA;
        } else if (linea.startsWith("get_directorio_trabajo:")) {
            this.linea = this.linea.replaceAll("get_directorio_trabajo:", "");
            this.tipo = TipoMensaje.DIRECTORIO;
        } else if (linea.startsWith("import_modules:")) {
            this.linea = this.linea.replaceAll("import_modules:", "");
            this.tipo = TipoMensaje.IMPORTS;
        } else if (linea.startsWith("errores_importacion:")) {
            this.linea = this.linea.replaceAll("errores_importacion:", "");
            this.tipo = TipoMensaje.ERROR;
        }
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
