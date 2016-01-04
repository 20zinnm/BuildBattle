package me.meyerzinn.buildbattle.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.intellectualcrafters.plot.PS;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotManager;

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
				// AddPlayerStatus status = BuildBattle.game.addPlayer(player,
				// plot)
				PlotManager plotManager = PS.get().getPlotManager(player.getWorld().getName());
				Plot plot = PS.get().getPlot(player.getWorld().getName(),
						plotManager.getPlotId(PS.get().getPlotWorld(player.getWorld().getName()),
								(int) player.getLocation().getX(), (int) player.getLocation().getY(),
								(int) player.getLocation().getZ()));
				// Plot plot = PS.get().getPlot(player.getWorld(),);
				if (plot != null) {
					if (plot.getOwners().contains(player.getUniqueId())) {
						switch (BuildBattle.game.addPlayer(player, plot)) {
						case ALREADY_JOINED:
							player.sendMessage(ChatColor.translateAlternateColorCodes('&',
									plugin.getConfig().getString("lang.already-joined")));
							break;
						case DUPLICATE_PLOT:
							player.sendMessage(ChatColor.translateAlternateColorCodes('&',
									plugin.getConfig().getString("lang.duplicate-plot")));
							break;
						case GAME_FULL:
							player.sendMessage(ChatColor.translateAlternateColorCodes('&',
									plugin.getConfig().getString("lang.game-full")));
							break;
						case IN_PROGRESS:
							player.sendMessage(ChatColor.translateAlternateColorCodes('&',
									plugin.getConfig().getString("lang.in-progress")));
							break;
						case NO_GAME:
							player.sendMessage(ChatColor.translateAlternateColorCodes('&',
									plugin.getConfig().getString("lang.no-game")));
							break;
						case OK:
							player.sendMessage(ChatColor.translateAlternateColorCodes('&',
									plugin.getConfig().getString("lang.joined").replace("%plot",
											plot.getId().x + ":" + plot.getId().y)));
							Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
									plugin.getConfig().getString("lang.joined-broadcast")
											.replace("%plot", plot.getId().x + ":" + plot.getId().y)
											.replace("%player%", player.getName())));
							break;
						default:
							break;
						}
					} else {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&',
								plugin.getConfig().getString("lang.not-owned")));
					}
				} else {
					player.sendMessage(
							ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lang.not-plot")));
				}
			} else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						plugin.getConfig().getString("lang.no-permission")));
			}
		} else {
			sender.sendMessage(
					ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lang.players-only")));
		}
		return true;
	}
}
