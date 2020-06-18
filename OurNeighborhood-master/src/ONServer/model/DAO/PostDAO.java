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
		mysql.sqlQuery("INSERT INTO �Խñ� (`�ۼ���`, `�ۼ��ð�`, `��ġ��`, `������`, `������`, `�۳���`) VALUES(?,?,?,?,?,?)");
		mysql.set(1, id);
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		mysql.set(2, time);
		mysql.set(3, district);
		mysql.set(4, dong);
		mysql.set(5, title);
		mysql.set(6, content);
		int n = mysql.insert(); // INSERT, DELETE, UPDATE�� ���� ���� ��ȯ  / �ƹ� ������ ������ 0
		
		if(n == 1) {
			return true;
		}
		else return false;
		
		
	}
	
	public boolean deletePost(String postNumber) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		
		mysql.sqlQuery("DELETE FROM ��� WHERE `�Խñ۹�ȣ`=?");
		mysql.set(1, postNumber);
		mysql.delete();
		
		mysql.sqlQuery("DELETE FROM �Խñ� WHERE `�۹�ȣ`=?");
		mysql.set(1, postNumber);
		int n = mysql.delete();
		
		if(n == 1) {
			return true;
		}
		else return false;
		
		
	}
	
	public boolean updatePost(String postNumber, String district, String dong, String title, String content) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("UPDATE �Խñ� SET `��ġ��`=?, `������`=?, `������`=?, `�۳���`=?  WHERE `�۹�ȣ`=? LIMIT 1");
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
		mysql.sqlQuery("SELECT * FROM �Խñ� WHERE `�۹�ȣ`=? LIMIT 1");
		mysql.set(1, postNumber);
		ResultSet rs = mysql.execute();
		
		if(rs.next()) {
			Post post = new Post();
			post.setPostNumber(rs.getString("�۹�ȣ"));
			post.setWriter(rs.getString("�ۼ���"));
			post.setAutoRegion(rs.getString("��ġ��"));
			post.setAdminDong(rs.getString("������"));
			post.setTitle(rs.getString("������"));
			post.setContent(rs.getString("�۳���"));
			post.setTime(rs.getString("�ۼ��ð�"));
			return post;
		}
		else return null;
		
		
	}
	
	public Post[] getPosts() throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM �Խñ�");
		ResultSet rs = mysql.execute();
		
		Vector<Post> v = new Vector<Post>();
		
		while(rs.next()) {
			Post post = new Post();
			post.setPostNumber(rs.getString("�۹�ȣ"));
			post.setWriter(rs.getString("�ۼ���"));
			post.setAutoRegion(rs.getString("��ġ��"));
			post.setAdminDong(rs.getString("������"));
			post.setTitle(rs.getString("������"));
			post.setContent(rs.getString("�۳���"));
			post.setTime(rs.getString("�ۼ��ð�"));
			v.add(post);
		}
		
		return v.toArray(new Post[0]);
		
		
	}
	
	public Post[] getPostsPartially(String district, String dong) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM �Խñ� WHERE `��ġ��`=? AND `������`=?");
		mysql.set(1, district);
		mysql.set(2, dong);
		ResultSet rs = mysql.execute();
		
		Vector<Post> v = new Vector<Post>();
		
		while(rs.next()) {
			Post post = new Post();
			post.setPostNumber(rs.getString("�۹�ȣ"));
			post.setWriter(rs.getString("�ۼ���"));
			post.setAutoRegion(rs.getString("��ġ��"));
			post.setAdminDong(rs.getString("������"));
			post.setTitle(rs.getString("������"));
			post.setContent(rs.getString("�۳���"));
			post.setTime(rs.getString("�ۼ��ð�"));
			v.add(post);
		}
		
		return v.toArray(new Post[0]);
		
		
	}
	
	public Post[] getMyPosts(String id) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM �Խñ� WHERE `�ۼ���`=?");
		mysql.set(1, id);
		ResultSet rs = mysql.execute();
		
		Vector<Post> v = new Vector<Post>();
		
		while(rs.next()) {
			Post post = new Post();
			post.setPostNumber(rs.getString("�۹�ȣ"));
			post.setWriter(rs.getString("�ۼ���"));
			post.setAutoRegion(rs.getString("��ġ��"));
			post.setAdminDong(rs.getString("������"));
			post.setTitle(rs.getString("������"));
			post.setContent(rs.getString("�۳���"));
			post.setTime(rs.getString("�ۼ��ð�"));
			v.add(post);
		}
		
		return v.toArray(new Post[0]);
		
		
	}

}