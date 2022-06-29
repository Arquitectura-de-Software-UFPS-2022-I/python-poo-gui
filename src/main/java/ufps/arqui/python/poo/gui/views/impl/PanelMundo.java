package ufps.arqui.python.poo.gui.views.impl;

import com.google.gson.Gson;
import ufps.arqui.python.poo.gui.controllers.IMundoController;
import ufps.arqui.python.poo.gui.models.Mensaje;
import ufps.arqui.python.poo.gui.models.Mundo;
import ufps.arqui.python.poo.gui.models.MundoInstancia;
import ufps.arqui.python.poo.gui.models.TipoMensaje;
import ufps.arqui.python.poo.gui.views.IPanelMundo;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

/**
 * Panel para visualizar el mundo el proyecto, es decir todas las intancias.
 *
 * En el panel mundo se visualizarán las instancias de las clases creadas,
 * con sus respectivos nombres y datos adicionales
 * @author Omar Ramón Montes
 */
public class PanelMundo implements IPanelMundo {

    private final IMundoController controller;
    private final JPanel panel;
    private JPanel contenedor;
    private JScrollPane scroll;

    public PanelMundo(IMundoController controller) {
        this.controller = controller;
        this.panel = new JPanel(new GridLayout(1, 0));

        this.inicializarContenido();
    }

    @Override
    public void inicializarContenido() {
        this.contenedor = new JPanel();
        this.scroll = new JScrollPane(this.contenedor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.panel.add(this.scroll);
        this.panel.setVisible(true);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Mundo) {
            Mundo m = (Mundo) o;
            Gson gson = new Gson();
            for (Mensaje salida : m.getSalidas2()) {
                if (salida.getTipo().esInstancia()) {
                    MundoInstancia[] list = gson.fromJson(salida.getLinea(), MundoInstancia[].class);
                    PanelObjeto obj;
                    this.contenedor.removeAll();
                    for (MundoInstancia item : list) {
                        obj = new PanelObjeto(item, controller);
                        this.contenedor.add(obj.getPanel());
                    }
                    this.contenedor.revalidate();
                    this.contenedor.repaint();
                    this.scroll.getVerticalScrollBar().setValue((int) this.contenedor.getPreferredSize().getHeight());
                }
            }
        }
    }
}
