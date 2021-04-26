package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import factories.TeacherFactory;
import models.Group;
import models.Teacher;

public class TeacherDAO {
	public static Teacher getTeacher(Group group) throws SQLException {
		Teacher teacher=null;
		Connection conn=JDBCUtil.getConnection();
		String sql = "select * from teacher where groupID=?";
		PreparedStatement st=conn.prepareStatement(sql);
		st.setInt(1, group.getGroupID());
		ResultSet result =st.executeQuery();
		while(result.next()){
			teacher = TeacherFactory.getTeacher(result.getInt(1),result.getString(2),result.getInt(3),result.getInt(4)); 			
		}
		return teacher;
}
}
