package me.muffin.skyblock;

import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import me.muffin.skyblock.commands.SkyblockCommand;
import me.muffin.skyblock.commands.SkyblockTab;
import me.muffin.skyblock.events.EventCreatureSpawn;
import me.muffin.skyblock.events.EventEntityCombust;
import me.muffin.skyblock.events.EventEntityDamage;
import me.muffin.skyblock.events.JoinEvent;
import me.muffin.skyblock.events.QuitEvent;
import me.muffin.skyblock.files.PlayerDataManager;

public class Main extends JavaPlugin implements Listener {
	public static NamespacedKey key_entity_health;
	public static NamespacedKey key_entity_max_health;
	public static NamespacedKey key_entity_name;
	public static NamespacedKey key_attribute_invisable;
	public static NamespacedKey key_attribute_combust;
	public static NamespacedKey key_attribute_ttl;
	public static PlayerDataManager data;

	@Override
	public void onEnable() {
		Main.key_entity_health = new NamespacedKey(this, "entity_health");
		Main.key_entity_max_health = new NamespacedKey(this, "entity_max_health");
		Main.key_entity_name = new NamespacedKey(this, "key_entity_name");
		Main.key_attribute_invisable = new NamespacedKey(this, "key_attribute_invisable");
		Main.key_attribute_combust = new NamespacedKey(this, "key_attribute_combust");
		Main.key_attribute_ttl = new NamespacedKey(this, "key_attribute_ttl");
		
		
		Main.data = new PlayerDataManager(this);
		this.getServer().getPluginManager().registerEvents(new CustomBoard(this), this);
		this.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new QuitEvent(this), this);
		this.getServer().getPluginManager().registerEvents(new EventCreatureSpawn(this), this);
		this.getServer().getPluginManager().registerEvents(new EventEntityDamage(this), this);
		this.getServer().getPluginManager().registerEvents(new EventEntityCombust(this), this);

		
		this.getCommand("skyblock").setExecutor(new SkyblockCommand(this));
		this.getCommand("skyblock").setTabCompleter(new SkyblockTab());
		BukkitTask TaskName = new MyVeryOwnRunnable(this).runTaskTimer(this, 20, 20);

	}

	@Override
	public void onDisable() {

	}

}
