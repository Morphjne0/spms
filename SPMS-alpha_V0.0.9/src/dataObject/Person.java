package dataObject;

public class Person {

	String name;
	String pwd;
	String id;
	String birth;
	String depart;
	String type;
	int grade=0;
	
	

	public Person(String id,String name,String pwd,String birth,String depart,String type) {
		// TODO �ڵ� ������ ������ ����
		this.id=id;
		this.name=name;
		this.pwd=pwd;
		this.birth=birth;
		this.depart=depart;
		this.type=type;
	}
	
	public Person(String id,String name,String birth,String depart,String type) {
		// TODO �ڵ� ������ ������ ����
		this.id=id;
		this.name=name;
		this.birth=birth;
		this.depart=depart;
		this.type=type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getBirth() {
		return birth;
	}

	public void setBrith(String birth) {
		this.birth = birth;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
