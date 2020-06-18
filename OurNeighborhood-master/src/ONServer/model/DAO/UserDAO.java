package ONServer.model.DAO;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.DTO.*;


public class UserDAO {
	
	private UserDAO() {
	}

	private static class LazyHolder {
		public static final UserDAO INSTANCE = new UserDAO();
	}

	public static UserDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public User getUser(String userID) throws IOException, SQLException, Exception {
		
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM ����� WHERE `���̵�`=? LIMIT 1");
		mysql.set(1, userID);
		
		ResultSet rs = mysql.execute();
		
		if(rs.next()) {
			
			User user = new User();
			user.setId(rs.getString("���̵�"));
			user.setResidenDistrict(rs.getString("���ֱ�"));
			user.setResidenDong(rs.getString("���ֵ�"));
			user.setPhoneNumber(rs.getString("��ȭ��ȣ"));
			user.setName(rs.getString("�̸�"));
			
			return user;
			
		}
		
		return null;
	}

}
