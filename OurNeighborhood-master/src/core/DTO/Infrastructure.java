package core.DTO;
import java.io.Serializable;

public class Infrastructure implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String autoRegion; // ��ġ��
	private String adminDong; // ������
	private int leisureRating; // ���� ����
	private int transportRating; // ���� ����
	private int commercialRating; // ��� ����
	private int housePriceRating; // ���� ����
	private float ratingAverage; // �������

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

}
