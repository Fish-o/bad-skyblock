package me.muffin.skyblock.mobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import me.muffin.skyblock.Main;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityArmorStand;

public class DamageIndicator extends EntityArmorStand {

	public static String c_name = "DamageIndicator";

	public DamageIndicator(Plugin plugin, Location loc, int damage, boolean crit) {
		super(net.minecraft.server.v1_16_R2.EntityTypes.ARMOR_STAND, ((CraftWorld) loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY()+0.25, loc.getZ());
		this.setCustomName(new ChatComponentText(ChatColor.GRAY + String.valueOf(damage)));
		this.setCustomNameVisible(true);
		this.setInvulnerable(true);
		this.setNoGravity(true); 
		this.setInvisible(true);
		this.setSmall(true);

		PersistentDataContainer container = this.getBukkitEntity().getPersistentDataContainer();
		container.set(Main.key_entity_name, PersistentDataType.STRING, c_name);
		container.set(Main.key_attribute_ttl, PersistentDataType.INTEGER, 1000);
	}

	@Override
	public boolean isFireProof() {
		return true;
	}

}
