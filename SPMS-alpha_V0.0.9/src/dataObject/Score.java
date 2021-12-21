package dataObject;

public class Score {

	String std_id;
	String lec_name;
	String std_name;
	int score;
	String semester;
	String p_name;
	int lec_score;
	ScoreRank rank;
	
	//학생 강의등록용
	public Score(String lec_name,String p_name,int lec_score, int score ) {
		// TODO �ڵ� ������ ������ ����
		this.lec_score = lec_score;
		this.lec_name = lec_name;
		this.p_name = p_name;
		this.score=score;
	}
	
	//교수학점부여용
	public Score(String std_id,String std_name,String lec_name,int score){
		this.std_id= std_id;
		this.std_name=std_name;
		this.lec_name=lec_name;
		this.score=score;
	}
	
	public int getLec_score() {
		return lec_score;
	}

	public void setLec_score(int lec_score) {
		this.lec_score = lec_score;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getStd_id() {
		return std_id;
	}

	public void setStd_id(String std_id) {
		this.std_id = std_id;
	}

	public String getLec_name() {
		return lec_name;
	}

	public void setLec_name(String lec_name) {
		this.lec_name = lec_name;
	}

	public String getStd_name() {
		return std_name;
	}

	public void setStd_name(String std_name) {
		this.std_name = std_name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ScoreRank getRank() {
		return rank;
	}

	public void setRank(ScoreRank rank) {
		this.rank = rank;
	}


	

}
