package core.DTO;

import java.io.Serializable;

public class DistrictInfra implements Serializable {

	private static final long serialVersionUID = 1L;

	private String autoRegion; // ��ġ��
	private String adminDong; // ������
	private int onePerson; // 1�ΰ���
	private int twoPerson; // 2�ΰ���
	private int threePerson; // 3�ΰ���
	private int fourPerson; // 4�ΰ���
	private int fivePerson; // 5�ΰ���
	private int sixPerson; // 6�ΰ���
	private int sevenPerson; // 7�ΰ���
	private int leisureRating; // ���� ����
	private int transportRating; // ���� ����
	private int commercialRating; // ��� ����
	private int housePriceRating; // ���� ����
	private float ratingAverage; // �������
	private String most; // ������ ���� ���� ��������

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