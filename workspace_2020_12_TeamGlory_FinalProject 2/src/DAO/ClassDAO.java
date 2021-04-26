package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Classroom;

public class ClassDAO {
	public static ArrayList<Classroom> getClasses() throws SQLException {
		Connection conn = JDBCUtil.getConnection();
		String sql = "select * from classroom";
		Statement s = conn.createStatement();
		ResultSet result = s.executeQuery(sql);
		ArrayList<Classroom> list = new ArrayList<Classroom>();
		while (result.next()) {
			Classroom classroom = new Classroom(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4),
					result.getInt(5), result.getInt(6));
			list.add(classroom);
		}
		return list;
	}

	public static int addClassroom(Classroom classroom) throws SQLException {
		int classroomID = 0;
		Connection conn = JDBCUtil.getConnection();
		String sql = "insert into classroom(capacity,minAge,maxAge,groupSize,maxGroupInOneRoom) values(?,?,?,?,?)";
		PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		st.setInt(1, classroom.getCapacity());
		st.setInt(2, classroom.getMinAge());
		st.setInt(3, classroom.getMaxAge());
		st.setInt(4, classroom.getGroupSize());
		st.setInt(5, classroom.getMaxGroupInOneRoom());
		st.executeUpdate();
		ResultSet result = st.getGeneratedKeys();
		while (result.next()) {
			classroomID = result.getInt(1);
		}
		return classroomID;
	}
}
