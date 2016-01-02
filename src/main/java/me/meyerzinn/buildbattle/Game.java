package me.meyerzinn.buildbattle;

import java.util.TreeMap;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.worldcretornica.plotme_core.Plot;

import me.meyerzinn.buildbattle.status.AddPlayerStatus;

/**
 * The main object that runs the show.
 * 
 * @author AniSkywalker
 */
public class Game {

	/*
	 * DEFINE RUNTIME VARIABLES
	 */

	private World world;
	private Long timeStarted;
	private GameStatus gameStatus;
	private GameConfig gameConfig;
	private TreeMap<Player, Plot> players = new TreeMap<Player, Plot>();

	/*
	 * Get the game going.
	 */

	public Game(BuildBattle plugin, World world, int timeLimit, int playerLimit, int joinTime, String theme,
			int judgingTime) {
		this.world = world;
		this.timeStarted = System.currentTimeMillis();
		this.gameConfig = new GameConfig(timeLimit, playerLimit, joinTime, theme, judgingTime);
	}

	/*
	 * THIS IS THE COMPLEX PART. Each stage of the game will call this method
	 * once it's done. Then, this will run the "next" stage or end the game.
	 */
	public void next() {
		/*
		 * Figure out the next section and initialize it. That section will
		 * replace the gameState, etc.
		 */
	}

	/**
	 * Adds players to the game.
	 * 
	 * @param player
	 *            The player who is being added.
	 * @param plot
	 *            The plot the player is adding.
	 * @return AddPlayerStatus
	 */

	public AddPlayerStatus addPlayer(Player player, Plot plot) {

		if (gameStatus != GameStatus.JOINING) {
			return AddPlayerStatus.IN_PROGRESS;
		}
		if (players.size() >= gameConfig.getPlayerLimit()) {
			return AddPlayerStatus.GAME_FULL;
		}
		this.players.put(player, plot);
		return AddPlayerStatus.OK;
	}

	/**
	 * @return World The world this takes place in (or null if there is not
	 *         one).
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @return Long The system time in milliseconds the game started (or null if
	 *         there is not one).
	 */
	public Long getTimeStarted() {
		return this.timeStarted;
	}

	/**
	 * 
	 * @return GameStatus The current status of the game (or null if there is
	 *         not one).
	 */
	public GameStatus getGameStatus() {
		return this.gameStatus;
	}

}
