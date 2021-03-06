package me.muffin.skyblock;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.md_5.bungee.api.ChatColor;

public class CustomBoard implements Listener {

	private Main plugin;
	NumberFormat format = NumberFormat.getInstance(Locale.US);
	

	public CustomBoard(Main plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		createScoreboard(e.getPlayer());
	}
	
	public void setCoins(Player player, long coins) {
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".skyblock.coins" , coins);
		plugin.data.saveConfig();
		createScoreboard(player);
	}
	
	public void setIronman(Player player, boolean state) {
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".skyblock.ironman", state);
		plugin.data.saveConfig();
		createScoreboard(player);
	}
	
	public void setWorld(Player player, World world) {
		plugin.data.getConfig().set("players." + player.getUniqueId() + ".location.world", world.getName());
		plugin.data.saveConfig();
		createScoreboard(player);
	}

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDateTime now = LocalDateTime.now();

	public void createScoreboard(Player player) {
		
		String currentLocation = "";
		
		switch(player.getWorld().getName()) {
			case "gold_mine":
				currentLocation = ChatColor.GOLD + "Gold Mine";
				break;
			case "hub" :
				currentLocation = ChatColor.AQUA + "Hub";
				break;
			case "world":
				currentLocation = ChatColor.GREEN + "Your Island";
				break;
			case "barn":
				currentLocation = ChatColor.AQUA + "The Barn";
				break;
			case "mushroom_desert":
				currentLocation = ChatColor.AQUA + "Mushroom Desert";
				break;
			case "deep_caverns":
				currentLocation = ChatColor.AQUA + "Deep Caverns";
				break;
		}
		
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("Scoreboard-1", "dummy",
				"" + ChatColor.translateAlternateColorCodes('&', "&e&lSKYBLOCK"));
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score score = obj.getScore(ChatColor.GRAY + dtf.format(now));
		score.setScore(11);
		Score score2 = obj.getScore(ChatColor.RESET.toString());
		score2.setScore(10);
		Score score3 = obj.getScore(ChatColor.WHITE + "date_coming_soon");
		score3.setScore(9);
		Score score4 = obj.getScore(ChatColor.GRAY + "time_coming_soon");
		score4.setScore(8);
		Score score5 = obj.getScore(ChatColor.GRAY + "??? " + currentLocation);
		score5.setScore(7);
		if (plugin.data.getConfig().getBoolean("players." + player.getUniqueId() + ".skyblock.ironman") == true) {
			Score score6 = obj.getScore(ChatColor.GRAY + "??? Ironman");
			score6.setScore(6);
		}
		Score score7 = obj.getScore(ChatColor.RESET.toString() + ChatColor.RESET.toString());
		score7.setScore(5);
		Score score9 = obj.getScore("Purse: " + ChatColor.GOLD
				+ format.format(plugin.data.getConfig().getLong(
				"players." + player.getUniqueId() + ".skyblock.coins".toString())));
		score9.setScore(3);
		Score score10 = obj
				.getScore(ChatColor.RESET.toString() + ChatColor.RESET.toString() + ChatColor.RESET.toString());
		score10.setScore(2);
		Score score11 = obj.getScore(ChatColor.YELLOW + "www.hypixel.net");
		score11.setScore(1);
		player.setScoreboard(board);
	}

}
