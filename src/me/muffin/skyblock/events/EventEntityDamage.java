package me.muffin.skyblock.events;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import me.muffin.skyblock.Main;
import me.muffin.skyblock.mobs.CustomZombie;
import me.muffin.skyblock.mobs.DamageIndicator;
import net.minecraft.server.v1_16_R2.WorldServer;

public class EventEntityDamage implements Listener {
	private Plugin plugin;

	public EventEntityDamage(Plugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void EntityDamage(EntityDamageEvent e) {

		Entity entity = e.getEntity();
		Location loc = entity.getLocation();
		int damage = (int) e.getDamage();
		PersistentDataContainer container = entity.getPersistentDataContainer();

		plugin.getLogger().log(Level.INFO, "Entity Damage Event: " + entity.getName() + ", " + String.valueOf(damage));

		if (container.has(Main.key_entity_name, PersistentDataType.STRING)) {
			if (container.get(Main.key_entity_name, PersistentDataType.STRING) == DamageIndicator.c_name) {
				e.setCancelled(true);
				return;
			} else if (container.get(Main.key_entity_name, PersistentDataType.STRING) == CustomZombie.c_name) {
				e.setDamage(0);
				if(!container.has(Main.key_entity_health, PersistentDataType.INTEGER)){
					e.setDamage(100000000000000000000000000D);
					return;
				}
				
				int old_damage = container.get(Main.key_entity_health, PersistentDataType.INTEGER);
				int new_damage = (int) (old_damage - damage);
				
				if (new_damage <= 0) {
					new_damage = 0;
					e.setDamage(100000000000000000000000000D);
				}
				
				container.set(Main.key_entity_health, PersistentDataType.INTEGER, new_damage);
				String health_text = CustomZombie.CreateName(new_damage);
				entity.setCustomName(health_text);
				
			}

		}

		DamageIndicator ind = new DamageIndicator(this.plugin, loc, damage, false);
		WorldServer world = ((CraftWorld) entity.getWorld()).getHandle();
		world.addEntity(ind);

	}
}
