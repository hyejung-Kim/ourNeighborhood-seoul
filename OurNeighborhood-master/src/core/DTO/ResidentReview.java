package core.DTO;

import java.io.Serializable;


public class ResidentReview implements Serializable {

	private static final long serialVersionUID = 1L;

	private int reviewNumber; // �����ȣ
	private String writer; // �ۼ���
	private String adminDong; // ������
	private int leisureRating; // ���� ����
	private int transportRating; // ���� ����
	private int commercialRating; // ��� ����
	private int housePriceRating; // ���� ����
	private float ratingAverage; // �������
	private String comment; // ������
	private String time; // �ۼ��ð�

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
