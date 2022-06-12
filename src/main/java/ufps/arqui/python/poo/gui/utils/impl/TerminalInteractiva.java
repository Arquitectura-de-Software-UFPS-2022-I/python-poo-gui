package ufps.arqui.python.poo.gui.utils.impl;

import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.views.IPanelTerminal;
import ufps.arqui.python.poo.gui.views.IPanelView;

import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Terminal interactiva que interactura con python.
 */
public class TerminalInteractiva {

    private final Logger logger = Logger.getLogger(TerminalInteractiva.class.getName());

    private String directorio;
    private Process process;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private BufferedReader bufferedReaderError;

    private final IPanelTerminal panelView;

    public TerminalInteractiva(IPanelTerminal panelView) {
        this.panelView = panelView;
    }

    public void inicializarTerminal(File directorio) throws IOException {

        // Validar que no se quiera reiniciar la terminal si el directorio es el mismo
        if (this.directorio == null || !directorio.getAbsolutePath().equals(this.directorio)) {
            this.directorio = directorio.getAbsolutePath();
            this.reiniciarTerminal();
        }
    }

    public boolean terminalActiva() {
        return this.process != null;
    }

    /**
     * Reinicia el proceso siempre y cuando el proceso este activo.
     */
    public void reiniciarTerminal() throws IOException {
        if (terminalActiva()) {
            this.process.destroyForcibly();
            this.bufferedReader.close();
            this.bufferedWriter.close();
            this.bufferedWriter.close();
        }

        this.process = new ProcessBuilder("python", "-i", "-q").directory(new File(this.directorio)).start();
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.process.getOutputStream()));
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
        this.bufferedReaderError = new BufferedReader(new InputStreamReader(this.process.getErrorStream()));

        this.leerSalida(this.bufferedReader, false);
        this.leerSalida(this.bufferedReaderError, true);
    }

    /**
     * Ingresar comando para ejecutar.
     *
     * @param command comando de python, debe ser una sola linea, sin salto de linea.
     * @throws IOException
     */
    public void ingresarComando(String command) throws IOException {
        if (terminalActiva()) {
            bufferedWriter.write(command);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } else {
            new IOException("Terminal inactiva");
        }
    }

    /**
     * Lee la salida linea por linea de la terminal de python.
     *
     * @param buffered bufer del archivo de lectura.
     */
    private void leerSalida(BufferedReader buffered, boolean error) {
        new Thread(() -> {
            try {
                String linea = "";
                while ((linea = buffered.readLine()) != null) {
                    this.panelView.nuevaSalida(linea, error);
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error al leer el archivo: " + e.getMessage() + ": " + e.getLocalizedMessage());
            }
        }).start();
    }

}
