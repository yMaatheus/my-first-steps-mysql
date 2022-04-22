package me.ymaatheus.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

	public static Connection connection;

	private ResultSet rs = null;
	private Statement stm = null;
	private PreparedStatement ps = null;

	private String url;
	private String user;
	private String passowrd;

	public MySQL(String ip, Integer port, String database, String user, String password) throws ClassNotFoundException{
		this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database + "?autoReconnect=true";
		this.user = user;
		this.passowrd = password;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			startDatabase();
		}catch (SQLException e) {
			
		}
	}

	private void startDatabase() {
		try {
			@SuppressWarnings("static-access")
			Statement stmt = this.connection.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS `nomes` (`Nick` varchar(255) not null);");
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}