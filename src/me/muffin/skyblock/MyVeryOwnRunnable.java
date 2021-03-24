package me.muffin.skyblock;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class MyVeryOwnRunnable implements Runnable {

	private Main plugin;

	public MyVeryOwnRunnable(Main plugin) {
		this.plugin = plugin;
	}
	
	
	private int taskID;
	
	public void start(Player player) {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			int count = 0;
			BoardUpdater board = new BoardUpdater(player.getUniqueId());
			
			
			@Override
			public void run() {
				if(!board.hasID())
					board.setID(taskID);
				
			}

		
			
		}, 0, 10);
		
	}

	public BukkitTask runTaskTimer(Main main, int i, int j) {
		
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
