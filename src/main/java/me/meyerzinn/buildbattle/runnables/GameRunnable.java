package me.meyerzinn.buildbattle.runnables;

import org.bukkit.scheduler.BukkitRunnable;

import me.meyerzinn.buildbattle.Game;

public class GameRunnable extends BukkitRunnable {

	private Game game;
	
	public GameRunnable(Game game) {
		this.game = game;
	}
	
	public void run() {
		game.setCurrentTime(System.currentTimeMillis());
		if (game.getCurrentTime() - game.getTimeStarted() >= game.getGameConfig().getTimeLimit() * 1000) {
			// Game is over.
			this.cancel();
		}
	}

}
