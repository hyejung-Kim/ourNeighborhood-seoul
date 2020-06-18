package ONServer.model.DAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import core.DTO.*;

public class PostDAO {
	
	private PostDAO() {
		
	}
	
	private static class LazyHolder {
		public static final PostDAO INSTANCE = new PostDAO();
	}

	public static PostDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public boolean postPost(String id, String district, String dong, String title, String content) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("INSERT INTO 게시글 (`작성자`, `작성시간`, `자치구`, `행정동`, `글제목`, `글내용`) VALUES(?,?,?,?,?,?)");
		mysql.set(1, id);
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		mysql.set(2, time);
		mysql.set(3, district);
		mysql.set(4, dong);
		mysql.set(5, title);
		mysql.set(6, content);
		int n = mysql.insert(); // INSERT, DELETE, UPDATE된 행의 수를 반환  / 아무 리턴이 없으면 0
		
		if(n == 1) {
			return true;
		}
		else return false;
		
		
	}
	
	public boolean deletePost(String postNumber) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		
		mysql.sqlQuery("DELETE FROM 댓글 WHERE `게시글번호`=?");
		mysql.set(1, postNumber);
		mysql.delete();
		
		mysql.sqlQuery("DELETE FROM 게시글 WHERE `글번호`=?");
		mysql.set(1, postNumber);
		int n = mysql.delete();
		
		if(n == 1) {
			return true;
		}
		else return false;
		
		
	}
	
	public boolean updatePost(String postNumber, String district, String dong, String title, String content) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("UPDATE 게시글 SET `자치구`=?, `행정동`=?, `글제목`=?, `글내용`=?  WHERE `글번호`=? LIMIT 1");
		mysql.set(1, district);
		mysql.set(2, dong);
		mysql.set(3, title);
		mysql.set(4, content);
		mysql.set(5, postNumber);
		int n = mysql.update();
		
		if(n == 1) {
			return true;
		}
		else return false;
		
		
	}
	
	
	public Post getPost(String postNumber) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 게시글 WHERE `글번호`=? LIMIT 1");
		mysql.set(1, postNumber);
		ResultSet rs = mysql.execute();
		
		if(rs.next()) {
			Post post = new Post();
			post.setPostNumber(rs.getString("글번호"));
			post.setWriter(rs.getString("작성자"));
			post.setAutoRegion(rs.getString("자치구"));
			post.setAdminDong(rs.getString("행정동"));
			post.setTitle(rs.getString("글제목"));
			post.setContent(rs.getString("글내용"));
			post.setTime(rs.getString("작성시간"));
			return post;
		}
		else return null;
		
		
	}
	
	public Post[] getPosts() throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 게시글");
		ResultSet rs = mysql.execute();
		
		Vector<Post> v = new Vector<Post>();
		
		while(rs.next()) {
			Post post = new Post();
			post.setPostNumber(rs.getString("글번호"));
			post.setWriter(rs.getString("작성자"));
			post.setAutoRegion(rs.getString("자치구"));
			post.setAdminDong(rs.getString("행정동"));
			post.setTitle(rs.getString("글제목"));
			post.setContent(rs.getString("글내용"));
			post.setTime(rs.getString("작성시간"));
			v.add(post);
		}
		
		return v.toArray(new Post[0]);
		
		
	}
	
	public Post[] getPostsPartially(String district, String dong) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 게시글 WHERE `자치구`=? AND `행정동`=?");
		mysql.set(1, district);
		mysql.set(2, dong);
		ResultSet rs = mysql.execute();
		
		Vector<Post> v = new Vector<Post>();
		
		while(rs.next()) {
			Post post = new Post();
			post.setPostNumber(rs.getString("글번호"));
			post.setWriter(rs.getString("작성자"));
			post.setAutoRegion(rs.getString("자치구"));
			post.setAdminDong(rs.getString("행정동"));
			post.setTitle(rs.getString("글제목"));
			post.setContent(rs.getString("글내용"));
			post.setTime(rs.getString("작성시간"));
			v.add(post);
		}
		
		return v.toArray(new Post[0]);
		
		
	}
	
	public Post[] getMyPosts(String id) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM 게시글 WHERE `작성자`=?");
		mysql.set(1, id);
		ResultSet rs = mysql.execute();
		
		Vector<Post> v = new Vector<Post>();
		
		while(rs.next()) {
			Post post = new Post();
			post.setPostNumber(rs.getString("글번호"));
			post.setWriter(rs.getString("작성자"));
			post.setAutoRegion(rs.getString("자치구"));
			post.setAdminDong(rs.getString("행정동"));
			post.setTitle(rs.getString("글제목"));
			post.setContent(rs.getString("글내용"));
			post.setTime(rs.getString("작성시간"));
			v.add(post);
		}
		
		return v.toArray(new Post[0]);
		
		
	}

}