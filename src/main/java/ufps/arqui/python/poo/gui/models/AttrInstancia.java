package ufps.arqui.python.poo.gui.models;

/**
 * Clase para el encapsulamiento de cada atributo de una clases de una instancia especifica.
 *
 * @author Omar Ramón Montes
 */
public class AttrInstancia {
    /**
     * Nombre del atributo
     */
    private String key;

    /**
     * Valor que tiene asignado el atributo
     */
    private String value;

    /**
     * Tipo de dato del atributo.
     */
    private String type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
