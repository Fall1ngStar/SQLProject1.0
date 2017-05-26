package app;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLTableModel class
 * Created by Thierry
 * 24/05/2017
 */
public class SQLTableModel extends AbstractTableModel implements TableModel {

    List<Object[]> data;
    Object[] columnNames;

    public SQLTableModel(List<Object[]> data, Object[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    public SQLTableModel() {
        this(new ArrayList<>(), new Object[]{});
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return (String) columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex)[columnIndex] = aValue;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        super.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        super.removeTableModelListener(l);
    }

    public void setData(List<Object[]> data) {
        this.data = data;
    }

    public void setColumnNames(Object[] columnNames) {
        this.columnNames = columnNames;
    }
}
