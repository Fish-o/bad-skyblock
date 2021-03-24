package me.muffin.skyblock.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.muffin.skyblock.Main;

public class PlayerDataManager {
	
	private Main plugin;
	private FileConfiguration playerDataConfig = null;
	private File configFile = null;
	
	
	public PlayerDataManager(Main plugin) {
		this.plugin = plugin;
		saveDefaultConfig();
	}
	
	
	public void reloadConfig() {
		if (this.configFile == null) 
			this.configFile = new File(this.plugin.getDataFolder(), "playerdata.yml");
		
		this.playerDataConfig = YamlConfiguration.loadConfiguration(this.configFile);
		
		InputStream defaultStream = this.plugin.getResource("playerdata.yml");
		if (defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.playerDataConfig.setDefaults(defaultConfig);
		}
	}
	
	public FileConfiguration getConfig() {
		if(this.playerDataConfig == null)
			reloadConfig();
		return this.playerDataConfig;
	}
	
	public void saveConfig() {
		if(this.playerDataConfig == null || this.configFile == null)
			return;
		
		try {
			this.getConfig().save(this.configFile);
		} catch(IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Couldn't save config to " + this.configFile, e);
		}
	}
	
	public void saveDefaultConfig() {
		if (this.configFile == null)
			this.configFile = new File(this.plugin.getDataFolder(), "playerdata.yml");
		
		if(!this.configFile.exists()) {
			this.plugin.saveResource("playerdata.yml", false);
		}
	}
	
}

























