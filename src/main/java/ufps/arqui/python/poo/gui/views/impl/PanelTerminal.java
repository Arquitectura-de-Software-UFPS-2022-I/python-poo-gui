package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.controllers.ITerminalController;
import ufps.arqui.python.poo.gui.models.Mundo;
import ufps.arqui.python.poo.gui.models.Proyecto;
import ufps.arqui.python.poo.gui.utils.impl.TerminalInteractiva;
import ufps.arqui.python.poo.gui.views.IPanelTerminal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
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
public class PanelTerminal implements IPanelTerminal {

    private final ITerminalController controller;
    private TerminalInteractiva interactiveShell;

    private final JPanel panel;
    private JTextField txtInput;
    private JPanel terminal;
    private JScrollPane scroll;
    private JPopupMenu menuTerminal;
    private JMenuItem limpiarTerminal;

    public PanelTerminal(ITerminalController controller) {
        this.controller = controller;
        this.interactiveShell = new TerminalInteractiva(controller);

        this.panel = new JPanel(new BorderLayout());

        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {
        this.menuTerminal = new JPopupMenu();
        this.limpiarTerminal = new JMenuItem("Limpiar");
        this.menuTerminal.add(this.limpiarTerminal);

        this.terminal = new JPanel();
        this.terminal.setLayout(new BoxLayout(this.terminal, BoxLayout.Y_AXIS));

        this.scroll = new JScrollPane(this.terminal);

        this.txtInput = new JTextField("print(1+2)");
        // Evento de input para ejecutar comando al momento de presionar enter
        this.txtInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ingresarComando();
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
        this.panel.add(this.scroll, BorderLayout.CENTER);
        this.panel.add(this.txtInput, BorderLayout.SOUTH);

        this.panel.setVisible(true);
    }

    /**
     * Obtener nuevo comando y ejecutarlo.
     */
    private void ingresarComando() {
        if (this.interactiveShell.terminalActiva()) {
            controller.ejecutarComando(txtInput.getText());
            txtInput.setText("");
        } else {
            JOptionPane.showMessageDialog(this.panel, "Seleccione el directorio de trabajo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Inicializa el shell con un nuevo directorio.
     *
     * @param directorio directorio raiz de ejecución de shell.
     */
    private void inicializarShell(String directorio) {
        try {
            this.interactiveShell.inicializarTerminal(directorio);
        } catch (IOException e) {
        }
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Proyecto) {
            Proyecto p = (Proyecto) o;
            if (p.getDirectorioRaiz() != null && arg.toString().equals("directorio")) {
                this.inicializarShell(p.getDirectorioRaiz().getAbsolutePath());
            }
        }
        if (o instanceof Mundo) {
            Mundo m = (Mundo) o;
            if (arg.toString().equals("nuevaEntrada")) {
                String comando = m.getEntrada();
                if (comando != null) {
                    try {
                        this.interactiveShell.ingresarComando(comando);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            appendOutput(m.getSalidas());
        }
    }

    public void appendOutput(List<String> outs) {
        try {
            JLabel lblSalida;
            for (String salida : outs) {
                lblSalida = new JLabel(salida);
                lblSalida.setForeground(salida.contains("error") ? Color.RED : Color.BLACK);
                this.terminal.add(lblSalida);
            }
            this.recalcularScroll();
        } catch (Exception e) {
        }
    }

    private void recalcularScroll() {
        this.terminal.revalidate();
        this.terminal.repaint();
        this.scroll.getVerticalScrollBar().setValue((int) this.terminal.getPreferredSize().getHeight());
    }
}
