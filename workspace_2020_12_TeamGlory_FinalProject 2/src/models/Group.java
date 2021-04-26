package models;

import java.util.ArrayList;

public class Group {
	private int groupID;
	private Teacher teacher;
	private ArrayList<Student> students;
	public Group() {
		students=new ArrayList<Student>();
	}
	public Group(int groupID) {
		this.groupID = groupID;
		students=new ArrayList<Student>();
	}
	
	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}
	public void addStudents(ArrayList<Student> ss) {
		for(Student s:ss) {
			students.add(s);
		}
	}
}
