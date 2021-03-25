package me.muffin.skyblock.mobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import me.muffin.skyblock.Main;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityHuman;
import net.minecraft.server.v1_16_R2.EntityZombie;
import net.minecraft.server.v1_16_R2.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R2.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_16_R2.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R2.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_16_R2.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R2.PathfinderGoalRandomStroll;

public class CustomZombie extends EntityZombie {

	public static String c_name = "CustomZombie";

	private static int c_level = 1;
	private static int c_health = 100;
	private static String c_DisplayName = "Zombie";

	public static String CreateName(int health) {
		String lvl = "HMMM";
		if (health > c_health * 0.5)
			lvl = ChatColor.GREEN + String.valueOf((int) Math.round(health));
		else 
			lvl = ChatColor.YELLOW + String.valueOf((int) Math.round(health));

		return ChatColor.RESET + "" + ChatColor.DARK_GRAY + "[" + ChatColor.RESET + ""
				+ ChatColor.GRAY + "Lv" + String.valueOf(c_level) + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "]"
				+ ChatColor.RESET + "" + ChatColor.RED + " " + c_DisplayName + " " + ChatColor.RESET + "" + lvl + ""
				+ ChatColor.RESET + "/" + ChatColor.GREEN + String.valueOf((int) Math.round(c_health)) + ChatColor.RESET
				+ "" + ChatColor.RED + "❤" + ChatColor.RESET;

	}

	public CustomZombie(Plugin plugin, Location loc) {
		super(net.minecraft.server.v1_16_R2.EntityTypes.ZOMBIE, ((CraftWorld) loc.getWorld()).getHandle());
		// this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		ChatComponentText health_text = new ChatComponentText(CreateName(c_health));
		this.setCustomName(health_text);
		this.setCustomNameVisible(true);
		this.setHealth(100f);

		// this.goalSelector.a(0, new PathfinderGoalAvoidTarget<EntityPlayer>(this,
		// EntityPlayer.class, 1, 1.0D, 1.0D));
		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
		this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
		this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this));
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());

		PersistentDataContainer container = this.getBukkitEntity().getPersistentDataContainer();
		container.set(Main.key_entity_name, PersistentDataType.STRING, c_name);
		container.set(Main.key_entity_max_health, PersistentDataType.INTEGER, c_health);
		container.set(Main.key_entity_health, PersistentDataType.INTEGER, c_health);
		container.set(Main.key_attribute_combust, PersistentDataType.INTEGER, 0);

	}

	@Override
	public boolean isFireProof() {
		return true;
	}

}
