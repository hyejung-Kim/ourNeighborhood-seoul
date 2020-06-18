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
		mysql.sqlQuery("SELECT * FROM �������� WHERE `��ġ��`= ? ORDER BY `������` ASC"); // ������ �����ټ����� ����
		mysql.set(1, district);
		ResultSet rs = mysql.execute();
		ResultSetMetaData rsmd = rs.getMetaData();
		Vector<AdminDistrict> v = new Vector<AdminDistrict>();
		
		while(rs.next()) {
			AdminDistrict adminDistrict = new AdminDistrict();
			adminDistrict.setAutoRegion(rs.getString("��ġ��"));
			adminDistrict.setAdminDong(rs.getString("������"));
			adminDistrict.setOnePerson(rs.getInt("1�ΰ���"));
			adminDistrict.setTwoPerson(rs.getInt("2�ΰ���"));
			adminDistrict.setThreePerson(rs.getInt("3�ΰ���"));
			adminDistrict.setFourPerson(rs.getInt("4�ΰ���"));
			adminDistrict.setFivePerson(rs.getInt("5�ΰ���"));
			adminDistrict.setSixPerson(rs.getInt("6�ΰ���"));
			adminDistrict.setSevenPerson(rs.getInt("7���̻󰡱�"));
			
			int max = rs.getInt("1�ΰ���");
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
		mysql.sqlQuery("SELECT * FROM �������� ORDER BY `��ġ��` ASC"); // ��ġ�� �����ټ� ����
		ResultSet rs = mysql.execute();
		ResultSetMetaData rsmd = rs.getMetaData();
		Vector<AdminDistrict> v = new Vector<AdminDistrict>();
		
		while(rs.next()) {
			AdminDistrict adminDistrict = new AdminDistrict();
			adminDistrict.setAutoRegion(rs.getString("��ġ��"));
			adminDistrict.setAdminDong(rs.getString("������"));
			adminDistrict.setOnePerson(rs.getInt("1�ΰ���"));
			adminDistrict.setTwoPerson(rs.getInt("2�ΰ���"));
			adminDistrict.setThreePerson(rs.getInt("3�ΰ���"));
			adminDistrict.setFourPerson(rs.getInt("4�ΰ���"));
			adminDistrict.setFivePerson(rs.getInt("5�ΰ���"));
			adminDistrict.setSixPerson(rs.getInt("6�ΰ���"));
			adminDistrict.setSevenPerson(rs.getInt("7���̻󰡱�"));
			
			int max = rs.getInt("1�ΰ���");
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
