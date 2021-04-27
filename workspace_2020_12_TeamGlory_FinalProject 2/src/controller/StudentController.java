package controller;

import java.sql.SQLException;
import DAO.ClassDAO;
import DAO.GroupDAO;
import DAO.StudentDAO;
import factories.StudentFactory;
import models.Classroom;
import models.Group;
import models.School;
import models.Student;
//test~test
public class StudentController {
	public static void addStudent(String name, int age, int grade, Classroom classroom) throws SQLException {
		Student s = StudentFactory.getStudent(name, age, grade);
		Group g = classroom.getGroups().get(classroom.getGroups().size() - 1);
		if (g.getStudents().size() < classroom.getGroupSize()) {
			s.setStudentID(StudentDAO.addStudent(s, g));
			g.addStudent(s);
		} else {
			Group group = new Group();
			group.setGroupID(GroupDAO.addGroup(classroom));
			s.setStudentID(StudentDAO.addStudent(s, group));
			group.addStudent(s);
			classroom.getGroups().add(group);
		}
	}
	
	public static void addStudent(String name, int age, int grade) throws SQLException {
		for(Classroom classroom:School.getInstance().getClassrooms()) {
			if(checkClassroom(age,classroom)) {
				addStudent(name, age, grade,classroom);
				return;
			}
		}
		Classroom classroom=addSuitableClass(age);
		addStudent(name, age, grade,classroom);
	}

	public static boolean checkClassroom(int age, Classroom classroom) {
		if (age < classroom.getMinAge() || age > classroom.getMaxAge())
			return false;
		int size = classroom.getGroups().size();
		if (size == classroom.getMaxGroupInOneRoom()
				&& classroom.getGroups().get(size - 1).getStudents().size() == classroom.getGroupSize())
			return false;
		return true;
	}

	public static Classroom addSuitableClass(int age) throws SQLException {
		int capacity = 0;
		int minAge = 0;
		int maxAge = 0;
		int groupSize = 0;
		int maxGroupInOneRoom = 0;
		if (age >= 6 && age <= 12) {
			minAge = 6;
			maxAge = 12;
			groupSize = 4;
			maxGroupInOneRoom = 3;
		}
		if (age >= 13 && age <= 24) {
			minAge = 13;
			maxAge = 24;
			groupSize = 5;
			maxGroupInOneRoom = 3;
		}
		if (age >= 25 && age <= 35) {
			minAge = 25;
			maxAge = 35;
			groupSize = 6;
			maxGroupInOneRoom = 3;
		}
		if (age >= 36 && age <= 47) {
			minAge = 36;
			maxAge = 47;
			groupSize = 8;
			maxGroupInOneRoom = 3;
		}
		if (age >= 48 && age <= 59) {
			minAge = 48;
			maxAge = 59;
			groupSize = 12;
			maxGroupInOneRoom = 2;
		}
		if (age >= 60) {
			minAge = 60;
			maxAge = Integer.MAX_VALUE;
			groupSize = 15;
			maxGroupInOneRoom = 2;
		}
		capacity = (groupSize + 1) * maxGroupInOneRoom;
		Classroom c = new Classroom(capacity, minAge, maxAge, groupSize, maxGroupInOneRoom);
		c.setClassroomID(ClassDAO.addClassroom(c));
		School.getInstance().getClassrooms().add(c);
		return c;
	}
}
