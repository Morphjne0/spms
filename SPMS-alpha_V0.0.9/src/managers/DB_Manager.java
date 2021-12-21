package managers;

import java.lang.reflect.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;

import dataObject.Department;
import dataObject.Lecture;
import dataObject.Person;
import dataObject.Score;
import other.OptionDialog;

public class DB_Manager {

	final String Addrass = "jdbc:mysql://localhost:3306/spmsdb?serverTimezone=Asia/Seoul";
	final String ID = "root";
	final String Pass = "0000";

	OptionDialog op;
	static Connection connect;
	static Statement statement;
	static ResultSet resultSet;
	static PreparedStatement pstmt;
	String password;
	static DB_Manager instance = null;

	public DB_Manager() {
		op = OptionDialog.getInstance();
		// TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
		try {
			System.out.println("DB_manager, start.");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(Addrass, ID, Pass);
			statement = connect.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占�
			e.printStackTrace();
			System.exit(-1);
		} catch (SQLException e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

	public Person connect(String ID, String Pass) {
		/*
		 * if(ID.equals("ST04")&&Pass.equals("0000")) { return new Person(ID, "홍占썸동",
		 * Pass, 20010101, "Unknown","S"); }
		 */
		try {
			String sql = "select * from member where id='" + ID + "'";
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			Person loginObject = new Person(resultSet.getString("id"), resultSet.getString("name"),
					resultSet.getString("pwd"), resultSet.getString("birthday"), resultSet.getString("depart"),
					resultSet.getString("type"));
			if (loginObject.getPwd().equals(Pass)) {
				return loginObject;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占�
			e.printStackTrace();
			return null;
		}

	}

	public String FindId(String name, String birth) {

		String sql = "select * from member where name = ? and birthday = ?";

		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birth);

			resultSet = pstmt.executeQuery();

			resultSet.next();
			return resultSet.getString("id");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public String FindPwd(String id, String birth) {

		String sql = "select * from member where id = ? and birthday = ?";

		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, birth);

			resultSet = pstmt.executeQuery();
			resultSet.next();
			return resultSet.getString("pwd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static ArrayList<Lecture> FindEnrollment(String id, String semester) {
		ArrayList<Lecture> list = new ArrayList<Lecture>();

		String sql = "SELECT * FROM spmsdb.lecture WHERE (lec_Name,lec_semester) IN ( SELECT lec_Name,semester FROM scoredata WHERE std_id=? AND semester=? ORDER BY num desc)";

		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, semester);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Lecture Obj = new Lecture(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7),
						resultSet.getString(8));
				list.add(Obj);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Lecture> FindLec(String depart, String lec_semester) {
		ArrayList<Lecture> list = new ArrayList<Lecture>();

		String sql = "select * from lecture where depart = ? and lec_semester = ?";

		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, depart);
			pstmt.setString(2, lec_semester);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Lecture Obj = new Lecture(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7),
						resultSet.getString(8));
				list.add(Obj);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public int Enrollment(String std_id, String lec_name, String std_name, String semester, String p_name,
			int lec_score) {
		String sql = "insert into scoredata (num,std_id,lec_name,std_name,score,semester,p_name,lec_score) values(null,?,?,?,0,?,?,?)";
		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, std_id);
			pstmt.setString(2, lec_name);
			pstmt.setString(3, std_name);
			pstmt.setString(4, semester);
			pstmt.setString(5, p_name);
			pstmt.setInt(6, lec_score);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Integer) null;
		}

	}

	public int DeleteEnrollment(String std_id, String lec_name) {

		String sql = "delete from scoredata where  std_id = ? and lec_name = ? ";
		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, std_id);
			pstmt.setString(2, lec_name);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	public static ArrayList<Score> FindScore(String std_id, String semester) {
		ArrayList<Score> list = new ArrayList<Score>();

		String sql = "select * from scoredata where std_id = ? and semester = ?";

		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, std_id);
			pstmt.setString(2, semester);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Score Obj = new Score(resultSet.getString(3), resultSet.getString(7), resultSet.getInt(8),
						resultSet.getInt(5));
				list.add(Obj);

			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 援먯닔�쟾�슜

	public void setScore(ArrayList<Score> list) {
		// TODO �옄�룞 �깮�꽦�맂 硫붿냼�뱶 �뒪�뀅
		String sql = "update scoredata set score= ? where std_id= ? and std_name = ? and lec_name = ? ";
		for (Score sv : list) {
			try {
				pstmt = connect.prepareStatement(sql);
				pstmt.setInt(1, sv.getScore());
				pstmt.setString(2, sv.getStd_id());
				pstmt.setString(3, sv.getStd_name());
				pstmt.setString(4, sv.getLec_name());

				pstmt.executeUpdate();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		op.infomation(1);
	}

	public ArrayList<Score> getscoreList(String name) {
		// TODO �옄�룞 �깮�꽦�맂 硫붿냼�뱶 �뒪�뀅
		ArrayList<Score> list = new ArrayList<Score>();
		String sql = "select * from scoredata where p_name=? order by lec_name";
		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, name);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Score s = new Score(resultSet.getString("std_id"), resultSet.getString("std_name"),
						resultSet.getString("lec_name"), resultSet.getInt("score"));
				list.add(s);
			}
			return list;
		} catch (SQLException e) {
			// TODO �옄�룞 �깮�꽦�맂 catch 釉붾줉
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<String> getUserslectures(String name, String depart) {
		// TODO �옄�룞 �깮�꽦�맂 硫붿냼�뱶 �뒪�뀅
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select lec_Name from lecture where professor=? and depart=?";
		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, depart);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString("lec_Name"));
			}
			return list;
		} catch (SQLException e) {
			// TODO �옄�룞 �깮�꽦�맂 catch 釉붾줉
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Score> SearchscoreList(String lecture, String name) {
		ArrayList<Score> list = new ArrayList<Score>();
		String sql = "select * from scoredata where lec_name=? and p_name=?";
		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, lecture);
			pstmt.setString(2, name);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Score s = new Score(resultSet.getString("std_id"), resultSet.getString("std_name"),
						resultSet.getString("lec_name"), resultSet.getInt("score"));
				list.add(s);
			}
			return list;
		} catch (SQLException e) {
			// TODO �옄�룞 �깮�꽦�맂 catch 釉붾줉
			e.printStackTrace();
			return null;
		}

	}

	// 援먯닔, 媛뺤쓽�슜

	public static ArrayList<Lecture> GetUsersLec(String depart, String name) {
		ArrayList<Lecture> list = new ArrayList<Lecture>();

		String sql = "select * from lecture where depart = ? and professor = ?";

		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, depart);
			pstmt.setString(2, name);

			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Lecture Obj = new Lecture(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7),
						resultSet.getString(8));
				list.add(Obj);
			}
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static int lecInsert(Lecture d) {// �겢�옒�뒪�뿉 ���옣�맂 �뜲�씠�꽣瑜� sql臾몄쓣 �씠�슜�빐 �뜲�씠�꽣踰좎씠�뒪 �뀒�씠釉붿뿉 �엯�젰

		int result = 0;
		String sql = "insert into lecture values(null,?,?,?,?,?,?,?)";

		try {
			pstmt = connect.prepareStatement(sql);// �룞�쟻 SqL�씠�땲源�

			pstmt.setString(1, d.getLec_Name());// �븰�깮�젙蹂� �떎�씠�뼹濡쒓렇�뿉�꽌 D.B�뿉 �꽔�쓣�닔 �엳�룄濡� 媛��졇�삩�떎.
			pstmt.setString(2, d.getDepart());
			pstmt.setString(3, d.getProfessor());
			pstmt.setString(4, d.getLec_date());
			pstmt.setString(5, d.getLec_type());
			pstmt.setInt(6, d.getLec_score());
			pstmt.setString(7, d.getLec_semester());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public static int lecUpdate(Lecture d) {
		int result = 0;
		String sql = "UPDATE lecture SET lec_name=?, depart=?, lec_date=?, lec_type=?, lec_score=?, lec_semester=? WHERE id=? and professor=? ";

		try {
			pstmt = connect.prepareStatement(sql);
			// ?�쓽 �닚�꽌��濡� 媛� �꽔湲�
			pstmt.setString(1, d.getLec_Name());
			pstmt.setString(2, d.getDepart());
			pstmt.setString(3, d.getLec_date());
			pstmt.setString(4, d.getLec_type());
			pstmt.setInt(5, d.getLec_score());
			pstmt.setString(6, d.getLec_semester());
			pstmt.setInt(7, d.getId());
			pstmt.setString(8, d.getProfessor());

			// �떎�뻾�븯湲�
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> profUpdate fail");
		}
		return result;
	}

	public static int lecDelete(int id) {
		int result = 0;
		try {
			pstmt = connect.prepareStatement("delete from lecture where id = ? ");
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> stdDelete fail");
		}

		return result;
	}

	// 愿�由ъ옄 �쟾�슜

	public static boolean getIdByCheck(String id, String table, String idtype) {
		boolean result = true;
		String sql = "SELECT * FROM " + table + " WHERE " + idtype + "=?";
		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, id);
			resultSet = pstmt.executeQuery();
			if (resultSet.next())
				result = false; // �젅肄붾뱶媛� 議댁옱�븯硫� false
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public static Vector<String> getdeparts() {
		// TODO �옄�룞 �깮�꽦�맂 硫붿냼�뱶 �뒪�뀅
		Vector<String> list = new Vector<String>();
		String sql = "select departname from department order by departid desc";
		try {
			pstmt = connect.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString(1));
			}
			return list;
		} catch (SQLException e) {
			// TODO �옄�룞 �깮�꽦�맂 catch 釉붾줉
			e.printStackTrace();
			return null;
		}

	}

	public static int deptInsert(Department d) {// �겢�옒�뒪�뿉 ���옣�맂 �뜲�씠�꽣瑜� sql臾몄쓣 �씠�슜�빐 �뜲�씠�꽣踰좎씠�뒪 �뀒�씠釉붿뿉 �엯�젰

		int result = 0;
		String sql = "insert into department values(?,?,?,?)";

		try {
			pstmt = connect.prepareStatement(sql);// �룞�쟻 SqL�씠�땲源�

			pstmt.setString(1, d.getDepartId());// setString: �닽�옄�뒗 泥ル쾲夷� ? , �꽔�쓣 媛�
			pstmt.setString(2, d.getDepartName());// �븰怨쇱젙蹂� �겢�옒�뒪�뿉�꽌 D.B�뿉 �꽔�쓣�닔 �엳�룄濡� 媛��졇�삩�떎.
			pstmt.setString(3, d.getDepartRepresent());
			pstmt.setInt(4, d.getStudents());// �떎�씠�뼹濡쒓렇�뿉�꽌 媛��졇�삩 String���엯 �뜲�씠�꽣瑜� int�삎�쑝濡� 媛뺤젣 �삎蹂��솚

			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public static int deptUpdate(Department d) {
		int result = 0;
		String sql = "UPDATE department SET departname=?, represent=? , students=? WHERE departid=?";

		try {
			pstmt = connect.prepareStatement(sql);
			// ?�쓽 �닚�꽌��濡� 媛� �꽔湲�
			pstmt.setString(1, d.getDepartName());
			pstmt.setString(2, d.getDepartRepresent());
			pstmt.setInt(3, d.getStudents());
			pstmt.setString(4, d.getDepartId());
			// �떎�뻾�븯湲�
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> userUpdate fail");
		}
		return result;
	}

	public static int deptDelete(String departid) {
		int result = 0;
		try {
			pstmt = connect.prepareStatement("delete from department where departid = ? ");
			pstmt.setString(1, departid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e + "=> userDelete fail");
		}

		return result;
	}

	public static ArrayList<Department> getdepartlist() {
		// TODO �옄�룞 �깮�꽦�맂 硫붿냼�뱶 �뒪�뀅
		ArrayList<Department> list = new ArrayList<Department>();
		try {
			resultSet = statement.executeQuery("select * from department order by departid");
			while (resultSet.next()) {
				Department d = new Department(resultSet.getString("departid"), resultSet.getString("departname"),
						resultSet.getString("represent"), resultSet.getInt("students"));
				list.add(d);
			}
		} catch (SQLException e) {
			// TODO �옄�룞 �깮�꽦�맂 catch 釉붾줉
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<Department> departSearch(String command) {
		// TODO �옄�룞 �깮�꽦�맂 硫붿냼�뱶 �뒪�뀅
		String adjustString = "%" + command + "%";
		ArrayList<Department> list = new ArrayList<Department>();
		try {
			String sql = "select * from department where departid like ? or departname like ? or represent like ? or students like ?";
			pstmt = connect.prepareStatement(sql);
			for (int i = 1; i <= 4; i++) {
				pstmt.setString(i, adjustString);
			}
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Department d = new Department(resultSet.getString("departid"), resultSet.getString("departname"),
						resultSet.getString("represent"), resultSet.getInt("students"));
				list.add(d);
			}
		} catch (SQLException e) {
			// TODO �옄�룞 �깮�꽦�맂 catch 釉붾줉
			e.printStackTrace();
		}
		return list;
	}

	// �븰�깮愿�由ш퀎�뿴

	public static int StudentUpdate(Person p) {
		int result = 0;
		String sql = "UPDATE member SET name=?, depart=?, birthday=?, grade=? WHERE id=?";
		try {
			pstmt = connect.prepareStatement(sql);
			// ?�쓽 �닚�꽌��濡� 媛� �꽔湲�
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getDepart());
			pstmt.setString(3, p.getBirth());
			pstmt.setInt(4, p.getGrade());
			pstmt.setString(5, p.getId());
			// �떎�뻾�븯湲�
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> profUpdate fail");
		}
		return result;
	}

	public static void rejectStudent(String id) {
		String sql = "UPDATE member SET type='SR' WHERE id=?";
		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO �옄�룞 �깮�꽦�맂 catch 釉붾줉
			e.printStackTrace();
		}
	}

	public static void unrejectStudent(String id) {
		String sql = "UPDATE member SET type='S' WHERE id=?";
		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO �옄�룞 �깮�꽦�맂 catch 釉붾줉
			e.printStackTrace();
		}
	}

	// 援먯닔愿�由ш퀎�뿴

	public static int profUpdate(Person p) {
		int result = 0;
		String sql = "UPDATE member SET name=?, depart=?, birthday=? WHERE id=?";

		try {
			pstmt = connect.prepareStatement(sql);
			// ?�쓽 �닚�꽌��濡� 媛� �꽔湲�
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getDepart());
			pstmt.setString(3, p.getBirth());
			pstmt.setString(4, p.getId());
			// �떎�뻾�븯湲�
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> profUpdate fail");
		}
		return result;
	}

	// 怨듯넻愿�由ш퀎�뿴 �븰�깮諛� 援먯닔

	public static ArrayList<Person> getmemberlist(String type) {
		ArrayList<Person> list = new ArrayList<Person>();
		try {
			String sql = "select * from member where type='" + type + "'";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Person obj = new Person(resultSet.getString("id"), resultSet.getString("name"),
						resultSet.getString("pwd"), resultSet.getString("birthday"), resultSet.getString("depart"),
						resultSet.getString("type"));
				if (obj.getType().equals("S") || obj.getType().equals("SR")) {
					obj.setGrade(resultSet.getInt("grade"));
				}
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			// TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占�
			e.printStackTrace();
			return list;
		}

	}

	public static ArrayList<Person> memberSearch(String command, String type) {
		String adjustString = "%" + command + "%";
		ArrayList<Person> list = new ArrayList<Person>();
		String sql = "select * from member where type='" + type
				+ "' and id=(select id from member where id like ? or birthday like ? or name like ? or depart like ?)";
		try {
			pstmt = connect.prepareStatement(sql);
			for (int i = 1; i < 5; i++) {
				pstmt.setString(i, adjustString);
			}
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Person obj = new Person(resultSet.getString("id"), resultSet.getString("name"),
						resultSet.getString("pwd"), resultSet.getString("birthday"), resultSet.getString("depart"),
						resultSet.getString("type"));
				if (obj.getType().equals("S") || obj.getType().equals("SR")) {
					obj.setGrade(resultSet.getInt("grade"));
				}
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			// TODO �옄�룞 �깮�꽦�맂 catch 釉붾줉
			e.printStackTrace();
			return list;
		}

	}

	public static int registMember(Person p) {// �겢�옒�뒪�뿉 ���옣�맂 �뜲�씠�꽣瑜� sql臾몄쓣 �씠�슜�빐 �뜲�씠�꽣踰좎씠�뒪 �뀒�씠釉붿뿉 �엯�젰

		int result = 0;
		String sql = "insert into member values(?,?,?,?,?,?,?)";

		try {
			pstmt = connect.prepareStatement(sql);// �룞�쟻 SqL�씠�땲源�

			pstmt.setString(1, p.getId());// setString: �닽�옄�뒗 泥ル쾲夷� ? , �꽔�쓣 媛�
			pstmt.setString(2, p.getType());// 援먯닔�젙蹂� �떎�씠�뼹濡쒓렇�뿉�꽌 D.B�뿉 �꽔�쓣�닔 �엳�룄濡� 媛��졇�삩�떎.
			pstmt.setString(3, p.getBirth());
			pstmt.setString(4, p.getBirth());
			pstmt.setString(5, p.getName());
			pstmt.setString(6, p.getDepart());
			if (p.getGrade() == 0) {
				pstmt.setNull(7, Types.INTEGER);
			} else {
				pstmt.setInt(7, p.getGrade());
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static int DeleteMember(String id) {
		int result = 0;
		try {
			pstmt = connect.prepareStatement("delete from member where id = ? ");
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e + "=> Delete fail");
		}
		return result;
	}
	
	public static void ChangePwd(String id,String pwd) {
		String sql ="update member set pwd = ? where id = ?";
		try {
			pstmt = connect.prepareStatement(sql);
			pstmt.setString(1,pwd);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 占쏙옙占쏙옙占쏙옙 占쌨소듸옙
	/*
	 * //占쏙옙占쏙옙 占쏙옙占쏙옙 public void NewEmp(Person obj) { if(obj instanceof Student) {
	 * String sql = "Insert Into employee values(upper('" + em.getId() + "'), '" +
	 * em.getName() + "', '" + em.getGender() + "', '" + em.getAge() + "' ,'" +
	 * em.getDept() + "', '" + em.getRank() + "');"; }else if(obj instanceof
	 * Professor) {
	 * 
	 * }else if(obj instanceof Admin) {
	 * 
	 * } String sql = "Insert Into employee values(upper('" + em.getId() + "'), '" +
	 * em.getName() + "', '" + em.getGender() + "', '" + em.getAge() + "' ,'" +
	 * em.getDept() + "', '" + em.getRank() + "');";
	 * 
	 * try { statement.executeUpdate(sql); op.infomation(1); } catch (SQLException
	 * e) { e.printStackTrace(); op.alert(2); }
	 * 
	 * }
	 * 
	 * //占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 public void editEmp(String id, String name, String dept,
	 * String rank) { String
	 * sql="update employee set name='"+name+"' , dept='"+dept+"' , erank='"
	 * +rank+"' where enumber='"+id+"'"; try { statement.execute(sql);
	 * op.infomation(2); } catch (SQLException e) { // TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占�
	 * e.printStackTrace(); op.alert(3); }
	 * 
	 * }
	 * 
	 * //占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 public void delete(String id) { String sql =
	 * "delete from employee where enumber='" + id + "'"; try {
	 * statement.executeUpdate(sql); op.infomation(3); } catch (SQLException e) { //
	 * TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占� e.printStackTrace(); op.alert(4); } }
	 * 
	 * //占쏙옙체占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占싱븝옙 占쏙옙占쏙옙 占쏙옙占쏙옙 public Vector<Person> getList_students()
	 * { Vector<Person> elist = new Vector<Person>(); try { String sql =
	 * "select * from person where "; resultSet = statement.executeQuery(sql); while
	 * (resultSet.next()) { String id = resultSet.getString("enumber"); String name
	 * = resultSet.getString("name"); String gender = resultSet.getString("gender");
	 * String dept = resultSet.getString("dept"); String rank =
	 * resultSet.getString("erank"); String age = resultSet.getString("age");
	 * 
	 * Person em = new Employees(id, name, gender, age, dept, rank); elist.add(em);
	 * 
	 * } } catch (SQLException e) { e.printStackTrace(); } return elist; }
	 * 
	 * //占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占싱븝옙 占쏙옙占쏙옙 占쏙옙占쏙옙 public Vector<Employees>
	 * getSelected_Employees(String F_name) { Vector<Employees> elist = new
	 * Vector<Employees>(); try { String sql =
	 * "select * from employee where name like '%" + F_name + "%'"; resultSet =
	 * statement.executeQuery(sql); while (resultSet.next()) { String id =
	 * resultSet.getString("enumber"); String name = resultSet.getString("name");
	 * String gender = resultSet.getString("gender"); String dept =
	 * resultSet.getString("dept"); String rank = resultSet.getString("erank");
	 * String age = resultSet.getString("age");
	 * 
	 * Employees em = new Employees(id, name, gender, age, dept, rank);
	 * elist.add(em);
	 * 
	 * } } catch (SQLException e) { e.printStackTrace(); } return elist; }
	 * 
	 * //占시곤옙占쏙옙占쏙옙占쏙옙 占쌨소듸옙
	 * 
	 * public void update_time(String id,String srt,String ert, int selectedIndex) {
	 * String sql="update rush set sRush='"+srt+"', eRush='"+ert+"' where enumber='"
	 * +id+"' and num="+selectedIndex; try { statement.execute(sql); } catch
	 * (SQLException e) { // TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占� e.printStackTrace(); } }
	 * 
	 * //占썩본占쏙옙占쏙옙 占시곤옙占쏙옙占쏙옙占쏙옙 占쏙옙占싱븝옙 占쏙옙占쏙옙 占쏙옙占쏙옙 public Vector<TimeStamp>
	 * getList_TimeStamps() { Vector<TimeStamp> tlist = new Vector<TimeStamp>();
	 * String sql =
	 * "select rush.*,employee.name from rush inner join employee on rush.enumber=employee.enumber order by rush.num desc"
	 * ; try { resultSet=statement.executeQuery(sql); while (resultSet.next()) { int
	 * num=resultSet.getInt("num"); String id=resultSet.getString("enumber"); String
	 * name=resultSet.getString("name"); Date date=resultSet.getDate("date"); String
	 * srt=resultSet.getString("sRush"); String ert=resultSet.getString("eRush");
	 * int wage=resultSet.getInt("workondays"); TimeStamp ts=new TimeStamp(num, id,
	 * name, date, srt, ert, wage); tlist.add(ts); } } catch (SQLException e) { //
	 * TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占� e.printStackTrace(); } return tlist; }
	 * 
	 * //占쏙옙占쏙옙占쏙옙占쏙옙 占시곤옙占쏙옙占쏙옙占쏙옙 占쏙옙占싱븝옙 占쏙옙占쏙옙占쏙옙占쏙옙 public Vector<TimeStamp>
	 * getSelected_TimeStamps(String target) { Vector<TimeStamp> tlist = new
	 * Vector<TimeStamp>(); String sql =
	 * "select rush.*,employee.name from rush inner join employee on rush.enumber=employee.enumber where name like '%"
	 * +target+"%' or rush.enumber like upper('%"+target+"%')"; try {
	 * resultSet=statement.executeQuery(sql); while (resultSet.next()) { int
	 * num=resultSet.getInt("num"); String id=resultSet.getString("enumber"); String
	 * name=resultSet.getString("name"); Date date=resultSet.getDate("date"); String
	 * srt=resultSet.getString("sRush"); String ert=resultSet.getString("eRush");
	 * int wage=resultSet.getInt("workondays"); TimeStamp ts=new TimeStamp(num, id,
	 * name, date, srt, ert, wage); tlist.add(ts); } } catch (SQLException e) { //
	 * TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占� e.printStackTrace(); } return tlist; }
	 * 
	 * //占쏙옙占쏙옙 占쌨울옙 占쏙옙占쏙옙占쏙옙 占쌨소듸옙 public Vector<salary> getSelected_Salarys(String
	 * target) { Vector<salary> slist = new Vector<salary>(); String sql =
	 * "select * from salary where enumber='"+target+"' order by paid_date desc";
	 * try { resultSet=statement.executeQuery(sql); while (resultSet.next()) { int
	 * num=resultSet.getInt("num"); String id=resultSet.getString("enumber"); Date
	 * date=resultSet.getDate("paid_date"); int wd=resultSet.getInt("workeddays");
	 * String salary=resultSet.getString("paid_salary");
	 * 
	 * salary s=new salary(num, id, date, wd, salary); slist.add(s); } } catch
	 * (SQLException e) { // TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占� e.printStackTrace(); } return
	 * slist; }
	 * 
	 * public Vector<salary> getSelected_Salarys(String target,String option) {
	 * Vector<salary> slist = new Vector<salary>(); String sql =
	 * "select * from salary where enumber='"+target+"' and (paid_date like '%"
	 * +option+"%' or num like '%"+option+"%')"; try {
	 * resultSet=statement.executeQuery(sql); while (resultSet.next()) { int
	 * num=resultSet.getInt("num"); String id=resultSet.getString("enumber"); Date
	 * date=resultSet.getDate("paid_date"); int wd=resultSet.getInt("workeddays");
	 * String salary=resultSet.getString("paid_salary");
	 * 
	 * salary s=new salary(num, id, date, wd, salary); slist.add(s); } } catch
	 * (SQLException e) { // TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占� e.printStackTrace(); } return
	 * slist; }
	 * 
	 * public String[] SalaryInitalize(String target) { String[] data = new
	 * String[2]; String chack=""; String
	 * sql="select enumber,name,erank from employee where enumber=upper('"+target+
	 * "')"; try { resultSet=statement.executeQuery(sql); resultSet.next();
	 * chack=resultSet.getString("enumber"); if(chack.equals(target)) {
	 * data[0]=resultSet.getString("name"); System.out.println(data[0]);
	 * data[1]=resultSet.getString("erank"); System.out.println(data[1]); return
	 * data; }else { return data; } } catch (SQLException e) { // TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙
	 * catch 占쏙옙占� e.printStackTrace(); return data; }
	 * 
	 * 
	 * }
	 * 
	 * public int getWorkOnDays(String target) { String
	 * sql="select workondays from rush where enumber=upper('"
	 * +target+"') and date <=LAST_DAY(now() - interval 1 month) order by rush.date desc limit 1"
	 * ; try { resultSet=statement.executeQuery(sql); resultSet.next(); return
	 * resultSet.getInt("workondays");
	 * 
	 * } catch (SQLException e) { // TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占� e.printStackTrace();
	 * return -1; } }
	 * 
	 * public void newSalarys(String id,String workondays,String paidSalary) {
	 * String
	 * sql="insert into salary values(null,'"+id+"',curdate(),"+Integer.parseInt(
	 * workondays)+",'"+paidSalary+"')"; try { statement.executeUpdate(sql);
	 * op.infomation(1); } catch (SQLException e) { // TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占�
	 * e.printStackTrace(); op.alert(2); }
	 * 
	 * }
	 */
	// 占쏙옙타 占쌨소듸옙

	public Date getDate() {
		String sql = "select curdate()";
		try {
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				return resultSet.getDate(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占�
			e.printStackTrace();
			return null;
		}
	}

	public void exit() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (SQLException e) {
			// TODO 占쌘듸옙 占쏙옙占쏙옙占쏙옙 catch 占쏙옙占�
			e.printStackTrace();
		} catch (NullPointerException e) {
		}
	}

	public static DB_Manager getInstance() {
		if (instance == null) {
			instance = new DB_Manager();
		}
		return instance;
	}

}
