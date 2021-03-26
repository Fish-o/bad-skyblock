package me.muffin.skyblock.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.muffin.skyblock.CustomBoard;
import me.muffin.skyblock.IslandSwitcher;
import me.muffin.skyblock.Main;

public class SignEvent implements Listener{
	
	
	private Main plugin;

	public SignEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	

	IslandSwitcher switcher = new IslandSwitcher(plugin);
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		if(e.getClickedBlock() == null)
			return;
		
		
		if(e.getClickedBlock().getState() instanceof Sign) {
			Sign sign = (Sign)e.getClickedBlock().getState();
			World world = Bukkit.getServer().getWorld(sign.getLine(3));
			if(sign.getLine(0).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', "&8[&aworld&8]&r"))); {
				this.switcher.sendPlayer(e.getPlayer(), world);
				e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7You have been teleported to &a" + sign.getLine(1) + "&7."));
			}
		}
		
	}
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("[world]")) {
			e.setLine(0, ChatColor.translateAlternateColorCodes('&', "&8[&aTeleport&8]&r"));
		}
		switch(e.getLine(1)) {
			case "hub":
				e.setLine(1, ChatColor.GRAY + "⏣" +ChatColor.AQUA + " Hub");
				e.setLine(3, "hub");
				break;
			case "world":
				e.setLine(1, ChatColor.GRAY + "⏣" +ChatColor.GREEN + " Your Island");
				e.setLine(3, "world");
				break;
			case "gold_mine":
				e.setLine(1, ChatColor.GRAY + "⏣" +ChatColor.GOLD + " Gold Mine");
				e.setLine(3, "gold_mine");
				break;
			case "barn":
				e.setLine(1, ChatColor.GRAY + "⏣" +ChatColor.AQUA + " The Barn");
				e.setLine(3, "barn");
				break;
			case "mushroom_desert":
				e.setLine(1,ChatColor.GRAY + "⏣" + ChatColor.AQUA + " Mushroom");
				e.setLine(2, ChatColor.AQUA + "Desert");
				e.setLine(3, "mushroom_desert");
				break;
			case "deep_caverns":
				e.setLine(1, ChatColor.GRAY + "⏣" + ChatColor.AQUA + " Deep Caverns");
				e.setLine(3, "deep_caverns");
				break;
		}
	}
	
	
	
}
