package pages.professor;

//교수 강의등록화면
import javax.swing.*;
import javax.swing.table.*;

import dataObject.Lecture;
import dataObject.Person;
import frames.MainFrame;
import other.Colors;
import other.FontList;
import other.JRButton;
import pages.professor.scoreProf.applyAction;
import managers.DB_Manager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;


public class lectureProf extends JPanel implements ActionListener {

	static DefaultTableModel model;

	String header[] = { "강의번호", "강의명", "학과", "담당교수", "수강요일/시간", "강좌구분", "학점", "학기" };
	// String header[] = {"강의명","학과","교수명","강의일","강의종류","제공학점","학기"};
	// String content[][]
	// ={{"1","컴퓨터정보","주간","JAVA","A반","홍길동"},{"2","컴퓨터정보","야간","JAVA","B반","홍길동"}};

	JButton adder;
	JButton updater;
	JButton deleter;

	static JTable table;
	
	static Person user;

	public lectureProf() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Container c = getContentPane();

		setLayout(new BorderLayout());
		// 프레임 설정구문
		// setSize(1280, 960);

		// setResizable(false);

		// 버튼 판넬
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(panel, BorderLayout.SOUTH);

		adder = new JRButton("추가");
		adder.setBackground(Colors.blue);
		adder.setFont(FontList.default14);
		adder.addActionListener(this);
		panel.add(adder);

		updater = new JRButton("수정");
		updater.setBackground(Colors.blue);
		updater.setFont(FontList.default14);
		updater.addActionListener(this);
		panel.add(updater);

		deleter = new JRButton("삭제");
		deleter.setBackground(Colors.blue);
		deleter.setFont(FontList.default14);
		deleter.addActionListener(this);
		panel.add(deleter);

		// 테이블 구현---------------------------------------------------------
		model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(model);

		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowHeight(30);

		JScrollPane scrollPane = new JScrollPane(table);

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		table.setAutoCreateRowSorter(true);
		TableRowSorter tablesorter = new TableRowSorter(table.getModel());
		table.setRowSorter(tablesorter);
		//table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth() / 3, 50));

		JTableHeader tableHeader = table.getTableHeader();
		//tableHeader.setBackground(Color.gray);
		tableHeader.setFont(FontList.default14);

		add(scrollPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("강의관리");
		lblNewLabel.setFont(FontList.bold20);
		panel_1.add(lblNewLabel);

		user=MainFrame.getLoginedPerson();
		
		setVisible(true);
		setList();
	}

	public static void setList() {
		ArrayList<Lecture> list= DB_Manager.GetUsersLec(user.getDepart(),user.getName());
		model.setRowCount(0);
		for(Lecture p : list) {
			Object[] temp= {p.getId(),p.getLec_Name(),p.getDepart(),p.getProfessor(),p.getLec_date(),p.getLec_type(),p.getLec_score(),p.getLec_semester()};
			model.addRow(temp);
		}
		if(model.getRowCount() > 0 ) table.setRowSelectionInterval(0, 0);
	}
	
	/*
	 * public static void main(String[] args) { new lectureProf(); }
	 */
	public void actionPerformed(ActionEvent e) {

		// 확인 버튼 클릭시 선택된 콤보박스에 있는 기능 실행
		if (e.getSource().equals(adder)) {
			lectureProfJDialog lpd= new lectureProfJDialog("강좌등록");
			lpd.addmode(user);
		} else if (e.getSource().equals(updater)) {
			lectureProfJDialog lpd = new lectureProfJDialog("강좌정보수정");
			int row = table.getSelectedRow();
			int id = Integer.parseInt(table.getValueAt(row, 0).toString());
			String name = table.getValueAt(row, 1).toString();
			String depart = table.getValueAt(row, 2).toString();
			String professor = table.getValueAt(row, 3).toString();
			String lecdate = table.getValueAt(row, 4).toString();
			String lectype = table.getValueAt(row, 5).toString();
			int lecscore = Integer.parseInt(table.getValueAt(row, 6).toString());
			String semester = table.getValueAt(row, 7).toString();
			Lecture d = new Lecture(id, name, depart, professor, lecdate, lectype, lecscore, semester);
			lpd.setField(d);

		} else if (e.getSource().equals(deleter)) {
			int row = table.getSelectedRow();
			System.out.println("선택행 : " + row);
			int v = Integer.parseInt(table.getValueAt(row, 0).toString());// 행 열에 해당하는 value
			System.out.println("값 : " + v);
			if (DB_Manager.lecDelete(v) > 0) {

				lectureProfJDialog.messageBox(this, "레코드 삭제되었습니다.");
				// 리스트 갱신
				setList();

			} else {
				lectureProfJDialog.messageBox(this, "레코드가 삭제되지 않았습니다.");
			}
		}
	}
	
	

	// jtf =TextField_SCH
	//
	// jt = table
	// dt = model

}
