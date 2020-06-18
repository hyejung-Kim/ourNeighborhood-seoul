package ONServer.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import ONServer.Protocol;
import ONServer.model.DAO.*;
import core.DTO.*;

public class Server {
	private final static int MAX_USER = 20;
	private final static int PORT_NUMBER = 8000;
	public static boolean isAdminLogin = false;
	public static HashMap<String, Boolean> logined = new HashMap<String, Boolean>();

	public static void start() {
		try {
			ExecutorService pool = Executors.newFixedThreadPool(MAX_USER);
			ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
			System.out.println("[서버] : 클라이언트 대기중...");

			while (true) {
				Socket connection = serverSocket.accept();
				InetSocketAddress remoteSocketAddress = (InetSocketAddress) connection.getRemoteSocketAddress();
				String clientAddress = remoteSocketAddress.getAddress().getHostAddress();
				System.out.println("[서버] 클라이언트IP: " + clientAddress);
				Callable<Void> task = new Task(connection); // 클라이언트마다 쓰레드 하나와 연결
				pool.submit(task);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class Task implements Callable<Void> {
		private Socket socket;
		private OutputStream os;
		private InputStream is;
		private String userID = "";
		private Boolean isAdmin = false;

		Task(Socket connection) throws IOException {
			this.socket = connection;
			is = socket.getInputStream();
			os = socket.getOutputStream();

		}

		public Void call() {
			byte[] header = new byte[Protocol.LEN_HEADER];
			Protocol protocol = new Protocol();
			System.out.println("[서버] : 클라이언트 연결됨");
			try {
				int totalReceived, readSize;
				while (true) {
					totalReceived = 0;
					readSize = 0;
					is.read(header, 0, Protocol.LEN_HEADER); // header에 입력 스트림에서 수신한 바이트를 6바이트 만큼 저장
					protocol.setPacketHeader(header);
					byte[] buf = new byte[protocol.getBodyLength()];

					while (totalReceived < protocol.getBodyLength()) {
						readSize = is.read(buf, totalReceived, protocol.getBodyLength() - totalReceived);
						totalReceived += readSize;
						System.out.println(
								userID + " : Data Received (" + totalReceived + "/" + protocol.getBodyLength() + ")");
						if (readSize == -1) { // 클라이언트 연결 종료

							return null;
						}
					}

					protocol.setPacketBody(buf);

					switch (protocol.getType()) {

					case Protocol.TYPE_UNDEFINED:
						ping();
						break;

					case Protocol.TYPE_EXIT:
						exit();
						return null;

					case Protocol.TYPE_LOGIN_REQ:
						login(protocol);
						break;

					case Protocol.TYPE_LOGOUT_REQ:
						logout(protocol);
						break;

					case Protocol.TYPE_SIGNUP_REQ:
						signUp(protocol);
						break;

					case Protocol.TYPE_DONG_INFRA_REQ:
						getInfrastructure(protocol);
						break;

					case Protocol.TYPE_DONGS_INFRA_REQ:
						getInfrastructures(protocol);
						break;

					case Protocol.TYPE_REVIEW_POST_REQ:
						postReview(protocol);
						break;

					case Protocol.TYPE_REVIEW_DELETE_REQ:
						deleteReview(protocol);
						break;

					case Protocol.TYPE_REVIEW_UPDATE_REQ:
						updateReview(protocol);
						break;

					case Protocol.TYPE_REVIEWS_INFO_REQ:
						getReviews(protocol);
						break;
						
					case Protocol.TYPE_REVIEW_INFO_REQ:
						getReview(protocol);
						break;
						
					case Protocol.TYPE_REVIEWS_PART_INFO_REQ:
						getReviewsPartially(protocol);
						break;
						
					case Protocol.TYPE_REVIEWS_MY_INFO_REQ:
						getMyReviews(protocol);
						break;

					case Protocol.TYPE_POST_POST_REQ:
						postPost(protocol);
						break;

					case Protocol.TYPE_POST_DELETE_REQ:
						deletePost(protocol);
						break;

					case Protocol.TYPE_POST_UPDATE_REQ:
						updatePost(protocol);
						break;

					case Protocol.TYPE_POST_INFO_REQ:
						getPost(protocol);
						break;

					case Protocol.TYPE_POSTS_INFO_REQ:
						getPosts(protocol);
						break;
						
					case Protocol.TYPE_POSTS_PART_INFO_REQ:
						getPostsPartially(protocol);
						break;
						
					case Protocol.TYPE_POSTS_MY_INFO_REQ:
						getMyPosts(protocol);
						break;

					case Protocol.TYPE_ADMIN_DISTRICT_INFO_REQ:
						getAdminDistrict(protocol);
						break;

					case Protocol.TYPE_ADMIN_DISTRICTS_INFO_REQ:
						getAdminDistricts(protocol);
						break;

					case Protocol.TYPE_COMMENT_POST_REQ:
						postComment(protocol);
						break;

					case Protocol.TYPE_COMMENT_DELETE_REQ:
						deleteComment(protocol);
						break;

					case Protocol.TYPE_COMMENT_UPDATE_REQ:
						updateComment(protocol);
						break;

					case Protocol.TYPE_COMMENT_INFO_REQ:
						getComment(protocol);
						break;
						
					case Protocol.TYPE_USER_INFO_REQ:
						getUser(protocol);
						break;
						
					case Protocol.TYPE_DISTRICT_INFRA_INFO_REQ:
						getDistrictInfra(protocol);
						break;

					}
				}
			} catch (IOException e) { // 연결 오류 발생시
				try {
					System.out.println(userID + " Client : Connection Error Occured");
					MySQL mysql = MySQL.getConnection();
					mysql.rollback();
					mysql.setAutoCommit(true);
					// logging(e);
					is.close();
					os.close();
					socket.close();
				} catch (Exception ex) {
					System.out.println(userID + " Client : Connection Error Process Failed");
					// e.printStackTrace();
				}
				return null;
			} catch (SQLException e) { // DB 접속 오류 발생시
				try {
					System.out.println(userID + " Client : DB Error Occured");
					MySQL mysql = MySQL.getConnection();
					mysql.rollback();
					mysql.setAutoCommit(true);
					// logging(e);
					Protocol sndData = new Protocol();
					sndData.setType(Protocol.TYPE_ERROR);
					os.write(sndData.getPacket());
					is.close();
					os.close();
					socket.close();
				} catch (Exception ex) {
					System.out.println(userID + " Client : DB Error Process Failed");
					e.printStackTrace();
				}
				return null;
			} catch (Exception e) { // 일반 오류 발생시
				try {
					System.out.println(userID + " Client : General Error Occured");
					MySQL mysql = MySQL.getConnection();
					mysql.rollback();
					mysql.setAutoCommit(true);
					// logging(e);
					Protocol sndData = new Protocol();
					sndData.setType(Protocol.TYPE_ERROR);
					os.write(sndData.getPacket());
					is.close();
					os.close();
					socket.close();
				} catch (Exception ex) {
					System.out.println(userID + " Client : General Error Process Failed");
					e.printStackTrace();
				}
			} finally {
				if (this.isAdmin == true)
					Server.isAdminLogin = false;
				else if (this.isAdmin == false && Server.logined.containsKey(userID) == true)
					Server.logined.remove(userID);
			}
			return null;
		}

		private void ping() throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : PING 메시지");

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_UNDEFINED);
			os.write(sndData.getPacket());
		}

		/* 프로그램 종료 */
		private void exit() throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 종료 메시지");
			if (this.isAdmin == true)
				Server.isAdminLogin = false;
			else if (this.isAdmin == false && Server.logined.containsKey(userID) == true)
				Server.logined.remove(userID);

			is.close();
			os.close();
			socket.close();
			System.out.println(userID + " Client : Closed");
		}

		/* 로그인 */
		private void login(Protocol rcvData) throws IOException, SQLException, Exception { // 로그인
			System.out.println("[" + userID + " 클라이언트] : 로그인 요청");

			String[] str = (String[]) rcvData.getBody();
			MySQL mysql = MySQL.getConnection();

			mysql.sqlQuery(
					"SELECT `아이디`, `비밀번호` FROM `관리자` WHERE `아이디` = ? UNION SELECT `아이디`, `비밀번호` FROM `사용자` WHERE `아이디`= ? LIMIT 1");
			mysql.set(1, str[0]);
			mysql.set(2, str[0]);
			ResultSet rs = mysql.execute();

			Protocol sndData = new Protocol(Protocol.TYPE_LOGIN_RES);
			if (rs.next() && rs.getString("비밀번호").equals(str[1])) { // 아이디, 패스워드 일치

				if (rs.getString("아이디").equals("admin") == true && Server.isAdminLogin == true) { // 관리자이고 같은 아이디로 이미
																									// 로그인 했을때
					sndData.setCode(2);
					os.write(sndData.getPacket());
					return;
				} else if (logined.containsKey(rs.getString("아이디")) == true) { // 일반사용자가 같은아이디로 이미 로그인 했을
					sndData.setCode(2);
					os.write(sndData.getPacket());
					return;
				}
				this.userID = rs.getString("아이디");
				this.isAdmin = rs.getString("아이디").equals("admin");
				if (this.isAdmin == true)
					Server.isAdminLogin = true;
				else if (this.isAdmin == false)
					Server.logined.put(rs.getString("아이디"), true);
				str[0] = rs.getString("아이디");
				sndData.setCode(1);
				sndData.setBody(str[0]);

			} else { // 아이디 패스워드 불일치
				sndData.setCode(0);
				sndData.setBody(null);
			}

			os.write(sndData.getPacket());
			System.out.println("[서버] : done");

		}

		/* 로그아웃 */
		private void logout(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println("[" + userID + " 클라이언트] : 로그아웃 요청");

			if (this.isAdmin == true)
				Server.isAdminLogin = false;
			else if (this.isAdmin == false && Server.logined.containsKey(userID) == true)
				Server.logined.remove(userID);

			this.userID = "";
			this.isAdmin = false;

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_LOGOUT_RES);
			sndData.setCode(1);
			os.write(sndData.getPacket());
		}

