package ufps.arqui.python.poo.gui.views.impl;

import ufps.arqui.python.poo.gui.models.AttrInstancia;

import javax.swing.table.AbstractTableModel;

/**
 * Tabla para la visualización de la tabla de las atributos de una instancia.
 *
 * @author Omar Ramón Montes
 */
public class AttrTablaInstancia extends AbstractTableModel {

    protected AttrInstancia[] listado;

    public AttrTablaInstancia(AttrInstancia[] listado) {
        this.listado = listado;
    }

    @Override
    public int getRowCount() {
        return this.listado.length;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AttrInstancia persona = this.listado[rowIndex];
        switch (columnIndex) {
            case 0:
                return persona.getKey();
            case 1:
                return persona.getValue();
            case 2:
                return persona.getType();
        }
        return ("");
    }

    @Override
    public String getColumnName(int aCol) {
        switch (aCol) {
            case 0:
                return "Atributo";
            case 1:
                return "Valor";
            case 2:
                return "Tipo de Dato";
        }
        return "";
    }
}
