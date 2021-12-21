package other;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import pages.TimePanel;
import pages.admin.DeptManagePanel;
import pages.admin.ProfessorManagePanel;
import pages.admin.StudentManagePanel;

public class TreeModel_Admin extends DefaultTreeModel {

	public TreeModel_Admin() {
		super(new TreeNode_Admin());
	}
	public static JPanel getRequest(DefaultMutableTreeNode request) {
		return TreeNode_Admin.getRequest(request);
	}
}

class TreeNode_Admin extends DefaultMutableTreeNode {

	static DefaultMutableTreeNode MemberManageNode;
	static DefaultMutableTreeNode std_manage_Node;
	static DefaultMutableTreeNode prof_manage_Node;

	static DefaultMutableTreeNode schoolManageNode;
	static DefaultMutableTreeNode departManageNode;

	public TreeNode_Admin() {
		// TODO 자동 생성된 생성자 스텁
		super("AdminMenu");
		// 맴버관리 메뉴
		MemberManageNode = new DefaultMutableTreeNode("맴버관리");
		std_manage_Node = new DefaultMutableTreeNode("학생맴버");
		MemberManageNode.add(std_manage_Node);
		prof_manage_Node = new DefaultMutableTreeNode("교수맴버");
		MemberManageNode.add(prof_manage_Node);
		add(MemberManageNode);

		// 학사관리 메뉴
		schoolManageNode = new DefaultMutableTreeNode("학사관리");
		departManageNode = new DefaultMutableTreeNode("학과관리");
		schoolManageNode.add(departManageNode);
		add(schoolManageNode);
	}

	public static JPanel getRequest(DefaultMutableTreeNode request) {
		if(request.equals(std_manage_Node)) {
			return new StudentManagePanel();
		}else if (request.equals(prof_manage_Node)) {
			return new ProfessorManagePanel();
		}else if (request.equals(departManageNode)) {
			return new DeptManagePanel();
		}else {
			return new TimePanel();
		}
	}
}
