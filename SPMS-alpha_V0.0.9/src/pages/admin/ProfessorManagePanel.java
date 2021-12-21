package pages.admin;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import dataObject.Department;
import dataObject.Person;
import managers.DB_Manager;
import other.Colors;
import other.FontList;
import other.JRButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.BevelBorder;

public class ProfessorManagePanel extends JPanel implements ActionListener {

	// deptManageDAO dao = new deptManageDAO();

	static DefaultTableModel model;

	String fieldName;

	String header[] = { "교번", "성명", "생년월일", "학과" };
	String[] AdOption = { "교수등록", "교수정보수정", "교수삭제"};
	// String[] comboName = { "ALL", "depcode", "depname", "representprof" };

	JComboBox Adcombo;
	// JComboBox combo;

	JPanel TPanel;

	JTextField TextField_SCH;

	JButton confirmBtn;
	JButton srchBtn;

	static JTable table;

	public ProfessorManagePanel() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 10, 10);
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 100, 80, 0, 100, 150, 80, 0 };
		gbl_panel.rowHeights = new int[] { 30, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 5.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		Adcombo = new JComboBox/* <String> */(AdOption);
		Adcombo.setFont(FontList.default14);
		GridBagConstraints gbc_Adcombo = new GridBagConstraints();
		gbc_Adcombo.fill = GridBagConstraints.BOTH;
		gbc_Adcombo.insets = new Insets(0, 0, 0, 5);
		gbc_Adcombo.gridx = 0;
		gbc_Adcombo.gridy = 0;
		panel.add(Adcombo, gbc_Adcombo);
		// Adcombo.setBounds(230, 105, 80, 30);

		confirmBtn = new JRButton("확인");
		GridBagConstraints gbc_confirmBtn = new GridBagConstraints();
		gbc_confirmBtn.fill = GridBagConstraints.BOTH;
		gbc_confirmBtn.insets = new Insets(0, 0, 0, 5);
		gbc_confirmBtn.gridx = 1;
		gbc_confirmBtn.gridy = 0;
		panel.add(confirmBtn, gbc_confirmBtn);
		confirmBtn.setFont(FontList.default14);
		confirmBtn.setBorderPainted(false);
		// confirmBtn.setBorder(new RoundedBorder());
		//confirmBtn.setForeground(Color.white);
		// confirmBtn.setBounds(320, 105, 50, 30);//
		confirmBtn.setBackground(Colors.blue);
		confirmBtn.addActionListener(this);// 확인버튼 클릭시 발생하는 Action 이벤트 리스너 등록

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		/*
		 * combo = new JComboBox(comboName); GridBagConstraints gbc_combo = new
		 * GridBagConstraints(); gbc_combo.fill = GridBagConstraints.VERTICAL;
		 * gbc_combo.anchor = GridBagConstraints.EAST; gbc_combo.insets = new Insets(0,
		 * 0, 0, 5); gbc_combo.gridx = 3; gbc_combo.gridy = 0; panel.add(combo,
		 * gbc_combo); // combo.setBounds(800, 0, 80, 30);
		 */
		TextField_SCH = new JTextField(20);
		TextField_SCH.setFont(FontList.default14);
		GridBagConstraints gbc_TextField_SCH = new GridBagConstraints();
		gbc_TextField_SCH.fill = GridBagConstraints.BOTH;
		gbc_TextField_SCH.insets = new Insets(0, 0, 0, 5);
		gbc_TextField_SCH.gridx = 4;
		gbc_TextField_SCH.gridy = 0;
		panel.add(TextField_SCH, gbc_TextField_SCH);
		TextField_SCH.setBounds(885, 0, 160, 30);

		srchBtn = new JRButton("검색");
		GridBagConstraints gbc_srchBtn = new GridBagConstraints();
		gbc_srchBtn.fill = GridBagConstraints.BOTH;
		gbc_srchBtn.gridx = 5;
		gbc_srchBtn.gridy = 0;
		panel.add(srchBtn, gbc_srchBtn);
		srchBtn.setFont(FontList.default14);
		srchBtn.setBorderPainted(false);
		// srchBtn.setBorder(new RoundedBorder());
		//srchBtn.setForeground(Color.white);
		// srchBtn.setBounds(1050, 0, 40, 30);
		srchBtn.setBackground(Colors.blue);
		srchBtn.addActionListener(this);

		TPanel = new JPanel();
		TPanel.setLayout(new BorderLayout());
		// TPanel.setBounds(190, 165, 1020, 425);
		TPanel.setBackground(Color.YELLOW);

		model = new DefaultTableModel(header, 0) {//
			public boolean isCellEditable(int i, int c) {

				return false;
			}
		};

		table = new JTable(model);
		table.setFont(FontList.default14);

		JScrollPane scrollPane = new JScrollPane(table);

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowHeight(30);

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		table.setAutoCreateRowSorter(true);
		TableRowSorter tablesorter = new TableRowSorter(table.getModel());
		table.setRowSorter(tablesorter);
		JTableHeader tableHeader = table.getTableHeader();
        //tableHeader.setBackground(Color.gray);
        tableHeader.setFont(FontList.default14);
		
		TPanel.add(scrollPane, BorderLayout.CENTER);
		add(TPanel, BorderLayout.CENTER);

		setList(DB_Manager.getmemberlist("P"));
		/*
		 * dao.deptSelectAll(model);
		 * 
		 * if (model.getRowCount() > 0) table.setRowSelectionInterval(0, 0);
		 */

	}

	public static void setList(ArrayList<Person> list) {
		model.setRowCount(0);
		for (Person p : list) {
			Object[] temp = { p.getId(), p.getName(), p.getBirth(), p.getDepart() };
			model.addRow(temp);
		}
		if (model.getRowCount() > 0)
			table.setRowSelectionInterval(0, 0);
	}

	public void actionPerformed(ActionEvent e) {

		// 확인 버튼 클릭시 선택된 콤보박스에 있는 기능 실행
		if (e.getSource() == confirmBtn) {

			String Adminfunc = Adcombo.getSelectedItem().toString();

			if (Adminfunc.equals(AdOption[0])) {
				new profManageJDialog(AdOption[0]);
			} else if (Adminfunc.equals(AdOption[1])) {
				profManageJDialog jd = new profManageJDialog(AdOption[1]);
				int row = table.getSelectedRow();
				String id = table.getValueAt(row, 0).toString();
				String name = table.getValueAt(row, 1).toString();
				String birth = table.getValueAt(row, 2).toString();
				String depart = table.getValueAt(row, 3).toString();
				Person p = new Person(id, name, birth, depart, "P");
				jd.setField(p);

			} else if (Adminfunc.equals(AdOption[2])) {// 삭제 메뉴아이템 클릭
				// 현재 Jtable의 선택된 행과 열의 값을 얻어온다.

				int row = table.getSelectedRow();
				System.out.println("선택행 : " + row);

				Object obj = table.getValueAt(row, 0);// 행 열에 해당하는 value
				System.out.println("값 : " + obj);

				if (DB_Manager.deptDelete(obj.toString()) > 0) {
					profManageJDialog.messageBox(this, "레코드 삭제되었습니다.");
					setList(DB_Manager.getmemberlist("P"));

				} else {
					profManageJDialog.messageBox(this, "레코드가 삭제되지 않았습니다.");
				}
			}
		}

		else if (e.getSource() == srchBtn) {// 검색버튼 클릭시 검색
			System.out.println("검색할 필드 : " + fieldName);
			if (TextField_SCH.getText().isBlank()) {
				// JOptionPane.showMessageDialog(null, "검색할 단어를
				// 입력해주세요","메세지",JOptionPane.ERROR_MESSAGE);
				setList(DB_Manager.getmemberlist("P"));
			} else {// 검색어를 입력했을경우
				setList(DB_Manager.memberSearch(TextField_SCH.getText(), "P"));
			}

			// jtf =TextField_SCH
			//
			// jt = table
			// dt = model

		}

	}

}
