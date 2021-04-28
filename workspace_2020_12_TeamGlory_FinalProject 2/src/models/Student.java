package models;

import java.util.Date;

public class Student extends Person {
	private int studentID;
	private int grade;
	private Date registrationDay;
	private int Hib;
	private int DTap;
	private int Pollio;
	private int HepatitisB;
	private boolean MMR;
	private boolean Varicella;
	
	public Student() {
		
	}
	public Student(String name,int age,int grade,int hib, int dTap,int pollio,int hepatitisB, boolean mMR,boolean varicella) {
		super(name,age);
		this.grade = grade;
		this.Hib = hib;
		this.DTap = dTap;
		this.Pollio=pollio;
		this.HepatitisB = hepatitisB;
		this.MMR = mMR;
		this.Varicella = varicella;
	}
	
	public Student(int studentID,String name,int age,int grade,Date registrationDay, int hib, int dTap,int pollio,int hepatitisB, boolean mMR,
			boolean varicella) {
		super(name,age);
		this.studentID = studentID;
		this.grade = grade;
		this.registrationDay = registrationDay;
		this.Hib = hib;
		this.DTap = dTap;
		this.Pollio=pollio;
		this.HepatitisB = hepatitisB;
		this.MMR = mMR;
		this.Varicella = varicella;
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
	
	public int getHib() {
		return Hib;
	}
	public void setHib(int hib) {
		Hib = hib;
	}
	public int getDTap() {
		return DTap;
	}
	public void setDTap(int dTap) {
		DTap = dTap;
	}
	
	public int getPollio() {
		return Pollio;
	}
	public void setPollio(int pollio) {
		Pollio = pollio;
	}
	public int getHepatitisB() {
		return HepatitisB;
	}
	public void setHepatitisB(int hepatitisB) {
		HepatitisB = hepatitisB;
	}
	public boolean isMMR() {
		return MMR;
	}
	public void setMMR(boolean mMR) {
		MMR = mMR;
	}
	public boolean isVaricella() {
		return Varicella;
	}
	public void setVaricella(boolean varicella) {
		Varicella = varicella;
	}
	@Override
	public String toString() {
		return studentID+"";
	}
	
}
