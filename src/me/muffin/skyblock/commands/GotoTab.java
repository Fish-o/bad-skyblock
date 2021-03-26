package me.muffin.skyblock.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class GotoTab implements TabCompleter {
	
	
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> arguments = new ArrayList<String>();
		
		if (arguments.isEmpty()) {
			arguments.add("hub");
			arguments.add("world");
			arguments.add("gold_mine");
			arguments.add("hub");
			arguments.add("barn");
			arguments.add("mushroom_desert");
			arguments.add("deep_caverns");
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