package pages.admin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataObject.Person;

import managers.DB_Manager;
import other.Colors;
import other.FontList;
import other.JRButton;
import javax.swing.SwingConstants;

//다이얼로그 클래스겸 DVO
public class profManageJDialog extends JDialog implements ActionListener {

	boolean flag = false;

	JTextField idField;
	JTextField nameField;
	// JTextField department;
	// JTextField profPosition=new JTextField();
	// JTextField profSex=new JTextField();
	// JTextField profNumber=new JTextField();
	JTextField profBirth;

	JComboBox departmentBox;

	JButton confirm;// 확인 버튼

	// profManageDAO dao =new profManageDAO();//UserDefaultJTableDAO = deptManageDAO

	public profManageJDialog(String index) {

		JPanel pw = new JPanel(new GridLayout(4, 1));// 가로2칸 세로 4칸, 레이블과 텍스트필드 순으로 붙을 페널
		JPanel pc = new JPanel(new GridLayout(4, 1));// 가로1칸 세로 4칸, 텍스트필드 붙을 페널
		JPanel ps = new JPanel();
		JPanel dcCkP = new JPanel(new BorderLayout());// 학과번호 중복체크 패널

		JLabel lable_pC = new JLabel("교수번호");
		lable_pC.setFont(FontList.default14);
		JLabel lable_pN = new JLabel("교수이름");
		lable_pN.setFont(FontList.default14);
		JLabel lable_dN = new JLabel("학과명");
		lable_dN.setFont(FontList.default14);
		// JLabel lable_pP= new JLabel("교수직급");
		// JLabel lable_pS=new JLabel("성별");
		// JLabel lable_pNu=new JLabel("전화번호");
		JLabel lable_pB = new JLabel("생년월일");
		lable_pB.setFont(FontList.default14);

		idField = new JTextField();
		idField.setFont(FontList.default14);
		nameField = new JTextField();
		nameField.setFont(FontList.default14);
		// department=new JTextField();
		// JTextField profPosition=new JTextField();
		// JTextField profSex=new JTextField();
		// JTextField profNumber=new JTextField();
		profBirth = new JTextField();
		profBirth.setFont(FontList.default14);

		JButton reset = new JRButton("취소");
		reset.setBackground(Colors.red);
		reset.setFont(FontList.default14);
		JButton dcCkBtn = new JRButton("중복체크");
		dcCkBtn.setBackground(Colors.blue);
		dcCkBtn.setFont(FontList.bold14);

		if (index.equals("교수등록")) {
			confirm = new JRButton("교수등록");
		} else {
			confirm = new JRButton("교수정보수정");
			
			/*
			 * //text박스에 선택된 레코드의 정보 넣기 int row = pm.table.getSelectedRow();//선택된 행
			 * 
			 * idField.setText( pm.table.getValueAt(row, 0).toString() ); nameField.setText(
			 * pm.table.getValueAt(row, 1).toString() ); department.setText(
			 * pm.table.getValueAt(row, 2).toString() ); profPosition.setText(
			 * pm.table.getValueAt(row, 3).toString() ); profSex.setText(
			 * pm.table.getValueAt(row, 4).toString() ); profNumber.setText(
			 * pm.table.getValueAt(row, 5).toString() ); profBirth.setText(
			 * pm.table.getValueAt(row, 6).toString() );
			 */

			// jt= table로 본 페널에서 만든 테이블

			// 학과번호 text박스 비활성
			idField.setEditable(false);

			// 학과번호Check버튼 비활성화
			dcCkBtn.setEnabled(false);
		}
		confirm.setBackground(Colors.blue);
		confirm.setFont(FontList.default14);

		// Label추가부분
		// pw :가로 2칸 세로 4칸 라벨, 텍스트필드 페널
		pw.add(lable_pC);// 교수번호 라벨

		pw.add(lable_pN);// 교수이름
		pw.add(lable_pB);// 생년월일
		pw.add(lable_dN);// 학과명

		// pw.add(lable_pP);//교수직급

		// pw.add(lable_pS);//성별
		// pw.add(lable_pNu);//전화번호
		dcCkP.add(idField, "Center");// dcCkP패널에 교수번호 텍스트필드와 체크버튼 부착
		dcCkP.add(dcCkBtn, "East");

		// TextField 추가

		// pc :가로 1칸 세로 4칸 텍스트필드 페널
		pc.add(dcCkP);
		pc.add(nameField);
		pc.add(profBirth);

		// pc.add(profPosition);
		// pc.add(profSex);
		// pc.add(profNumber);

		departmentBox = new JComboBox(DB_Manager.getdeparts());
		departmentBox.setFont(FontList.default14);
		if (departmentBox.getItemCount() == 0) {
			messageBox(this, "등록된 학과가 없습니다!\n학과생성후 다시진행해주십시요.");
			dispose();
		}
		pc.add(departmentBox);
		ps.add(confirm);
		ps.add(reset);

		getContentPane().add(pw, "West");
		getContentPane().add(pc, "Center");
		getContentPane().add(ps, "South");
		
		JLabel lblNewLabel = new JLabel("교수 에디터");
		lblNewLabel.setFont(FontList.bold20);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		setSize(300, 250);// 300,250
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// 이벤트등록
		confirm.addActionListener(this); // 가입/수정 이벤트등록
		reset.addActionListener(this); // 취소 이벤트등록
		dcCkBtn.addActionListener(this);// 학과번호 중복체크 이벤트 등록
		idField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO 자동 생성된 메소드 스텁
				flag = false;
			}
		});

	}// 생성자끝

	/**
	 * 가입/수정/삭제 기능에 대한 부분
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String btnLabel = e.getActionCommand();// 이벤트주체 대한 Label 가져오기

		if (btnLabel.equals("교수등록")) {
			Person p = new Person(idField.getText(), nameField.getText(), profBirth.getText(),
					departmentBox.getSelectedItem().toString(), "P");
			if (!flag) {
				messageBox(this, "중복체크가 진행되어있지 않습니다!");
				idField.requestFocus();
				return;
			}
			if (DB_Manager.registMember(p) > 0) {// 가입성공//반환형이 int

				messageBox(this, "교수번호: " + idField.getText() + "가 생성되었습니다");
				dispose();// JDialog 창닫기
				ProfessorManagePanel.setList(DB_Manager.getmemberlist("P"));

			} else {// 가입실패
				messageBox(this, "교수정보가 등록되지 않았습니다.");
			}

		} else if (btnLabel.equals("교수정보수정")) {
			Person p = new Person(idField.getText(), nameField.getText(), profBirth.getText(),
					departmentBox.getSelectedItem().toString(), "P");
			if (DB_Manager.profUpdate(p) > 0) {
				messageBox(this, "교수정보가 수정완료되었습니다.");
				dispose();
				ProfessorManagePanel.setList(DB_Manager.getmemberlist("P"));

			} else {
				messageBox(this, "교수정보가 수정되지 않았습니다.");
			}

		} else if (btnLabel.equals("취소")) {
			dispose();

		} else if (btnLabel.equals("중복체크")) {
			// id텍스트박스에 값 없으면 메세지 출력 있으면 DB연동한다.
			if (idField.getText().trim().equals("")) {
				messageBox(this, "교수번호를 입력해주세요.");
				idField.requestFocus();// 포커스이동
			} else {

				if (DB_Manager.getIdByCheck(idField.getText(), "member", "id")) { // 중복아니다.(사용가능)
					messageBox(this, idField.getText() + "는 사용가능합니다.");
					flag = true;
				} else { // 중복이다.
					messageBox(this, idField.getText() + "는 중복입니다.");
					idField.setText("");// text박스지우기
					idField.requestFocus();// 커서놓기
				}

			}

		}

	}// actionPerformed끝

	/**
	 * 메시지박스 띄워주는 메소드 작성
	 */
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}

	public void setField(Person p) {
		// TODO 자동 생성된 메소드 스텁
		idField.setText(p.getId());
		nameField.setText(p.getName());
		profBirth.setText(p.getBirth());
		for (int i = 0; i < departmentBox.getItemCount(); i++) {
			if (departmentBox.getItemAt(i).equals(p.getDepart())) {
				departmentBox.setSelectedIndex(i);
			}
		}
	}

}// 클래스끝