		/* 회원가입 */
		private void signUp(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println("[" + userID + " 클라이언트] : 회원가입 요청");

			String[] data = (String[]) rcvData.getBody();

			MySQL mysql = MySQL.getConnection();
			mysql.sqlQuery("SELECT * FROM 사용자  WHERE `아이디`=?");
			mysql.set(1, data[0]);
			ResultSet rs = mysql.execute();

			Protocol sndData = new Protocol(Protocol.TYPE_SIGNUP_RES);

			while (rs.next()) {
				if (rs.getString("아이디").equals(data[0])) { // 아이디 중복
					System.out.println("[서버] : 아이디중복");
					sndData.setCode(0);
					os.write(sndData.getPacket());
					return;
				} else if (data[0].equals("admin")) { // 가입하려는 아이디가 admin 일때
					System.out.println("[서버] : 잘못된 아이디 형식");
					sndData.setCode(0);
					os.write(sndData.getPacket());
					return;

				}

			}

			if (!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", data[5])) { // 전화번호 패턴과 맞지 않는경우
				System.out.println("[서버] : 잘못된 전화번호 형식");
				sndData.setCode(0);
				os.write(sndData.getPacket());
				return;
			}

			mysql.sqlQuery("INSERT INTO 사용자 (`아이디`, `비밀번호`, `이름`, `거주구`, `거주동`, `전화번호`) VALUES (?, ?, ?, ?, ?, ?)");
			mysql.set(1, data[0]);
			mysql.set(2, data[1]);
			mysql.set(3, data[2]);
			mysql.set(4, data[3]);
			mysql.set(5, data[4]);
			mysql.set(6, data[5]);
			mysql.insert();

			sndData.setCode(1);
			os.write(sndData.getPacket());
			System.out.println("[서버] : 회원가입 성공");

		}

