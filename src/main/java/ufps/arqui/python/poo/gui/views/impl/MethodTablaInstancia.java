package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.models.MethodInstancia;

import javax.swing.table.AbstractTableModel;

/**
 * Tabla para la visualización de la tabla de las metodos de una instancia.
 *
 * @author Omar Ramón Montes
 */
public class MethodTablaInstancia extends AbstractTableModel {

    protected MethodInstancia[] listado;

    public MethodTablaInstancia(MethodInstancia[] listado) {
        this.listado = listado;
    }

    @Override
    public int getRowCount() {
        return this.listado.length;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MethodInstancia persona = this.listado[rowIndex];
        switch (columnIndex) {
            case 0:
                return persona.getName();
            case 1:
                return String.join(", ", persona.getArgs());
        }
        return ("");
    }

    @Override
    public String getColumnName(int aCol) {
        switch (aCol) {
            case 0:
                return "Metodo";
            case 1:
                return "Parametros";
        }
        return "";
    }
}
