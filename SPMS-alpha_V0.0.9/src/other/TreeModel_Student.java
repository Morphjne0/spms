package other;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.sun.org.apache.xpath.internal.operations.Equals;

import pages.PasswordChanger;
import pages.TimePanel;
import pages.student.AddLecturePanel;
import pages.student.FindLecturepanel;
import pages.student.NextScorePanel;

public class TreeModel_Student extends DefaultTreeModel {

	public TreeModel_Student() {
		super(new TreeNode_Student());
	}
	public static JPanel getRequest(DefaultMutableTreeNode request) {
		return TreeNode_Student.getRequest(request);
	}
	
}

class TreeNode_Student extends DefaultMutableTreeNode {

	//수강메뉴 관련 노드
	static DefaultMutableTreeNode lectureNode;
	static DefaultMutableTreeNode add_lec_Node;
	static DefaultMutableTreeNode find_lec_Node;
	
	static DefaultMutableTreeNode scoreNode;
	static DefaultMutableTreeNode nowScoreNode;
	
	static DefaultMutableTreeNode mypage;
	static DefaultMutableTreeNode changePassword;

	public TreeNode_Student() {
		
		super("StudentMenu");
		
		//수강메뉴 관련
		lectureNode = new DefaultMutableTreeNode("수강메뉴");
		add_lec_Node= new DefaultMutableTreeNode("수강신청");
		lectureNode.add(add_lec_Node);
		find_lec_Node = new DefaultMutableTreeNode("개설강좌검색");
		lectureNode.add(find_lec_Node);
		add(lectureNode);
		
		//학점관련
		scoreNode = new DefaultMutableTreeNode("학점메뉴");
		nowScoreNode = new DefaultMutableTreeNode("학점조회");
		scoreNode.add(nowScoreNode);
		add(scoreNode);
		
		mypage = new DefaultMutableTreeNode("마이페이지");
		changePassword = new DefaultMutableTreeNode("비밀번호 변경");
		mypage.add(changePassword);
		add(mypage);
		
		
	}
	
	public static JPanel getRequest(DefaultMutableTreeNode request) {
		if(request.equals(add_lec_Node)) {
			return new AddLecturePanel();
		}else if (request.equals(find_lec_Node)) {
			return new FindLecturepanel();
		}else if (request.equals(nowScoreNode)) {
			return new NextScorePanel();
		}else if (request.equals(changePassword)) {
			return new PasswordChanger();
		}else {
			return new TimePanel();
		}
	}

}
