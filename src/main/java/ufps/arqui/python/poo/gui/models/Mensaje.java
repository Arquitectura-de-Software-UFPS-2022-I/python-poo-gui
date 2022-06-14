package ufps.arqui.python.poo.gui.models;

/**
 *
 * @author Sachikia
 */
public class Mensaje {
    private String line;
    private Boolean error;

    public Mensaje() {
    }

    public Mensaje(String line, Boolean error) {
        this.line = (error?"--error--":"")+line.replaceAll(">>>","");
        this.error = error;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}
