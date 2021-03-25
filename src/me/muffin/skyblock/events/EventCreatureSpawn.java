package me.muffin.skyblock.events;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.muffin.skyblock.Main;
import me.muffin.skyblock.mobs.CustomZombie;
import net.minecraft.server.v1_16_R2.WorldServer;

public class EventCreatureSpawn implements Listener {

	private Main plugin;

	public EventCreatureSpawn(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void CreatureSpawn(CreatureSpawnEvent e) {
		Entity entity = e.getEntity();
		String type = entity.getType().toString();
		PersistentDataContainer container = entity.getPersistentDataContainer();

		plugin.getLogger().log(Level.INFO, "Entity Spawn Event: " + type);

		if (container.has(Main.key_attribute_ttl, PersistentDataType.INTEGER)) {
			new java.util.Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					entity.remove();
				}
			}, container.get(Main.key_attribute_ttl, PersistentDataType.INTEGER));
		}

		if (container.has(Main.key_entity_name, PersistentDataType.STRING))
			return;

		Location loc = e.getLocation();
		if (type == "ZOMBIE") {
			CustomZombie zomb = new CustomZombie(this.plugin, loc);
			WorldServer world = ((CraftWorld) entity.getWorld()).getHandle();
			world.addEntity(zomb);
			e.setCancelled(true);
		}

	}
}
