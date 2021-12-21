package pages;

import javax.swing.JPanel;

import other.Colors;
import other.FontList;
import other.JRButton;
import other.OptionDialog;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import dataObject.Person;
import frames.MainFrame;
import managers.DB_Manager;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class PasswordChanger extends JPanel {
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private boolean flag = false;
	private OptionDialog op;
	Person user;
	
	/**
	 * Create the panel.
	 */
	public PasswordChanger() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("비밀번호 변경");
		lblNewLabel.setFont(FontList.bold20);
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{226, 70, 0};
		gbl_panel.rowHeights = new int[]{0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{2.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_3_1 = new JLabel("  ");
		GridBagConstraints gbc_lblNewLabel_3_1 = new GridBagConstraints();
		gbc_lblNewLabel_3_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3_1.gridx = 0;
		gbc_lblNewLabel_3_1.gridy = 0;
		panel.add(lblNewLabel_3_1, gbc_lblNewLabel_3_1);
		
		JLabel lblNewLabel_1 = new JLabel("기존 비밀번호");
		lblNewLabel_1.setFont(FontList.default14);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(FontList.default14);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 2;
		panel.add(passwordField, gbc_passwordField);
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO 자동 생성된 메소드 스텁
				flag = false;
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("  ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("변경할 비밀번호");
		lblNewLabel_2.setFont(FontList.default14);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(FontList.default14);
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 0;
		gbc_passwordField_1.gridy = 5;
		panel.add(passwordField_1, gbc_passwordField_1);
		passwordField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO 자동 생성된 메소드 스텁
				flag = false;
			}
		});
		
		JLabel lblNewLabel_3_2 = new JLabel("  ");
		GridBagConstraints gbc_lblNewLabel_3_2 = new GridBagConstraints();
		gbc_lblNewLabel_3_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3_2.gridx = 0;
		gbc_lblNewLabel_3_2.gridy = 6;
		panel.add(lblNewLabel_3_2, gbc_lblNewLabel_3_2);
		
		JLabel lblNewLabel_4 = new JLabel("변경될 비밀번호 확인");
		lblNewLabel_4.setFont(FontList.default14);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 7;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(FontList.default14);
		GridBagConstraints gbc_passwordField_2 = new GridBagConstraints();
		gbc_passwordField_2.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_2.gridx = 0;
		gbc_passwordField_2.gridy = 8;
		panel.add(passwordField_2, gbc_passwordField_2);
		passwordField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO 자동 생성된 메소드 스텁
				flag = false;
			}
		});
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton checkButton = new JRButton("체크");
		checkButton.setFont(FontList.bold14);
		checkButton.setBackground(Colors.orange);
		panel_1.add(checkButton,BorderLayout.EAST);
		checkButton.addActionListener(new checkAction());
		
		JButton applyButton = new JRButton("적용");
		applyButton.setBackground(Colors.blue);
		applyButton.setFont(FontList.default14);
		panel_1.add(applyButton, BorderLayout.WEST);
		applyButton.addActionListener(new applyAction());

		setVisible(true);
		
		user=MainFrame.getLoginedPerson();
		op = OptionDialog.getInstance();
	}

	class applyAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(flag) {
				DB_Manager.ChangePwd(user.getId(), new String(passwordField_1.getPassword()));
				flag=false;
				user.setPwd(new String(passwordField_1.getPassword()));
				passwordField.setText("");
				passwordField_1.setText("");
				passwordField_2.setText("");
				op.infomation(2);
			}else {
			op.alert(4);
			}
		}
		
	}
	
	class checkAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String pwd = new String(passwordField.getPassword());
			System.out.println(pwd);
			String pwd2 = new String(passwordField_1.getPassword());
			System.out.println(pwd2);
			String pwd3 = new String(passwordField_2.getPassword());
			System.out.println(pwd3);
			if(user.getPwd().equals(pwd)) {
				if(!pwd2.isBlank() && pwd2.equals(pwd3)) {
					flag = true;
					op.infomation(4);
				}else{
					op.alert(3);
				}
			}else {
				op.alert(2);
			}
			
		}
		
	}
	
}
