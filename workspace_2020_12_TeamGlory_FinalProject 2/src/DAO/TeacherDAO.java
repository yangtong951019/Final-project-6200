package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import factories.TeacherFactory;
import models.Classroom;
import models.Group;
import models.Student;
import models.Teacher;

public class TeacherDAO {
	public static Teacher getTeacher(Group group) throws SQLException {
		Teacher teacher = null;
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from teacher where groupID=?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, group.getGroupID());
		ResultSet result = st.executeQuery();
		while (result.next()) {
			teacher = TeacherFactory.getTeacher(result.getInt(1), result.getString(2), result.getInt(3),result.getInt(4));
		}
		return teacher;
	}

	public static ArrayList<Teacher> getTeachers() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from teacher";
		Statement s = conn.createStatement();
		ResultSet result = s.executeQuery(sql);
		ArrayList<Teacher> list = new ArrayList<Teacher>();
		while (result.next()) {
			Teacher teacher =TeacherFactory.getTeacher(result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4),result.getInt(5));
			list.add(teacher);
		}
		return list;
	}

	public static int addTeacher(Teacher t) throws SQLException {
		int TeacherID = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into teacher(name,age,credits) values(?,?,?)";
		PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, t.getName());
		st.setInt(2, t.getAge());
		st.setInt(3, t.getCredits());
		//st.setInt(4, 0);
		st.executeUpdate();
		ResultSet result = st.getGeneratedKeys();
		while (result.next()) {
			TeacherID = result.getInt(1);
		}
		return TeacherID;
	}

	public static void updateGroupID(Teacher t, int groupID) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update teacher set groupID=? where teacherID=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, groupID);
		ps.setInt(2, t.getTeacherID());
		ps.executeUpdate();
	}
}
