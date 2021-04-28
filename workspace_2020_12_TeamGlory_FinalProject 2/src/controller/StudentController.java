package controller;

import java.sql.SQLException;
import java.util.Date;

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
	public static void addStudent(String name, int age, int grade, int hib, int dTap, int pollio, int hepatitisB,boolean mMR, boolean varicella,Classroom classroom) throws SQLException {
		Student s = StudentFactory.getStudent(name, age, grade,hib,dTap,pollio,hepatitisB,mMR,varicella);
		s.setRegistrationDay(new java.sql.Date(new Date().getTime()));
		for (Group g : classroom.getGroups()) {
			if (g.getStudents().size() < classroom.getGroupSize()) {
				s.setStudentID(StudentDAO.addStudent(s, g));
				g.addStudent(s);
				return;
			}
		}
		Group group = new Group();
		group.setGroupID(GroupDAO.addGroup(classroom));
		s.setStudentID(StudentDAO.addStudent(s, group));
		group.addStudent(s);
		classroom.getGroups().add(group);
	}

	public static void addStudent(String name, int age, int grade,int hib, int dTap, int pollio, int hepatitisB,boolean mMR, boolean varicella) throws SQLException {
		for (Classroom classroom : School.getInstance().getClassrooms()) {
			if (checkClassroom(age, classroom)) {
				addStudent(name, age, grade,hib,dTap,pollio,hepatitisB,mMR,varicella,classroom);
				return;
			}
		}
		Classroom classroom = addSuitableClass(age);
		addStudent(name, age, grade,hib,dTap,pollio,hepatitisB,mMR,varicella,classroom);
	}

	public static boolean checkClassroom(int age, Classroom classroom) {
		if (age < classroom.getMinAge() || age > classroom.getMaxAge())
			return false;
		if (classroom.getGroups().size() < classroom.getMaxGroupInOneRoom())
			return true;
		for (Group g : classroom.getGroups()) {
			if (g.getStudents().size() < classroom.getGroupSize())
				return true;
		}
		return false;
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

	public static void UpdateStudentInformation(Student s, int age, int grade,int hib, int dTap, int pollio, int hepatitisB,boolean mMR, boolean varicella) throws SQLException {
		StudentDAO.updateStudent(s, age, grade,hib,dTap,pollio,hepatitisB,mMR,varicella);
		s.setAge(age);
		s.setGrade(grade);
		s.setRegistrationDay(new java.sql.Date(new Date().getTime()));
	}

	public static void UpdateStudentInformation(Student s, int age, int grade, int hib, int dTap, int pollio, int hepatitisB,boolean mMR, boolean varicella,Classroom classroom, Group group)
			throws SQLException {
		s.setAge(age);
		s.setGrade(grade);
		s.setRegistrationDay(new java.sql.Date(new Date().getTime()));
		for (Group g : classroom.getGroups()) {
			if (g.getStudents().size() < classroom.getGroupSize()) {
				StudentDAO.updateStudent(s, age, grade,hib,dTap,pollio,hepatitisB,mMR,varicella,g);
				group.getStudents().remove(s);
				g.addStudent(s);
				return;
			}
		}
		Group g = new Group();
		g.setGroupID(GroupDAO.addGroup(classroom));
		StudentDAO.updateStudent(s, age, grade,hib,dTap,pollio,hepatitisB,mMR,varicella,g);
		group.getStudents().remove(s);
		g.addStudent(s);
		classroom.getGroups().add(g);
	}
}
