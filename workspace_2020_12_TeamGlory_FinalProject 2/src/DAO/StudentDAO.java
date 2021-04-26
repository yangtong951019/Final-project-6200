package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
					result.getInt(4));
			list.add(student);
		}
		return list;
	}

	public static int addStudent(Student student, Group group) throws SQLException {
		int studentID = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into student(name,age,grade,groupID) values(?,?,?,?)";
		PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, student.getName());
		st.setInt(2, student.getAge());
		st.setInt(3, student.getGrade());
		st.setInt(4, group.getGroupID());
		st.executeUpdate();
		ResultSet result = st.getGeneratedKeys();
		while (result.next()) {
			studentID = result.getInt(1);
		}
		return studentID;
	}

}
