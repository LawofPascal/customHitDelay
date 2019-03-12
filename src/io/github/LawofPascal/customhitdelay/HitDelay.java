package io.github.LawofPascal.customhitdelay;

import java.util.*;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HitDelay implements Listener, CommandExecutor {

	private int Delay1 = 10;
	private int Delay2 = 8;
	private int Delay3 = 4;
	private int Delay4 = 1;

	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@EventHandler
	public void onDamage(final EntityDamageEvent event) {
		Entity e = event.getEntity();
		if (e instanceof Player) {
			Player player = (Player) e;
			if (!player.hasPotionEffect(PotionEffectType.REGENERATION)) {
				player.setMaximumNoDamageTicks(20);
			}
			Collection<PotionEffect> pe = player.getActivePotionEffects();
			for (PotionEffect effect : pe) {
				if (effect.getType().equals(PotionEffectType.REGENERATION)) {
					if (effect.getAmplifier() == 1) {
						player.setMaximumNoDamageTicks(Delay1);
					}
					if (effect.getAmplifier() == 2) {
						player.setMaximumNoDamageTicks(Delay2);
					}
					if (effect.getAmplifier() == 3) {
						player.setMaximumNoDamageTicks(Delay3);
					}
					if (effect.getAmplifier() == 4) {
						player.setMaximumNoDamageTicks(Delay4);
					}
				}
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "OnCommand WORKS!");
		String usage = "Usage: /UcHD Amplifier <Regeneration Amplifier> <Damage Delay: (20 = Normal; 0 = No Delay)>";
		if (cmd.getName().equalsIgnoreCase("UcHD")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + usage);
			} else {
				if (args.length == 3 && args[0].equalsIgnoreCase("Amplifier")) {
					if (args[1].equals("1")) {
						if (isInt(args[2])) {
							Delay1 =  Integer.parseInt(args[2]);
							sender.sendMessage(ChatColor.GREEN + "Success. New delay for Regeneration I  is " + Delay1 + " ticks.");
						} else {
							sender.sendMessage(ChatColor.RED + usage);
							
						}
					} else if (args[1].equals("2")) {
						if (isInt(args[2])) {
							Delay2 = Integer.parseInt(args[2]);
							sender.sendMessage(ChatColor.GREEN + "Success. New delay for Regeneration II  is " + Delay2 + " ticks.");
						} else {
							sender.sendMessage(ChatColor.RED + usage);
							return false;
						}
					} else if (args[1].equals("3")) {
						if (isInt(args[2])) {
							Delay3 = Integer.parseInt(args[2]);
							sender.sendMessage(ChatColor.GREEN + "Success. New delay for Regeneration III  is " + Delay3 + " ticks.");
							return false;

						} else {
							sender.sendMessage(ChatColor.RED + usage);
							return false;
						}
					} else if (args[1].equals("4")) {
						if (isInt(args[2])) {
							Delay4 = Integer.parseInt(args[2]);
							sender.sendMessage(ChatColor.GREEN + "Success. New delay for Regeneration IV  is " + Delay4 + " ticks.");
						} else {
							sender.sendMessage(ChatColor.RED + usage);
							return false;
						}
					} else {
						sender.sendMessage(usage);
						return false;
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Your first argument was: " + args[0] + "try using" + usage);
					return false;
				}
			}
		}
		return false;
	}

}
