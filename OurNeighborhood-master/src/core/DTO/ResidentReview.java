package core.DTO;

import java.io.Serializable;


public class ResidentReview implements Serializable {

	private static final long serialVersionUID = 1L;

	private int reviewNumber; // 리뷰번호
	private String writer; // 작성자
	private String adminDong; // 행정동
	private int leisureRating; // 여가 평점
	private int transportRating; // 교통 평점
	private int commercialRating; // 상권 평점
	private int housePriceRating; // 집값 평점
	private float ratingAverage; // 평점평균
	private String comment; // 한줄평
	private String time; // 작성시간

	public int getReviewNumber() {
		return reviewNumber;
	}

	public void setReviewNumber(int reviewNumber) {
		this.reviewNumber = reviewNumber;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getAdminDong() {
		return adminDong;
	}

	public void setAdminDong(String adminDong) {
		this.adminDong = adminDong;
	}

	public int getLeisureRating() {
		return leisureRating;
	}

	public void setLeisureRating(int leisureRating) {
		this.leisureRating = leisureRating;
	}

	public int getTransportRating() {
		return transportRating;
	}

	public void setTransportRating(int transportRating) {
		this.transportRating = transportRating;
	}

	public int getCommercialRating() {
		return commercialRating;
	}

	public void setCommercialRating(int commercialRating) {
		this.commercialRating = commercialRating;
	}

	public int getHousePriceRating() {
		return housePriceRating;
	}

	public void setHousePriceRating(int housePriceRating) {
		this.housePriceRating = housePriceRating;
	}

	public float getRatingAverage() {
		return ratingAverage;
	}

	public void setRatingAverage(float ratingAverage) {
		this.ratingAverage = ratingAverage;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
