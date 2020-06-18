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
		mysql.sqlQuery("SELECT `작성자`, `행정동` FROM 거주자리뷰  WHERE `작성자`=?  AND `행정동`=? ");
		mysql.set(1, id);
		mysql.set(2, dong);
		ResultSet rs = mysql.execute();

		if (rs.next() && rs.getString("작성자").equals(id) && rs.getString("행정동").equals(dong)) { // 이전에 작성한 리뷰가 있는지 검사

			return false;

		} else {

			mysql.sqlQuery(
					"INSERT INTO 거주자리뷰 (`작성자`, `작성시간`, `행정동`, `여가평점`, `교통평점`, `상권평점`, `집값평점`, `한줄평`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			mysql.set(1, id);
			String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			mysql.set(2, time);
			mysql.set(3, dong);
			mysql.set(4, leisureRating);
			mysql.set(5, transportRating);
			mysql.set(6, commercialRating);
			mysql.set(7, housePriceRating);
			mysql.set(8, comment);

			int n = mysql.insert(); // INSERT, DELETE, UPDATE된 행의 수를 반환 / 아무 리턴이 없으면 0

			if (n == 1) {
				return true;
			} else
				return false;

		}

	}

	public boolean deleteReview(String id, String dong) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("DELETE FROM 거주자리뷰 WHERE `작성자`=? AND `행정동`=?");
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
				"UPDATE 거주자리뷰 SET `여가평점` = ?, `교통평점`=?, `상권평점`=?, `집값평점`=?, `한줄평`=? WHERE `작성자`=? AND `행정동`=? LIMIT 1");
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
		mysql.sqlQuery("SELECT * FROM 거주자리뷰");
		ResultSet rs = mysql.execute();

		float ratingAverage = 0; // 평점평균

		Vector<ResidentReview> v = new Vector<ResidentReview>();
		while (rs.next()) {

			ResidentReview residentReview = new ResidentReview();
			residentReview.setReviewNumber(rs.getInt("리뷰번호"));
			residentReview.setWriter(rs.getString("작성자"));
			residentReview.setAdminDong(rs.getString("행정동"));
			residentReview.setLeisureRating(rs.getInt("여가평점"));
			residentReview.setTransportRating(rs.getInt("교통평점"));
			residentReview.setCommercialRating(rs.getInt("상권평점"));
			residentReview.setHousePriceRating(rs.getInt("집값평점"));
			ratingAverage = (float) ((rs.getInt("여가평점") + rs.getInt("교통평점") + rs.getInt("상권평점") + rs.getInt("집값평점"))
					/ 4.0);
			ratingAverage = (float) (Math.round(ratingAverage * 100) / 100.0);
			residentReview.setRatingAverage(ratingAverage);
			residentReview.setComment(rs.getString("한줄평"));
			residentReview.setTime(rs.getString("작성시간"));
			v.add(residentReview);

		}

		return v.toArray(new ResidentReview[0]);
	}

	public ResidentReview getReview(String reviewNumber) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 거주자리뷰 WHERE `리뷰번호`=? LIMIT 1");
		mysql.set(1, reviewNumber);
		ResultSet rs = mysql.execute();

		float ratingAverage = 0; // 평점평균

		if (rs.next()) {

			ResidentReview residentReview = new ResidentReview();
			residentReview.setReviewNumber(rs.getInt("리뷰번호"));
			residentReview.setWriter(rs.getString("작성자"));
			residentReview.setAdminDong(rs.getString("행정동"));
			residentReview.setLeisureRating(rs.getInt("여가평점"));
			residentReview.setTransportRating(rs.getInt("교통평점"));
			residentReview.setCommercialRating(rs.getInt("상권평점"));
			residentReview.setHousePriceRating(rs.getInt("집값평점"));
			ratingAverage = (float) ((rs.getInt("여가평점") + rs.getInt("교통평점") + rs.getInt("상권평점") + rs.getInt("집값평점"))
					/ 4.0);
			ratingAverage = (float) (Math.round(ratingAverage * 100) / 100.0);
			residentReview.setRatingAverage(ratingAverage);
			residentReview.setComment(rs.getString("한줄평"));
			residentReview.setTime(rs.getString("작성시간"));
			return residentReview;

		} 
		else return null;

	}

	public ResidentReview[] getReviewsPartially(String dong) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 거주자리뷰 WHERE `행정동`=?");
		mysql.set(1, dong);
		ResultSet rs = mysql.execute();

		float ratingAverage = 0; // 평점평균

		Vector<ResidentReview> v = new Vector<ResidentReview>();
		while (rs.next()) {

			ResidentReview residentReview = new ResidentReview();
			residentReview.setReviewNumber(rs.getInt("리뷰번호"));
			residentReview.setWriter(rs.getString("작성자"));
			residentReview.setAdminDong(rs.getString("행정동"));
			residentReview.setLeisureRating(rs.getInt("여가평점"));
			residentReview.setTransportRating(rs.getInt("교통평점"));
			residentReview.setCommercialRating(rs.getInt("상권평점"));
			residentReview.setHousePriceRating(rs.getInt("집값평점"));
			ratingAverage = (float) ((rs.getInt("여가평점") + rs.getInt("교통평점") + rs.getInt("상권평점") + rs.getInt("집값평점"))
					/ 4.0);
			ratingAverage = (float) (Math.round(ratingAverage * 100) / 100.0);
			residentReview.setRatingAverage(ratingAverage);
			residentReview.setComment(rs.getString("한줄평"));
			residentReview.setTime(rs.getString("작성시간"));
			v.add(residentReview);

		}

		return v.toArray(new ResidentReview[0]);
	}

	public ResidentReview[] getMyReviews(String id) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 거주자리뷰 WHERE `작성자`=?");
		mysql.set(1, id);

		ResultSet rs = mysql.execute();

		float ratingAverage = 0; // 평점평균

		Vector<ResidentReview> v = new Vector<ResidentReview>();
		while (rs.next()) {

			ResidentReview residentReview = new ResidentReview();
			residentReview.setReviewNumber(rs.getInt("리뷰번호"));
			residentReview.setWriter(rs.getString("작성자"));
			residentReview.setAdminDong(rs.getString("행정동"));
			residentReview.setLeisureRating(rs.getInt("여가평점"));
			residentReview.setTransportRating(rs.getInt("교통평점"));
			residentReview.setCommercialRating(rs.getInt("상권평점"));
			residentReview.setHousePriceRating(rs.getInt("집값평점"));
			ratingAverage = (float) ((rs.getInt("여가평점") + rs.getInt("교통평점") + rs.getInt("상권평점") + rs.getInt("집값평점"))
					/ 4.0);
			ratingAverage = (float) (Math.round(ratingAverage * 100) / 100.0);
			residentReview.setRatingAverage(ratingAverage);
			residentReview.setComment(rs.getString("한줄평"));
			residentReview.setTime(rs.getString("작성시간"));
			v.add(residentReview);

		}

		return v.toArray(new ResidentReview[0]);
	}

}