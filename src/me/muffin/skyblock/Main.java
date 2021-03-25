package me.muffin.skyblock;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import me.muffin.skyblock.commands.SkyblockCommand;
import me.muffin.skyblock.commands.SkyblockTab;
import me.muffin.skyblock.events.EventCreatureSpawn;
import me.muffin.skyblock.events.EventEntityDamage;
import me.muffin.skyblock.events.JoinEvent;
import me.muffin.skyblock.files.PlayerDataManager;
import me.muffin.skyblock.skyblockmenu.skills.SkillsManager;
import me.muffin.skyblock.skyblockmenu.stats.StatsManager;

public class Main extends JavaPlugin implements Listener {

	public PlayerDataManager data;

	@Override
	public void onEnable() {
		this.data = new PlayerDataManager(this);
		this.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new EventCreatureSpawn(this), this);
		this.getServer().getPluginManager().registerEvents(new EventEntityDamage(this), this);
		this.getServer().getPluginManager().registerEvents(new CustomBoard(this), this);
		this.getCommand("skyblock").setExecutor(new SkyblockCommand(this));
		this.getCommand("skyblock").setTabCompleter(new SkyblockTab());
		new SkillsManager(this);
		new StatsManager(this);
		
	}

	@Override
	public void onDisable() {

	}

}
