package models;

import java.util.ArrayList;

public class School {
	private volatile static School school;
	private ArrayList<Classroom> classrooms;
	private ArrayList<Teacher> teachers;
	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	private School() {};
	
	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}

	public static School getInstance() {
		if(school==null) {
			synchronized(School.class) {
				if(school==null) {
					school=new School();
				}
			}
		}
		return school;
	}
	
	public void setClassrooms(ArrayList<Classroom> classrooms) {
		this.classrooms = classrooms;
	}

	public ArrayList<Classroom> getClassrooms() {
		return classrooms;
	}

	public static School getSchool() {
		return school;
	}

	public static void setSchool(School school) {
		School.school = school;
	}
}
