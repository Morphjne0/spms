package pages.professor;
//교수 - 학생점수 관리기능

import dataObject.Person;
import dataObject.Score;
import frames.MainFrame;
import managers.DB_Manager;
import other.Colors;
import other.FontList;
import other.JRButton;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;


public class scoreProf extends JPanel {
    static scoreProf instance=null;
    static DefaultTableModel model;
    JTable table;
    String header[] = new String[]{"학번","이름","과목","학점"};

    DB_Manager DAO = DB_Manager.getInstance();
    Person user;
    JComboBox<String> lectCombo;
    private JTextField search;
    public scoreProf(){
    	setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        
        setLayout(new BorderLayout(0, 0));
        
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(panel_2, BorderLayout.NORTH);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{85, 150, 400, 100, 30, 0};
        gbl_panel_2.rowHeights = new int[]{30, 0};
        gbl_panel_2.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);
        
        JLabel lblNewLabel = new JLabel("강의명");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(FontList.default14);
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel_2.add(lblNewLabel, gbc_lblNewLabel);
        
        lectCombo = new JComboBox<String>();
        lectCombo.setFont(FontList.default14);
        GridBagConstraints gbc_lectCombo_1 = new GridBagConstraints();
        gbc_lectCombo_1.insets = new Insets(0, 0, 0, 5);
        gbc_lectCombo_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_lectCombo_1.gridx = 1;
        gbc_lectCombo_1.gridy = 0;
        panel_2.add(lectCombo, gbc_lectCombo_1);
        lectCombo.addActionListener(new lecsortAction());
        
        search = new JTextField(20);
        search.setFont(FontList.default14);
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 0, 5);
        gbc_textField.fill = GridBagConstraints.BOTH;
        gbc_textField.gridx = 3;
        gbc_textField.gridy = 0;
        panel_2.add(search, gbc_textField);
        
        JButton searchB_1 = new JRButton("검색");
        searchB_1.setFont(FontList.default14);
        searchB_1.setBackground(Colors.blue);
        GridBagConstraints gbc_searchB_1 = new GridBagConstraints();
        gbc_searchB_1.fill = GridBagConstraints.BOTH;
        gbc_searchB_1.gridx = 4;
        gbc_searchB_1.gridy = 0;
        panel_2.add(searchB_1, gbc_searchB_1);
        searchB_1.addActionListener(null);
        
        model = new DefaultTableModel(header,0){
            public boolean isCellEditable(int row, int column) {
                if(column==3){
                    return true;
                }
                return false;
            }
        };
        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setRowHeight(30);
        table.setPreferredScrollableViewportSize(new Dimension(800, 400));

        DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
        tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tcmSchedule = table.getColumnModel();
        for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
            tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
        }
        table.setAutoCreateRowSorter(true);
        TableRowSorter tablesorter = new TableRowSorter(table.getModel());
        table.setRowSorter(tablesorter);
        table.setBackground(Colors.white);
        //table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth()/3,50));

        JTableHeader tableHeader = table.getTableHeader();
        //tableHeader.setBackground(Color.gray);
        tableHeader.setFont(FontList.default14);
        
        JScrollPane scrollPane_1 = new JScrollPane(table);
        add(scrollPane_1, BorderLayout.CENTER);
        
        JPanel panel_3 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
        flowLayout.setHgap(10);
        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(panel_3, BorderLayout.SOUTH);
        
        JButton confirm_1 = new JRButton("적용");
        confirm_1.setBackground(Colors.blue);
        confirm_1.setFont(FontList.default14);
        panel_3.add(confirm_1);
        confirm_1.addActionListener(new applyAction());
        
        JButton cancel = new JRButton("취소");
        cancel.setBackground(Colors.blue);
        cancel.setFont(FontList.default14);
        panel_3.add(cancel);
        cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 자동 생성된 메소드 스텁
				setList(DAO.getscoreList(user.getName()));
			}
		});
        
        revalidate();
        
        user=MainFrame.getLoginedPerson();
        
        lectCombo.addItem("ALL");
        ArrayList<String> lecList = DAO.getUserslectures(user.getName(),user.getDepart());
        for(String s:lecList) {
            lectCombo.addItem(s);
        }
        setList(DAO.getscoreList(user.getName()));
        
    }
    
    public static void setList(ArrayList<Score> list) {
    	model.setNumRows(0);
        //ArrayList<Score> scoreArrayList = DAO.getscoreList();
        for(Score s:list) {
            Vector<Object>v=new Vector<Object>();
            v.add(s.getStd_id());
            v.add(s.getStd_name());
            v.add(s.getLec_name());
            v.add(s.getScore());
            model.addRow(v);
        }
    }
    /*
    public static void main(String[] args) {
        new scoreProf();
    }

    static scoreProf getInstance() throws NullPointerException{
        if(instance==null){instance=new scoreProf();}
        return instance;
    }
    */
    
    class applyAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Score> list=new ArrayList<Score>();
			// TODO 자동 생성된 메소드 스텁
			for(int i=0;i<table.getRowCount();i++) {
	            String sid = table.getValueAt(i, 0).toString();
	            String name = table.getValueAt(i, 1).toString();
	            String lecture = table.getValueAt(i, 2).toString();
	            int score = Integer.parseInt(table.getValueAt(i, 3).toString());
	            Score sv=new Score(sid, name, lecture, score);
				list.add(sv);
			}
			/*
			int srow = table.getSelectedRow();
            String sid = table.getValueAt(srow, 0).toString();
            String name = table.getValueAt(srow, 1).toString();
            String lecture = table.getValueAt(srow, 2).toString();
            int score = Integer.parseInt(table.getValueAt(srow, 3).toString());
            System.out.println((String) lectCombo.getSelectedItem());
            Score sv=new Score(sid, name, lecture, score);
            */
            DAO.setScore(list);
            for (int i = 0; i < table.getRowCount(); i++) {
                System.out.println(table.getValueAt(i, 2));
            }
		}
    	
    }
    
    class lecsortAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
            if(lectCombo.getSelectedItem().equals("ALL")){
                //DAO.trigger="on";
                setList(DAO.getscoreList(user.getName()));
                
            }else{
                //DAO.trigger="off";
                String lecture=lectCombo.getSelectedItem().toString();
                //System.out.println(DAO.lecture);
                setList(DAO.SearchscoreList(lecture, user.getName()));
            }
        }
    	
    }
}