package me.ymaatheus.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerStorage {

	public static boolean exitirPlayerNoMySQL(final String player) {
		try {
			final PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM `nomes`;");
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("Nick").equalsIgnoreCase(player.toLowerCase())) {
					return true;
				}
			}
		} catch (Exception ex) {
		}
		return false;
	}

	public static String getSenha(final String p) {
		try {
			final PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM `Login` WHERE Nick = '" + p.toLowerCase() + "';");
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("senha") != null) {
					return rs.getString("senha");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Integer setSenha(final String p, String senha) {
		try {
			String sql = "UPDATE Login SET Senha = '" + senha + "' WHERE Nick = '" + p.toLowerCase() + "'";
			PreparedStatement stmt = MySQL.connection.prepareStatement(sql);

			stmt.execute();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static int setRegister(final String p, int config) {
		try {
			String sql = "UPDATE Login SET Registrado = " + config + " WHERE Nick = '" + p.toLowerCase() + "'";
			PreparedStatement stmt = MySQL.connection.prepareStatement(sql);

			stmt.execute();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static Integer getRegister(final String p) {
		try {
			final PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM `Login` WHERE Nick = '" + p.toLowerCase() + "';");
			final ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("Registrado") != null) {
					return rs.getInt("Registrado");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	public static void registrarLogin(final String p) {
		try {
			final PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO `Login` (`Nick`,`Senha`,`Registrado`) VALUES ('" + p.toLowerCase() + "',0,0);");
			ps.executeUpdate();
		} catch (Exception ex) {
		}
	}
}