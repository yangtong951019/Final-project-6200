package models;

public class Student extends Person {
	private int studentID;
	private int grade;
	
	public Student() {
		
	}
	public Student(String name,int age,int grade) {
		super(name,age);
		this.grade = grade;
	}
	
	public Student(int studentID,String name,int age,int grade) {
		super(name,age);
		this.studentID = studentID;
		this.grade = grade;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return studentID+"";
	}
	
}
