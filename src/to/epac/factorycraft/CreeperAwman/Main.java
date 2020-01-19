package to.epac.factorycraft.CreeperAwman;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static File configFile;
	
	private static Main instance;
	
	public static boolean useVault;
	
	public void onEnable() {
		instance = this;
		
		Metrics metrics = new Metrics(this);
		
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new Events(), this);
		
		configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "Configuration not found. Generating the default one.");

            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        
        useVault = pm.isPluginEnabled("Vault");
		if (!useVault) {
			getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Vault not found. This plugin will be disabled.");
			this.setEnabled(false);
		} else {
			VaultHook.HookIntoVault();
		}
	}
	
	public void onDisable() {
		instance = null;
	}
	
    public static Plugin getInstance() {
        return instance;
    }
}
