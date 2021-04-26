package models;

import java.util.ArrayList;

public class School {
	private volatile static School school;
	private ArrayList<Classroom> classrooms;
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
}
