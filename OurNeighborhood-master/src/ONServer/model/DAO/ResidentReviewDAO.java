package ONServer.model.DAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import core.DTO.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResidentReviewDAO {

	private ResidentReviewDAO() {
	}

	private static class LazyHolder {
		public static final ResidentReviewDAO INSTANCE = new ResidentReviewDAO();
	}

	public static ResidentReviewDAO getInstance() {
		return LazyHolder.INSTANCE;
	}

	public boolean postReview(String id, String dong, String leisureRating, String transportRating,
			String commercialRating, String housePriceRating, String comment)
			throws IOException, SQLException, Exception {

		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT `�ۼ���`, `������` FROM �����ڸ���  WHERE `�ۼ���`=?  AND `������`=? ");
		mysql.set(1, id);
		mysql.set(2, dong);
		ResultSet rs = mysql.execute();

		if (rs.next() && rs.getString("�ۼ���").equals(id) && rs.getString("������").equals(dong)) { // ������ �ۼ��� ���䰡 �ִ��� �˻�

			return false;

		} else {

			mysql.sqlQuery(
					"INSERT INTO �����ڸ��� (`�ۼ���`, `�ۼ��ð�`, `������`, `��������`, `��������`, `�������`, `��������`, `������`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			mysql.set(1, id);
			String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			mysql.set(2, time);
			mysql.set(3, dong);
			mysql.set(4, leisureRating);
			mysql.set(5, transportRating);
			mysql.set(6, commercialRating);
			mysql.set(7, housePriceRating);
			mysql.set(8, comment);

			int n = mysql.insert(); // INSERT, DELETE, UPDATE�� ���� ���� ��ȯ / �ƹ� ������ ������ 0

			if (n == 1) {
				return true;
			} else
				return false;

		}

	}

	public boolean deleteReview(String id, String dong) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("DELETE FROM �����ڸ��� WHERE `�ۼ���`=? AND `������`=?");
		mysql.set(1, id);
		mysql.set(2, dong);
		int n = mysql.delete();

		if (n == 1) {
			return true;
		} else
			return false;

	}

	public boolean updateReview(String id, String dong, String leisureRating, String transportRating,
			String commercialRating, String housePriceRating, String comment)
			throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery(
				"UPDATE �����ڸ��� SET `��������` = ?, `��������`=?, `�������`=?, `��������`=?, `������`=? WHERE `�ۼ���`=? AND `������`=? LIMIT 1");
		mysql.set(1, leisureRating);
		mysql.set(2, transportRating);
		mysql.set(3, commercialRating);
		mysql.set(4, housePriceRating);
		mysql.set(5, comment);
		mysql.set(6, id);
		mysql.set(7, dong);
		int n = mysql.update();

		if (n == 1) {
			return true;
		} else
			return false;

	}

	public ResidentReview[] getReviews() throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM �����ڸ���");
		ResultSet rs = mysql.execute();

		float ratingAverage = 0; // �������

		Vector<ResidentReview> v = new Vector<ResidentReview>();
		while (rs.next()) {

			ResidentReview residentReview = new ResidentReview();
			residentReview.setReviewNumber(rs.getInt("�����ȣ"));
			residentReview.setWriter(rs.getString("�ۼ���"));
			residentReview.setAdminDong(rs.getString("������"));
			residentReview.setLeisureRating(rs.getInt("��������"));
			residentReview.setTransportRating(rs.getInt("��������"));
			residentReview.setCommercialRating(rs.getInt("�������"));
			residentReview.setHousePriceRating(rs.getInt("��������"));
			ratingAverage = (float) ((rs.getInt("��������") + rs.getInt("��������") + rs.getInt("�������") + rs.getInt("��������"))
					/ 4.0);
			ratingAverage = (float) (Math.round(ratingAverage * 100) / 100.0);
			residentReview.setRatingAverage(ratingAverage);
			residentReview.setComment(rs.getString("������"));
			residentReview.setTime(rs.getString("�ۼ��ð�"));
			v.add(residentReview);

		}

		return v.toArray(new ResidentReview[0]);
	}

	public ResidentReview getReview(String reviewNumber) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM �����ڸ��� WHERE `�����ȣ`=? LIMIT 1");
		mysql.set(1, reviewNumber);
		ResultSet rs = mysql.execute();

		float ratingAverage = 0; // �������

		if (rs.next()) {

			ResidentReview residentReview = new ResidentReview();
			residentReview.setReviewNumber(rs.getInt("�����ȣ"));
			residentReview.setWriter(rs.getString("�ۼ���"));
			residentReview.setAdminDong(rs.getString("������"));
			residentReview.setLeisureRating(rs.getInt("��������"));
			residentReview.setTransportRating(rs.getInt("��������"));
			residentReview.setCommercialRating(rs.getInt("�������"));
			residentReview.setHousePriceRating(rs.getInt("��������"));
			ratingAverage = (float) ((rs.getInt("��������") + rs.getInt("��������") + rs.getInt("�������") + rs.getInt("��������"))
					/ 4.0);
			ratingAverage = (float) (Math.round(ratingAverage * 100) / 100.0);
			residentReview.setRatingAverage(ratingAverage);
			residentReview.setComment(rs.getString("������"));
			residentReview.setTime(rs.getString("�ۼ��ð�"));
			return residentReview;

		} 
		else return null;

	}

	public ResidentReview[] getReviewsPartially(String dong) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM �����ڸ��� WHERE `������`=?");
		mysql.set(1, dong);
		ResultSet rs = mysql.execute();

		float ratingAverage = 0; // �������

		Vector<ResidentReview> v = new Vector<ResidentReview>();
		while (rs.next()) {

			ResidentReview residentReview = new ResidentReview();
			residentReview.setReviewNumber(rs.getInt("�����ȣ"));
			residentReview.setWriter(rs.getString("�ۼ���"));
			residentReview.setAdminDong(rs.getString("������"));
			residentReview.setLeisureRating(rs.getInt("��������"));
			residentReview.setTransportRating(rs.getInt("��������"));
			residentReview.setCommercialRating(rs.getInt("�������"));
			residentReview.setHousePriceRating(rs.getInt("��������"));
			ratingAverage = (float) ((rs.getInt("��������") + rs.getInt("��������") + rs.getInt("�������") + rs.getInt("��������"))
					/ 4.0);
			ratingAverage = (float) (Math.round(ratingAverage * 100) / 100.0);
			residentReview.setRatingAverage(ratingAverage);
			residentReview.setComment(rs.getString("������"));
			residentReview.setTime(rs.getString("�ۼ��ð�"));
			v.add(residentReview);

		}

		return v.toArray(new ResidentReview[0]);
	}

	public ResidentReview[] getMyReviews(String id) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM �����ڸ��� WHERE `�ۼ���`=?");
		mysql.set(1, id);

		ResultSet rs = mysql.execute();

		float ratingAverage = 0; // �������

		Vector<ResidentReview> v = new Vector<ResidentReview>();
		while (rs.next()) {

			ResidentReview residentReview = new ResidentReview();
			residentReview.setReviewNumber(rs.getInt("�����ȣ"));
			residentReview.setWriter(rs.getString("�ۼ���"));
			residentReview.setAdminDong(rs.getString("������"));
			residentReview.setLeisureRating(rs.getInt("��������"));
			residentReview.setTransportRating(rs.getInt("��������"));
			residentReview.setCommercialRating(rs.getInt("�������"));
			residentReview.setHousePriceRating(rs.getInt("��������"));
			ratingAverage = (float) ((rs.getInt("��������") + rs.getInt("��������") + rs.getInt("�������") + rs.getInt("��������"))
					/ 4.0);
			ratingAverage = (float) (Math.round(ratingAverage * 100) / 100.0);
			residentReview.setRatingAverage(ratingAverage);
			residentReview.setComment(rs.getString("������"));
			residentReview.setTime(rs.getString("�ۼ��ð�"));
			v.add(residentReview);

		}

		return v.toArray(new ResidentReview[0]);
	}

}