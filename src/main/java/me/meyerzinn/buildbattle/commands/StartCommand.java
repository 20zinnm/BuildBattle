package me.meyerzinn.buildbattle.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.meyerzinn.buildbattle.BuildBattle;
import me.meyerzinn.buildbattle.Game;
import me.meyerzinn.buildbattle.status.GameStatus;
import net.md_5.bungee.api.ChatColor;

public class StartCommand implements CommandExecutor {

	private BuildBattle plugin;

	public StartCommand(BuildBattle plugin) {
		this.plugin = plugin;
	}

	// BuildBattle plugin, World world, int timeLimit, int playerLimit, int
	// joinTime, String theme,
	// int judgingTime

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("buildbattle.start")) {
				if (BuildBattle.game == null || BuildBattle.game.getGameStatus() == GameStatus.NO_GAME) {
					if (args.length == 5) {
						World world = player.getWorld();
						int playerLimit = Integer.valueOf(args[1]);
						int joinTime = Integer.valueOf(args[2]) * 1000;
						String theme = args[3];
						int judgingTime = Integer.valueOf(args[4]) * 1000;
						int buildingTime = Integer.valueOf(args[0]) * 1000;
						BuildBattle.game = new Game(plugin, world, buildingTime, playerLimit, joinTime, theme,
								judgingTime);
						player.sendMessage(ChatColor.GREEN + "Game started!");
					} else {
						player.sendMessage(ChatColor.RED + "Invalid arguments!");
					}
				} else {
					// Already a game
					player.sendMessage(ChatColor.RED + "There is already a game in progress!");
				}
			} else {
				// No permission.
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("lang.no-permission")));
			}
		} else {
			// Not player
			sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
		}
		return true;
	}

}
