package dataObject;

public class Semester {
	public static String _1_1 ="1학년 1학기";
	public static String _1_2 ="1학년 2학기";
	public static String _2_1 ="2학년 1학기";
	public static String _2_2 ="2학년 2학기";
	
	public static String[] GetAll() {
		
		String[] SemesterString = {_1_1,_1_2,_2_1,_2_2};
		return SemesterString;
	}
}
