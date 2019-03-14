package io.github.LawofPascal.customhitdelay;

import java.io.File;

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
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Ultimatecraft Hit Delay V1.1 Enabled.");
		pl = this;
		createConfig();
	}

	private void createConfig() {
		try {

			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}

			File file = new File(getDataFolder(), "config.yml");

			if (!file.exists()) {
				getLogger().info("Config.yml not found, creating!");
				getConfig().set("Amp1", 0);
				getConfig().set("Amp2", 20);
				getConfig().set("Amp3", 0);
				getConfig().set("Amp4", 20);
				saveDefaultConfig();
			} else {
				getLogger().info(ChatColor.GREEN + "Config.yml found, loading!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onDisable() {
	}
}