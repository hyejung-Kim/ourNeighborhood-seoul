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

		if(condition.equals("여가평점순")) {
			mysql.sqlQuery("SELECT * FROM seoul.행정구역인프라  WHERE `자치구`=? ORDER BY `여가평점` DESC");
			mysql.set(1, district);

		}else if(condition.equals("교통평점순")) {
			mysql.sqlQuery("SELECT * FROM seoul.행정구역인프라  WHERE `자치구`=? ORDER BY `교통평점` DESC");
			mysql.set(1, district);

		}else if(condition.equals("상권평점순")) {
			mysql.sqlQuery("SELECT * FROM seoul.행정구역인프라  WHERE `자치구`=? ORDER BY `상권평점` DESC");
			mysql.set(1, district);

		}else if(condition.equals("집값평점순")) {
			mysql.sqlQuery("SELECT * FROM seoul.행정구역인프라  WHERE `자치구`=? ORDER BY `집값평점` DESC");
			mysql.set(1, district);

		}else if(condition.equals("평점평균순")) {
			mysql.sqlQuery("SELECT * FROM seoul.행정구역인프라  WHERE `자치구`=? ORDER BY `평점평균` DESC");
			mysql.set(1, district);

		}

		rs = mysql.execute();
		rsmd = rs.getMetaData();


		while(rs.next()) {
			DistrictInfra districtInfra = new DistrictInfra();

			districtInfra.setAutoRegion(rs.getString("자치구"));
			districtInfra.setAdminDong(rs.getString("행정동"));
			districtInfra.setOnePerson(rs.getInt("1인가구"));
			districtInfra.setTwoPerson(rs.getInt("2인가구"));
			districtInfra.setThreePerson(rs.getInt("3인가구"));
			districtInfra.setFourPerson(rs.getInt("4인가구"));
			districtInfra.setFivePerson(rs.getInt("5인가구"));
			districtInfra.setSixPerson(rs.getInt("6인가구"));
			districtInfra.setSevenPerson(rs.getInt("7인이상가구"));
			districtInfra.setLeisureRating(rs.getInt("여가평점"));
			districtInfra.setTransportRating(rs.getInt("교통평점"));
			districtInfra.setCommercialRating(rs.getInt("상권평점"));
			districtInfra.setHousePriceRating(rs.getInt("집값평점"));
			districtInfra.setRatingAverage(rs.getFloat("평점평균"));


			int max = rs.getInt("1인가구");
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