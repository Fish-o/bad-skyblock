package me.muffin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.muffin.skyblock.Main;
import net.md_5.bungee.api.ChatColor;

public class SkyblockCommand implements CommandExecutor{
	
	private Main plugin;



	public SkyblockCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("skyblock")) {
			
			if (args.length < 1) {
				sender.sendMessage(" ");
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Use &a/skyblock help&7 for a list of commands."));
				return true;
			}
			
			// HELP COMMANDS
			if(args[0].equalsIgnoreCase("help")) {
				if (args.length == 1) {
					sender.sendMessage(" ");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &aHelp commands&7:"));
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &a/skyblock help coins&7 - List the commands in the coins category."));
					
					if(sender.hasPermission("skyblock.admin")) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &a/skyblock help admin&7 - List the admin commands."));
					}
				}else if(args.length == 2) {
					if (args[1].equalsIgnoreCase("coins")) {
						sender.sendMessage(" ");
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &a/skyblock balance &7- Displays your current balance."));
					}else if(args[1].equalsIgnoreCase("admin")) {
						sender.sendMessage(" ");
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &a/skyblock addcoins <player> <amount> &7- Adds coins to a user's account. To remove coins use a - in front of the amount."));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &a/skyblock setcoins <player> <amount> &7- Sets a user's balance."));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &a/skyblock balance <player> &7- Displays a player's balance."));
					}else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7That is not a valid help category."));
					}
					
				}
			}
			
			
			
			
			
			
			// COINS COMMANDS
			// addcoins command
			else if(args[0].equalsIgnoreCase("addcoins")) {
				
				if(sender.hasPermission("skyblock.coins.addcoins")|| sender.hasPermission("skyblock.admin")) {
					if(!(args.length == 3)) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Incorrect usage! Correct usage: &a /skyblock addcoins <player> <amount>&7."));
						return true;
					}else {
						Player p = Bukkit.getServer().getPlayer(args[1]); 
						double coins = 0.0;
						
						if (plugin.data.getConfig().contains("players." + p.getUniqueId().toString() + ".coins"));
							coins = plugin.data.getConfig().getInt("players." + p.getUniqueId().toString() + ".coins");
						
						plugin.data.getConfig().set("players." + p.getUniqueId() + ".coins", (coins + Integer.parseInt(args[2])));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Set coins of &a" + p.getName() + "&7 to &a" + plugin.data.getConfig().getDouble("players." + p.getUniqueId().toString() + ".coins") + "&7."));
						plugin.data.saveConfig();
					}
				}
				
			}
			//setcoins command
			else if(args[0].equalsIgnoreCase("setcoins")) {
				if(sender.hasPermission("skyblock.coins.setcoins") || sender.hasPermission("skyblock.admin")) {
					if(!(args.length == 3)) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Incorrect usage! Correct usage: &a /skyblock setcoins <player> <amount>&7."));
						return true;
				}else {
					Player p = Bukkit.getServer().getPlayer(args[1]);
					plugin.data.getConfig().set("players." + p.getUniqueId() + ".coins", Integer.parseInt(args[2]));
					plugin.data.saveConfig();
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Set the balance of &a" + p.getName() + "&7 to &a" + args[2] + "&7."));
				}
				}
			}
			//balance command
			else if(args[0].equalsIgnoreCase("balance")) {
				//this.getLogger().log(Level.INFO, "command received.");
				if(args.length == 1) {
					if(sender.hasPermission("skyblock.coins.balance.self")) {
						Player player = (Player) sender;
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Your balance: &a" + this.plugin.data.getConfig().getInt("players." + player.getUniqueId() + ".coins") + "&7."));
					}
					
				}else {
					if(sender.hasPermission("skyblock.coins.balance.others") || sender.hasPermission("skyblock.admin")){
						Player p = Bukkit.getServer().getPlayer(args[1]);
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Balance of &a" + p.getName() + "&7: &a" + plugin.data.getConfig().getInt("players." + p.getUniqueId() + ".coins") + "&7."));
					}
					
				}
				
				
			}
			// ironman command (TEMPORARY
			else if(args[0].equalsIgnoreCase("ironman")) {
				if (args.length == 1) {
					sender.sendMessage("bad usage");
				}else{
					Player player = (Player) sender;
					if(args[1].equalsIgnoreCase("true")) {
						plugin.data.getConfig().set("players." + player.getUniqueId() + ".ironman", true);
						plugin.data.saveConfig();
					}else if(args[1].equalsIgnoreCase("false")) {
						plugin.data.getConfig().set("players." + player.getUniqueId() + ".ironman", false);
						plugin.data.saveConfig();
					}
					
				}
			
			}else if(args[0].equalsIgnoreCase("reloaddata")) {
				if(sender.hasPermission("skyblock.data.reload") || sender.hasPermission("skyblock.admin"))
					this.plugin.data.reloadConfig();
			}
		}
		return true;
	}
	
	
}