package dataObject;

public class Lecture {
	int id;
	String lec_Name;
	String depart;
	String professor;
	String lec_date;
	String lec_type;
	int lec_score;
	String lec_semester;
	
	public Lecture(int id,String lec_Name,String depart,String professer,String lec_date,String lec_type,int lec_score,String lec_semester) {
		this.id = id;
		this.lec_Name = lec_Name;
		this.depart = depart;
		this.professor = professer;
		this.lec_date = lec_date;
		this.lec_type = lec_type;
		this.lec_score = lec_score;
		this.lec_semester = lec_semester;
	}

	public String getLec_semester() {
		return lec_semester;
	}

	public void setLec_semester(String lec_semester) {
		this.lec_semester = lec_semester;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLec_Name() {
		return lec_Name;
	}

	public void setLec_Name(String lec_Name) {
		this.lec_Name = lec_Name;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getLec_date() {
		return lec_date;
	}

	public void setLec_date(String lec_date) {
		this.lec_date = lec_date;
	}

	public String getLec_type() {
		return lec_type;
	}

	public void setLec_type(String lec_type) {
		this.lec_type = lec_type;
	}

	public int getLec_score() {
		return lec_score;
	}

	public void setLec_score(int lec_score) {
		this.lec_score = lec_score;
	}


	

}
