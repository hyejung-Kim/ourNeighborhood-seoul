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
		mysql.sqlQuery("SELECT * FROM ������������ WHERE `��ġ��`=? AND `������`=? ");
		mysql.set(1, district);
		mysql.set(2, dong);
		ResultSet rs = mysql.execute();
		float ratingAverage = 0; // �������
		if (rs.next()) {
			Infrastructure infrastructure = new Infrastructure();
			infrastructure.setAutoRegion(rs.getString("��ġ��"));
			infrastructure.setAdminDong(rs.getString("������"));
			infrastructure.setLeisureRating(rs.getInt("��������"));
			infrastructure.setTransportRating(rs.getInt("��������"));
			infrastructure.setCommercialRating(rs.getInt("�������"));
			infrastructure.setHousePriceRating(rs.getInt("��������"));
			ratingAverage = (float)((rs.getInt("��������")+rs.getInt("��������")+rs.getInt("�������")+rs.getInt("��������"))/4.0);
			ratingAverage = (float) (Math.round(ratingAverage*100)/100.0); // �Ҽ��� 2�ڸ��� ���� ���
			infrastructure.setRatingAverage(ratingAverage);
			return infrastructure;
			
		}
		
		else return null;
		
	}
	
	public Infrastructure[] getInfrastructures(String district) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM ������������ WHERE `��ġ��`=? ORDER BY `������` ASC");
		mysql.set(1, district);
		ResultSet rs = mysql.execute();
		float ratingAverage = 0; // �������
		Vector<Infrastructure> v = new Vector<Infrastructure>();
		while (rs.next()) {
			Infrastructure infrastructure = new Infrastructure();
			infrastructure.setAutoRegion(rs.getString("��ġ��"));
			infrastructure.setAdminDong(rs.getString("������"));
			infrastructure.setLeisureRating(rs.getInt("��������"));
			infrastructure.setTransportRating(rs.getInt("��������"));
			infrastructure.setCommercialRating(rs.getInt("�������"));
			infrastructure.setHousePriceRating(rs.getInt("��������"));
			ratingAverage = (float)((rs.getInt("��������")+rs.getInt("��������")+rs.getInt("�������")+rs.getInt("��������"))/4.0);
			ratingAverage = (float) (Math.round(ratingAverage*100)/100.0); // �Ҽ��� 2�ڸ��� ���� ���
			infrastructure.setRatingAverage(ratingAverage);
			v.add(infrastructure);
			
		}
		
		return v.toArray(new Infrastructure[0]);
		
		
	}
}
