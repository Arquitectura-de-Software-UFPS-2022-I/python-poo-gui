package ufps.arqui.python.poo.gui.views.impl;

import com.google.gson.Gson;
import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.exceptions.Exceptions;
import ufps.arqui.python.poo.gui.models.Directorio;
import ufps.arqui.python.poo.gui.models.Mensaje;
import ufps.arqui.python.poo.gui.models.Mundo;
import ufps.arqui.python.poo.gui.models.TipoMensaje;
import ufps.arqui.python.poo.gui.utils.Portapapeles;
import ufps.arqui.python.poo.gui.views.IPanelTerminal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;

/**
 * Panel para visualizar el cuadro de texto para ingresar comandos.
 * <p>
 * Se podrá ingresar comandos, interacturando con los objetos del panel del
 * mundo y visualizar su respuesta al momento de ejecutarlo.
 *
 * @author Omar Ramón Montes
 */
public class PanelTerminal implements IPanelTerminal  {

    private final ITerminalController controller;
    private int posComando = 0;

    private final JPanel panel;
    private JTextField txtInput;
    private JPanel terminal;
    private JScrollPane scroll;
    private JPopupMenu menuTerminal;
    private JMenuItem limpiarTerminal;
    private JMenuItem resetearTerminal;

    public PanelTerminal(ITerminalController controller) {
        this.controller = controller;

        this.panel = new JPanel(new BorderLayout());

        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {
        this.menuTerminal = new JPopupMenu();
        this.limpiarTerminal = new JMenuItem("Limpiar terminal");
        this.resetearTerminal = new JMenuItem("Resetear terminal");
        this.menuTerminal.add(this.limpiarTerminal);
        this.menuTerminal.add(this.resetearTerminal);

        this.terminal = new JPanel();
        this.terminal.setLayout(new BoxLayout(this.terminal, BoxLayout.Y_AXIS));

        this.scroll = new JScrollPane(this.terminal);

        // Valor por default para ingresar en la terminal
        this.txtInput = new JTextField("m=Mundo7()");

        // Evento de input para ejecutar comando al momento de presionar enter
        this.txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ingresarComando();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    getComandoIngresado(false);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    getComandoIngresado(true);
                }
            }
        });
        // Evento del scroll para abrir el menú al presionar clic secundario
        this.scroll.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    menuTerminal.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        // Evento del menú para limpiar la terminal al darle click primario.
        this.limpiarTerminal.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    terminal.removeAll();
                    recalcularScroll();
                }
            }
        });
        // Evento del menú para resetear la terminal al darle click primario.
        this.resetearTerminal.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    try {
                        controller.reiniciarTerminal();
                        terminal.removeAll();
                        recalcularScroll();
                    } catch (Exceptions ex) {
                        mostrarError(ex);
                    }
                }
            }
        });
        this.panel.add(this.scroll, BorderLayout.CENTER);
        this.panel.add(this.txtInput, BorderLayout.SOUTH);

        this.panel.setVisible(true);
    }

    /**
     * Obtener nuevo comando y ejecutarlo.
     */
    private void ingresarComando() {
        try {
            controller.ejecutarComando(txtInput.getText());
        } catch (Exceptions ex) {
            mostrarError(ex);
        }
        txtInput.setText("");
    }

    /**
     * Obtener comando.
     */
    private void getComandoIngresado(boolean abajo) {
        String comando = controller.getComando(posComando);
        if (comando != null) {
            if (abajo) {
                posComando -= 1;
            } else {
                posComando += 1;
            }
        } else {
            comando = "";
            posComando = 0;
        }
        txtInput.setText(comando);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Mundo) {
            Mundo m = (Mundo) o;
            visualizarNuevasSalidas(m.getSalidas());
        }
    }

    /**
     * Visualiza en el scroll las nuevas salidas.
     *
     * @param salidas listado de nuevas salidas.
     */
    private void visualizarNuevasSalidas(List<Mensaje> salidas) {
        JLabel lblSalida;
        for (Mensaje salida : salidas) {

            Gson gson = new Gson();
            try {
                gson.fromJson(salida.getLinea(), Directorio.class);
                continue;
            } catch (Exception e) {
            }
            if (salida.getLinea().startsWith("list_all_instancias:")) {
                continue;
            }

            lblSalida = new JLabel(salida.getLinea());
            lblSalida.setForeground(Color.GRAY);
            if (salida.getTipo().equals(TipoMensaje.COMANDO)) {
                lblSalida.setText(">>>"+salida.getLinea());
                lblSalida.setForeground(Color.BLACK);
            }
            if (salida.getTipo().equals(TipoMensaje.ERROR)) {
                lblSalida.setForeground(Color.RED);
            }
            lblSalida.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        String texto = ((JLabel) e.getComponent()).getText();
                        Portapapeles.pegar(texto.replaceAll(">>>",""));
                    }
                }
            });
            this.terminal.add(lblSalida);
        }
        this.recalcularScroll();
    }

    /**
     * Realiza la verificacion de cambios en el scroll y vuelve a pintar, para visualizar los nuevos comandos.
     */
    private void recalcularScroll() {
        this.terminal.revalidate();
        this.terminal.repaint();
        this.scroll.getVerticalScrollBar().setValue((int) this.terminal.getPreferredSize().getHeight());
    }
}
