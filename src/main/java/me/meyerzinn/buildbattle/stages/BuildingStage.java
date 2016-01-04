package me.meyerzinn.buildbattle.stages;

import org.bukkit.Bukkit;

import me.meyerzinn.buildbattle.Game;
import me.meyerzinn.buildbattle.status.GameStatus;
import net.md_5.bungee.api.ChatColor;

public class BuildingStage implements Stage {

	public void beginStage(final Game game) {
		game.setGameStatus(GameStatus.BUILDING);
		Bukkit.broadcastMessage(
				ChatColor.translateAlternateColorCodes('&', game.getPlugin().getConfig().getString("lang.building")));
		Bukkit.getScheduler().runTaskLater(game.getPlugin(), new Runnable() {
			public void run() {
				game.next();
			}
		}, (game.getGameConfig().getBuildingTime() / 1000) * 20);
	}

}
