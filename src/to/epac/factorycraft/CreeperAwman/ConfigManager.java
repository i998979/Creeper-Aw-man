package to.epac.factorycraft.CreeperAwman;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
	
	static Plugin plugin = Main.getInstance();
	
	public static ArrayList<String> getLyricsList() {
		ArrayList<String> lyrics = new ArrayList<>();
		
		ConfigurationSection lyricsList = plugin.getConfig().getConfigurationSection("CreeperAwman");
		
		for (String key : lyricsList.getKeys(false)) {
			lyrics.add(key);
		}
		return lyrics;
	}
	
	public static ArrayList<String> getMultipleLyrics(String lyric) {
		ArrayList<String> lyrics = new ArrayList<>();
		
		List<String> lyricsList = plugin.getConfig().getStringList("CreeperAwman." + lyric);
		
		for (String key : lyricsList) {
			lyrics.add(key);
		}
		return lyrics;
		
	}
	public static int getReward() {
		return plugin.getConfig().getInt("Reward");
	}
	
	public static int getRewardWhen() {
		return plugin.getConfig().getInt("Reward_When");
	}
	
	public static int getCount() {
		return plugin.getConfig().getInt("Count");
	}
	public static void setCount(int count) {
		plugin.getConfig().set("Count", count);
		plugin.saveConfig();
	}
	
	public static String getPrev() {
		return plugin.getConfig().getString("Previous");
	}
	public static void setPrev(String prev) {
		plugin.getConfig().set("Previous", prev);
		plugin.saveConfig();
	}
	
	public static boolean getSequential() {
		return plugin.getConfig().getBoolean("Sequential");
	}
}
