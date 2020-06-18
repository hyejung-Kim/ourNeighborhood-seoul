package core;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Protocol {

	public static final int LEN_TYPE = 1;
	public static final int LEN_CODE = 1;
	public static final int LEN_BODYLENGTH = 4;
	public static final int LEN_HEADER = 6; // 6 Byte
	
	public static final int TYPE_ERROR = -1; // ���� ����
	public static final int TYPE_UNDEFINED = 0; // ���������� �����Ǿ� ���� ���� ���
	public static final int TYPE_EXIT = 1; // ���α׷� ����
	public static final int TYPE_LOGIN_REQ = 2; // �α��� ��û
	public static final int TYPE_LOGIN_RES = 3; // �α��� ����
	public static final int TYPE_LOGOUT_REQ = 4; // �α׾ƿ� ��û
	public static final int TYPE_LOGOUT_RES = 5; // �α׾ƿ� ����
	public static final int TYPE_SIGNUP_REQ = 6; // ȸ������ ��û
	public static final int TYPE_SIGNUP_RES = 7; // ȸ������ ����
	

	public static final int TYPE_DONG_INFRA_REQ = 10; // ������ ������ ��û(�Ѱ�)
	public static final int TYPE_DONG_INFRA_RES = 11; // ������ ������ ����(�Ѱ�)
	public static final int TYPE_DONGS_INFRA_REQ = 12; // ������ ������ ��û(�� ����)
	public static final int TYPE_DONGS_INFRA_RES = 13; // ������ ������ ����(�� ����)
	
	public static final int TYPE_REVIEW_POST_REQ = 14; // ���� ��� ��û
	public static final int TYPE_REVIEW_POST_RES = 15; // ���� ��� ����
	public static final int TYPE_REVIEW_DELETE_REQ = 16; // ���� ���� ��û
	public static final int TYPE_REVIEW_DELETE_RES = 17; // ���� ���� ����
	public static final int TYPE_REVIEW_UPDATE_REQ = 18; // ���� ���� ��û
	public static final int TYPE_REVIEW_UPDATE_RES = 19; // ���� ���� ����
	public static final int TYPE_REVIEWS_INFO_REQ = 20; // ���� ��ȸ(��ü) ��û
	public static final int TYPE_REVIEWS_INFO_RES = 21; // ���� ��ȸ(��ü) ����
	public static final int TYPE_REVIEWS_PART_INFO_REQ = 47; // ���� ��ȸ(������) ��û
	public static final int TYPE_REVIEWS_PART_INFO_RES = 48; // ���� ��ȸ(������) ����
	public static final int TYPE_REVIEWS_MY_INFO_REQ = 51; // ���� ��ȸ(�ڽ��� �ۼ�) ��û
	public static final int TYPE_REVIEWS_MY_INFO_RES = 52; // ���� ��ȸ(�ڽ��� �ۼ�) ����
	
	public static final int TYPE_POST_POST_REQ = 22; // �Խñ� ��� ��û
	public static final int TYPE_POST_POST_RES = 23; // �Խñ� ��� ����
	public static final int TYPE_POST_DELETE_REQ = 24; // �Խñ� ���� ��û
	public static final int TYPE_POST_DELETE_RES = 25; // �Խñ� ���� ����
	public static final int TYPE_POST_UPDATE_REQ = 26; // �Խñ� ���� ��û
	public static final int TYPE_POST_UPDATE_RES = 27; // �Խñ� ���� ����
	public static final int TYPE_POST_INFO_REQ = 28; // �Խñ� ��ȸ(�Ѱ�) ��û
	public static final int TYPE_POST_INFO_RES = 29; // �Խñ� ��ȸ(�Ѱ�) ����
	public static final int TYPE_POSTS_INFO_REQ = 30; // �Խñ� ��ȸ(��ü) ��û
	public static final int TYPE_POSTS_INFO_RES = 31; // �Խñ� ��ȸ(��ü) ����
	public static final int TYPE_POSTS_PART_INFO_REQ = 45; // �Խñ� ��ȸ(������) ��û
	public static final int TYPE_POSTS_PART_INFO_RES = 46; // �Խñ� ��ȸ(������) ����
	public static final int TYPE_POSTS_MY_INFO_REQ = 49; // �Խñ� ��ȸ(�ڽ��� �ۼ�) ��û
	public static final int TYPE_POSTS_MY_INFO_RES = 50; // �Խñ� ��ȸ(�ڽ��� �ۼ�) ����
	
	
	public static final int TYPE_ADMIN_DISTRICT_INFO_REQ = 31; // �������� ����(������) ��û
	public static final int TYPE_ADMIN_DISTRICT_INFO_RES = 32; // �������� ����(������) ����
	public static final int TYPE_ADMIN_DISTRICTS_INFO_REQ = 33; // �������� ����(��ü) ��û
	public static final int TYPE_ADMIN_DISTRICTS_INFO_RES = 34; // �������� ����(��ü) ����
	
	public static final int TYPE_COMMENT_POST_REQ = 35; // ��� ��� ��û
	public static final int TYPE_COMMENT_POST_RES = 36; // ��� ��� ����
	public static final int TYPE_COMMENT_DELETE_REQ = 37; // ��� ���� ��û
	public static final int TYPE_COMMENT_DELETE_RES = 38; // ��� ���� ����
	public static final int TYPE_COMMENT_UPDATE_REQ = 39; // ��� ���� ��û
	public static final int TYPE_COMMENT_UPDATE_RES = 40; // ��� ���� ����
	public static final int TYPE_COMMENT_INFO_REQ = 41; // ��� ���� ��û
	public static final int TYPE_COMMENT_INFO_RES = 42; // ��� ���� ����
	
	public static final int TYPE_USER_INFO_REQ = 43; // ����� ���� ��û(�ڱ��ڽ�)
	public static final int TYPE_USER_INFO_RES = 44; // ����� ���� ����(�ڱ��ڽ�)
	
	public static final int TYPE_DISTRICT_INFRA_INFO_REQ = 53; // �������������� ���� ��û(������)
	public static final int TYPE_DISTRICT_INFRA_INFO_RES = 54; // �������������� ���� ��û(������)
	
	public static final int TYPE_REVIEW_INFO_REQ = 55; // ���� ��ȸ(�Ѱ�) ����
	public static final int TYPE_REVIEW_INFO_RES = 56; // ���� ��ȸ(�Ѱ�) ����



	private byte type;
	private byte code;
	private int bodyLength;

	private byte[] body; // ����ȭ �Ͽ� ����

	public Protocol() {
		this(TYPE_UNDEFINED, 0); // Ÿ��, �ڵ� 
	}

	public Protocol(int type) {
		this(type, 0);
	}

	public Protocol(int type, int code) {
		setType(type);
		setCode(code);
		setBodyLength(0);
	}

	public byte getType() {
		return type;
	}

	public void setType(int type) {
		this.type = (byte) type;
	}

	public byte getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = (byte) code;
	}

	public int getBodyLength() {
		return bodyLength;
	}

	private void setBodyLength(int bodyLength) { // Body Length�� ���� ������ �� ����
		this.bodyLength = bodyLength;
	}

	public Object getBody() {
		if (getType() == 121 || getType() == 124)
			return body;
		else
			return deserialize(body);
	}

	public void setBody(Object body) {
		if (getType() == 121 || getType() == 124) {
			this.body = (byte[]) body;
			setBodyLength(this.body.length);
		} else {
			byte[] serializedObject = serialize(body);
			this.body = serializedObject;
			setBodyLength(serializedObject.length);
		}
	}

	public byte[] getPacket() { // ���� header�� body�� ��Ŷ�� �����Ͽ� ����
		byte[] packet = new byte[LEN_HEADER + getBodyLength()];
		packet[0] = getType();
		packet[LEN_CODE] = getCode();
		System.arraycopy(intToByte(getBodyLength()), 0, packet, LEN_TYPE + LEN_CODE, LEN_BODYLENGTH);
		if (getBodyLength() > 0) {
			System.arraycopy(body, 0, packet, LEN_HEADER, getBodyLength());
		}

		return packet;
	}

	public void setPacketHeader(byte[] packet) { // �Ű� ���� packet�� ���� header�� ����
		byte[] data;

		setType(packet[0]);
		setCode(packet[LEN_CODE]);

		data = new byte[LEN_BODYLENGTH];
		System.arraycopy(packet, LEN_TYPE + LEN_CODE, data, 0, LEN_BODYLENGTH);
		setBodyLength(byteToInt(data));
	}

	public void setPacketBody(byte[] packet) { // �Ű� ���� packet�� ���� body�� ����
		byte[] data;

		if (getBodyLength() > 0) {
			data = new byte[getBodyLength()];
			System.arraycopy(packet, 0, data, 0, getBodyLength());
			if (getType() == 121 || getType() == 124)
				setBody(data);
			else
				setBody(deserialize(data));
		}
	}

	private byte[] serialize(Object b) { // ����ȭ
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(b);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Object deserialize(byte[] b) { // ������ȭ
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(b);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object ob = ois.readObject();
			return ob;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private byte[] intToByte(int i) {
		return ByteBuffer.allocate(Integer.SIZE / 8).putInt(i).array();
	}

	private int byteToInt(byte[] b) {
		return ByteBuffer.wrap(b).getInt();
	}

}