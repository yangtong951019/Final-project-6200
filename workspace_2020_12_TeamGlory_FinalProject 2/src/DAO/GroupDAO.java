package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Classroom;
import models.Group;

public class GroupDAO {
	public static ArrayList<Group> getGroups(Classroom classroom) throws SQLException {
		Connection conn=JDBCUtil.getConnection();
		String sql = "select * from `group` where classroomID=?";
		PreparedStatement st=conn.prepareStatement(sql);
		st.setInt(1,classroom.getClassroomID());
		ResultSet result =st.executeQuery();
		ArrayList<Group> list=new ArrayList<Group>();
		while(result.next()){
			Group group = new Group(result.getInt(1));
			list.add(group);      
		}
		return list;
}
	public static int addGroup(Classroom classroom) throws SQLException {
		int groupID=0;
		Connection conn=JDBCUtil.getConnection();
		String sql = "insert into `group`(classroomID) values(?)";
		PreparedStatement st=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		st.setInt(1,classroom.getClassroomID());
		st.executeUpdate();  
		ResultSet result = st.getGeneratedKeys();
		while(result.next()) {
			groupID=result.getInt(1);
		}
		return groupID;
	}
}
