package pages.student;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dataObject.Lecture;
import dataObject.Person;
import dataObject.Semester;
import frames.MainFrame;
import managers.DB_Manager;
import other.Colors;
import other.Ctcr;
import other.FontList;
import other.JRButton;

import javax.swing.table.TableModel;
import javax.swing.border.SoftBevelBorder;

public class AddLecturePanel extends JPanel {
	String[] LectureString = { "체크" ,"과목코드", "강의명", "학과", "담당 교수", "강의 시간", "이수구분", "학점" };

	// 신청된 강의리스트
	private JTable listed_table;

	// 학과 콤보박스
	private JLabel department_cbx;

	// 학기/학년 콤보박스
	private JComboBox semester_cbx;

	private JTable unlisted_table;

	static DefaultTableModel unlisted_LectureModel;
	static DefaultTableModel listed_lectureModel;
	static DefaultTableCellRenderer cr2;
	private JCheckBox checkBox;
	ArrayList<Lecture> list;
	ArrayList<Lecture> list2;

	DB_Manager dbms;
	String semesterS="";

	/**
	 * Create the panel.
	 * 
	 * 
	 * 
	 */
	public AddLecturePanel() {
		setBackground(Color.WHITE);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 126, 55, 0 };
		gbl_panel.rowHeights = new int[] { 15, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("수강신청");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(FontList.bold20);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 111, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNewLabel_3 = new JLabel("학과");
		lblNewLabel_3.setFont(FontList.default16);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);

		department_cbx = new JLabel();
		department_cbx.setFont(FontList.default14);
		GridBagConstraints gbc_department_cbx = new GridBagConstraints();
		gbc_department_cbx.insets = new Insets(0, 0, 5, 0);
		gbc_department_cbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_department_cbx.gridx = 0;
		gbc_department_cbx.gridy = 1;
		panel_1.add(department_cbx, gbc_department_cbx);

		JLabel lblNewLabel_1 = new JLabel("학년/학기");
		lblNewLabel_1.setFont(FontList.default16);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

		semester_cbx = new JComboBox(Semester.GetAll());
		semester_cbx.setFont(FontList.default14);
		GridBagConstraints gbc_semester_cbx = new GridBagConstraints();
		gbc_semester_cbx.insets = new Insets(0, 0, 5, 0);
		gbc_semester_cbx.fill = GridBagConstraints.HORIZONTAL;
		gbc_semester_cbx.gridx = 0;
		gbc_semester_cbx.gridy = 3;
		panel_1.add(semester_cbx, gbc_semester_cbx);

