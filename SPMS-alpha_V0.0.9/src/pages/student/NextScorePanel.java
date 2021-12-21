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
import java.util.Calendar;

import javax.swing.table.DefaultTableModel;

import dataObject.Lecture;
import dataObject.Person;
import dataObject.Score;
import dataObject.Semester;
import frames.MainFrame;
import managers.DB_Manager;
import other.FontList;

public class NextScorePanel extends JPanel {
	private JTable table;
	private JLabel depart;
	private JComboBox semester;
	
	ArrayList<Score> list;
	
	private static Person user;

	private static DefaultTableModel NextScoreModel;
	String[] nsmStrings = { "수강 과목","교수 이름","신청 학점","점수","등급"};
	
	/**
	 * Create the panel.
	 */
	public NextScorePanel() {
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
		
		JLabel lblNewLabel = new JLabel("학점조회");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.add(panel_2, BorderLayout.NORTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{50, 126, 0, 97, 108, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("학과:");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		depart = new JLabel();
		depart.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 0;
		panel_2.add(depart, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_1 = new JLabel("\uD559\uB144/\uD559\uAE30:");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		semester = new JComboBox(Semester.GetAll());
		semester.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 0;
		panel_2.add(semester, gbc_lblNewLabel_2);
		semester.addActionListener(new ChangeAction());
		
		NextScoreModel=new DefaultTableModel(nsmStrings,0) {
	         public boolean isCellEditable(int rowindex, int Colindex) {
	               return false;
	         };
	      };
	      
	      table = new JTable(NextScoreModel);
	      table.setFont(FontList.default14);
	      JScrollPane jsp=new JScrollPane(table);
	      panel_1.add(jsp, BorderLayout.CENTER);
	      
		user = MainFrame.getLoginedPerson();
		depart.setText(user.getDepart());
		
		list = DB_Manager.FindScore(user.getId(), semester.getSelectedItem().toString());
		SetTableData(list);
	}
	
	public void SetTableData(ArrayList<Score> list) {
		NextScoreModel.setNumRows(0);
		for (Score Obj : list) {
			String grade = "";
			int Iscore = Obj.getScore();
			if (Iscore >= 95) {
				grade = "A+";
			} else if (Iscore < 95 && Iscore >= 90) {
				grade = "A";
			} else if (Iscore < 90 && Iscore >= 85) {
				grade = "B+";
			} else if (Iscore < 85 && Iscore >= 80) {
				grade = "B";
			} else if (Iscore < 80 && Iscore >= 75) {
				grade = "C+";
			} else if (Iscore < 75 && Iscore >= 70) {
				grade = "C";
			} else if (Iscore < 75 && Iscore >= 65) {
				grade = "D+";
			} else if (Iscore < 65 && Iscore >= 60) {
				grade = "D";
			} else if (Iscore < 60) {
				grade = "F";
			}
			Object[] temp = { Obj.getLec_name(), Obj.getP_name(), Obj.getLec_score(), Obj.getScore(), grade };

			NextScoreModel.addRow(temp);
		}
	}
	class ChangeAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			list = DB_Manager.FindScore(user.getId(), semester.getSelectedItem().toString());
			SetTableData(list);
		}

		
		
	}
	

}
