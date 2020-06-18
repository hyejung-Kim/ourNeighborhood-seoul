package core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import core.DTO.*;

public class Network {
	private Socket socket;
	private InputStream is;
	private OutputStream os;

	public Network() {
		try {
			socket = new Socket("localhost", 8000);
			is = socket.getInputStream();
			os = socket.getOutputStream();
		} catch (IOException e) {

			System.out.println("[��ſ���] : ������ ���� ����");
		}

	}

	private void send(Protocol protocol) throws Exception {

		try {
			os.write(protocol.getPacket());
		} catch (IOException e) {

			System.out.println("[��ſ���] : ������ �۽� ����");
		}
	}

	private Protocol recv(int type) throws Exception {
		byte[] header = new byte[Protocol.LEN_HEADER];
		Protocol protocol = new Protocol();
		try {
			int totalReceived, readSize;
			do {
				totalReceived = 0;
				readSize = 0;
				is.read(header, 0, Protocol.LEN_HEADER); // header�� �Է� ��Ʈ������ ������ ����Ʈ�� 6����Ʈ ��ŭ ����
				protocol.setPacketHeader(header);

				int bytesToRead = protocol.getBodyLength();

				byte[] buf = new byte[bytesToRead];
				while (totalReceived < bytesToRead) {
					readSize = is.read(buf, totalReceived, bytesToRead - totalReceived);
					totalReceived += readSize;
					if (readSize == -1) { // Ŭ���̾�Ʈ ���� ����

						throw new Exception("[��ſ���] : ������ ������");
					}
				}
				protocol.setPacketBody(buf);

				if (protocol.getType() == type) {
					return protocol;
				} else if (protocol.getType() == Protocol.TYPE_ERROR) {
					throw new Exception("[��ſ���] : �������� �߻�");
				}

			} while (true);
		} catch (Exception e) {
			throw new Exception("[��ſ���] : ������ ���� ����");
		}
	}

