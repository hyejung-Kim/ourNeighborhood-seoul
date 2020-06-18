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
	
	public static final int TYPE_ERROR = -1; // 오류 응답
	public static final int TYPE_UNDEFINED = 0; // 프로토콜이 지정되어 있지 않은 경우
	public static final int TYPE_EXIT = 1; // 프로그램 종료
	public static final int TYPE_LOGIN_REQ = 2; // 로그인 요청
	public static final int TYPE_LOGIN_RES = 3; // 로그인 응답
	public static final int TYPE_LOGOUT_REQ = 4; // 로그아웃 요청
	public static final int TYPE_LOGOUT_RES = 5; // 로그아웃 응답
	public static final int TYPE_SIGNUP_REQ = 6; // 회원가입 요청
	public static final int TYPE_SIGNUP_RES = 7; // 회원가입 응답
	

	public static final int TYPE_DONG_INFRA_REQ = 10; // 행정동 인프라 요청(한개)
	public static final int TYPE_DONG_INFRA_RES = 11; // 행정동 인프라 응답(한개)
	public static final int TYPE_DONGS_INFRA_REQ = 12; // 행정동 인프라 요청(구 별로)
	public static final int TYPE_DONGS_INFRA_RES = 13; // 행정동 인프라 응답(구 별로)
	
	public static final int TYPE_REVIEW_POST_REQ = 14; // 리뷰 등록 요청
	public static final int TYPE_REVIEW_POST_RES = 15; // 리뷰 등록 응답
	public static final int TYPE_REVIEW_DELETE_REQ = 16; // 리뷰 삭제 요청
	public static final int TYPE_REVIEW_DELETE_RES = 17; // 리뷰 삭제 응답
	public static final int TYPE_REVIEW_UPDATE_REQ = 18; // 리뷰 수정 요청
	public static final int TYPE_REVIEW_UPDATE_RES = 19; // 리뷰 수정 응답
	public static final int TYPE_REVIEWS_INFO_REQ = 20; // 리뷰 조회(전체) 요청
	public static final int TYPE_REVIEWS_INFO_RES = 21; // 리뷰 조회(전체) 응답
	public static final int TYPE_REVIEWS_PART_INFO_REQ = 47; // 리뷰 조회(동별로) 요청
	public static final int TYPE_REVIEWS_PART_INFO_RES = 48; // 리뷰 조회(동별로) 응답
	public static final int TYPE_REVIEWS_MY_INFO_REQ = 51; // 리뷰 조회(자신이 작성) 요청
	public static final int TYPE_REVIEWS_MY_INFO_RES = 52; // 리뷰 조회(자신이 작성) 응답
	
	public static final int TYPE_POST_POST_REQ = 22; // 게시글 등록 요청
	public static final int TYPE_POST_POST_RES = 23; // 게시글 등록 응답
	public static final int TYPE_POST_DELETE_REQ = 24; // 게시글 삭제 요청
	public static final int TYPE_POST_DELETE_RES = 25; // 게시글 삭제 응답
	public static final int TYPE_POST_UPDATE_REQ = 26; // 게시글 수정 요청
	public static final int TYPE_POST_UPDATE_RES = 27; // 게시글 수정 응답
	public static final int TYPE_POST_INFO_REQ = 28; // 게시글 조회(한개) 요청
	public static final int TYPE_POST_INFO_RES = 29; // 게시글 조회(한개) 응답
	public static final int TYPE_POSTS_INFO_REQ = 30; // 게시글 조회(전체) 요청
	public static final int TYPE_POSTS_INFO_RES = 31; // 게시글 조회(전체) 응답
	public static final int TYPE_POSTS_PART_INFO_REQ = 45; // 게시글 조회(동별로) 요청
	public static final int TYPE_POSTS_PART_INFO_RES = 46; // 게시글 조회(동별로) 응답
	public static final int TYPE_POSTS_MY_INFO_REQ = 49; // 게시글 조회(자신이 작성) 요청
	public static final int TYPE_POSTS_MY_INFO_RES = 50; // 게시글 조회(자신이 작성) 응답
	
	
	public static final int TYPE_ADMIN_DISTRICT_INFO_REQ = 31; // 행정구역 정보(구별로) 요청
	public static final int TYPE_ADMIN_DISTRICT_INFO_RES = 32; // 행정구역 정보(구별로) 응답
	public static final int TYPE_ADMIN_DISTRICTS_INFO_REQ = 33; // 행정구역 정보(전체) 요청
	public static final int TYPE_ADMIN_DISTRICTS_INFO_RES = 34; // 행정구역 정보(전체) 응답
	
	public static final int TYPE_COMMENT_POST_REQ = 35; // 댓글 등록 요청
	public static final int TYPE_COMMENT_POST_RES = 36; // 댓글 등록 응답
	public static final int TYPE_COMMENT_DELETE_REQ = 37; // 댓글 삭제 요청
	public static final int TYPE_COMMENT_DELETE_RES = 38; // 댓글 삭제 응답
	public static final int TYPE_COMMENT_UPDATE_REQ = 39; // 댓글 수정 요청
	public static final int TYPE_COMMENT_UPDATE_RES = 40; // 댓글 수정 응답
	public static final int TYPE_COMMENT_INFO_REQ = 41; // 댓글 수정 요청
	public static final int TYPE_COMMENT_INFO_RES = 42; // 댓글 수정 응답
	
	public static final int TYPE_USER_INFO_REQ = 43; // 사용자 정보 요청(자기자신)
	public static final int TYPE_USER_INFO_RES = 44; // 사용자 정보 응답(자기자신)
	
	public static final int TYPE_DISTRICT_INFRA_INFO_REQ = 53; // 행정구역인프라 정보 요청(구별로)
	public static final int TYPE_DISTRICT_INFRA_INFO_RES = 54; // 행정구역인프라 정보 요청(구별로)
	
	public static final int TYPE_REVIEW_INFO_REQ = 55; // 리뷰 조회(한개) 응답
	public static final int TYPE_REVIEW_INFO_RES = 56; // 리뷰 조회(한개) 응답



	private byte type;
	private byte code;
	private int bodyLength;

	private byte[] body; // 직렬화 하여 저장

	public Protocol() {
		this(TYPE_UNDEFINED, 0); // 타입, 코드 
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

	private void setBodyLength(int bodyLength) { // Body Length는 직접 설정할 수 없음
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

	public byte[] getPacket() { // 현재 header와 body로 패킷을 생성하여 리턴
		byte[] packet = new byte[LEN_HEADER + getBodyLength()];
		packet[0] = getType();
		packet[LEN_CODE] = getCode();
		System.arraycopy(intToByte(getBodyLength()), 0, packet, LEN_TYPE + LEN_CODE, LEN_BODYLENGTH);
		if (getBodyLength() > 0) {
			System.arraycopy(body, 0, packet, LEN_HEADER, getBodyLength());
		}

		return packet;
	}

	public void setPacketHeader(byte[] packet) { // 매개 변수 packet을 통해 header만 생성
		byte[] data;

		setType(packet[0]);
		setCode(packet[LEN_CODE]);

		data = new byte[LEN_BODYLENGTH];
		System.arraycopy(packet, LEN_TYPE + LEN_CODE, data, 0, LEN_BODYLENGTH);
		setBodyLength(byteToInt(data));
	}

	public void setPacketBody(byte[] packet) { // 매개 변수 packet을 통해 body를 생성
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

	private byte[] serialize(Object b) { // 직렬화
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

	private Object deserialize(byte[] b) { // 역직렬화
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