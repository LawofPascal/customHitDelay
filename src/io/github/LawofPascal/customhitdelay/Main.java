package io.github.LawofPascal.customhitdelay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public static Main pl;
	
	public static Main getPL() {
		return pl;
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new HitDelay(), this);
		this.getCommand("UcHD").setExecutor(new HitDelay());
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "My Plugin Works!");
		pl = this;
	}
	
	public void onDisable() {
		Bukkit.broadcastMessage("Be back soon!");
  }
}
