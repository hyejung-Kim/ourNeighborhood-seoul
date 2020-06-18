package core.DTO;

import java.io.Serializable;

public class DistrictInfra implements Serializable {

	private static final long serialVersionUID = 1L;

	private String autoRegion; // 자치구
	private String adminDong; // 행정동
	private int onePerson; // 1인가구
	private int twoPerson; // 2인가구
	private int threePerson; // 3인가구
	private int fourPerson; // 4인가구
	private int fivePerson; // 5인가구
	private int sixPerson; // 6인가구
	private int sevenPerson; // 7인가구
	private int leisureRating; // 여가 평점
	private int transportRating; // 교통 평점
	private int commercialRating; // 상권 평점
	private int housePriceRating; // 집값 평점
	private float ratingAverage; // 평점평균
	private String most; // 동에서 가장 많은 가구형태

	public String getAutoRegion() {
		return autoRegion;
	}

	public void setAutoRegion(String autoRegion) {
		this.autoRegion = autoRegion;
	}

	public String getAdminDong() {
		return adminDong;
	}

	public void setAdminDong(String adminDong) {
		this.adminDong = adminDong;
	}

	public int getOnePerson() {
		return onePerson;
	}

	public void setOnePerson(int onePerson) {
		this.onePerson = onePerson;
	}

	public int getTwoPerson() {
		return twoPerson;
	}

	public void setTwoPerson(int twoPerson) {
		this.twoPerson = twoPerson;
	}

	public int getThreePerson() {
		return threePerson;
	}

	public void setThreePerson(int threePerson) {
		this.threePerson = threePerson;
	}

	public int getFourPerson() {
		return fourPerson;
	}

	public void setFourPerson(int fourPerson) {
		this.fourPerson = fourPerson;
	}

	public int getFivePerson() {
		return fivePerson;
	}

	public void setFivePerson(int fivePerson) {
		this.fivePerson = fivePerson;
	}

	public int getSixPerson() {
		return sixPerson;
	}

	public void setSixPerson(int sixPerson) {
		this.sixPerson = sixPerson;
	}

	public int getSevenPerson() {
		return sevenPerson;
	}

	public void setSevenPerson(int sevenPerson) {
		this.sevenPerson = sevenPerson;
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

	public String getMost() {
		return most;
	}

	public void setMost(String most) {
		this.most = most;
	}

}