	/* ���� */
	public void exit() throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_EXIT);

		send(protocol); // ����
		System.exit(0);
	}

	/* �α��� */
	public boolean[] login(String id, String pw) throws Exception {
		String[] body = new String[2];
		body[0] = id;
		body[1] = pw;

		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_LOGIN_REQ);
		protocol.setBody(body);

		send(protocol); // ����
		protocol = recv(Protocol.TYPE_LOGIN_RES); // ����

		boolean[] result = new boolean[2];
		if (protocol.getCode() == 1) { // �α��� ����
			result[0] = true;
			result[1] = ((String) protocol.getBody()).equals("������");
		} else if (protocol.getCode() == 2) { // �α��� ���� (�ߺ� �α���)
			result[0] = false;
			result[1] = true;
		} else { // �α��� ���� (���̵�, �н����� ����)
			result[0] = false;
			result[1] = false;
		}

		return result;
	}

	/* �α׾ƿ� */
	public boolean logout() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_LOGOUT_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_LOGOUT_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	/* ȸ������ */
	public boolean signUp(String id, String pw, String name, String district, String dong, String phoneNumber) throws Exception {

		String[] body = new String[6];
		body[0] = id;
		body[1] = pw;
		body[2] = name;
		body[3] = district;
		body[4] = dong;
		body[5] = phoneNumber;

		Protocol protocol = new Protocol(Protocol.TYPE_SIGNUP_REQ);

		protocol.setBody(body);

		send(protocol);
		protocol = recv(Protocol.TYPE_SIGNUP_RES);

		if (protocol.getCode() == 1)
			return true; // ȸ������ ����
		else
			return false; // ȸ������ ����
	}

	/* ������ ������ ��û(�Ѱ�) */
	public Infrastructure getInfrastructure(String district, String dong) throws Exception {

		String[] body = new String[2];

		body[0] = district;
		body[1] = dong;
		Protocol protocol = new Protocol();

		protocol.setType(Protocol.TYPE_DONG_INFRA_REQ);
		protocol.setBody(body);

		send(protocol);

		protocol = recv(Protocol.TYPE_DONG_INFRA_RES);

		return (Infrastructure) protocol.getBody();

	}

	/* ������ ������ ��û(�� ����) */
	public Infrastructure[] getInfrastructures(String district) throws Exception {

		Protocol protocol = new Protocol();

		protocol.setType(Protocol.TYPE_DONGS_INFRA_REQ);
		protocol.setBody(district);

		send(protocol);

		protocol = recv(Protocol.TYPE_DONGS_INFRA_RES);

		return (Infrastructure[]) protocol.getBody();

	}

	/* ���� ��� ��û (�̹� ����ڰ� �������� ���並 ������ ������ ���x)*/
	public boolean postReview(String id, String dong, int leisureRating, int transportRating, int commercialRating,
			int housePriceRating, String comment) throws Exception {

		String[] body = new String[7];

		body[0] = id;
		body[1] = dong;
		body[2] = Integer.toString(leisureRating);
		body[3] = Integer.toString(transportRating);
		body[4] = Integer.toString(commercialRating);
		body[5] = Integer.toString(housePriceRating);
		body[6] = comment;

		Protocol protocol = new Protocol();

		protocol.setType(Protocol.TYPE_REVIEW_POST_REQ);
		protocol.setBody(body);

		send(protocol);

		protocol = recv(Protocol.TYPE_REVIEW_POST_RES);

		if (protocol.getCode() == 1) { // ���� ��� ����
			return true;
		}

		else { // ���� ��� ����
			return false;
		}

	}

	/* ���� ���� ��û */
	public boolean deleteReview(String id, String dong) throws Exception {

		String[] body = new String[2];

		body[0] = id;
		body[1] = dong;

		Protocol protocol = new Protocol();

		protocol.setType(Protocol.TYPE_REVIEW_DELETE_REQ);
		protocol.setBody(body);

		send(protocol);

		protocol = recv(Protocol.TYPE_REVIEW_DELETE_RES);

		if (protocol.getCode() == 1) { // ���� ���� ����
			return true;
		}

		else { // ���� ���� ����
			return false;
		}

	}

	/* ���� ���� ��û */
	public boolean updateReview(String id, String dong, int leisureRating, int transportRating, int commercialRating,
			int housePriceRating, String comment) throws Exception {

		String[] body = new String[7];

		body[0] = id;
		body[1] = dong;
		body[2] = Integer.toString(leisureRating);
		body[3] = Integer.toString(transportRating);
		body[4] = Integer.toString(commercialRating);
		body[5] = Integer.toString(housePriceRating);
		body[6] = comment;

		Protocol protocol = new Protocol();

		protocol.setType(Protocol.TYPE_REVIEW_UPDATE_REQ);
		protocol.setBody(body);

		send(protocol);

		protocol = recv(Protocol.TYPE_REVIEW_UPDATE_RES);

		if (protocol.getCode() == 1) { // ���� ������Ʈ ����
			return true;
		}

		else { // ���� ���� ����
			return false;
		}

	}
	
	/* ���� ��ȸ ��û(��ü) */
	public ResidentReview[] getReviews()  throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_REVIEWS_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_REVIEWS_INFO_RES);
		return (ResidentReview[])protocol.getBody();
		
	}
	/* ���� ��ȸ ��û(�Ѱ�) */
	public ResidentReview getReview(String reviewNumber)  throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_REVIEW_INFO_REQ);
		protocol.setBody(reviewNumber);
		send(protocol);

		protocol = recv(Protocol.TYPE_REVIEW_INFO_RES);
		return (ResidentReview)protocol.getBody();

	}
	
	/* ���� ��ȸ ��û(������) */
	   public ResidentReview[] getReviewsPartially(String dong)  throws Exception {
	      
	      Protocol protocol = new Protocol();
	      protocol.setType(Protocol.TYPE_REVIEWS_PART_INFO_REQ);
	      protocol.setBody(dong);
	      send(protocol);
	      
	      protocol = recv(Protocol.TYPE_REVIEWS_PART_INFO_RES);
	      return (ResidentReview[])protocol.getBody();
	      
	   }
	
	/* ���� ��ȸ ��û(�ڽ��� �ۼ�) */
	public ResidentReview[] getMyReviews()  throws Exception {
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_REVIEWS_MY_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_REVIEWS_MY_INFO_RES);
		return (ResidentReview[])protocol.getBody();
		
	}
	
	/* �Խñ� ��� ��û */
	public  boolean postPost(String id, String district, String dong, String title, String content) throws Exception {
		String[] data = new String[5];
		
		data[0] = id;
		data[1] = district;
		data[2] = dong;
		data[3] = title;
		data[4] = content;
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POST_POST_REQ);
		protocol.setBody(data);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POST_POST_RES);
		

		if (protocol.getCode() == 1) { // �Խñ� ��� ����
			return true;
		}

		else { // �Խñ� ��� ����
			return false;
		}
		
	}
	
	/* �Խñ� ���� ��û */
	public boolean deletePost(String postNumber) throws Exception{
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POST_DELETE_REQ);
		protocol.setBody(postNumber);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POST_DELETE_RES);
		
		if (protocol.getCode() == 1) { // �Խñ� ���� ����
			return true;
		}

		else { // �Խñ� ���� ����
			return false;
		}
		
		
	}
	
	/* �Խñ� ���� ��û */
	public boolean updatePost(String postNumber, String district, String dong, String title, String content) throws Exception{
		
		String[] data = new String[5];
		
		data[0] = postNumber;
		data[1] = district;
		data[2] = dong;
		data[3] = title;
		data[4] = content;
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POST_UPDATE_REQ);
		protocol.setBody(data);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POST_UPDATE_RES);
		
		if (protocol.getCode() == 1) { // �Խñ� ����
			return true;
		}

		else { // �Խñ� ���� ����
			return false;
		}
		
		
	}
	
	/* �Խñ� ��ȸ(�Ѱ�) ��û */
	public Post getPost(String postNumber) throws Exception{
		

		
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POST_INFO_REQ);
		protocol.setBody(postNumber);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POST_INFO_RES);
		
		return (Post)protocol.getBody();
		
		
	}
	
	/* �Խñ� ��ȸ (��ü) ��û*/
	public Post[] getPosts()  throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POSTS_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POSTS_INFO_RES);
		return (Post[])protocol.getBody();
		
	}
	
	
	/* �Խñ� ��ȸ ��û(������) */
	public Post[] getPostsPartially(String district, String dong)  throws Exception {
		String[] data = new String[2];
		data[0] = district;
		data[1] = dong;
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POSTS_PART_INFO_REQ);
		protocol.setBody(data);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POSTS_PART_INFO_RES);
		return (Post[])protocol.getBody();
		
	}
	
	/* �Խñ� ��ȸ ��û(�ڽ��� �ۼ�) */
	public Post[] getMyPosts()  throws Exception {
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POSTS_MY_INFO_REQ);
		
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POSTS_MY_INFO_RES);
		return (Post[])protocol.getBody();
		
	}
	
	/* �������� ���� ��û(������) */
	public AdminDistrict[] getAdminDistrict(String district)  throws Exception {
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_ADMIN_DISTRICT_INFO_REQ);
		protocol.setBody(district);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_ADMIN_DISTRICT_INFO_RES);
		return (AdminDistrict[])protocol.getBody();
		
	}
	
	/* �������� ���� ��û(��ü) */
	public AdminDistrict[] getAdminDistricts()  throws Exception {
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_ADMIN_DISTRICTS_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_ADMIN_DISTRICTS_INFO_RES);
		return (AdminDistrict[])protocol.getBody();
		
	}
	
	/* ��� ��� ��û */
	public boolean postComment(String id, String content, String postNumber)  throws Exception {
		
		String[] data = new String[3];
		
		data[0] = id;
		data[1] = content;
		data[2] = postNumber;
		
		Protocol protocol = new Protocol();
		
		protocol.setType(Protocol.TYPE_COMMENT_POST_REQ);
		protocol.setBody(data);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_COMMENT_POST_RES);
		
		if (protocol.getCode() == 1) { // ��� ��� ����
			return true;
		}

		else { // ��� ��� ����
			return false;
		}
		
	}
	
	/* ��� ���� ��û */
	public boolean deleteComment(String time, String id)  throws Exception {
		
		String[] data = new String[2];
		
		data[0] = time;
		data[1] = id;
		
		Protocol protocol = new Protocol();
		
		protocol.setType(Protocol.TYPE_COMMENT_DELETE_REQ);
		protocol.setBody(data);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_COMMENT_DELETE_RES);
		
		if (protocol.getCode() == 1) { // ��� ��� ����
			return true;
		}

		else { // ��� ��� ����
			return false;
		}
		
	}
	
	/* ��� ���� ��û */
	public boolean updateComment(String time, String id, String content)  throws Exception {
		
		String[] data = new String[3];
		
		data[0] = time;
		data[1] = id;
		data[2] = content;
		
		Protocol protocol = new Protocol();
		
		protocol.setType(Protocol.TYPE_COMMENT_UPDATE_REQ);
		protocol.setBody(data);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_COMMENT_UPDATE_RES);
		
		if (protocol.getCode() == 1) { // ��� ��� ����
			return true;
		}

		else { // ��� ��� ����
			return false;
		}
		
	}
	
	/* ��� ���� ��û */
	public Comment[] getComment(String postNumber)  throws Exception {
		
		
		
		Protocol protocol = new Protocol();
		
		protocol.setType(Protocol.TYPE_COMMENT_INFO_REQ);
		protocol.setBody(postNumber);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_COMMENT_INFO_RES);
		
		return (Comment[]) protocol.getBody();
		
		
		
	}
	
	/* ����� ���� ��û(�ڱ��ڽ�) */
	public User getUser()  throws Exception {
		
		
		
		Protocol protocol = new Protocol();
		
		protocol.setType(Protocol.TYPE_USER_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_USER_INFO_RES);
		
		return (User) protocol.getBody();
		
		
		
	}
	
	/* �������������� ���� ��û(������) */
	public DistrictInfra[] getDistrictInfra(String district, String condition)  throws Exception {

		String[] data = new String[2];

		data[0] = district;
		data[1] = condition;

		Protocol protocol = new Protocol();

		protocol.setType(Protocol.TYPE_DISTRICT_INFRA_INFO_REQ);
		protocol.setBody(data);
		send(protocol);

		protocol = recv(Protocol.TYPE_DISTRICT_INFRA_INFO_RES);

		return (DistrictInfra[]) protocol.getBody();
	}

}
