package me.muffin.skyblock.events;

import java.util.logging.Level;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class EventEntityDamage  implements Listener{
	private Plugin plugin;
	public EventEntityDamage(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void EntityDamage(EntityDamageEvent e) {
		plugin.getLogger().log(Level.INFO, e.getEntity().getType().toString());
	}
}
