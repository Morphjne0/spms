package other;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


//체크박스가 들어갈수있게 하는 상속 렌더러 클래스
public class Ctcr extends DefaultTableCellRenderer {

	public Ctcr() {
		// TODO 자동 생성된 생성자 스텁
		super();
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		// TODO 자동 생성된 메소드 스텁
		JCheckBox cbx= new JCheckBox();
		cbx.setSelected(((Boolean)value).booleanValue());
		cbx.setHorizontalAlignment(JLabel.CENTER);
		return cbx;
	}
	
}
