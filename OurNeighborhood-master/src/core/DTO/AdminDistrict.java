package core.DTO;

import java.io.Serializable;

public class AdminDistrict implements Serializable {

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
	
	public String getMost() {
		return most;
	}

	public void setMost(String most) {
		this.most = most;
	}

}