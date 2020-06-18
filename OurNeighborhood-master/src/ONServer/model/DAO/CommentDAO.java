package ONServer.model.DAO;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import core.DTO.*;


public class CommentDAO {

	private CommentDAO() {
	}

	private static class LazyHolder {
		public static final CommentDAO INSTANCE = new CommentDAO();
	}

	public static CommentDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public boolean postComment(String id, String content, String postNumber) throws IOException, SQLException, Exception {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("INSERT INTO ��� (`�ۼ��ð�`, `�ۼ���`, `����`, `�Խñ۹�ȣ`) VALUES(?,?,?,?)");
		
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		
		mysql.set(1, time);
		mysql.set(2, id);
		mysql.set(3, content);
		mysql.set(4, postNumber);
		int n = mysql.insert(); // INSERT, DELETE, UPDATE�� ���� ���� ��ȯ  / �ƹ� ������ ������ 0
		
		if(n == 1) {
			return true;
		}
		else return false;
		
		
	}
	
	public boolean deleteComment(String time, String id) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("DELETE FROM ��� WHERE `�ۼ��ð�`=? AND `�ۼ���`=?");
		mysql.set(1, time);
		mysql.set(2, id);
		int n = mysql.delete();
		
		if(n == 1) {
			return true;
		}
		else return false;
		
		
	}
	
	public boolean updateComment(String time, String id, String content) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("UPDATE ��� SET `����`=?  WHERE `�ۼ��ð�`=? AND `�ۼ���`=? LIMIT 1");
		mysql.set(1, content);
		mysql.set(2, time);
		mysql.set(3, id);
		int n = mysql.update();
		
		if(n == 1) {
			return true;
		}
		else return false;
		
		
	}
	
	public Comment[] getComment(String postNumber) throws IOException, SQLException, Exception  {
		MySQL mysql = MySQL.getConnection();
		mysql.sqlQuery("SELECT * FROM ��� WHERE `�Խñ۹�ȣ`=?");
		mysql.set(1, postNumber);
		ResultSet rs = mysql.execute();
		
		Vector<Comment> v = new Vector<Comment>();
		
		while(rs.next()) {
			Comment comment = new Comment();
			comment.setTime(rs.getString("�ۼ��ð�"));
			comment.setWriter(rs.getString("�ۼ���"));
			comment.setContent(rs.getString("����"));
			comment.setPostNumber(rs.getInt("�Խñ۹�ȣ"));
			v.add(comment);
		}
		
		return v.toArray(new Comment[0]);
	
}
}
