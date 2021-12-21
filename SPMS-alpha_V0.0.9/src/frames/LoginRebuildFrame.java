package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import managers.DB_Manager;
import other.Colors;
import other.FontList;
import other.JRButton;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class LoginRebuildFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField birthField;
	private JTextField idField;
	private JTextField pbirthField;
	private JLabel resultLabel; //아이디 결과 라벨
	private JLabel lblNewLabel_7; //패스워드 결과라벨
	private JButton idfindButton;
	private JButton passfindButton;
	private JButton copyButton; //아이디 전달버튼

	DB_Manager dbms;


	/**
	 * Create the frame.
	 */
	public LoginRebuildFrame() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(FontList.default14);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel idfindpanel = new JPanel();
		idfindpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("아이디 찾기", null, idfindpanel, null);
		GridBagLayout gbl_idfindpanel = new GridBagLayout();
		gbl_idfindpanel.columnWidths = new int[]{0, 0};
		gbl_idfindpanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_idfindpanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_idfindpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		idfindpanel.setLayout(gbl_idfindpanel);
		
		JLabel lblNewLabel_1 = new JLabel("아이디 찾기");
		lblNewLabel_1.setFont(FontList.default20);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		idfindpanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2_1 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2_1.gridx = 0;
		gbc_lblNewLabel_2_1.gridy = 1;
		idfindpanel.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
		JLabel lblNewLabel = new JLabel("성명");
		lblNewLabel.setFont(FontList.default12);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		idfindpanel.add(lblNewLabel, gbc_lblNewLabel);
		
		nameField = new JTextField();
		nameField.setFont(FontList.default14);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 0;
		gbc_nameField.gridy = 3;
		idfindpanel.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		idfindpanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("생년일월 (ex.20210102)");
		lblNewLabel_3.setFont(FontList.default12);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 5;
		idfindpanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		birthField = new JTextField();
		birthField.setFont(FontList.default14);
		GridBagConstraints gbc_birthField = new GridBagConstraints();
		gbc_birthField.insets = new Insets(0, 0, 5, 0);
		gbc_birthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_birthField.gridx = 0;
		gbc_birthField.gridy = 6;
		idfindpanel.add(birthField, gbc_birthField);
		birthField.setColumns(10);
		
		idfindButton = new JRButton("인증");
		idfindButton.setBackground(Colors.gray200);
		idfindButton.setFont(FontList.default12);
		GridBagConstraints gbc_idfindButton = new GridBagConstraints();
		gbc_idfindButton.insets = new Insets(0, 0, 5, 0);
		gbc_idfindButton.fill = GridBagConstraints.VERTICAL;
		gbc_idfindButton.gridx = 0;
		gbc_idfindButton.gridy = 7;
		idfindpanel.add(idfindButton, gbc_idfindButton);
		idfindButton.addActionListener(new findAction());
		
		resultLabel = new JLabel("");
		resultLabel.setFont(FontList.default14);
		GridBagConstraints gbc_resultLabel = new GridBagConstraints();
		gbc_resultLabel.insets = new Insets(0, 0, 5, 0);
		gbc_resultLabel.fill = GridBagConstraints.VERTICAL;
		gbc_resultLabel.anchor = GridBagConstraints.WEST;
		gbc_resultLabel.gridx = 0;
		gbc_resultLabel.gridy = 8;
		idfindpanel.add(resultLabel, gbc_resultLabel);
		
		copyButton = new JRButton("로그인창에 복사");
		copyButton.setBackground(Colors.gray200);
		copyButton.setEnabled(false);
		copyButton.setFont(FontList.default12);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 9;
		idfindpanel.add(copyButton, gbc_btnNewButton_2);
		copyButton.addActionListener(new SendId());
		
		JPanel passfindpanel = new JPanel();
		passfindpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("비밀번호 찾기", null, passfindpanel, null);
		GridBagLayout gbl_passfindpanel = new GridBagLayout();
		gbl_passfindpanel.columnWidths = new int[]{0, 0};
		gbl_passfindpanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_passfindpanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_passfindpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		passfindpanel.setLayout(gbl_passfindpanel);
		
		JLabel lblNewLabel_4 = new JLabel("비밀번호 찾기");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(FontList.default20);
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		passfindpanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_2_2 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_2_2 = new GridBagConstraints();
		gbc_lblNewLabel_2_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2_2.gridx = 0;
		gbc_lblNewLabel_2_2.gridy = 1;
		passfindpanel.add(lblNewLabel_2_2, gbc_lblNewLabel_2_2);
		
		JLabel lblNewLabel_5 = new JLabel("ID");
		lblNewLabel_5.setFont(FontList.default12);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 2;
		passfindpanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		idField = new JTextField();
		idField.setFont(FontList.default14);
		GridBagConstraints gbc_idField = new GridBagConstraints();
		gbc_idField.insets = new Insets(0, 0, 5, 0);
		gbc_idField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idField.gridx = 0;
		gbc_idField.gridy = 3;
		passfindpanel.add(idField, gbc_idField);
		idField.setColumns(10);
		
		JLabel lblNewLabel_2_2_1 = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel_2_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2_2_1.gridx = 0;
		gbc_lblNewLabel_2_2_1.gridy = 4;
		passfindpanel.add(lblNewLabel_2_2_1, gbc_lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_6 = new JLabel("생년월일 (ex.20210102)");
		lblNewLabel_6.setFont(FontList.default12);
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 5;
		passfindpanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		pbirthField = new JTextField();
		pbirthField.setFont(FontList.default14);
		GridBagConstraints gbc_pbirthField = new GridBagConstraints();
		gbc_pbirthField.insets = new Insets(0, 0, 5, 0);
		gbc_pbirthField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pbirthField.gridx = 0;
		gbc_pbirthField.gridy = 6;
		passfindpanel.add(pbirthField, gbc_pbirthField);
		pbirthField.setColumns(10);
		
		passfindButton = new JRButton("인증");
		passfindButton.setBackground(Colors.gray200);
		passfindButton.setFont(FontList.default12);
		GridBagConstraints gbc_passfindButton = new GridBagConstraints();
		gbc_passfindButton.insets = new Insets(0, 0, 5, 0);
		gbc_passfindButton.gridx = 0;
		gbc_passfindButton.gridy = 7;
		passfindpanel.add(passfindButton, gbc_passfindButton);
		passfindButton.addActionListener(new findAction());
		
		lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setFont(FontList.default14);
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 8;
		passfindpanel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JButton btnNewButton = new JRButton("확인");
		btnNewButton.setBackground(Colors.gray200);
		btnNewButton.setFont(FontList.default14);
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		btnNewButton.addActionListener(new exitAction());
		
		dbms=DB_Manager.getInstance();
		setVisible(true);
	}
	class SendId implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			LoginFrame.setID(resultLabel.getText());
		}
		
	}
	class findAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton ev=(JButton)e.getSource();
			if(ev.equals(idfindButton)) {
				String result = dbms.FindId(nameField.getText(),birthField.getText() );
				resultLabel.setText(result);
				if(!result.isEmpty()) {
					copyButton.setEnabled(true);
					
				}
			}else if(ev.equals(passfindButton)) {
				String result = dbms.FindPwd(idField.getText(), pbirthField.getText());
				lblNewLabel_7.setText(result);
			}
		}
	}
	class exitAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
}
