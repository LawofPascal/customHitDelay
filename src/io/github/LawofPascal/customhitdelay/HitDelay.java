package io.github.LawofPascal.customhitdelay;

import java.util.*;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HitDelay implements Listener, CommandExecutor {

	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@EventHandler
	public void onDamage(final EntityDamageByEntityEvent event) {
		Entity e = event.getEntity();
		if (e instanceof Player) {
			Player player = (Player) e;
			if (!player.hasPotionEffect(PotionEffectType.REGENERATION)) {
				player.setMaximumNoDamageTicks(20);
			}
			Collection<PotionEffect> pe = player.getActivePotionEffects();
			for (PotionEffect effect : pe) {
				if (effect.getType().equals(PotionEffectType.REGENERATION)) {
					player.setMaximumNoDamageTicks(Main.getPL().getConfig().getInt("Amp1"));
					if (effect.getAmplifier() == 2) {
						player.setMaximumNoDamageTicks(Main.getPL().getConfig().getInt("Amp2"));
					}
					if (effect.getAmplifier() == 3) {
						player.setMaximumNoDamageTicks(Main.getPL().getConfig().getInt("Amp3"));
					}
					if (effect.getAmplifier() == 4) {
						player.setMaximumNoDamageTicks(Main.getPL().getConfig().getInt("Amp4"));
					}
				}
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (sender.hasPermission("uchdperm")) {
			String usage = "Usage: /UcHD Amplifier <Regeneration Amplifier> <Damage Delay: (20 = Normal; 0 = No Delay)>";
			if (cmd.getName().equalsIgnoreCase("UcHD")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED + usage + " /UcHD R(Value) to see values. Perm: uchdperm.");
				} else {
					if (args.length == 1 && args[0].equalsIgnoreCase("Reload")) {
						Main.getPL().reloadConfig();
						sender.sendMessage(ChatColor.GREEN + "Config reload successful.");
					} else if (args.length == 1 && args[0].equalsIgnoreCase("R1")) {
						sender.sendMessage(ChatColor.AQUA + "Delay for Regeneration I is "
								+ Main.getPL().getConfig().getInt("Amp1"));
					} else if (args.length == 1 && args[0].equalsIgnoreCase("R2")) {
						sender.sendMessage(ChatColor.AQUA + "Delay for Regeneration II is "
								+ Main.getPL().getConfig().getInt("Amp2"));
					} else if (args.length == 1 && args[0].equalsIgnoreCase("R3")) {
						sender.sendMessage(ChatColor.AQUA + "Delay for Regeneration III is "
								+ Main.getPL().getConfig().getInt("Amp3"));
					} else if (args.length == 1 && args[0].equalsIgnoreCase("R4")) {
						sender.sendMessage(ChatColor.AQUA + "Delay for Regeneration IV is "
								+ Main.getPL().getConfig().getInt("Amp4"));
					} else if (args.length == 3 && args[0].equalsIgnoreCase("Amplifier")) {
						if (args[1].equals("1")) {
							if (isInt(args[2])) {
								Main.getPL().getConfig().set("Amp1", Integer.parseInt(args[2]));
								Main.getPL().saveConfig();
								sender.sendMessage(ChatColor.GREEN + "Success. New delay for Regeneration I  is "
										+ (args[2]) + " ticks.");
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + usage);

							}
						} else if (args[1].equals("2")) {
							if (isInt(args[2])) {
								Main.getPL().getConfig().set("Amp2", Integer.parseInt(args[2]));
								Main.getPL().saveConfig();
								Main.getPL().reloadConfig();
								sender.sendMessage(ChatColor.GREEN + "Success. New delay for Regeneration II  is "
										+ (args[2]) + " ticks.");
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + usage);
								return false;
							}
						} else if (args[1].equals("3")) {
							if (isInt(args[2])) {
								Main.getPL().getConfig().set("Amp3", Integer.parseInt(args[2]));
								Main.getPL().saveConfig();
								Main.getPL().reloadConfig();
								sender.sendMessage(ChatColor.GREEN + "Success. New delay for Regeneration III  is "
										+ (args[2]) + " ticks.");
								return true;

							} else {
								sender.sendMessage(ChatColor.RED + usage);
								return false;
							}
						} else if (args[1].equals("4")) {
							if (isInt(args[2])) {
								Main.getPL().getConfig().set("Amp4", Integer.parseInt(args[2]));
								Main.getPL().saveConfig();
								Main.getPL().reloadConfig();
								sender.sendMessage(ChatColor.GREEN + "Success. New delay for Regeneration IV  is "
										+ (args[2]) + " ticks.");
								return true;
							} else {
								sender.sendMessage(ChatColor.RED + usage);
								return false;
							}
						} else {
							sender.sendMessage(ChatColor.RED + usage);
							return false;
						}
					} else {
						sender.sendMessage(ChatColor.RED + "You used: " + args[0] + " try using " + usage);
						return false;
					}
				}
			}
			return true;
		}
		sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
		return false;
	}
}
