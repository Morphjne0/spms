package pages.admin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataObject.Department;
import managers.DB_Manager;
import other.FontList;
import javax.swing.SwingConstants;

//다이얼로그 클래스겸 DVO
public class deptManageJDialog extends JDialog implements ActionListener {

	boolean flag = false;

	
	JTextField deptCode;
	JTextField deptName;
	JTextField repProf;
	JTextField stuCount;

	JButton confirm;

	

	//DeptManagePanel dm;// MenuJTabaleExam = deptManage_Test, me =dm

	

	

	// deptManageDAO dao =new deptManageDAO();//UserDefaultJTableDAO = deptManageDAO

	public deptManageJDialog(String index) {

		JPanel dcCkP = new JPanel(new BorderLayout());// 학과번호 중복체크 패널
		JPanel pw = new JPanel(new GridLayout(4, 1));
		JPanel pc = new JPanel(new GridLayout(4, 1));
		JPanel ps = new JPanel();

		JLabel lable_dC = new JLabel("학과번호");
		lable_dC.setFont(FontList.default14);
		JLabel lable_dN = new JLabel("학과명");
		lable_dN.setFont(FontList.default14);
		JLabel lable_rP = new JLabel("대표교수");
		lable_rP.setFont(FontList.default14);
		JLabel lable_sC = new JLabel("학생수");
		lable_sC.setFont(FontList.default14);

		deptCode = new JTextField();
		deptCode.setFont(FontList.default14);
		deptName = new JTextField();
		deptName.setFont(FontList.default14);
		repProf = new JTextField();
		repProf.setFont(FontList.default14);
		stuCount = new JTextField();
		stuCount.setFont(FontList.default14);
		
		JButton reset = new JButton("취소");
		reset.setFont(FontList.default14);
		JButton dcCkBtn = new JButton("중복체크");
		dcCkBtn.setFont(FontList.bold14);
		
		if (index.equals("학과생성")) {
			confirm = new JButton("학과생성");

		} else {
			confirm = new JButton("학과수정");

			// text박스에 선택된 레코드의 정보 넣기
			/*
			 * int row = dm.table.getSelectedRow();//선택된 행 deptCode.setText(
			 * dm.table.getValueAt(row, 0).toString() ); deptName.setText(
			 * dm.table.getValueAt(row, 1).toString() ); repProf.setText(
			 * dm.table.getValueAt(row, 2).toString() ); stuCount.setText(
			 * dm.table.getValueAt(row, 3).toString() );//int?
			 */

			// jt= table로 본 페널에서 만든 테이블
			// 학과번호 text박스 비활성
			deptCode.setEditable(false);

			// 학과번호Check버튼 비활성화
			dcCkBtn.setEnabled(false);
		}

		// Label추가부분
		pw.add(lable_dC);// 학과번호 라벨
		pw.add(lable_dN);// 학과명
		pw.add(lable_rP);// 대표교수
		pw.add(lable_sC);// 학생수

		dcCkP.add(deptCode, "Center");
		dcCkP.add(dcCkBtn, "East");

		// TextField 추가
		pc.add(dcCkP);
		pc.add(deptName);
		pc.add(repProf);
		pc.add(stuCount);

		ps.add(confirm);

		ps.add(reset);

		getContentPane().add(pw, "West");
		getContentPane().add(pc, "Center");
		getContentPane().add(ps, "South");
		
		JLabel lblNewLabel = new JLabel("학과 에디터");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(FontList.bold20);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		setSize(300, 250);
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// 이벤트등록
		confirm.addActionListener(this); // 가입/수정 이벤트등록
		reset.addActionListener(this); // 취소 이벤트등록
		dcCkBtn.addActionListener(this);// 학과번호 중복체크 이벤트 등록
		deptCode.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			// TODO 자동 생성된 메소드 스텁
			flag=false;
		}
		});
	}// 생성자끝

	/**
	 * 가입/수정/삭제 기능에 대한 부분
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String btnLabel = e.getActionCommand();// 이벤트주체 대한 Label 가져오기

		if (btnLabel.equals("학과생성")) {
			if (!flag) {
				messageBox(this, "중복체크를 진행해주십시요.");
				deptCode.requestFocus();// 포커스이동
				return;
			}
			Department d = new Department(deptCode.getText(), deptName.getText(), repProf.getText(),
					Integer.parseInt(stuCount.getText()));
			if (DB_Manager.deptInsert(d) > 0) {// 가입성공//반환형이 int
				messageBox(this, "학과번호: " + deptCode.getText() + "가 생성되었습니다");
				dispose();// JDialog 창닫기
				DeptManagePanel.setList(DB_Manager.getdepartlist());
			} else {// 가입실패
				messageBox(this, "학과가 생성되지 않았습니다.");
			}

		} else if (btnLabel.equals("학과수정")) {
			Department d = new Department(deptCode.getText(), deptName.getText(), repProf.getText(),
					Integer.parseInt(stuCount.getText()));
			if (DB_Manager.deptUpdate(d) > 0) {
				messageBox(this, "학과정보가 수정완료되었습니다.");
				dispose();
				DeptManagePanel.setList(DB_Manager.getdepartlist());
			} else {
				messageBox(this, "학과정보가 수정되지 않았습니다.");
			}

		} else if (btnLabel.equals("취소")) {
			dispose();

		} else if (btnLabel.equals("중복체크")) {
			// id텍스트박스에 값 없으면 메세지 출력 있으면 DB연동한다.
			if (deptCode.getText().isBlank()) {
				messageBox(this, "학과번호를 입력해주세요.");
				deptCode.requestFocus();// 포커스이동
			} else {
				if (DB_Manager.getIdByCheck(deptCode.getText(),"department","departid")) { // 중복아니다.(사용가능)
					messageBox(this, deptCode.getText() + "는 사용가능합니다.");
					flag = true;
				} else { // 중복이다.
					messageBox(this, deptCode.getText() + "는 중복입니다.");
					deptCode.setText("");// text박스지우기
					deptCode.requestFocus();// 커서놓기
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

	public void setField(Department d) {
		deptCode.setText(d.getDepartId());
		deptName.setText(d.getDepartName());
		repProf.setText(d.getDepartRepresent());
		stuCount.setText(Integer.toString(d.getStudents()));
	}
}// 클래스끝
