package models;

public class Teacher extends Person{

	private int teacherID;
	private int credits;
	private int groupID;
	
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public Teacher() {
		
	}
	public Teacher(int teacherID,String name,int age,int credits) {
		super(name, age);
		this.teacherID = teacherID;
		this.credits = credits;
	}
	public Teacher(String name,int age,int credits) {
		super(name, age);
		this.credits = credits;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	@Override
	public String toString() {
		return String.valueOf(teacherID);
	}
}
