package org.melonbrew.fe.database.databases;


import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.melonbrew.fe.Fe;
import org.melonbrew.fe.SQLibrary.Database;
import org.melonbrew.fe.SQLibrary.MySQL;

public class MySQLDB extends SQLDB {
	private final Fe plugin;
	
	public MySQLDB(Fe plugin){
		super(plugin, true);
		
		this.plugin = plugin;
	}
	
	protected Database getNewDatabase(){
		ConfigurationSection config = plugin.getMySQLConfig();

		MySQL mySQL = new MySQL(plugin.getLogger(), "Fe", config.getString("host"), config.getString("port"), config.getString("database"), config.getString("user"), config.getString("password"));
		
		return mySQL;
	}
	
	public void getConfigDefaults(Configuration config){
		config.addDefault("host", "localhost");
		
		config.addDefault("port", 3306);
		
		config.addDefault("user", "root");
		
		config.addDefault("password", "minecraft");
		
		config.addDefault("database", "Fe");
	}
}
