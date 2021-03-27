package me.muffin.skyblock.skyblockmenu.stats;

import org.bukkit.entity.Player;

import me.muffin.skyblock.Main;

public class StatsManager {
	
	private Main plugin;

	public StatsManager(Main plugin) {
		this.plugin = plugin;
	}
	
	public enum Stat {
		DAMAGE,
		HEALTH,
		DEFENSE,
		TRUE_DEFENSE,
		STRENGTH,
		SPEED,
		CRIT_CHANCE,
		CRIT_DMG,
		INTELLIGENCE,
		MINING_SPEED,
		SEACREATURESPAWNRATE,
		MAGICFIND,
		PETLUCK,
		ABILITYDAMAGE,
		FEROCITY,
		MINING_FORTUNE,
		FARMING_FORTUNE,
		FORAGING_FORTUNE
	}
	
	public void gainStat(Stat stat, Player player, int amount) {
		double currentStat = plugin.data.getConfig().getDouble("players." + player.getUniqueId() + ".skyblock.stats." + stat);
		
		double newStat = currentStat + amount;
		plugin.data.getConfig().set("player." + player.getUniqueId() + ".skyblock.stats." + stat, newStat);
		plugin.data.saveConfig();
		
	}
	
	
	
}
