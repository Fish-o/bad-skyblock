package me.muffin.skyblock.skyblockmenu.skills;

import org.bukkit.entity.Player;

import me.muffin.skyblock.Main;
import net.md_5.bungee.api.ChatColor;

public class SkillsManager {
	private Main plugin;

	
	public enum Skill {
		FARMING,
		MINING,
		COMBAT,
		FORAGING,
		FISHING,
		ENCHANTING,
		ALCHEMY,
		CARPENTRY,
		RUNECRAFTING,
		SOCIAL,
		TAMING
	}
	
	public void sendSkillMessage(Player player, Skill skill, int level, int coins) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3&l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l SKILL LEVEL UP &r&3" + skill.toString().toCharArray()[0] + skill.toString().toLowerCase().substring(1) + String.valueOf(level)));
		
	}
	
	
	public void gainSkillExp(Skill skill, Player player, double exp) {
		double currentExp = plugin.data.getConfig().getDouble("player." + player.getUniqueId() + ".skyblock.skill." + skill);
		if(currentExp < 50 && currentExp + exp > 100) {
			sendSkillMessage(player, skill, 1, 25);
		}
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".skyblock.skill." + skill, exp);
		plugin.data.saveConfig();
	}
	
	
	public SkillsManager(Main plugin) {
		this.plugin = plugin;
	}
	
	
	
	
	
	

}
