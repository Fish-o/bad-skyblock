package me.muffin.skyblock.events;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.muffin.skyblock.BoardUpdater;
import me.muffin.skyblock.Main;

public class QuitEvent implements Listener{
	
	private Main plugin;


	public QuitEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	
	public void onQuit(PlayerQuitEvent e) {
		BoardUpdater board = new BoardUpdater(e.getPlayer().getUniqueId());
		if (board.hasID())
			board.stop();
		
		
		
		
		
		
		
		
	}

}
