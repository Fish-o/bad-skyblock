package me.muffin.skyblock;

import java.util.HashMap;

import me.muffin.skyblock.mobs.MobData;





public class Reference {
	public static HashMap<String, MobData> mobs = new HashMap<String, MobData>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("ZOMBIE_VILLAGER", new MobData(100.0, "Zombie", 1));
		}
	};
}
