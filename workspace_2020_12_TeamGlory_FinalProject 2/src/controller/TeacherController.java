package controller;

import java.sql.SQLException;

import DAO.GroupDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import factories.TeacherFactory;
import models.Classroom;
import models.Group;
import models.School;
import models.Teacher;

public class TeacherController {
	public static void addTeacher(String name, int age, int credit) throws SQLException {
		Teacher t = TeacherFactory.getTeacher(name, age, credit);
		t.setTeacherID(TeacherDAO.addTeacher(t));
		School school = School.getInstance();
		school.getTeachers().add(t);
	}
	public static void AssignGroupForTeacher(Teacher t, Group p) throws SQLException {
		t.setGroupID(p.getGroupID());
		TeacherDAO.updateGroupID(t, p.getGroupID());//update teacher table in database
		p.setTeacher(t);
	}
}
