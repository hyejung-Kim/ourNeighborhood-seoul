package ONServer.model.DAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.DTO.*;

public class InfrastructureDAO {

	
	private InfrastructureDAO() {
	}

	private static class LazyHolder {
		public static final InfrastructureDAO INSTANCE = new InfrastructureDAO();
	}

	public static InfrastructureDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	
	public Infrastructure getInfrastructure(String district, String dong) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 지역별인프라 WHERE `자치구`=? AND `행정동`=? ");
		mysql.set(1, district);
		mysql.set(2, dong);
		ResultSet rs = mysql.execute();
		float ratingAverage = 0; // 평점평균
		if (rs.next()) {
			Infrastructure infrastructure = new Infrastructure();
			infrastructure.setAutoRegion(rs.getString("자치구"));
			infrastructure.setAdminDong(rs.getString("행정동"));
			infrastructure.setLeisureRating(rs.getInt("여가평점"));
			infrastructure.setTransportRating(rs.getInt("교통평점"));
			infrastructure.setCommercialRating(rs.getInt("상권평점"));
			infrastructure.setHousePriceRating(rs.getInt("집값평점"));
			ratingAverage = (float)((rs.getInt("여가평점")+rs.getInt("교통평점")+rs.getInt("상권평점")+rs.getInt("집값평점"))/4.0);
			ratingAverage = (float) (Math.round(ratingAverage*100)/100.0); // 소수점 2자리수 까지 출력
			infrastructure.setRatingAverage(ratingAverage);
			return infrastructure;
			
		}
		
		else return null;
		
	}
	
	public Infrastructure[] getInfrastructures(String district) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 지역별인프라 WHERE `자치구`=? ORDER BY `행정동` ASC");
		mysql.set(1, district);
		ResultSet rs = mysql.execute();
		float ratingAverage = 0; // 평점평균
		Vector<Infrastructure> v = new Vector<Infrastructure>();
		while (rs.next()) {
			Infrastructure infrastructure = new Infrastructure();
			infrastructure.setAutoRegion(rs.getString("자치구"));
			infrastructure.setAdminDong(rs.getString("행정동"));
			infrastructure.setLeisureRating(rs.getInt("여가평점"));
			infrastructure.setTransportRating(rs.getInt("교통평점"));
			infrastructure.setCommercialRating(rs.getInt("상권평점"));
			infrastructure.setHousePriceRating(rs.getInt("집값평점"));
			ratingAverage = (float)((rs.getInt("여가평점")+rs.getInt("교통평점")+rs.getInt("상권평점")+rs.getInt("집값평점"))/4.0);
			ratingAverage = (float) (Math.round(ratingAverage*100)/100.0); // 소수점 2자리수 까지 출력
			infrastructure.setRatingAverage(ratingAverage);
			v.add(infrastructure);
			
		}
		
		return v.toArray(new Infrastructure[0]);
		
		
	}
}
