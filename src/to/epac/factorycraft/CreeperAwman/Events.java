package to.epac.factorycraft.CreeperAwman;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Events implements Listener {
	
	ArrayList<Player> participated = new ArrayList<>();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		String message = event.getMessage();
		Set<Player> recipients = event.getRecipients();
		
		// If soundtrack plays non-sequentially
		if (!ConfigManager.getSequential()) {
			// Loop through all available lyrics
			for (String lyric : ConfigManager.getLyricsList()) {
				// Loop through all multi-lyrics
				for (String mlyric : ConfigManager.getMultipleLyrics(lyric)) {
					// if multi-lyric contains the message
					if (mlyric.equalsIgnoreCase(message)) {
						String path = lyric.replaceAll(" ", "_");
						path = "custom." + path;
			            path = path.toLowerCase();
			            
			            for (Player reciver: recipients) {
			                reciver.stopSound(ConfigManager.getPrev());
			                reciver.playSound(player.getLocation(), path, 100, 1);
			            }
			            ConfigManager.setPrev(path);
					}
				}
			}
			return;
		}
		
		int count = ConfigManager.getCount();
		
		if (ConfigManager.getLyricsList().size() <= count) {
			ConfigManager.setCount(0);
			return;
		}
		
		String id = ConfigManager.getLyricsList().get(count);
		ArrayList <String> mLyricList = ConfigManager.getMultipleLyrics(id);
		
		for (String mlyric : mLyricList) {
			if (mlyric.equalsIgnoreCase(message)) {
				
				String path = id.replaceAll(" ", "_");
				path = "custom." + path;
	            path = path.toLowerCase();
	            
	            for (Player reciver: recipients) {
	                reciver.stopSound(ConfigManager.getPrev());
	                reciver.playSound(player.getLocation(), path, 100, 1);
	            }
	            
	            ConfigManager.setPrev(path);
	            ConfigManager.setCount(count + 1);
	            
	            if (!participated.contains(player))
	            	participated.add(player);
	            
	            if (Main.useVault) {
	            	if ((count + 1) % ConfigManager.getRewardWhen() == 0) {
	            		
	            		if (participated != null && !participated.isEmpty()) {
	            			for (Player participant : participated) {
	            				VaultHook.getEconomy().depositPlayer(participant, ConfigManager.getReward());
	            				participant.sendMessage(ChatColor.GREEN + "You have received $" + ConfigManager.getReward() + " in participating the event.");
	            			}
	            			participated = new ArrayList<>();
	            		}
	            	}
	            }
	            
	            return;
			}
		}
		
		ConfigManager.setCount(0);
		// Update the variable
		count = ConfigManager.getCount();
		id = ConfigManager.getLyricsList().get(count);
		mLyricList = ConfigManager.getMultipleLyrics(id);
		
		for (String mlyric : mLyricList) {
			if (mlyric.equalsIgnoreCase(message)) {
				
				String path = id.replaceAll(" ", "_");
				path = "custom." + path;
	            path = path.toLowerCase();
	            
	            for (Player reciver: recipients) {
	                reciver.stopSound(ConfigManager.getPrev());
	                reciver.playSound(player.getLocation(), path, 100, 1);
	            }
	            
	            ConfigManager.setPrev(path);
	            ConfigManager.setCount(count + 1);
	            
	            if (!participated.contains(player))
	            	participated.add(player);
	            
	            return;
			}
		}
	}
}