		JButton findbutton = new JRButton("조회");
		findbutton.setBackground(Colors.blue);
		findbutton.setFont(FontList.default14);
		GridBagConstraints gbc_findbutton = new GridBagConstraints();
		gbc_findbutton.anchor = GridBagConstraints.SOUTH;
		gbc_findbutton.fill = GridBagConstraints.HORIZONTAL;
		gbc_findbutton.gridx = 0;
		gbc_findbutton.gridy = 13;
		panel_1.add(findbutton, gbc_findbutton);
		findbutton.addActionListener(new findAction());

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_2.setBackground(Color.LIGHT_GRAY);
		add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 100, 225, 0 };
		gbl_panel_2.rowHeights = new int[] { 164, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, 0.0, 2.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		checkBox = new JCheckBox();
		unlisted_LectureModel = new DefaultTableModel(LectureString, 0) {
			public boolean isCellEditable(int rowindex, int Colindex) {
				if (Colindex == 0) {
					return true;
				} else {
					return false;
				}
			};
		};
		unlisted_table = new JTable(unlisted_LectureModel);
		unlisted_table.setFont(FontList.default14);
		unlisted_table.getColumn("체크").setCellRenderer(new Ctcr());
		unlisted_table.getColumn("체크").setCellEditor(new DefaultCellEditor(checkBox));
		checkBox.setHorizontalAlignment(JLabel.CENTER);
		JScrollPane jsp2 = new JScrollPane(unlisted_table);

		JLabel lblNewLabel_4 = new JLabel("비 수강 목록");
		lblNewLabel_4.setBackground(SystemColor.control);
		lblNewLabel_4.setFont(FontList.default16);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		panel_2.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 0;
		panel_2.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		panel_4.add(jsp2, BorderLayout.CENTER);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 1;
		panel_2.add(panel_3, gbc_panel_3);

		JButton addbutton = new JRButton("추가 ▼");
		addbutton.setBackground(Colors.blue);
		panel_3.add(addbutton);
		addbutton.setFont(FontList.default14);
		addbutton.addActionListener(new addToListAction());

		JButton removebutton = new JRButton("제거 ▲");
		removebutton.setBackground(Colors.red);
		panel_3.add(removebutton);
		removebutton.setFont(FontList.default14);
		removebutton.addActionListener(new removeToListAction());

		listed_lectureModel = new DefaultTableModel(LectureString, 0) {
			public boolean isCellEditable(int rowindex, int Colindex) {
				if (Colindex == 0) {
					return true;
				} else {
					return false;
				}
			};
		};

		listed_table = new JTable(listed_lectureModel);
		listed_table.setFont(FontList.default14);
		cr2 = new Ctcr();
		listed_table.getColumn("체크").setCellRenderer(cr2);
		listed_table.getColumn("체크").setCellEditor(new DefaultCellEditor(checkBox));
		checkBox.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane jsp = new JScrollPane(listed_table);

		JLabel lblNewLabel_5 = new JLabel("수강 목록");
		lblNewLabel_5.setFont(FontList.default16);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 2;
		panel_2.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 2;
		panel_2.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		panel_5.add(jsp, BorderLayout.CENTER);

		setInfomation();
		dbms = DB_Manager.getInstance();
	}

	// 이 패널이 생성될때, 학생의 정보를 받아서 각 콤보박스에 데이터를 입력하는 메소드
	void setInfomation() {
		Person p = MainFrame.getLoginedPerson();
		department_cbx.setText(p.getDepart());
	}

	// 신청하기전 조회하는 액션
	class findAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String semester = semester_cbx.getSelectedItem().toString();
	         semesterS = semester;

			list2 = DB_Manager.FindEnrollment(MainFrame.getLoginedPerson().getId(), semester);
			listed_lectureModel.setNumRows(0);
			for (Lecture Obj : list2) {
				Object[] temp = { false, Obj.getId(), Obj.getLec_Name(), Obj.getDepart(), Obj.getProfessor(),
						Obj.getLec_date(), Obj.getLec_type(), Obj.getLec_score(), Obj.getLec_semester() };
				listed_lectureModel.addRow(temp);
			}

			list = DB_Manager.FindLec(department_cbx.getText(), semester);
			unlisted_LectureModel.setNumRows(0);
			for (Lecture Obj : list) {
				boolean flag = true;
				for (Lecture obj2 : list2) {
					if (obj2.getLec_Name().equals(Obj.getLec_Name())) {
						flag = false;
					}
				}
				if (flag) {
					Object[] temp = { false, Obj.getId(), Obj.getLec_Name(), Obj.getDepart(), Obj.getProfessor(),
							Obj.getLec_date(), Obj.getLec_type(), Obj.getLec_score(), Obj.getLec_semester() };
					unlisted_LectureModel.addRow(temp);
				}
			}

		}

	}

	// 신청된 리스트로 추가하는 액션
	class addToListAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
	         
			for (int i = 0; i < unlisted_table.getRowCount(); i++) {
				if ((boolean) unlisted_table.getValueAt(i, 0)) {

					for (Lecture Obj : list) {
						if (Obj.getId() == (int) unlisted_table.getValueAt(i, 1)) {
							Object[] temp = { false, Obj.getId(), Obj.getLec_Name(), Obj.getDepart(),Obj.getProfessor(), Obj.getLec_date(), Obj.getLec_type(), Obj.getLec_score(),Obj.getLec_semester() };
							unlisted_LectureModel.removeRow(i);
							listed_lectureModel.addRow(temp);
							String std_id = MainFrame.getLoginedPerson().getId();
			                  String lec_name = Obj.getLec_Name();
			                  String std_name = MainFrame.getLoginedPerson().getName();
			                  //String semester = semesterS;
			                  String semester = semesterS;
			                  String p_name = Obj.getProfessor();
			                  int lec_score = Obj.getLec_score();
			                  dbms.Enrollment(std_id, lec_name, std_name, semester, p_name, lec_score);
						}
					}
				}
			}
		}

	}

	// 신청된 리스트에서 제거하는 액션
	class removeToListAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < listed_table.getRowCount(); i++) {
				if ((boolean) listed_table.getValueAt(i, 0)) {

					for (Lecture Obj : list) {
						if (Obj.getId() == (int) listed_table.getValueAt(i, 1)) {
							Object[] temp = { false, Obj.getId(), Obj.getLec_Name(), Obj.getDepart(),
									Obj.getProfessor(), Obj.getLec_date(), Obj.getLec_type(), Obj.getLec_score(),
									Obj.getLec_semester() };
							listed_lectureModel.removeRow(i);
							unlisted_LectureModel.addRow(temp);
							String std_id = MainFrame.getLoginedPerson().getId();
							String lec_name = Obj.getLec_Name();
							dbms.DeleteEnrollment(std_id, lec_name);
						}
						
					}
				}
			}
		}

	}

}
