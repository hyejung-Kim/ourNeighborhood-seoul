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
		mysql.sqlQuery("SELECT * FROM 사용자 WHERE `아이디`=? LIMIT 1");
		mysql.set(1, userID);
		
		ResultSet rs = mysql.execute();
		
		if(rs.next()) {
			
			User user = new User();
			user.setId(rs.getString("아이디"));
			user.setResidenDistrict(rs.getString("거주구"));
			user.setResidenDong(rs.getString("거주동"));
			user.setPhoneNumber(rs.getString("전화번호"));
			user.setName(rs.getString("이름"));
			
			return user;
			
		}
		
		return null;
	}

}
