package core.DTO;
import java.io.Serializable;


public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id; // ���̵�
	private String residenDistrict; // ���ֱ�
	private String residenDong; // ���ֵ�
	private String phoneNumber; // ��ȭ��ȣ
	private String name; // �̸�

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