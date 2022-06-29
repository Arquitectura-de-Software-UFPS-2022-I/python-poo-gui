package ufps.arqui.python.poo.gui.models;

/**
 * Enumerador para categorizar los tipos de mensajes que maneja la terminal interactiva.
 * @author Omar Ramón Montes
 */
public enum TipoMensaje {
    COMANDO,
    SALIDA,
    ERROR,
    INSTANCIA,
    DIRECTORIO,
    IMPORTS,
    LINE_CLASS;

    public boolean esInterno() {
        return this.esInstancia() || this.esDirectorio() || this.esImports() || this.esLineClass();
    }

    public boolean esInstancia() {
        return this.toString().equals(TipoMensaje.INSTANCIA.toString());
    }

    public boolean esDirectorio() {
        return this.toString().equals(TipoMensaje.DIRECTORIO.toString());
    }

    public boolean esImports() {
        return this.toString().equals(TipoMensaje.IMPORTS.toString());
    }

    public boolean esLineClass() {
        return this.toString().equals(TipoMensaje.LINE_CLASS.toString());
    }
}
