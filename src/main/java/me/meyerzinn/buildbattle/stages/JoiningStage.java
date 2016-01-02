package me.meyerzinn.buildbattle.stages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.meyerzinn.buildbattle.Game;
import me.meyerzinn.buildbattle.status.GameStatus;

public class JoiningStage implements Stage {
	/**
	 * @return Stage
	 */
	public Stage beginStage(final Game game) {
		game.setGameStatus(GameStatus.JOINING);
		Bukkit.broadcastMessage(
				ChatColor.translateAlternateColorCodes('&', game.getPlugin().getConfig().getString("lang.joining")));
		Bukkit.getScheduler().runTaskLater(game.getPlugin(), new Runnable() {
			public void run() {
				game.next();
			}
		}, game.getGameConfig().getJoinTime() * 20);
		return this;
	}

}
