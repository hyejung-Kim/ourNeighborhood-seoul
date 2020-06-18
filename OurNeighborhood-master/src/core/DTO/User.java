package core.DTO;
import java.io.Serializable;


public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id; // 아이디
	private String residenDistrict; // 거주구
	private String residenDong; // 거주동
	private String phoneNumber; // 전화번호
	private String name; // 이름

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResidenDistrict() {
		return residenDistrict;
	}

	public void setResidenDistrict(String residenDistrict) {
		this.residenDistrict = residenDistrict;
	}

	public String getResidenDong() {
		return residenDong;
	}

	public void setResidenDong(String residenDong) {
		this.residenDong = residenDong;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}