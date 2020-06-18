package ONServer.model.DAO;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import core.DTO.DistrictInfra;

public class DistrictInfraDAO {


	private DistrictInfraDAO() {
	}

	private static class LazyHolder {
		public static final DistrictInfraDAO INSTANCE = new DistrictInfraDAO();
	}

	public static DistrictInfraDAO getInstance() {
		return LazyHolder.INSTANCE;
	}


public DistrictInfra[] getDistrictInfra(String district, String condition) throws IOException, SQLException, Exception {

		MySQL mysql = MySQL.getConnection();
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;

		Vector<DistrictInfra> v = new Vector<DistrictInfra>();

		if(condition.equals("����������")) {
			mysql.sqlQuery("SELECT * FROM seoul.��������������  WHERE `��ġ��`=? ORDER BY `��������` DESC");
			mysql.set(1, district);

		}else if(condition.equals("����������")) {
			mysql.sqlQuery("SELECT * FROM seoul.��������������  WHERE `��ġ��`=? ORDER BY `��������` DESC");
			mysql.set(1, district);

		}else if(condition.equals("���������")) {
			mysql.sqlQuery("SELECT * FROM seoul.��������������  WHERE `��ġ��`=? ORDER BY `�������` DESC");
			mysql.set(1, district);

		}else if(condition.equals("����������")) {
			mysql.sqlQuery("SELECT * FROM seoul.��������������  WHERE `��ġ��`=? ORDER BY `��������` DESC");
			mysql.set(1, district);

		}else if(condition.equals("������ռ�")) {
			mysql.sqlQuery("SELECT * FROM seoul.��������������  WHERE `��ġ��`=? ORDER BY `�������` DESC");
			mysql.set(1, district);

		}

		rs = mysql.execute();
		rsmd = rs.getMetaData();


		while(rs.next()) {
			DistrictInfra districtInfra = new DistrictInfra();

			districtInfra.setAutoRegion(rs.getString("��ġ��"));
			districtInfra.setAdminDong(rs.getString("������"));
			districtInfra.setOnePerson(rs.getInt("1�ΰ���"));
			districtInfra.setTwoPerson(rs.getInt("2�ΰ���"));
			districtInfra.setThreePerson(rs.getInt("3�ΰ���"));
			districtInfra.setFourPerson(rs.getInt("4�ΰ���"));
			districtInfra.setFivePerson(rs.getInt("5�ΰ���"));
			districtInfra.setSixPerson(rs.getInt("6�ΰ���"));
			districtInfra.setSevenPerson(rs.getInt("7���̻󰡱�"));
			districtInfra.setLeisureRating(rs.getInt("��������"));
			districtInfra.setTransportRating(rs.getInt("��������"));
			districtInfra.setCommercialRating(rs.getInt("�������"));
			districtInfra.setHousePriceRating(rs.getInt("��������"));
			districtInfra.setRatingAverage(rs.getFloat("�������"));


			int max = rs.getInt("1�ΰ���");
			districtInfra.setMost(rsmd.getColumnName(3));
			for(int i=3; i<10; i++) {

				if(max<rs.getInt(i)) {
					max = rs.getInt(i);
					districtInfra.setMost(rsmd.getColumnName(i));
				}

			}
			v.add(districtInfra);
		}



		return v.toArray(new DistrictInfra[0]);
	}





}