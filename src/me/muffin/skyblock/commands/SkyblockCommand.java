package me.muffin.skyblock.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.muffin.skyblock.CustomBoard;
import me.muffin.skyblock.Main;
import me.muffin.skyblock.skyblockmenu.SkyblockMenuGUI;
import me.muffin.skyblock.skyblockmenu.skills.SkillsManager;
import me.muffin.skyblock.skyblockmenu.stats.StatsManager.Stat;
import net.md_5.bungee.api.ChatColor;

public class SkyblockCommand implements CommandExecutor{
	
	private Main plugin;
	public static SkillsManager skills;
	
	CustomBoard board = new CustomBoard(plugin);
	SkyblockMenuGUI menu = new SkyblockMenuGUI();
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
						long coins = 0;
						
						if (plugin.data.getConfig().contains("players." + p.getUniqueId().toString() + ".skyblock.coins"));
							coins = plugin.data.getConfig().getLong("players." + p.getUniqueId().toString() + ".skyblock.coins");
						
						this.board.setCoins(p, coins + Long.parseLong(args[2]));
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7You have added &a" + args[2] + "&7 coins to the balance of: &a" + p.getName()));
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
					this.board.setCoins(p, Long.parseLong(args[2]));
					
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Set the balance of &a" + p.getName() + "&7 to &a" + args[2] + "&7."));
				}
				}
			}else if(args[0].equalsIgnoreCase("menu")) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# *aConsole&7 cannot open their skyblock menu."));
					return true;
				}
				Player player = (Player) sender;
				menu.createInv(player);
				
			}
			//balance command
			else if(args[0].equalsIgnoreCase("balance")) {
				//this.getLogger().log(Level.INFO, "command received.");
				if(args.length == 1) {
					if(sender.hasPermission("skyblock.coins.balance.self")) {
						Player player = (Player) sender;
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Your balance: &a" + this.plugin.data.getConfig().getLong("players." + player.getUniqueId() + ".coins") + "&7."));
					}
					
				}else {
					if(sender.hasPermission("skyblock.coins.balance.others") || sender.hasPermission("skyblock.admin")){
						Player p = Bukkit.getServer().getPlayer(args[1]);
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Balance of &a" + p.getName() + "&7: &a" + plugin.data.getConfig().getLong("players." + p.getUniqueId() + ".coins") + "&7."));
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
						board.setIronman(player, true);
					}else if(args[1].equalsIgnoreCase("false")) {
						board.setIronman(player, false);
					}
					
				}
			}
			// RELOAD COMM
			}else if(args[0].equalsIgnoreCase("reloaddata")) {
				if(sender.hasPermission("skyblock.data.reload") || sender.hasPermission("skyblock.admin"))
					this.plugin.data.reloadConfig();
				
				
		}else if(args[0].equalsIgnoreCase("stats")) {
			Player player = (Player) sender;
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8# &7Viewing stats of player &a" + sender.getName() + "&7."));
			for (Stat stat : Stat.values()) {
				double statistic = plugin.data.getConfig().getDouble("players." + player.getUniqueId() + ".skyblock.stat." + stat);
				if(stat == Stat.HEALTH) {
					sender.sendMessage(ChatColor.RED + "❤ Health " + ChatColor.WHITE + statistic);
				}if(stat == Stat.DEFENSE) {
					sender.sendMessage(ChatColor.GREEN + "❈ Defense " + ChatColor.WHITE + statistic);
				}if(stat == Stat.TRUE_DEFENSE) {
					sender.sendMessage(ChatColor.WHITE + "❂ True Defense " + statistic);
				}if(stat == Stat.DAMAGE) {
					sender.sendMessage(ChatColor.RED + "❁ Damage " + ChatColor.WHITE + statistic) ;
				}if(stat == Stat.STRENGTH) {
					sender.sendMessage(ChatColor.RED + "❁ Strength " + ChatColor.WHITE + statistic);
				}if(stat == Stat.SPEED) {
					sender.sendMessage(ChatColor.WHITE + "✦ Speed " + statistic);
				}if(stat == Stat.CRIT_CHANCE) {
					sender.sendMessage(ChatColor.BLUE + "☣ Crit Chance " + ChatColor.WHITE + statistic);
				}if(stat == Stat.CRIT_DMG) {
					sender.sendMessage(ChatColor.BLUE + "☠ Crit Damage " + ChatColor.WHITE + statistic);
				}if(stat == Stat.INTELLIGENCE) {
					sender.sendMessage(ChatColor.AQUA + "✎ Intelligence " + ChatColor.WHITE + statistic);
				}if(stat == Stat.MINING_SPEED) {
					sender.sendMessage(ChatColor.GOLD + "⸕ Mining Speed " + ChatColor.WHITE + statistic);
				}if(stat == Stat.SEACREATURESPAWNRATE) {
					sender.sendMessage(ChatColor.DARK_AQUA + "α Sea Creature Chance " + ChatColor.WHITE + statistic);
				}if(stat == Stat.MAGICFIND) {
					sender.sendMessage(ChatColor.AQUA + "✯ Magic Find " + ChatColor.WHITE + statistic);
				}if(stat == Stat.PETLUCK) {
					sender.sendMessage(ChatColor.LIGHT_PURPLE + "♣ Pet Luck " + ChatColor.WHITE + statistic);
				}if(stat == Stat.ABILITYDAMAGE) {
					sender.sendMessage(ChatColor.RED + "✹ Ability Damage " + ChatColor.WHITE + statistic);
				}if(stat == Stat.FEROCITY) {
					sender.sendMessage(ChatColor.RED + "⫽ Ferocity " + ChatColor.WHITE + statistic);
				}if(stat == Stat.MINING_FORTUNE) {
					sender.sendMessage(ChatColor.GOLD + "☘ Mining Fortune " + ChatColor.WHITE + statistic);
				}if(stat == Stat.FARMING_FORTUNE) {
					sender.sendMessage(ChatColor.GOLD + "☘ Farming Fortune " + ChatColor.WHITE + statistic);
				}if(stat == Stat.FORAGING_FORTUNE) {
					sender.sendMessage(ChatColor.GOLD + "☘ Foraging Fortune " + ChatColor.WHITE + statistic);
				}
				
					
			}
		
	}
		return true;
	
	}
}
