package me.meyerzinn.buildbattle.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.meyerzinn.buildbattle.BuildBattle;
import net.md_5.bungee.api.ChatColor;

public class JoinCmd implements CommandExecutor {
	private BuildBattle plugin;

	public JoinCmd(BuildBattle plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("buildbattle.join")) {
				
			} else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lang.no-permission")));
			}
		} else {
			sender.sendMessage(
					ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lang.players-only")));
		}
		return true;
	}
}
