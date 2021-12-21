package pages.admin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
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
public class stdManageJDialog extends JDialog implements ActionListener {

	boolean flag = false;

	JTextField stuID;
	JTextField stuName;
	// JTextField depName;
	JTextField stuGrade;
	JTextField stuBirth;

	JComboBox departmentBox;

	JButton confirm;

	// stdManageDAO dao =new stdManageDAO();//UserDefaultJTableDAO = deptManageDAO

	public stdManageJDialog(String index) {
		JPanel pw = new JPanel(new GridLayout(5, 1));// 가로1칸 세로 7칸, 레이블과 텍스트필드 순으로 붙을 페널
		JPanel pc = new JPanel(new GridLayout(5, 1));// 가로1칸 세로 7칸, 텍스트필드 붙을 페널
		JPanel ps = new JPanel();
		JPanel dcCkP = new JPanel(new BorderLayout());// 학과번호 중복체크 패널
		JLabel lable_sC = new JLabel("학생ID");
		lable_sC.setFont(FontList.default14);
		JLabel lable_sN = new JLabel("학생이름");
		lable_sN.setFont(FontList.default14);
		JLabel lable_dN = new JLabel("학과명");
		lable_dN.setFont(FontList.default14);
		JLabel lable_sG = new JLabel("학년");
		lable_sG.setFont(FontList.default14);
		// JLabel lable_sS=new JLabel("성별");
		// JLabel lable_sNu=new JLabel("전화번호");
		JLabel lable_sB = new JLabel("생년월일");
		lable_sB.setFont(FontList.default14);

		stuID = new JTextField();
		stuID.setFont(FontList.default14);
		stuName = new JTextField();
		stuName.setFont(FontList.default14);
		// depName = new JTextField();
		stuGrade = new JTextField();
		stuGrade.setFont(FontList.default14);
		// JTextField stuSex=new JTextField();
		// JTextField stuNumber=new JTextField();
		stuBirth = new JTextField();
		stuBirth.setFont(FontList.default14);
		
		departmentBox = new JComboBox(DB_Manager.getdeparts());
		departmentBox.setFont(FontList.default14);
		if(departmentBox.getItemCount()==0) {
			messageBox(this, "등록된 학과가 없습니다!\n학과생성후 다시진행해주십시요.");
			dispose();
		}
		// 확인 버튼

		JButton reset = new JRButton("취소");
		reset.setBackground(Colors.red);
		reset.setFont(FontList.default14);
		JButton dcCkBtn = new JRButton("중복체크");
		dcCkBtn.setBackground(Colors.blue);
		dcCkBtn.setFont(FontList.bold14);

		if (index.equals("학생등록")) {
			confirm = new JRButton("학생등록");
		} else {
			confirm = new JRButton("학생정보수정");
			
			/*
			 * //text박스에 선택된 레코드의 정보 넣기 int row = sm.table.getSelectedRow();//선택된 행
			 * 
			 * stuID.setText( sm.table.getValueAt(row, 0).toString() ); stuName.setText(
			 * sm.table.getValueAt(row, 1).toString() ); depName.setText(
			 * sm.table.getValueAt(row, 2).toString() ); stuGrade.setText(
			 * sm.table.getValueAt(row, 3).toString() ); //stuSex.setText(
			 * sm.table.getValueAt(row, 4).toString() ); //stuNumber.setText(
			 * sm.table.getValueAt(row, 5).toString() ); stuBirth.setText(
			 * sm.table.getValueAt(row, 6).toString() );
			 */

			// jt= table로 본 페널에서 만든 테이블

			// 학과번호 text박스 비활성
			stuID.setEditable(false);

			// 학과번호Check버튼 비활성화
			dcCkBtn.setEnabled(false);
		}
		confirm.setBackground(Colors.blue);
		confirm.setFont(FontList.default14);
		// Label추가부분
		// pw :1열 7행 라벨붙을 패널
		pw.add(lable_sC);// 학생번호 라벨
		pw.add(lable_sN);// 학생이름
		pw.add(lable_dN);// 학과명
		pw.add(lable_sG);// 학년
		// pw.add(lable_sS);//성별
		// pw.add(lable_sNu);//전화번호
		pw.add(lable_sB);// 생년월일
		dcCkP.add(stuID, "Center");// dcCkP패널에 교수번호 텍스트필드와 체크버튼 부착
		dcCkP.add(dcCkBtn, "East");
		// TextField 추가

		// pc :1열 4행 텍스트필드 페널
		pc.add(dcCkP);

		pc.add(stuName);
		pc.add(departmentBox);
		// pc.add(depName);
		pc.add(stuGrade);

		// pc.add(stuSex);
		// pc.add(stuNumber);
		pc.add(stuBirth);

		ps.add(confirm);

		ps.add(reset);

		getContentPane().add(pw, "West");
		getContentPane().add(pc, "Center");
		getContentPane().add(ps, "South");
		
		JLabel lblNewLabel = new JLabel("학생 에디터");
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
		stuID.addFocusListener(new FocusAdapter() {
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

		if (btnLabel.equals("학생등록")) {
			Person p = new Person(stuID.getText(), stuName.getText(), stuBirth.getText(),
					departmentBox.getSelectedItem().toString(), "S");
			p.setGrade(Integer.parseInt(stuGrade.getText()));
			if (!flag) {
				messageBox(this, "중복체크가 진행되어있지 않습니다!");
				stuID.requestFocus();// 포커스이동
				return;
			}
			if (DB_Manager.registMember(p) > 0) {// 가입성공//반환형이 int

				messageBox(this, "학생번호: " + stuID.getText() + "가 생성되었습니다");
				dispose();// JDialog 창닫기
				StudentManagePanel.setList(DB_Manager.getmemberlist("S"));

			} else {// 가입실패
				messageBox(this, "학생정보가 등록되지 않았습니다.");
			}

		} else if (btnLabel.equals("학생정보수정")) {
			Person p = new Person(stuID.getText(), stuName.getText(), stuBirth.getText(),
					departmentBox.getSelectedItem().toString(), "S");
			p.setGrade(Integer.parseInt(stuGrade.getText()));
			if (DB_Manager.StudentUpdate(p) > 0) {
				messageBox(this, "학생정보가 수정완료되었습니다.");
				dispose();
				StudentManagePanel.setList(DB_Manager.getmemberlist("S"));

			} else {
				messageBox(this, "학생정보가 수정되지 않았습니다.");
			}

		} else if (btnLabel.equals("취소")) {
			dispose();

		} else if (btnLabel.equals("중복체크")) {
			// id텍스트박스에 값 없으면 메세지 출력 있으면 DB연동한다.
			if (stuID.getText().trim().equals("")) {
				messageBox(this, "학생번호를 입력해주세요.");
				stuID.requestFocus();// 포커스이동
			} else {

				if (DB_Manager.getIdByCheck(stuID.getText(), "member", "id")) { // 중복아니다.(사용가능)
					messageBox(this, stuID.getText() + "는 사용가능합니다.");
					flag = true;
				} else { // 중복이다.
					messageBox(this, stuID.getText() + "는 중복입니다.");

					stuID.setText("");// text박스지우기
					stuID.requestFocus();// 커서놓기
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
		stuID.setText(p.getId());
		stuName.setText(p.getName());
		stuBirth.setText(p.getBirth());
		for(int i=0;i<departmentBox.getItemCount();i++) {
		if(departmentBox.getItemAt(i).equals(p.getDepart())){
			departmentBox.setSelectedIndex(i);
		}
		}
		//depName.setText(p.getDepart());
		stuGrade.setText(Integer.toString(p.getGrade()));
	}
}// 클래스끝
