package dataGenerator;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ClassDAO;
import DAO.GroupDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import models.Classroom;
import models.Group;
import models.School;
import models.Teacher;

public class DataGenerator {
	public static void Generate() throws SQLException {
		School school=School.getInstance();
		ArrayList<Classroom> classrooms=ClassDAO.getClasses();
		for(Classroom c:classrooms) {
			ArrayList<Group> groups=GroupDAO.getGroups(c);
			for(Group g:groups) {
				g.setStudents(StudentDAO.getStudents(g));
				g.setTeacher(TeacherDAO.getTeacher(g));
			}
			c.setGroups(groups);
		}
		school.setClassrooms(classrooms);
		ArrayList<Teacher> teachers = TeacherDAO.getTeachers();
		school.setTeachers(teachers);
	}
}
