package me.muffin.skyblock.mobs;

import java.lang.reflect.Field;
import java.util.Map;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityHuman;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.EntityZombie;
import net.minecraft.server.v1_16_R2.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R2.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_16_R2.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R2.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_16_R2.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_16_R2.PathfinderGoalRandomStroll;

public class CustomZombie extends EntityZombie {
	private int level = 1;
	private int health = 100;
	private String name = "Zombie";

	public CustomZombie(Location loc) {
		super(EntityTypes.ZOMBIE, ((CraftWorld) loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		ChatComponentText health_text = new ChatComponentText(ChatColor.RESET + "" + ChatColor.DARK_GRAY + "["
				+ ChatColor.RESET + "" + ChatColor.GRAY + "Lv" + String.valueOf(level) + ChatColor.RESET + ""
				+ ChatColor.DARK_GRAY + "]" + ChatColor.RESET + "" + ChatColor.RED + " " + name + " " + ChatColor.RESET
				+ "" + ChatColor.GREEN + String.valueOf((int) Math.round(health)) + "" + ChatColor.RESET + "/"
				+ ChatColor.GREEN + String.valueOf((int) Math.round(health)) + ChatColor.RESET + "" + ChatColor.RED
				+ "❤" + ChatColor.RESET);

		this.setCustomName(health_text);
		this.setCustomNameVisible(true);
		this.setHealth(100);
		this.setFireTicks(0);

		// this.goalSelector.a(0, new PathfinderGoalAvoidTarget<EntityPlayer>(this,
		// EntityPlayer.class, 1, 1.0D, 1.0D));
		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
		this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
		this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this));

	}
	@SuppressWarnings("unchecked")
	public static void register(String name, int id, Class<?> registryClass) {
	   ((Map) getPrivateField("c", EntityTypes.class, null)).put(name, registryClass);
	   ((Map)getPrivateField("d", EntityTypes.class, null)).put(registryClass, name);
	   ((Map) getPrivateField("f", EntityTypes.class, null)).put(registryClass, id);
	}
	@Nullable
    public static Object getPrivateField(String fieldName, Class<?> clazz, Object object) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
