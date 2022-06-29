package ufps.arqui.python.poo.gui.utils;

import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Mensaje;
import ufps.arqui.python.poo.gui.models.TipoMensaje;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Terminal interactiva que interactura con python.
 *
 * @author Omar Ramón Montes
 */
public class TerminalInteractiva extends Observable {

    private final Logger logger = Logger.getLogger(TerminalInteractiva.class.getName());

    private String directorio;
    private String python;
    private Process process;
    private String parameters[];
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private BufferedReader bufferedReaderError;

    /**
     * Inicializa la terminal de python.
     *
     * @param directorio directorio raiz donde ejecutará python
     * @param parameters listado de parametros que ejecutará despues de inicializar la terminal de python.
     * @throws Exceptions En caso de que los comando sean invalidos.
     */
    public void inicializarTerminal(File directorio, String python, String parameters[]) throws Exceptions {
        this.parameters = parameters;
        this.python = python;

        this.directorio = directorio.getAbsolutePath();
        this.reiniciarTerminal();
    }

    /**
     * Verifica si la terminal interactiva cuenta con el proceso activo.
     * @return true si la terminal está activa.
     */
    public boolean terminalActiva() {
        return this.process != null;
    }

    /**
     * Reinicia el proceso siempre y cuando el proceso este activo.
     */
    public void reiniciarTerminal() throws Exceptions {
        try {
            if (terminalActiva()) {
                this.process.destroyForcibly();
                this.bufferedReader.close();
                this.bufferedWriter.close();
                this.bufferedWriter.close();
            }
            List<String> lineas = new ArrayList();
            // Inicializar proceso de python, el usuario debe contar con la variable de entorno en sus systema operativo.
            lineas.add(this.python);
            // Terminal de python interactiva, donde espera la interacción del usuario.
            lineas.add("-i");
            // No imprimir la versión de python.
            lineas.add("-q");

            for (String p : this.parameters) {
                lineas.add(p);
            }
            this.process = new ProcessBuilder(lineas).directory(new File(this.directorio)).start();
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.process.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
            this.bufferedReaderError = new BufferedReader(new InputStreamReader(this.process.getErrorStream()));

            this.leerSalida(this.bufferedReader, false);
            this.leerSalida(this.bufferedReaderError, true);
        } catch (IOException e) {
            throw new Exceptions("La terminal ha fallado", e);
        }
    }


    /**
     * Ingresar comando para ejecutar.
     *
     * @param command comando de python, debe ser una sola linea, sin salto de linea.
     * @throws IOException en caso de que los buffers no están abiertos.
     */
    public void ingresarComando(String command) throws Exceptions {
        try {
            if (terminalActiva()) {
                bufferedWriter.write(command);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                // En caso de que se modifique las instancias, volver a consultar las instancias.
                bufferedWriter.write("list_all_instancias(locals()) if 'list_all_instancias' in dir() else [].clear()");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } else {
                throw new Exceptions("Terminal inactiva", null);
            }
        } catch (IOException e) {
            throw new Exceptions("La terminal ha fallado", e);
        }
    }

    /**
     *
     * Lee la salida linea por linea de la terminal de python.
     *
     * @param buffered bufer del archivo de lectura.
     * @param error true si el buffer es de la salida de errores.
     */
    private void leerSalida(BufferedReader buffered, boolean error) {
        new Thread(() -> {
            try {
                String linea = "";
                while ((linea = buffered.readLine()) != null) {
                    this.setChanged();
                    this.notifyObservers(new Mensaje(linea, error ? TipoMensaje.ERROR : TipoMensaje.SALIDA));
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error al leer el archivo: " + e.getMessage() + ": " + e.getLocalizedMessage());
            }
        }).start();
    }

}