package me.muffin.skyblock;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class IslandSwitcher {

	
	private Main plugin;
	CustomBoard board = new CustomBoard(plugin);

	public IslandSwitcher(Main plugin) {
		this.plugin = plugin;
	}
	
	public void sendPlayer(Player player, World world) {
		Location loc = new Location(world, world.getSpawnLocation().getX(), world.getSpawnLocation().getY(), world.getSpawnLocation().getZ());
		loc.setPitch(world.getSpawnLocation().getPitch());
		loc.setYaw(world.getSpawnLocation().getYaw());
		player.teleport(loc);
		board.setWorld(player, world);
	}
	
	

	
}
