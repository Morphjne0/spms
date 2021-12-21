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
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import dataObject.Lecture;
import dataObject.Person;
import dataObject.Semester;
import frames.MainFrame;
import managers.DB_Manager;
import other.Colors;
import other.FontList;
import other.JRButton;

public class FindLecturepanel extends JPanel {
		static DefaultTableModel modelLectureBasic;
		String[] LectureString = {"과목코드","강의명","학과","담당 교수","강의 시간","이수구분","학점"};
		
		private JTable table;
		
		// 학과 콤보박스
		private JLabel department_cbx;
		
		// 학기/학년 콤보박스
		private JComboBox semester_cbx;
		
		
	/**
	 * Create the panel.
	 */
	public FindLecturepanel() {
	
		setBackground(Color.WHITE);
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{126, 55, 0};
		gbl_panel.rowHeights = new int[]{15, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("개설강의조회");
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
		gbl_panel_1.columnWidths = new int[]{111, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
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
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 0;
		gbc_comboBox_2.gridy = 1;
		panel_1.add(department_cbx, gbc_comboBox_2);
		
		JLabel lblNewLabel_1 = new JLabel("학년/학기");
		lblNewLabel_1.setFont(FontList.default16);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		semester_cbx= new JComboBox(Semester.GetAll());
		semester_cbx.setFont(FontList.default14);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 3;
		panel_1.add(semester_cbx, gbc_comboBox);
		
		JButton findButton = new JRButton("조회");
		findButton.setBackground(Colors.blue);
		findButton.setFont(FontList.default14);
		GridBagConstraints gbc_findButton = new GridBagConstraints();
		gbc_findButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_findButton.anchor = GridBagConstraints.SOUTH;
		gbc_findButton.gridx = 0;
		gbc_findButton.gridy = 13;
		panel_1.add(findButton, gbc_findButton);
		findButton.addActionListener(new findAction());
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_2.setBackground(SystemColor.control);
		add(panel_2, BorderLayout.CENTER);
		
		modelLectureBasic = new DefaultTableModel(LectureString,0) {
			public boolean isCellEditable(int rowindex, int Colindex) {
				return false;
			};
		};
		
		table = new JTable(modelLectureBasic);
		JScrollPane jsp = new JScrollPane(table);
		panel_2.setLayout(new BorderLayout(0, 0));
		table.setFont(FontList.default14);
		panel_2.add(jsp,BorderLayout.CENTER);
				
		setInfomation();
	}
	//이 패널이 생성될때, 학생의 정보를 받아서 각 콤보박스에 데이터를 입력하는 메소드
	void setInfomation() {
		Person p = MainFrame.getLoginedPerson();
		department_cbx.setText(p.getDepart());
	}

	//신청하기전 조회하는 액션
		class findAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String semester = semester_cbx.getSelectedItem().toString();
				modelLectureBasic.setNumRows(0);
				ArrayList<Lecture> list = DB_Manager.FindLec(department_cbx.getText(), semester) ;
				
				for(Lecture Obj : list)	{
					Object[]  temp = {Obj.getId(),Obj.getLec_Name(),Obj.getDepart(),Obj.getProfessor(),Obj.getLec_date(),Obj.getLec_type(),Obj.getLec_score(),Obj.getLec_semester()};
					modelLectureBasic.addRow(temp);
					
			}
				
			
		}
}
		
}
