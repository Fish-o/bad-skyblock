package me.muffin.skyblock.events;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.muffin.skyblock.CustomBoard;
import me.muffin.skyblock.Main;
import me.muffin.skyblock.skyblockmenu.skills.SkillsManager.Skill;
import me.muffin.skyblock.skyblockmenu.stats.StatsManager.Stat;

public class JoinEvent implements Listener {

	private Main plugin;

	public JoinEvent(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) {
		
		//Config stuff
		Player player = e.getPlayer();
		Location loc = player.getLocation();
		String worldname = player.getWorld().getName();
		
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".uuid", player.getUniqueId().toString());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".name", player.getName());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".displayname", player.getDisplayName());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".joinedbefore", true);
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".ipadress", player.getAddress().toString());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".location.world", worldname);
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".location.position.x", loc.getX());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".location.position.y", loc.getY());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".location.position.z", loc.getZ());
		if (!(plugin.data.getConfig().contains("players." + player.getUniqueId().toString() + ".skyblock.coins"))) {
			plugin.data.getConfig().set("players." + player.getUniqueId() + ".skyblock.coins", 0L);
		}
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".skyblock.ironman", false);
		
		
		for (Skill skill : Skill.values()) {
			if(!plugin.data.getConfig().contains("players." + player.getUniqueId() + ".skyblock.skill." + skill))
				plugin.data.getConfig().set("players." + player.getUniqueId() + ".skyblock.skill." + skill, 0.0D);
		}
		
		
		for (Stat stat : Stat.values()) {
			if(!plugin.data.getConfig().contains("players." + player.getUniqueId() + ".skyblock.stat." + stat))
				plugin.data.getConfig().set("players." + player.getUniqueId() + ".skyblock.stat." + stat, 0.0D);
		}
		
		
		
		plugin.data.saveConfig();
		
		
		// Saturation effect
		player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000000, 255, false, false, false));
		
		// Skyblock menu
		
		ItemStack skyblockmenu = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta meta = skyblockmenu.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "View all of your SkyBlock");
		lore.add(ChatColor.GRAY + "progress, including your Skills,");
		lore.add(ChatColor.GRAY + "Collections, Recipes, and more!");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to open!");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GREEN + "SkyBlock Menu " + ChatColor.GRAY + "(Right Click)");
		skyblockmenu.setItemMeta(meta);
		player.getInventory().setItem(8, skyblockmenu);
		
		

	}
}
