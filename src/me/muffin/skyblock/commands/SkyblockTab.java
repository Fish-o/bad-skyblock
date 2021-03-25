package me.muffin.skyblock.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class SkyblockTab implements TabCompleter {
	
	
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> arguments = new ArrayList<String>();
		
		if (arguments.isEmpty()) {
			arguments.add("help"); 
			arguments.add("balance");
			arguments.add("test");
			if(sender.hasPermission("skyblock.coins.addcoins") || sender.hasPermission("skyblock.admin"))
				arguments.add("addcoins");
			if(sender.hasPermission("skyblock.coins.setcoins") || sender.hasPermission("skyblock.admin"))
				arguments.add("setcoins"); 
			arguments.add("ironman"); 
			if(sender.hasPermission("skyblock.data.reload") || sender.hasPermission("skyblock.admin"))
				arguments.add("reloaddata");
		}
		
		List<String> result = new ArrayList<String>();
		if(args.length == 1) {
			for(String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase())){
					result.add(a);
				}
				
			}
			return result;
		}	
		return null;
	}
	
	
	
}
