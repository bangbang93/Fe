package org.melonbrew.fe.database.converter.converters;

import java.io.File;
import java.io.FilenameFilter;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.melonbrew.fe.Fe;
import org.melonbrew.fe.database.converter.Converter;
import org.melonbrew.fe.database.converter.ConverterType;

public class Converter_Essentials implements Converter {
	private final Fe plugin;

	public Converter_Essentials(Fe plugin){
		this.plugin = plugin;
	}

	public String getName(){
		return "Essentials";
	}

	public boolean convert(ConverterType type){
		File accountsFolder = new File("plugins/Essentials/userdata/");

		if (!accountsFolder.isDirectory()){
			return false;
		}

		File[] accounts = accountsFolder.listFiles(new FilenameFilter(){
			public boolean accept(File file, String name){
				return name.toLowerCase().endsWith(".yml");
			}
		});

		for (File account : accounts){
			Configuration config = YamlConfiguration.loadConfiguration(account);

			String name = account.getName().replace(".yml", "");

			double money = config.getDouble("money");

			if (money != -1){
				plugin.getAPI().createAccount(name).setMoney(money);
			}
		}

		return true;
	}

	public ConverterType[] getConverterTypes(){
		return new ConverterType[]{ConverterType.FLAT_FILE};
	}
}
