package dataObject;

public class Department {

	String departId;
	String departName;
	String departRepresent;
	int students;

	public Department(String departId, String departName, String departRepresent, int students) {
		this.departId = departId;
		this.departName = departName;
		this.departRepresent = departRepresent;
		this.students = students;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getDepartRepresent() {
		return departRepresent;
	}

	public void setDepartRepresent(String departRepresent) {
		this.departRepresent = departRepresent;
	}

	public int getStudents() {
		return students;
	}

	public void setStudents(int students) {
		this.students = students;
	}
	

}
