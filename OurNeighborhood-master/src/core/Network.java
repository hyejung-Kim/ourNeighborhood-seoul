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

			System.out.println("[통신오류] : 서버와 연결 실패");
		}

	}

	private void send(Protocol protocol) throws Exception {

		try {
			os.write(protocol.getPacket());
		} catch (IOException e) {

			System.out.println("[통신오류] : 데이터 송신 실패");
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
				is.read(header, 0, Protocol.LEN_HEADER); // header에 입력 스트림에서 수신한 바이트를 6바이트 만큼 저장
				protocol.setPacketHeader(header);

				int bytesToRead = protocol.getBodyLength();

				byte[] buf = new byte[bytesToRead];
				while (totalReceived < bytesToRead) {
					readSize = is.read(buf, totalReceived, bytesToRead - totalReceived);
					totalReceived += readSize;
					if (readSize == -1) { // 클라이언트 연결 종료

						throw new Exception("[통신오류] : 연결이 끊어짐");
					}
				}
				protocol.setPacketBody(buf);

				if (protocol.getType() == type) {
					return protocol;
				} else if (protocol.getType() == Protocol.TYPE_ERROR) {
					throw new Exception("[통신오류] : 서버오류 발생");
				}

			} while (true);
		} catch (Exception e) {
			throw new Exception("[통신오류] : 데이터 수신 실패");
		}
	}

	/* 종료 */
	public void exit() throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_EXIT);

		send(protocol); // 전송
		System.exit(0);
	}

	/* 로그인 */
	public boolean[] login(String id, String pw) throws Exception {
		String[] body = new String[2];
		body[0] = id;
		body[1] = pw;

		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_LOGIN_REQ);
		protocol.setBody(body);

		send(protocol); // 전송
		protocol = recv(Protocol.TYPE_LOGIN_RES); // 수신

		boolean[] result = new boolean[2];
		if (protocol.getCode() == 1) { // 로그인 성공
			result[0] = true;
			result[1] = ((String) protocol.getBody()).equals("관리자");
		} else if (protocol.getCode() == 2) { // 로그인 실패 (중복 로그인)
			result[0] = false;
			result[1] = true;
		} else { // 로그인 실패 (아이디, 패스워드 오류)
			result[0] = false;
			result[1] = false;
		}

		return result;
	}

	/* 로그아웃 */
	public boolean logout() throws Exception {
		Protocol protocol = new Protocol(Protocol.TYPE_LOGOUT_REQ);

		send(protocol);
		protocol = recv(Protocol.TYPE_LOGOUT_RES);

		if (protocol.getCode() == 1)
			return true;
		else
			return false;
	}

	/* 회원가입 */
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
			return true; // 회원가입 성공
		else
			return false; // 회원가입 실패
	}

	/* 행정동 인프라 요청(한개) */
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

	/* 행정동 인프라 요청(구 별로) */
	public Infrastructure[] getInfrastructures(String district) throws Exception {

		Protocol protocol = new Protocol();

		protocol.setType(Protocol.TYPE_DONGS_INFRA_REQ);
		protocol.setBody(district);

		send(protocol);

		protocol = recv(Protocol.TYPE_DONGS_INFRA_RES);

		return (Infrastructure[]) protocol.getBody();

	}

	/* 리뷰 등록 요청 (이미 사용자가 같은동의 리뷰를 쓴적이 있으면 등록x)*/
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

		if (protocol.getCode() == 1) { // 리뷰 등록 성공
			return true;
		}

		else { // 리뷰 등록 실패
			return false;
		}

	}

	/* 리뷰 삭제 요청 */
	public boolean deleteReview(String id, String dong) throws Exception {

		String[] body = new String[2];

		body[0] = id;
		body[1] = dong;

		Protocol protocol = new Protocol();

		protocol.setType(Protocol.TYPE_REVIEW_DELETE_REQ);
		protocol.setBody(body);

		send(protocol);

		protocol = recv(Protocol.TYPE_REVIEW_DELETE_RES);

		if (protocol.getCode() == 1) { // 리뷰 삭제 성공
			return true;
		}

		else { // 리뷰 삭제 실패
			return false;
		}

	}

	/* 리뷰 수정 요청 */
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

		if (protocol.getCode() == 1) { // 리뷰 업데이트 성공
			return true;
		}

		else { // 리뷰 삭제 실패
			return false;
		}

	}
	
	/* 리뷰 조회 요청(전체) */
	public ResidentReview[] getReviews()  throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_REVIEWS_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_REVIEWS_INFO_RES);
		return (ResidentReview[])protocol.getBody();
		
	}
	/* 리뷰 조회 요청(한개) */
	public ResidentReview getReview(String reviewNumber)  throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_REVIEW_INFO_REQ);
		protocol.setBody(reviewNumber);
		send(protocol);

		protocol = recv(Protocol.TYPE_REVIEW_INFO_RES);
		return (ResidentReview)protocol.getBody();

	}
	
	/* 리뷰 조회 요청(동별로) */
	   public ResidentReview[] getReviewsPartially(String dong)  throws Exception {
	      
	      Protocol protocol = new Protocol();
	      protocol.setType(Protocol.TYPE_REVIEWS_PART_INFO_REQ);
	      protocol.setBody(dong);
	      send(protocol);
	      
	      protocol = recv(Protocol.TYPE_REVIEWS_PART_INFO_RES);
	      return (ResidentReview[])protocol.getBody();
	      
	   }
	
	/* 리뷰 조회 요청(자신이 작성) */
	public ResidentReview[] getMyReviews()  throws Exception {
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_REVIEWS_MY_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_REVIEWS_MY_INFO_RES);
		return (ResidentReview[])protocol.getBody();
		
	}
	
	/* 게시글 등록 요청 */
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
		

		if (protocol.getCode() == 1) { // 게시글 등록 성공
			return true;
		}

		else { // 게시글 등록 실패
			return false;
		}
		
	}
	
	/* 게시글 삭제 요청 */
	public boolean deletePost(String postNumber) throws Exception{
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POST_DELETE_REQ);
		protocol.setBody(postNumber);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POST_DELETE_RES);
		
		if (protocol.getCode() == 1) { // 게시글 삭제 성공
			return true;
		}

		else { // 게시글 삭제 실패
			return false;
		}
		
		
	}
	
	/* 게시글 수정 요청 */
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
		
		if (protocol.getCode() == 1) { // 게시글 수정
			return true;
		}

		else { // 게시글 수정 실패
			return false;
		}
		
		
	}
	
	/* 게시글 조회(한개) 요청 */
	public Post getPost(String postNumber) throws Exception{
		

		
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POST_INFO_REQ);
		protocol.setBody(postNumber);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POST_INFO_RES);
		
		return (Post)protocol.getBody();
		
		
	}
	
	/* 게시글 조회 (전체) 요청*/
	public Post[] getPosts()  throws Exception {
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POSTS_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POSTS_INFO_RES);
		return (Post[])protocol.getBody();
		
	}
	
	
	/* 게시글 조회 요청(동별로) */
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
	
	/* 게시글 조회 요청(자신이 작성) */
	public Post[] getMyPosts()  throws Exception {
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_POSTS_MY_INFO_REQ);
		
		send(protocol);
		
		protocol = recv(Protocol.TYPE_POSTS_MY_INFO_RES);
		return (Post[])protocol.getBody();
		
	}
	
	/* 행정구역 정보 요청(구별로) */
	public AdminDistrict[] getAdminDistrict(String district)  throws Exception {
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_ADMIN_DISTRICT_INFO_REQ);
		protocol.setBody(district);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_ADMIN_DISTRICT_INFO_RES);
		return (AdminDistrict[])protocol.getBody();
		
	}
	
	/* 행정구역 정보 요청(전체) */
	public AdminDistrict[] getAdminDistricts()  throws Exception {
		
		Protocol protocol = new Protocol();
		protocol.setType(Protocol.TYPE_ADMIN_DISTRICTS_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_ADMIN_DISTRICTS_INFO_RES);
		return (AdminDistrict[])protocol.getBody();
		
	}
	
	/* 댓글 등록 요청 */
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
		
		if (protocol.getCode() == 1) { // 댓글 등록 성공
			return true;
		}

		else { // 댓글 등록 실패
			return false;
		}
		
	}
	
	/* 댓글 삭제 요청 */
	public boolean deleteComment(String time, String id)  throws Exception {
		
		String[] data = new String[2];
		
		data[0] = time;
		data[1] = id;
		
		Protocol protocol = new Protocol();
		
		protocol.setType(Protocol.TYPE_COMMENT_DELETE_REQ);
		protocol.setBody(data);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_COMMENT_DELETE_RES);
		
		if (protocol.getCode() == 1) { // 댓글 등록 성공
			return true;
		}

		else { // 댓글 등록 실패
			return false;
		}
		
	}
	
	/* 댓글 수정 요청 */
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
		
		if (protocol.getCode() == 1) { // 댓글 등록 성공
			return true;
		}

		else { // 댓글 등록 실패
			return false;
		}
		
	}
	
	/* 댓글 정보 요청 */
	public Comment[] getComment(String postNumber)  throws Exception {
		
		
		
		Protocol protocol = new Protocol();
		
		protocol.setType(Protocol.TYPE_COMMENT_INFO_REQ);
		protocol.setBody(postNumber);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_COMMENT_INFO_RES);
		
		return (Comment[]) protocol.getBody();
		
		
		
	}
	
	/* 사용자 정보 요청(자기자신) */
	public User getUser()  throws Exception {
		
		
		
		Protocol protocol = new Protocol();
		
		protocol.setType(Protocol.TYPE_USER_INFO_REQ);
		send(protocol);
		
		protocol = recv(Protocol.TYPE_USER_INFO_RES);
		
		return (User) protocol.getBody();
		
		
		
	}
	
	/* 행정구역인프라 정보 요청(구별로) */
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
