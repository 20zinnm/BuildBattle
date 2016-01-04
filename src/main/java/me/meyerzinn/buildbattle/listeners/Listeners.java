package me.meyerzinn.buildbattle.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import me.meyerzinn.buildbattle.BuildBattle;
import me.meyerzinn.buildbattle.status.GameStatus;

public class Listeners implements Listener {

	private BuildBattle plugin;

	public Listeners(BuildBattle plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent e) {
		if (BuildBattle.game != null && BuildBattle.game.getGameStatus() != GameStatus.NO_GAME) {
			if (BuildBattle.game.getGameStatus() != GameStatus.BUILDING) {
				if (BuildBattle.game.getPlayers().contains(e.getPlayer().getUniqueId())) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
							plugin.getConfig().getString("lang.no-block-place")));
				}
			}
		}
	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		if (BuildBattle.game != null && BuildBattle.game.getGameStatus() != GameStatus.NO_GAME) {
			if (BuildBattle.game.getGameStatus() != GameStatus.BUILDING) {
				if (BuildBattle.game.getPlayers().contains(e.getPlayer().getUniqueId())) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
							plugin.getConfig().getString("lang.no-block-break")));
				}
			}
		}
	}

//	@EventHandler
//	public void onPlayerMoveEvent(PlayerMoveEvent e) {
//		if (BuildBattle.game != null && BuildBattle.game.getGameStatus() != GameStatus.NO_GAME) {
//			if (BuildBattle.game.getGameStatus() != GameStatus.BUILDING) {
//				if (BuildBattle.game.getPlayers().contains(e.getPlayer().getUniqueId())) {
//					e.getPlayer().setVelocity(new Vector());
//					e.getPlayer().sendMessage(
//							ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lang.no-move")));
//				}
//			}
//		}
//	}

	@EventHandler
	public void onPlayerTeleportEvent(PlayerTeleportEvent e) {
		if (BuildBattle.game != null && BuildBattle.game.getGameStatus() != GameStatus.NO_GAME) {
			if (BuildBattle.game.getGameStatus() != GameStatus.BUILDING) {
				if (BuildBattle.game.getPlayers().contains(e.getPlayer().getUniqueId())) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(
							ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("lang.no-move")));
				}
			}
		}
	}

}
