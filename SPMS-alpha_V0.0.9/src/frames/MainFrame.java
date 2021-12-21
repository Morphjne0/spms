package frames;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import dataObject.Person;

import java.awt.Color;

import other.JRButton;
import other.TreeModel_Admin;
import other.TreeModel_Professor;
import other.TreeModel_Student;
import other.FontList;
import pages.*;
import pages.student.*;

import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTree;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;

public class MainFrame extends JFrame {

	static JLabel idTypeLabel;
	static JLabel nameLabel;
	static JLabel departLabel;
	static JLabel departTypeLabel;
	static JLabel Idlabel;
	
	static JTree menuTree;
	
	static TreeModel_Student tms= new TreeModel_Student();
	
	private JPanel contentPane;
	public JLabel timeLabel;
	JPanel workstation;
	JPanel statusPanel;
	TimeSys st;
	
	private static Person user;
		
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("SPMS_MAIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(15, 15, 15, 15));
		panel.setBackground(Color.GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		timeLabel = new JLabel("TIME");
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setFont(FontList.default20);
		timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(timeLabel, BorderLayout.EAST);
		
		JLabel lblNewLabel_7 = new JLabel("SPMS");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(FontList.bold30);
		panel.add(lblNewLabel_7, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 10));
		panel_1.setBackground(Color.GRAY);
		contentPane.add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{200, 0};
		gbl_panel_1.rowHeights = new int[]{200, 50, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		statusPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_statusPanel = new GridBagConstraints();
		gbc_statusPanel.insets = new Insets(0, 0, 5, 0);
		gbc_statusPanel.fill = GridBagConstraints.BOTH;
		gbc_statusPanel.gridx = 0;
		gbc_statusPanel.gridy = 0;
		panel_1.add(statusPanel, gbc_statusPanel);
		statusPanel.setLayout(new GridLayout(6, 1, 0, 0));
		
		idTypeLabel = new JLabel("학번");
		idTypeLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		idTypeLabel.setFont(FontList.default12);
		statusPanel.add(idTypeLabel);
		
		Idlabel = new JLabel("2002111559");
		Idlabel.setFont(FontList.bold14);
		statusPanel.add(Idlabel);
		
		JLabel lblNewLabel_3 = new JLabel("성명");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_3.setFont(FontList.default12);
		statusPanel.add(lblNewLabel_3);
		
		nameLabel = new JLabel("김학생");
		nameLabel.setFont(FontList.bold14);
		statusPanel.add(nameLabel);
		
		departTypeLabel = new JLabel("학과");
		departTypeLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		departTypeLabel.setFont(FontList.default12);
		statusPanel.add(departTypeLabel);
		
		departLabel = new JLabel("NULL");
		departLabel.setFont(FontList.bold14);
		statusPanel.add(departLabel);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(FontList.default20);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		menuTree = new JTree();
		menuTree.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		menuTree.setLargeModel(true);
		menuTree.setFont(FontList.default14);
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.insets = new Insets(0, 0, 5, 0);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 0;
		gbc_tree.gridy = 2;
		panel_1.add(menuTree, gbc_tree);
		menuTree.addTreeSelectionListener(new treeSelecter());
		
		JButton btnNewButton = new JRButton("로그아웃");
		btnNewButton.setFont(FontList.default14);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		panel_1.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new LogoutAction());
		
		workstation = new JPanel();
		workstation.setBorder(new EmptyBorder(10, 10, 10, 10));
		workstation.setBackground(Color.WHITE);
		contentPane.add(workstation, BorderLayout.CENTER);
		workstation.setLayout(new BorderLayout(0, 0));
		
		setVisible(true);
		st=new TimeSys();
		st.start();
		
		setTask(new TimePanel());
	}
	
	void setTask(JPanel task) {
		workstation.removeAll();
		repaint();
		workstation.add(task,BorderLayout.CENTER);
		revalidate();
	}

	public static void setUser(Person user) {
		switch(user.getType()) {
		case "S":
			idTypeLabel.setText("학번");
			departTypeLabel.setVisible(true);
			departLabel.setVisible(true);
			departLabel.setText(user.getDepart());
			menuTree.setModel(new TreeModel_Student());
			break;
		case "P":
			idTypeLabel.setText("교번");
			departTypeLabel.setVisible(true);
			departLabel.setVisible(true);
			departLabel.setText(user.getDepart());
			menuTree.setModel(new TreeModel_Professor());
			break;
		case "A":
			idTypeLabel.setText("관리ID");
			departTypeLabel.setVisible(false);
			departLabel.setVisible(false);
			menuTree.setModel(new TreeModel_Admin());
			break;
		}
		Idlabel.setText(user.getId());
		nameLabel.setText(user.getName());
		MainFrame.user=user;
	}
	
	public static Person getLoginedPerson() {
		return user;
	}
	
	
	class TimeSys extends Thread {

		public void run() {
			while (true) {
				long systime = System.currentTimeMillis();
				SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss", Locale.KOREA);
				timeLabel.setText(fmt.format(systime));
				try {
					super.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		
	}
	
	class treeSelecter implements TreeSelectionListener{
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode selected= (DefaultMutableTreeNode)menuTree.getLastSelectedPathComponent();
			
			if(menuTree.getModel() instanceof TreeModel_Student) {
				System.out.println("send reqeust to Student linker");
				setTask(TreeModel_Student.getRequest(selected));
			}else if(menuTree.getModel() instanceof TreeModel_Professor) {
				System.out.println("send reqeust to Professor linker");
				setTask(TreeModel_Professor.getRequest(selected));
			}else if (menuTree.getModel() instanceof TreeModel_Admin) {
				System.out.println("send reqeust to Admin linker");
				setTask(TreeModel_Admin.getRequest(selected));
			}
		}
	}
	
	class LogoutAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new LoginFrame();
		}
		
	}
	
}

