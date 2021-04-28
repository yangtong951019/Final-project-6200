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
					result.getInt(4), result.getDate(5), result.getInt(6), result.getInt(7), result.getInt(8),
					result.getInt(9), result.getBoolean(10), result.getBoolean(11));
			list.add(student);
		}
		return list;
	}

	public static int addStudent(Student student, Group group) throws SQLException {
		int studentID = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into student(name,age,grade,registrationDay,Hib,DTap,Pollio,HepatitisB,MMR,Varicella,groupID) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, student.getName());
		st.setInt(2, student.getAge());
		st.setInt(3, student.getGrade());
		st.setDate(4, new java.sql.Date(new Date().getTime()));
		st.setInt(5, student.getHib());
		st.setInt(6, student.getDTap());
		st.setInt(7, student.getPollio());
		st.setInt(8, student.getHepatitisB());
		st.setBoolean(9, student.isMMR());
		st.setBoolean(10, student.isVaricella());
		st.setInt(11, group.getGroupID());
		st.executeUpdate();
		ResultSet result = st.getGeneratedKeys();
		while (result.next()) {
			studentID = result.getInt(1);
		}
		return studentID;
	}

	public static void updateStudent(Student student, int age, int grade, int hib, int dTap, int pollio, int hepatitisB,
			boolean mMR, boolean varicella) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update student set age=?,grade=?,registrationDay=?,Hib=?,DTap=?,Pollio=?,HepatitisB=?,MMR=?,Varicella=? where studentID=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, age);
		ps.setInt(2, grade);
		ps.setDate(3, new java.sql.Date(new Date().getTime()));
		ps.setInt(4, hib);
		ps.setInt(5, dTap);
		ps.setInt(6, pollio);
		ps.setInt(7, hepatitisB);
		ps.setBoolean(8, mMR);
		ps.setBoolean(9, varicella);
		ps.setInt(10, student.getStudentID());
		ps.executeUpdate();
	}

	public static void updateStudent(Student student, int age, int grade,int hib, int dTap, int pollio, int hepatitisB, boolean mMR, boolean varicella,Group group) throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "update student set age=?,grade=?,registrationDay=?,Hib=?,DTap=?,Pollio=?,HepatitisB=?,MMR=?,Varicella=?,groupID=? where studentID=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, age);
		ps.setInt(2, grade);
		ps.setDate(3, new java.sql.Date(new Date().getTime()));
		ps.setInt(4, hib);
		ps.setInt(5, dTap);
		ps.setInt(6, pollio);
		ps.setInt(7, hepatitisB);
		ps.setBoolean(8, mMR);
		ps.setBoolean(9, varicella);
		ps.setInt(10, group.getGroupID());
		ps.setInt(11, student.getStudentID());
		ps.executeUpdate();
	}
}
