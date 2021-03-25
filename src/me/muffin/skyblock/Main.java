package me.muffin.skyblock;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.muffin.skyblock.commands.SkyblockCommand;
import me.muffin.skyblock.commands.SkyblockTab;
import me.muffin.skyblock.events.EventCreatureSpawn;
import me.muffin.skyblock.events.EventEntityDamage;
import me.muffin.skyblock.events.JoinEvent;
import me.muffin.skyblock.events.QuitEvent;
import me.muffin.skyblock.files.PlayerDataManager;

public class Main extends JavaPlugin implements Listener {
	
	public PlayerDataManager data;
	
	@Override
	public void onEnable() {
		this.data = new PlayerDataManager(this);
		this.getServer().getPluginManager().registerEvents(new CustomBoard(this), this);
		this.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new QuitEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new EventCreatureSpawn(this), this);
		this.getServer().getPluginManager().registerEvents(new EventEntityDamage(this), this);
		// asdfasdf
		this.getCommand("skyblock").setExecutor(new SkyblockCommand(this));
		this.getCommand("skyblock").setTabCompleter(new SkyblockTab());
		BukkitTask TaskName = new MyVeryOwnRunnable(this).runTaskTimer(this, 20, 20);
		
		
	}
	@Override
	public void onDisable() {
		
	}	
	
	
	
	
}
