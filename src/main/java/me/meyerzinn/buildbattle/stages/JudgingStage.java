package me.meyerzinn.buildbattle.stages;

import org.bukkit.Bukkit;

import me.meyerzinn.buildbattle.Game;
import me.meyerzinn.buildbattle.status.GameStatus;
import net.md_5.bungee.api.ChatColor;

public class JudgingStage implements Stage {

	public void beginStage(final Game game) {
		game.setGameStatus(GameStatus.JUDGING);
		Bukkit.broadcastMessage(ChatColor.RED + "Judging!");
		Bukkit.getScheduler().runTaskLater(game.getPlugin(), new Runnable() {
			public void run() {
				game.next();
			}
		}, game.getGameConfig().getJudgingTime() / 1000 * 20);
	}

}
