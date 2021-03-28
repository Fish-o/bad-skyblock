package me.muffin.skyblock.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.muffin.skyblock.skyblockmenu.SkyblockMenuGUI;

public class InteractEvent implements Listener {
	
	
	


	SkyblockMenuGUI menu = new SkyblockMenuGUI();
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack skyblockmenu = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta meta = skyblockmenu.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "View all of your SkyBlock");
		lore.add(ChatColor.GRAY + "progress, including your Skills,");
		lore.add(ChatColor.GRAY + "Collections, Recipes, and more!");
		lore.add(" ");
		lore.add(ChatColor.YELLOW + "Click to open!");
		meta.setLore(lore);
		meta.setDisplayName(ChatColor.GREEN + "SkyBlock Menu " + ChatColor.GRAY + "(Right Click)");
		skyblockmenu.setItemMeta(meta);
		
		
		
		 if(player.getInventory().getItemInMainHand().isSimilar(skyblockmenu)){
	            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
	                menu.createInv(player);
	            }
	}
}
	
	
}
