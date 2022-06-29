package ufps.arqui.python.poo.gui.models;

/**
 * Contenedor de datos de una clase.
 * @author Omar Ramón Montes
 */
public class LineasClase {
    private int inicio;
    private int fin;
    private String archivo;
    private String clase;

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LineasClase{");
        sb.append("inicio=").append(inicio);
        sb.append(", fin=").append(fin);
        sb.append(", archivo='").append(archivo).append('\'');
        sb.append(", clase='").append(clase).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
