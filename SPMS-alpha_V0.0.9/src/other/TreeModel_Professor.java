package other;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import pages.PasswordChanger;
import pages.TimePanel;
import pages.admin.DeptManagePanel;
import pages.admin.ProfessorManagePanel;
import pages.admin.StudentManagePanel;
import pages.professor.lectureProf;
import pages.professor.scoreProf;

public class TreeModel_Professor extends DefaultTreeModel {

	public TreeModel_Professor() {
		// TODO �ڵ� ������ ������ ����
		super(new TreeNode_Professor());
	}
	public static JPanel getRequest(DefaultMutableTreeNode request) {
		return TreeNode_Professor.getRequest(request);
	}
}

class TreeNode_Professor extends DefaultMutableTreeNode {

	//�����޴� ���� ���
	static DefaultMutableTreeNode ranagemantNode;
	static  DefaultMutableTreeNode add_lec_Node;
	
	static DefaultMutableTreeNode scoreNode;
	static DefaultMutableTreeNode rejectStudentNode;
	
	static DefaultMutableTreeNode mypage;
	static DefaultMutableTreeNode changePassword;

	public TreeNode_Professor() {
		// TODO �ڵ� ������ ������ ����
		super("ProfessorMenu");
		
		//�����޴� ����
		ranagemantNode = new DefaultMutableTreeNode("강의관련메뉴");
		add_lec_Node= new DefaultMutableTreeNode("강의관리");
		ranagemantNode.add(add_lec_Node);
		scoreNode = new DefaultMutableTreeNode("학점부여");
		ranagemantNode.add(scoreNode);
		add(ranagemantNode);
		/*
		rejectStudentNode = new DefaultMutableTreeNode("학생제적조회");
		add(rejectStudentNode);
		*/
		mypage = new DefaultMutableTreeNode("마이페이지");
		changePassword = new DefaultMutableTreeNode("비밀번호 변경");
		mypage.add(changePassword);
		add(mypage);
	}

	public static JPanel getRequest(DefaultMutableTreeNode request) {
		if(request.equals(add_lec_Node)) {
			return new lectureProf();
		}else if (request.equals(scoreNode)) {
			return new scoreProf();
		}else if (request.equals(changePassword)) {
			return new PasswordChanger();
		}else{
			return new TimePanel();
		}
	}
}
