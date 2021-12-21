package other;

import javax.swing.JOptionPane;

public class OptionDialog {

	static OptionDialog instance = null;

	OptionDialog() {
		System.out.println("POPUP_Manager, Start.");
	}

	private String[] yn = { "예", "아니요" };
	private String[] msgs = { "종료하시겠습니까?", "해당학생을 제적하시겠습니까?", "해당학생을 제적에서 제외 하시겠습니까?", "해당정보를 삭제하시겠습니까?" };
	private String[] titles = { "SPMS 종료", "직원정보 등록", "직원정보 수정", "직원정보 삭제" };
	private String[] alertMsg = { "로그인할 내용이 일부가 틀립니다.\n확인해보시고 다시입력해보십시요.", "귀하는 현재 재적상태로 등록되어있습니다.\n학교의 행정실에 연락하십시요.","현재 로그인된 계정의 비밀번호가 아닙니다.","변경할 비밀번호가 서로 일치 하지 않습니다.","비밀번호 체크가 진행 되어있지 않습니다."};

	private String[] infoMsg = { "작업이 취소 되었습니다.", "정보가 등록 되었습니다.", "정보가 수정 되었습니다.", "정보가 삭제 되었습니다.","체크가 진행되었습니다." };

	public int question(int option) {
		int result = -1;
		if (option < 10) {
			result = JOptionPane.showOptionDialog(null, msgs[option], titles[option], JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, yn, yn[0]);
		}
		return result;
	}

	public void infomation(int option) {
		JOptionPane.showMessageDialog(null, infoMsg[option], "알림", JOptionPane.INFORMATION_MESSAGE);
	}

	public void alert(int option) {
		JOptionPane.showMessageDialog(null, alertMsg[option], "경고", JOptionPane.WARNING_MESSAGE);
	}

	public void error(String message) {
		JOptionPane.showConfirmDialog(null, message, "오류", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
	}

	public String checkPass() {
		String pass = JOptionPane.showInputDialog(null, "본 작업은 암호입력을 한번 더 요구하는 작업입니다.\n암호를 입력해주십시요.", "관리자 확인",
				JOptionPane.QUESTION_MESSAGE);
		try {
			pass.equals(null);
		} catch (NullPointerException e) {
			pass = "";
		}
		return pass;

	}

	public static OptionDialog getInstance() {
		if (instance == null) {
			instance = new OptionDialog();
		}
		return instance;
	}
}