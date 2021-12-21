package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataObject.Person;
import managers.DB_Manager;
import other.Colors;
import other.FontList;
import other.JRButton;
import other.OptionDialog;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	static private JTextField idField;
	private DB_Manager dbms;
	OptionDialog op;
	private JPasswordField passwordField;

	static File cache;
	Scanner scan;
	static HashMap<String, String> cacheMap;
	static FileWriter fw = null;
	static PrintWriter pw = null;

	JCheckBox idsBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("SPMS-alpha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel caption = new JLabel("team spms");
		caption.setFont(FontList.default12);
		contentPane.add(caption, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("image");
		lblNewLabel.setFont(FontList.default14);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 358, 0 };
		gbl_panel_1.rowHeights = new int[] { 99, 99, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 5, 10, 5));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_1.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 58, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(FontList.default14);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		idField = new JTextField();
		idField.setFont(FontList.default14);
		idField.setColumns(10);
		GridBagConstraints gbc_idField = new GridBagConstraints();
		gbc_idField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idField.insets = new Insets(0, 0, 5, 0);
		gbc_idField.gridx = 1;
		gbc_idField.gridy = 0;
		panel.add(idField, gbc_idField);

		JLabel lblNewLabel_2 = new JLabel("PASS");
		lblNewLabel_2.setFont(FontList.default14);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setFont(FontList.default14);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		panel.add(passwordField, gbc_passwordField);

		idsBox = new JCheckBox("아이디 기억");
		idsBox.setFont(FontList.bold12);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 2;
		panel.add(idsBox, gbc_chckbxNewCheckBox);

		JButton btnNewButton_1 = new JRButton("로그인에 문제가 있습니까?");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setFont(FontList.bold12);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 3;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.addActionListener(new LoginRebuildAction());

		JButton loginButton = new JRButton("login");
		loginButton.setBackground(Colors.blue);
		loginButton.setFont(FontList.default20);
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.fill = GridBagConstraints.BOTH;
		gbc_loginButton.gridx = 0;
		gbc_loginButton.gridy = 1;
		panel_1.add(loginButton, gbc_loginButton);
		loginButton.addActionListener(new loginAction());

		setVisible(true);
		op = OptionDialog.getInstance();
		dbms = DB_Manager.getInstance();
		cacheMap = new HashMap<String, String>();

		try {
			File folder = new File("data");
			if (!folder.exists()) {
				folder.mkdir();
			}
			cache = new File("data/cache.txt");
			scan = new Scanner(cache);
			System.out.println("cacheFile detected.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			if (cache.canRead()) {
				System.out.println("data exists.");
				int data_index = 0;
				while (scan.hasNextLine()) {
					String buf = scan.nextLine();
					System.out.println("read: " + buf);
					String[] buff = buf.split("`");
					System.out.println("key: " + buff[0] + ", value: " + buff[1]);
					cacheMap.put(buff[0], buff[1]);
					System.out.println("cache data" + data_index + " readed.");
					data_index++;
				}
			}
			scan.close();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (cacheMap.get("LOADSTAT").equals("true")) {
			try {
				idField.setText(cacheMap.get("ID"));
				idsBox.setSelected(true);
			} catch (NullPointerException e) {
				// TODO: handle exception
				e.printStackTrace();
				idField.setText("");
			}
		}
	}

	public static void setCache(String key, String value) {
		try {
			if (fw == null) {
				fw = new FileWriter(cache, false);
			}
			if (pw == null) {
				pw = new PrintWriter(fw);
			}
			if (pw != null) {
				pw.println(key + "`" + value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
		}
	}

	public static void settingEnd() {
		try {
			pw.flush();
			fw.flush();
			pw = null;
			fw = null;
			System.out.println("cache record end.");
		} catch (NullPointerException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO �ڵ� ������ catch ���
			e.printStackTrace();
		}
	}

	class loginAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Person user = dbms.connect(idField.getText(), new String(passwordField.getPassword()));
				if (user == null) {
					op.alert(0);
					return;
				}
				if(user.getType().equals("SR")) {
					op.alert(1);
					return;
				}
				if (idsBox.isSelected()) {
					setCache("ID", idField.getText());
					setCache("LOADSTAT", "true");	
				}else {
					setCache("LOADSTAT", "false");
				}
				settingEnd();
				new MainFrame();
				MainFrame.setUser(user);
				dispose();
			} catch (NullPointerException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
	}

	class LoginRebuildAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new LoginRebuildFrame();
		}
	}

	public static void setID(String text) {
		// TODO 자동 생성된 메소드 스텁
		idField.setText(text);
	}

}
