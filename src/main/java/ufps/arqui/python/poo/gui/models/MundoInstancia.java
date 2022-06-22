package ufps.arqui.python.poo.gui.models;

public class MundoInstancia {
    private String name;
    private String name_class;
    private AttrInstancia[] attrs;
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
