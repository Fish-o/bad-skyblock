package me.muffin.skyblock.mobs;

import net.minecraft.server.v1_16_R2.Entity;

public class MobData { 
	public double health; 
	public String name;
	public int level;
	 // Constructor 
	public MobData(double health, String name, int level) 
	 { 
		this.health = health; 
		this.name = name;
		this.level = level;
	    // Todo: Armour, Create entity function
	 } 
}