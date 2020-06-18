package ONServer.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MySQL {
	private static MySQL obj = null;
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	MySQL() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seoul?characterEncoding=UTF-8&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&useSSL=false", "root", "0000");
		} catch (ClassNotFoundException e) {

			System.out.println("�����ͺ��̽� ������� : " + e.getMessage());
		}

	}
	public static MySQL getConnection() throws SQLException {
		if (MySQL.obj == null)
			MySQL.obj = new MySQL();

		return MySQL.obj;
	}

	public void set(int index, String value) throws SQLException { // ���Ǽ���
		ps.setString(index, value);
		

	}
	
	public void setTime(int index, Timestamp value) throws SQLException { // ���Ǽ���(�ð�)
		ps.setTimestamp(index, value);
		

	}
	

	public void sqlQuery(String query) throws SQLException { // ��������
		ps = con.prepareStatement(query);

	}

	public ResultSet execute() throws SQLException { // ��������
		rs = ps.executeQuery();
		rsmd = rs.getMetaData();

		return rs;

	}

	public int insert() throws SQLException { // �߰�
		return ps.executeUpdate();
	}

	public int delete() throws SQLException { // ����
		return ps.executeUpdate();
	}

	public int update() throws SQLException { // ������Ʈ
		return ps.executeUpdate();
	}

	public void setAutoCommit(boolean v) throws SQLException {
		con.setAutoCommit(v);
	}

	public void commit() throws SQLException {
		con.commit();
	}

	public void rollback() throws SQLException {
		con.rollback();
	}

	public void close() throws SQLException { // ��� ��ü ����
		if (rs != null)
			rs.close();
		if (ps != null)
			ps.close();
		if (con != null)
			con.close();
	}

}
