package me.ymaatheus.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
	public static Main plugin;
	public static ArrayList<String> nomes = new ArrayList<>();

	public void onEnable() {
		Atualizacao();
		try {
			new MySQL("127.0.0.1", 3306, "nomes", "root", "password");
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM `nomes`;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nomes.add(rs.getString("Nick"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void onDisable() {
		try {
		for (String n : nomes) {
			PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM `nomes`;");
			ResultSet rs = ps.executeQuery();
			boolean find = false;
			while (rs.next()) {
				if (rs.getString("Nick").equalsIgnoreCase(n)) {
					find = true;
					break;
				}
			}
			if (find == true) {
				
			}
			if (find == false) {
			    PreparedStatement ps2 = MySQL.connection.prepareStatement("INSERT INTO `nomes` (`Nick`) VALUES ('" + n.toLowerCase() + "',0,0);");
				ps2.executeUpdate();
			}
		}
		} catch (SQLException e) {
			
		}
	}
	
	public void Atualizacao() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
			}
		}.runTaskTimerAsynchronously(plugin, 600*20, 600*20);
	}

}
