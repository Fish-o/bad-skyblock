package me.muffin.skyblock.skyblockmenu;

import java.awt.Event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class SkyblockMenuGUI implements Listener {

	
	public void createInv(Player owner) { 
		
		Inventory inv = Bukkit.createInventory(owner, 54, "Skyblock Menu");
		
		ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta panemeta = pane.getItemMeta();
		panemeta.setDisplayName(ChatColor.RESET + " ");
		inv.setItem(0, pane);
		inv.setItem(1, pane);
		inv.setItem(2, pane);
		inv.setItem(3, pane);
		inv.setItem(4, pane);
		inv.setItem(5, pane);
		inv.setItem(6, pane);
		inv.setItem(7, pane);
		inv.setItem(8, pane);
		inv.setItem(9, pane);
		inv.setItem(10, pane);
		inv.setItem(11, pane);
		inv.setItem(12, pane);
		inv.setItem(14, pane);
		inv.setItem(15, pane);
		inv.setItem(16, pane);
		inv.setItem(17, pane);
		inv.setItem(18, pane);
		inv.setItem(26, pane);
		inv.setItem(27, pane);
		inv.setItem(28, pane);
		inv.setItem(34, pane);
		inv.setItem(35, pane);
		inv.setItem(37, pane);
		inv.setItem(38, pane);
		inv.setItem(39, pane);
		inv.setItem(40, pane);
		inv.setItem(41, pane);
		inv.setItem(42, pane);
		inv.setItem(46, pane);
		
		
		
		
		owner.openInventory(inv);
	}
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(!(e.getView().getTitle() == "Skyblock Menu"))
			return;
		if(e.getCurrentItem() == null)
			return;
		if(e.getCurrentItem().getItemMeta() == null)
			return;
		if(e.getCurrentItem().getItemMeta().getDisplayName() == null)
			return;
		ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta panemeta = pane.getItemMeta();
		panemeta.setDisplayName(ChatColor.RESET + " ");
		if(e.getCurrentItem().equals(pane))
			return;
		
		
		e.setCancelled(true);
		
	}
	
	
	
	
}
