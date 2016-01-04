package me.meyerzinn.buildbattle.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.worldcretornica.plotme_core.Plot;
import com.worldcretornica.plotme_core.api.IPlayer;

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
				IPlayer iplayer = BuildBattle.plotme.wrapPlayer(player);
				Plot plot = BuildBattle.plotApi.getPlot(iplayer);
				if (plot != null) {
					if (plot.getOwnerId() == player.getUniqueId()) {
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
											plot.getId().getX() + ":" + plot.getId().getZ())));
							Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
									plugin.getConfig().getString("lang.joined-broadcast")
											.replace("%plot", plot.getId().getX() + ":" + plot.getId().getZ())
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
