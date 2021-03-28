package me.muffin.skyblock.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import me.muffin.skyblock.Main;

public class EventEntityCombust  implements Listener{
	@SuppressWarnings("unused")
	private Plugin plugin;
	public EventEntityCombust(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void EntityDamage(EntityCombustEvent e) {
		Entity entity = e.getEntity();
		PersistentDataContainer container = entity.getPersistentDataContainer();
		if(container.has(Main.key_attribute_combust, PersistentDataType.INTEGER)
		&& container.get(Main.key_attribute_combust, PersistentDataType.INTEGER) == 0)  {
			e.setCancelled(true);
		}
	}
}
