package me.muffin.skyblock.events;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.muffin.skyblock.Main;

public class JoinEvent implements Listener{ 
	
	private Main plugin;


	public JoinEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void PlayerJoin(PlayerJoinEvent e) {
		plugin.getLogger().log(Level.INFO, "event :D");
		Player player = e.getPlayer();
		Location loc = player.getLocation();
		String worldname = player.getWorld().getName();
		player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 1000000, 255), false);
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".uuid", player.getUniqueId().toString());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".name", player.getName());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".displayname", player.getDisplayName());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".joinedbefore", true);
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".ipadress", player.getAddress().toString());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".location.world", worldname);
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".location.position.x", loc.getX());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".location.position.y", loc.getY());
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".location.position.z", loc.getZ());
		if(!(plugin.data.getConfig().contains("players." + player.getUniqueId().toString() + ".coins"))) {
			plugin.data.getConfig().set("players." + player.getUniqueId() + ".coins", 0);
		}
		plugin.data.saveConfig();
		
	}
}
