package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import factories.StudentFactory;
import models.Group;
import models.Student;

public class StudentDAO {
	public static ArrayList<Student> getStudents(Group group) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from student where groupID=?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, group.getGroupID());
		ResultSet result = st.executeQuery();
		ArrayList<Student> list = new ArrayList<Student>();
		while (result.next()) {
			Student student = StudentFactory.getStudent(result.getInt(1), result.getString(2), result.getInt(3),
					result.getInt(4),result.getDate(5));
			list.add(student);
		}
		return list;
	}
	public static int addStudent(Student student, Group group) throws SQLException {
		int studentID = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into student(name,age,grade,groupID,registrationDay) values(?,?,?,?,?)";
		PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, student.getName());
		st.setInt(2, student.getAge());
		st.setInt(3, student.getGrade());
		st.setInt(4, group.getGroupID());
		st.setDate(5,new java.sql.Date(new Date().getTime()));
		st.executeUpdate();
		ResultSet result = st.getGeneratedKeys();
		while (result.next()) {
			studentID = result.getInt(1);
		}
		return studentID;
	}
	public static void updateStudent(Student student,int age,int grade) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update student set age=?,grade=?,registrationDay=? where studentID=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, age);
		ps.setInt(2, grade);
		ps.setDate(3,new java.sql.Date(new Date().getTime()));
		ps.setInt(4, student.getStudentID());
		ps.executeUpdate();
	}
	public static void updateStudent(Student student,int age,int grade,Group group) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update student set age=?,grade=?,registrationDay=?,groupID=? where studentID=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, age);
		ps.setInt(2, grade);
		ps.setDate(3,new java.sql.Date(new Date().getTime()));
		ps.setInt(4, group.getGroupID());
		ps.setInt(5, student.getStudentID());
		ps.executeUpdate();
	}
}
