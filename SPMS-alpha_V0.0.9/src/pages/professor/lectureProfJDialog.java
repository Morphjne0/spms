package pages.professor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataObject.Department;
import dataObject.Lecture;
import dataObject.Person;
import dataObject.Semester;
import managers.DB_Manager;
import other.Colors;
import other.FontList;
import other.JRButton;
import javax.swing.SwingConstants;

//다이얼로그 클래스겸 DVO
public class lectureProfJDialog extends JDialog implements ActionListener {

	JPanel pw = new JPanel(new GridLayout(5, 1));// 가로1칸 세로 5칸, 레이블과 텍스트필드 순으로 붙을 페널
	JPanel pc = new JPanel(new GridLayout(5, 1));// 가로1칸 세로 5칸, 텍스트필드 붙을 페널
	JPanel ps = new JPanel();
	
	// lec_Sem
	JTextField lec_Name = new JTextField();// 강의명
	// JTextField Professor=new JTextField();//담당교수
	JTextField lec_Date = new JTextField();// 수강요일
	JTextField lec_Type = new JTextField();// 강좌구분
	JTextField lec_Score = new JTextField();// 수강학점
	//JTextField lec_Sem = new JTextField();// 학기
	JComboBox lecsem = new JComboBox(Semester.GetAll());
	
	JButton confirm;// 확인 버튼

	JButton reset = new JRButton("취소");
	

	lectureProf lp;// MenuJTabaleExam = profManage, me =pm//메인화면 정보
	private int lec_id;
	private String profname;
	private String depart;
	

	// lectureProfDAO dao =new lectureProfDAO();//UserDefaultJTableDAO =
	// deptManageDAO

	public lectureProfJDialog(String index) {

		JLabel titleLabel = new JLabel("강의 에디터");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(FontList.bold20);
		JLabel lable_LN = new JLabel("강의명");
		lable_LN.setFont(FontList.default14);
		JLabel lable_LD = new JLabel("수강요일/시간");// lec_Date
		lable_LD.setFont(FontList.default14);
		JLabel lable_LT = new JLabel("강좌구분");
		lable_LT.setFont(FontList.default14);
		JLabel lable_LS = new JLabel("수강학점");
		lable_LS.setFont(FontList.default14);
		JLabel lable_LSem = new JLabel("학기");
		lable_LSem.setFont(FontList.default14);
		
		
		lec_Name.setFont(FontList.default14);
		lec_Date.setFont(FontList.default14);
		lec_Type.setFont(FontList.default14);
		lec_Score.setFont(FontList.default14);
		lecsem.setFont(FontList.default14);
		
		reset.setFont(FontList.default14);
		reset.setBackground(Colors.blue);
		
		// super(lp,"다이어로그");

		if (index.equals("강좌등록")) {
			confirm = new JRButton("강좌등록");

		} else {
			confirm = new JRButton("강좌정보수정");

			// text박스에 선택된 레코드의 정보 넣기
			int row = lp.table.getSelectedRow();// 선택된 행

			// 학과번호 text박스 비활성
		}
		confirm.setBackground(Colors.blue);
		confirm.setFont(FontList.default14);
		pw.add(lable_LN);
		pw.add(lable_LD);// 강의시간

		pw.add(lable_LT);// 강의구분

		pw.add(lable_LS);// 수강학점
		pw.add(lable_LSem);

		pc.add(lec_Name);

		// pc.add(Professor);
		pc.add(lec_Date);
		pc.add(lec_Type);
		pc.add(lec_Score);
		pc.add(lecsem);

		ps.add(confirm);

		ps.add(reset);

		getContentPane().add(pw, "West");
		getContentPane().add(pc, "Center");
		getContentPane().add(ps, "South");
		
		getContentPane().add(titleLabel, BorderLayout.NORTH);

		setSize(300, 250);// 300,250
		setVisible(true);
		setResizable(false);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// 이벤트등록
		confirm.addActionListener(this); // 가입/수정 이벤트등록
		reset.addActionListener(this); // 취소 이벤트등록

	}// 생성자끝

	/**
     * 가입/수정/삭제 기능에 대한 부분
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
    	
       String btnLabel =e.getActionCommand();//이벤트주체 대한 Label 가져오기
       
       if(btnLabel.equals("강좌등록")){
    	   Lecture d = new Lecture(-1, lec_Name.getText(), depart, profname, lec_Date.getText(), lec_Type.getText(), Integer.parseInt(lec_Score.getText()), lecsem.getSelectedItem().toString());
           if(DB_Manager.lecInsert(d) > 0 ){//가입성공//반환형이 int
        	   
               messageBox(this ,"강의가 생성되었습니다");
               dispose();//JDialog 창닫기
               lectureProf.setList();
               
           }else{//가입실패
               messageBox(this,"강좌정보가 등록되지 않았습니다.");
           }
           
       }else if(btnLabel.equals("강좌정보수정")){
           Lecture d = new Lecture(lec_id, lec_Name.getText(), depart, profname, lec_Date.getText(), lec_Type.getText(), Integer.parseInt(lec_Score.getText()), lecsem.getSelectedItem().toString());
            if(DB_Manager.lecUpdate(d) > 0){
                messageBox(this, "강좌정보가 수정완료되었습니다.");
                dispose();
                lectureProf.setList();
               
            }else{
                messageBox(this, "강좌정보가 수정되지 않았습니다.");
            }
           
           
           
       }else if(btnLabel.equals("취소")){
           dispose();
           
           
       }
       
       
    }// actionPerformed끝

	public void setField(Lecture d) {
		lec_id = d.getId();
		profname = d.getProfessor();
		lec_Name.setText(d.getLec_Name());
		lec_Date.setText(d.getLec_date());
		lec_Type.setText(d.getLec_type());
		lec_Score.setText(Integer.toString(d.getLec_score()));// Int값이지만 텍스트박스는 String값이므로
		lecsem.setSelectedItem(d.getLec_semester());
	}
	public void addmode(Person p) {
		profname=p.getName();
		depart=p.getDepart();
	}

	/**
	 * 메시지박스 띄워주는 메소드 작성
	 */
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}

}// 클래스끝
