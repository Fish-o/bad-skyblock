package me.muffin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.muffin.skyblock.IslandSwitcher;
import me.muffin.skyblock.Main;

public class GotoCommand implements CommandExecutor {

	private Main plugin;
	
	IslandSwitcher switcher = new IslandSwitcher(plugin);


	public GotoCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Consoles cannot run this command."));
			return true;
		}
		
		if(label.equalsIgnoreCase("goto") && sender.hasPermission("skyblock.admin")) {
			
			if(args.length > 1) {
				
				Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
			
				switch(args[0]) {
				case "hub":
					this.switcher.sendPlayer(targetPlayer, Bukkit.getServer().getWorld("hub"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &ahub"));
					break;
				case "world":
					this.switcher.sendPlayer(targetPlayer, Bukkit.getServer().getWorld("world"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &aworld"));
					break;
				case "gold_mine":
					this.switcher.sendPlayer(targetPlayer, Bukkit.getServer().getWorld("gold_mine"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &agold_mine"));
					break;
				case "barn":
					this.switcher.sendPlayer(targetPlayer, Bukkit.getServer().getWorld("barn"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &abarn"));
					break;
				case "mushroom_desert":
					this.switcher.sendPlayer(targetPlayer, Bukkit.getServer().getWorld("mushroom_desert"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &amushroom_desert"));
					break;
				case "deep_caverns":
					this.switcher.sendPlayer(targetPlayer, Bukkit.getServer().getWorld("deep_caverns"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &adeep_caverns"));
					break;
			}
			}else {
				
			
			
			switch(args[0]) {
			case "hub":
				this.switcher.sendPlayer(player, Bukkit.getServer().getWorld("hub"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &ahub"));
				break;
			case "world":
				this.switcher.sendPlayer(player, Bukkit.getServer().getWorld("world"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &aworld"));
				break;
			case "gold_mine":
				this.switcher.sendPlayer(player, Bukkit.getServer().getWorld("gold_mine"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &agold_mine"));
				break;
			case "barn":
				this.switcher.sendPlayer(player, Bukkit.getServer().getWorld("barn"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &abarn"));
				break;
			case "mushroom_desert":
				this.switcher.sendPlayer(player, Bukkit.getServer().getWorld("mushroom_desert"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &amushroom_desert"));
				break;
			case "deep_caverns":
				this.switcher.sendPlayer(player, Bukkit.getServer().getWorld("deep_caverns"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Sending you to &adeep_caverns"));
				break;
			}
			
			}
			
		}
		
		return true;
	}
	
}
