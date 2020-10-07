package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class MyJTable extends JTable{
	public MyJTable(TableModel dm) {
        super(dm);
    }
	
	public Component prepareRenderer(TableCellRenderer renderer, int fila, int columna) {
        Component comp = super.prepareRenderer(renderer, fila, columna);
        Color colorAlternativo = new Color(186, 198, 245);
        if(!comp.getBackground().equals(getSelectionBackground())) {
           Color c = (fila % 2 == 0 ? colorAlternativo : Color.WHITE);
           comp.setBackground(c);
           c = null;
        }
        
        return comp;
    }
	
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
