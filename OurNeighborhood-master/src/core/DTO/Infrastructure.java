package core.DTO;
import java.io.Serializable;

public class Infrastructure implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String autoRegion; // 자치구
	private String adminDong; // 행정동
	private int leisureRating; // 여가 평점
	private int transportRating; // 교통 평점
	private int commercialRating; // 상권 평점
	private int housePriceRating; // 집값 평점
	private float ratingAverage; // 평점평균

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
