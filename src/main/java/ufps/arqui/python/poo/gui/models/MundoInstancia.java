package ufps.arqui.python.poo.gui.models;

/**
 * Clase que encapsula una instancia de una clase creada por el usuario.
 *
 * @author Omar Ramón Montes
 */
public class MundoInstancia {

    /**
     * Nombre de la instancia.
     */
    private String name;

    /**
     * Nombre de la clase a la que pertenece la instalcia.
     */
    private String name_class;

    /**
     * Listado de atributos que contiene la instancia.
     */
    private AttrInstancia[] attrs;

    /**
     * Listado de metodos que contiene la instancia.
     */
    private MethodInstancia[] methods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_class() {
        return name_class;
    }

    public void setName_class(String name_class) {
        this.name_class = name_class;
    }

    public AttrInstancia[] getAttrs() {
        return attrs;
    }

    public void setAttrs(AttrInstancia[] attrs) {
        this.attrs = attrs;
    }

    public MethodInstancia[] getMethods() {
        return methods;
    }

    public void setMethods(MethodInstancia[] methods) {
        this.methods = methods;
    }
}