		/* 행정동 인프라 요청(한개) */
		public void getInfrastructure(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 인프라요청(한개)");
			String[] data = (String[]) rcvData.getBody();

			InfrastructureDAO infrastructureDAO = InfrastructureDAO.getInstance();
			Infrastructure infrastructure = infrastructureDAO.getInfrastructure(data[0], data[1]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_DONG_INFRA_RES);

			if (infrastructure == null) {
				sndData.setCode(0);

			} else {
				sndData.setCode(1);
				sndData.setBody(infrastructure);

			}

			os.write(sndData.getPacket());

		}

		/* 행정동 인프라 요청(구 별로) */
		public void getInfrastructures(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 인프라요청(여러개)");
			String district = (String) rcvData.getBody();

			InfrastructureDAO infrastructureDAO = InfrastructureDAO.getInstance();
			Infrastructure[] infrastructures = infrastructureDAO.getInfrastructures(district);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_DONGS_INFRA_RES);

			if (infrastructures == null) {
				sndData.setCode(0);

			} else {
				sndData.setCode(1);
				sndData.setBody(infrastructures);

			}

			os.write(sndData.getPacket());

		}

		/* 리뷰 등록 */
		public void postReview(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 리뷰 등록 요청");
			String[] data = (String[]) rcvData.getBody();

			ResidentReviewDAO residentReivewDAO = ResidentReviewDAO.getInstance();
			boolean isExecute = residentReivewDAO.postReview(data[0], data[1], data[2], data[3], data[4], data[5],
					data[6]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_REVIEW_POST_RES);

			if (isExecute) {
				sndData.setCode(1);

			} else {
				sndData.setCode(0);

			}

			os.write(sndData.getPacket());

		}

		/* 리뷰 삭제 */
		public void deleteReview(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 리뷰 삭제 요청");
			String[] data = (String[]) rcvData.getBody();

			ResidentReviewDAO residentReivewDAO = ResidentReviewDAO.getInstance();
			boolean isExecute = residentReivewDAO.deleteReview(data[0], data[1]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_REVIEW_DELETE_RES);

			if (isExecute) {
				sndData.setCode(1);

			} else {
				sndData.setCode(0);

			}

			os.write(sndData.getPacket());

		}

		/* 리뷰 수정 */
		public void updateReview(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 리뷰 수정 요청");
			String[] data = (String[]) rcvData.getBody();

			ResidentReviewDAO residentReivewDAO = ResidentReviewDAO.getInstance();
			boolean isExecute = residentReivewDAO.updateReview(data[0], data[1], data[2], data[3], data[4], data[5],
					data[6]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_REVIEW_UPDATE_RES);

			if (isExecute) {
				sndData.setCode(1);

			} else {
				sndData.setCode(0);

			}

			os.write(sndData.getPacket());

		}

		/* 리뷰 조회(전체) */
		public void getReviews(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 리뷰 조회 요청(전체)");
			ResidentReviewDAO residentReivewDAO = ResidentReviewDAO.getInstance();
			ResidentReview[] reviews = residentReivewDAO.getReviews();

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_REVIEWS_INFO_RES);

			if (reviews == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(reviews);
			}

			os.write(sndData.getPacket());

		}
		
		/* 리뷰 조회(한개) */
		public void getReview(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 리뷰 조회 요청(한개)");
			String reviewNumber = (String)rcvData.getBody();
			ResidentReviewDAO residentReivewDAO = ResidentReviewDAO.getInstance();
			ResidentReview review = residentReivewDAO.getReview(reviewNumber);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_REVIEW_INFO_RES);

			if (review == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(review);
			}

			os.write(sndData.getPacket());

		}
		
		/* 리뷰 조회(동별로) */
		public void getReviewsPartially(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 리뷰 조회 요청(동별로)");
			String dong = (String)rcvData.getBody();
			ResidentReviewDAO residentReivewDAO = ResidentReviewDAO.getInstance();
			ResidentReview[] reviews = residentReivewDAO.getReviewsPartially(dong);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_REVIEWS_PART_INFO_RES);

			if (reviews == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(reviews);
			}

			os.write(sndData.getPacket());

		}
		
		/* 리뷰 조회(자신이 작성) */
		public void getMyReviews(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 리뷰 조회 요청(자신이 작성)");
			ResidentReviewDAO residentReivewDAO = ResidentReviewDAO.getInstance();
			ResidentReview[] reviews = residentReivewDAO.getMyReviews(userID);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_REVIEWS_MY_INFO_RES);

			if (reviews == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(reviews);
			}

			os.write(sndData.getPacket());

		}

		/* 게시글 등록 */
		public void postPost(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 게시글 등록 요청");
			String[] data = (String[]) rcvData.getBody();

			PostDAO postDAO = PostDAO.getInstance();
			boolean isExecute = postDAO.postPost(data[0], data[1], data[2], data[3], data[4]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_POST_POST_RES);

			if (isExecute) {
				sndData.setCode(1);

			} else {
				sndData.setCode(0);

			}

			os.write(sndData.getPacket());

		}

		/* 게시글 삭제 */
		public void deletePost(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 게시글 삭제 요청");
			String postNumber = (String) rcvData.getBody();

			PostDAO postDAO = PostDAO.getInstance();
			boolean isExecute = postDAO.deletePost(postNumber);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_POST_DELETE_RES);

			if (isExecute) {
				sndData.setCode(1);

			} else {
				sndData.setCode(0);

			}

			os.write(sndData.getPacket());

		}

		/* 게시글 수정 */
		public void updatePost(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 게시글 수정 요청");
			String[] data = (String[]) rcvData.getBody();

			PostDAO postDAO = PostDAO.getInstance();
			boolean isExecute = postDAO.updatePost(data[0], data[1], data[2], data[3], data[4]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_POST_UPDATE_RES);

			if (isExecute) {
				sndData.setCode(1);

			} else {
				sndData.setCode(0);

			}

			os.write(sndData.getPacket());

		}

		/* 게시글 조회(한개) 요청 */
		public void getPost(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 게시글 조회 요청(한개)");
			String postNumber = (String) rcvData.getBody();

			PostDAO postDAO = PostDAO.getInstance();
			Post post = postDAO.getPost(postNumber);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_POST_INFO_RES);

			if (post == null) {
				sndData.setCode(0);

			} else {
				sndData.setCode(1);
				sndData.setBody(post);

			}

			os.write(sndData.getPacket());

		}

		/* 게시글 조회 (전체) 요청 */
		public void getPosts(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 게시글 조회 요청(전체)");
			PostDAO postDAO = PostDAO.getInstance();
			Post[] posts = postDAO.getPosts();

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_POSTS_INFO_RES);

			if (posts == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(posts);
			}

			os.write(sndData.getPacket());

		}
		
		/* 게시글 조회(동별로) */
		public void getPostsPartially(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 게시글 조회 요청(동별로)");
			String[] data = (String[])rcvData.getBody();
			PostDAO postDAO = PostDAO.getInstance();
			Post[] posts = postDAO.getPostsPartially(data[0],data[1]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_POSTS_PART_INFO_RES);

			if (posts == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(posts);
			}

			os.write(sndData.getPacket());

		}
		
		/* 게시글 조회(자신이 작성) */
		public void getMyPosts(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 게시글 조회 요청(자신이 작성)");
			PostDAO postDAO = PostDAO.getInstance();
			Post[] posts = postDAO.getMyPosts(userID);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_POSTS_MY_INFO_RES);

			if (posts == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(posts);
			}

			os.write(sndData.getPacket());

		}

		/* 행정구역 정보 요청(구별로) */
		public void getAdminDistrict(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 행정구역 정보 요청(구별로)");
			String district = (String) rcvData.getBody();
			AdminDistrictDAO adminDistrictDAO = AdminDistrictDAO.getInstance();
			AdminDistrict[] adminDistrict = adminDistrictDAO.getAdminDistrict(district);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_ADMIN_DISTRICT_INFO_RES);

			if (adminDistrict == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(adminDistrict);
			}

			os.write(sndData.getPacket());

		}

		/* 행정구역 정보 요청(전체) */
		public void getAdminDistricts(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 행정구역 정보 요청(전체)");
			AdminDistrictDAO adminDistrictDAO = AdminDistrictDAO.getInstance();
			AdminDistrict[] adminDistricts = adminDistrictDAO.getAdminDistricts();

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_ADMIN_DISTRICTS_INFO_RES);

			if (adminDistricts == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(adminDistricts);
			}

			os.write(sndData.getPacket());

		}

		/* 댓글 등록 */
		public void postComment(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 댓글 등록 요청");
			String[] data = (String[]) rcvData.getBody();

			CommentDAO commentDAO = CommentDAO.getInstance();
			boolean isExecute = commentDAO.postComment(data[0], data[1], data[2]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_COMMENT_POST_RES);

			if (isExecute) {
				sndData.setCode(1);

			} else {
				sndData.setCode(0);

			}

			os.write(sndData.getPacket());

		}

		/* 댓글 삭제 */
		public void deleteComment(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 댓글 삭제 요청");
			String[] data = (String[]) rcvData.getBody();

			CommentDAO commentDAO = CommentDAO.getInstance();
			boolean isExecute = commentDAO.deleteComment(data[0], data[1]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_COMMENT_DELETE_RES);

			if (isExecute) {
				sndData.setCode(1);

			} else {
				sndData.setCode(0);

			}

			os.write(sndData.getPacket());

		}

		/* 댓글 수정 */
		public void updateComment(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 댓글 수정 요청");
			String[] data = (String[]) rcvData.getBody();

			CommentDAO commentDAO = CommentDAO.getInstance();
			boolean isExecute = commentDAO.updateComment(data[0], data[1], data[2]);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_COMMENT_UPDATE_RES);

			if (isExecute) {
				sndData.setCode(1);

			} else {
				sndData.setCode(0);

			}

			os.write(sndData.getPacket());

		}

		/* 댓글 정보 요청 */
		public void getComment(Protocol rcvData) throws IOException, SQLException, Exception {
			System.out.println(userID + " 클라이언트 : 댓글 정보 요청");
			String postNumber = (String) rcvData.getBody();
			CommentDAO commentDAO = CommentDAO.getInstance();
			Comment[] comment = commentDAO.getComment(postNumber);

			Protocol sndData = new Protocol();
			sndData.setType(Protocol.TYPE_COMMENT_INFO_RES);

			if (comment == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(comment);
			}

			os.write(sndData.getPacket());

		}

		/* 사용자 정보 조회 (자기자신) */
		private void getUser(Protocol rcvData) throws IOException, SQLException, Exception { // 학생정보
			System.out.println(userID + " 클라이언트 : 사용자 정보 요청(자기자신)");

			UserDAO userDAO = UserDAO.getInstance();
			User user = userDAO.getUser(userID);

			Protocol sndData = new Protocol(Protocol.TYPE_USER_INFO_RES);
			if (user == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(user);
			}
			os.write(sndData.getPacket());
		}
		
		
		/* 행정구역인프라  정보 요청 (구별로) */
		private void getDistrictInfra(Protocol rcvData) throws IOException, SQLException, Exception { // 학생정보
			System.out.println(userID + " 클라이언트 : 행정구역인프로 정보 요청(구별로)");
			
			String[] data = (String[])rcvData.getBody();
			
			DistrictInfraDAO districtInfraDAO = DistrictInfraDAO.getInstance();
			DistrictInfra[] districtInfra = districtInfraDAO.getDistrictInfra(data[0], data[1]);

			Protocol sndData = new Protocol(Protocol.TYPE_DISTRICT_INFRA_INFO_RES);
			if (districtInfra == null) {
				sndData.setCode(0);
			} else {
				sndData.setCode(1);
				sndData.setBody(districtInfra);
			}
			os.write(sndData.getPacket());
		}

	}

}