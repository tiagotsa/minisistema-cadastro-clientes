package View;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class GenericAbstractTableModel<T> extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    private final List<ColumnDef<?>> columns = new ArrayList<>();
    private List<T> data = new ArrayList<>();

    public GenericAbstractTableModel(List<T> data) {
        this.data = data;
    }

    public <V> void addColumn(String label, Function<T, V> fnValue) {
        columns.add(new ColumnDef<V>(label, fnValue));
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T rowValue = getRowValue(rowIndex);
        return columns.get(columnIndex).fnValue.apply(rowValue);
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns.get(columnIndex).label;
    }

    public T getRowValue(int rowIndex) {
        return data.get(rowIndex);
    }

    private class ColumnDef<V> {

        private final String label;
        private final Function<T, V> fnValue;

        public ColumnDef(String label, Function<T, V> fnValue) {
            this.label = label;
            this.fnValue = fnValue;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> GenericAbstractTableModel<T> getModelFrom(JTable table) {
        return (GenericAbstractTableModel<T>) table.getModel();
    }
}
