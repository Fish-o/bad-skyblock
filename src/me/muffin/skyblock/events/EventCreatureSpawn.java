package me.muffin.skyblock.events;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import me.muffin.skyblock.Main;
import me.muffin.skyblock.Reference;
import me.muffin.skyblock.mobs.CustomZombie;
import me.muffin.skyblock.mobs.MobData;
import net.minecraft.server.v1_16_R2.WorldServer;

public class EventCreatureSpawn implements Listener {

	private Main plugin;
	public NamespacedKey key_entitiy_health;

	public EventCreatureSpawn(Main plugin) {
		this.plugin = plugin;
		this.key_entitiy_health = new NamespacedKey(plugin, "entity-health");
	}

	@SuppressWarnings("unused")
	@EventHandler
	public void CreatureSpawn(CreatureSpawnEvent e) {
		Entity entity = e.getEntity();
		String type = entity.getType().toString();
		if (Reference.mobs.containsKey(type)) {
			plugin.getLogger().log(Level.INFO, "Entity Spawn Event");
			int WeaponDamage = 11;
			double Strength = 1;
			double CritDamage = 2;

			double Enchants = 0;
			double WeaponBonus = 0;
			double ArmorBonus = 1;

			int CombatLevel = 2;

			double BaseDamage = (5 + WeaponDamage + Math.round(Strength / 5)) * (1 + (Strength / 100));
			double DamageMultiplier = 1 + (CombatLevel * 0.04) + Enchants + WeaponBonus;
			int FinalDamage = (int) Math.round(BaseDamage * DamageMultiplier * ArmorBonus * (1 + (CritDamage / 100)));

			MobData mobData = Reference.mobs.get(type);
			int lvl = mobData.level;
			String name = mobData.name;
			double health = mobData.health;
			
			plugin.getLogger().log(Level.INFO, "Spawing entity");

			Location loc = new Location(entity.getWorld(), 7.0, 101.0, 7.0);
			CustomZombie zomb =  new CustomZombie(loc);
			WorldServer world = ((CraftWorld) entity.getWorld()).getHandle();
			world.addEntity(zomb);
			e.setCancelled(true);
			if (1 == 2) {
				PersistentDataContainer container = entity.getPersistentDataContainer();
				container.set(this.key_entitiy_health, PersistentDataType.DOUBLE, health);
			}
		}

	}
}
