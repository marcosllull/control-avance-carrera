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
	
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component comp = super.prepareRenderer(renderer, row, column);
        Color alternateColor = new Color(186, 198, 245);
        Color whiteColor = Color.WHITE;
        if(!comp.getBackground().equals(getSelectionBackground())) {
           Color c = (row % 2 == 0 ? alternateColor : whiteColor);
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
