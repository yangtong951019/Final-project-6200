package models;

import java.util.Date;

public class Student extends Person {
	private int studentID;
	private int grade;
	private Date registrationDay;
	
	public Student() {
		
	}
	public Student(String name,int age,int grade) {
		super(name,age);
		this.grade = grade;
	}
	
	public Student(int studentID,String name,int age,int grade,Date registrationDay) {
		super(name,age);
		this.studentID = studentID;
		this.grade = grade;
		this.registrationDay=registrationDay;
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
	
	public Date getRegistrationDay() {
		return registrationDay;
	}
	public void setRegistrationDay(Date registrationDay) {
		this.registrationDay = registrationDay;
	}
	@Override
	public String toString() {
		return studentID+"";
	}
	
}
