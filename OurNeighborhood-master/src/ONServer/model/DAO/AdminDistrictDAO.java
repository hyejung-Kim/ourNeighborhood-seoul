package ONServer.model.DAO;


import java.io.IOException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import core.DTO.*;

public class AdminDistrictDAO {

	private AdminDistrictDAO() {
	}

	private static class LazyHolder {
		public static final AdminDistrictDAO INSTANCE = new AdminDistrictDAO();
	}

	public static AdminDistrictDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public AdminDistrict[] getAdminDistrict(String district) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 행정구역 WHERE `자치구`= ? ORDER BY `행정동` ASC"); // 행정동 가나다순으로 정렬
		mysql.set(1, district);
		ResultSet rs = mysql.execute();
		ResultSetMetaData rsmd = rs.getMetaData();
		Vector<AdminDistrict> v = new Vector<AdminDistrict>();
		
		while(rs.next()) {
			AdminDistrict adminDistrict = new AdminDistrict();
			adminDistrict.setAutoRegion(rs.getString("자치구"));
			adminDistrict.setAdminDong(rs.getString("행정동"));
			adminDistrict.setOnePerson(rs.getInt("1인가구"));
			adminDistrict.setTwoPerson(rs.getInt("2인가구"));
			adminDistrict.setThreePerson(rs.getInt("3인가구"));
			adminDistrict.setFourPerson(rs.getInt("4인가구"));
			adminDistrict.setFivePerson(rs.getInt("5인가구"));
			adminDistrict.setSixPerson(rs.getInt("6인가구"));
			adminDistrict.setSevenPerson(rs.getInt("7인이상가구"));
			
			int max = rs.getInt("1인가구");
			adminDistrict.setMost(rsmd.getColumnName(3));
			for(int i=3; i<10; i++) {
				
				if(max<rs.getInt(i)) {
					max = rs.getInt(i);
					adminDistrict.setMost(rsmd.getColumnName(i));
				}
				
			}
			v.add(adminDistrict);
		}
		
		return v.toArray(new AdminDistrict[0]);
		
		
	}
	
	public AdminDistrict[] getAdminDistricts() throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 행정구역 ORDER BY `자치구` ASC"); // 자치구 가나다순 정렬
		ResultSet rs = mysql.execute();
		ResultSetMetaData rsmd = rs.getMetaData();
		Vector<AdminDistrict> v = new Vector<AdminDistrict>();
		
		while(rs.next()) {
			AdminDistrict adminDistrict = new AdminDistrict();
			adminDistrict.setAutoRegion(rs.getString("자치구"));
			adminDistrict.setAdminDong(rs.getString("행정동"));
			adminDistrict.setOnePerson(rs.getInt("1인가구"));
			adminDistrict.setTwoPerson(rs.getInt("2인가구"));
			adminDistrict.setThreePerson(rs.getInt("3인가구"));
			adminDistrict.setFourPerson(rs.getInt("4인가구"));
			adminDistrict.setFivePerson(rs.getInt("5인가구"));
			adminDistrict.setSixPerson(rs.getInt("6인가구"));
			adminDistrict.setSevenPerson(rs.getInt("7인이상가구"));
			
			int max = rs.getInt("1인가구");
			adminDistrict.setMost(rsmd.getColumnName(3));
			for(int i=3; i<10; i++) {
				
				if(max<rs.getInt(i)) {
					max = rs.getInt(i);
					adminDistrict.setMost(rsmd.getColumnName(i));
				}
				
			}
			v.add(adminDistrict);
		}
		
		return v.toArray(new AdminDistrict[0]);
		
		
	}

}
