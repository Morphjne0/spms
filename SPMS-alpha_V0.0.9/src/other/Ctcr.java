package other;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


//üũ�ڽ��� �����ְ� �ϴ� ��� ������ Ŭ����
public class Ctcr extends DefaultTableCellRenderer {

	public Ctcr() {
		// TODO �ڵ� ������ ������ ����
		super();
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		// TODO �ڵ� ������ �޼ҵ� ����
		JCheckBox cbx= new JCheckBox();
		cbx.setSelected(((Boolean)value).booleanValue());
		cbx.setHorizontalAlignment(JLabel.CENTER);
		return cbx;
	}
	
}
