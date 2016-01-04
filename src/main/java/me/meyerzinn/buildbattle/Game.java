package me.meyerzinn.buildbattle;

import java.util.Set;
import java.util.TreeMap;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.intellectualcrafters.plot.object.Plot;

import me.meyerzinn.buildbattle.runnables.GameRunnable;
import me.meyerzinn.buildbattle.stages.JoiningStage;
import me.meyerzinn.buildbattle.stages.PregameStage;
import me.meyerzinn.buildbattle.stages.Stage;
import me.meyerzinn.buildbattle.status.AddPlayerStatus;
import me.meyerzinn.buildbattle.status.GameStatus;

/**
 * The main object that runs the show.
 * 
 * @author AniSkywalker
 */
public class Game {

	private BuildBattle plugin;

	/*
	 * DEFINE RUNTIME VARIABLES
	 */

	private World world;
	private Long timeStarted;
	private Long currentTime;
	private GameStatus gameStatus;
	private GameConfig gameConfig;
	private TreeMap<Player, Plot> players;
	private Stage stage;

	/*
	 * Get the game going.
	 */

	public Game(BuildBattle plugin, World world, int timeLimit, int playerLimit, int joinTime, String theme,
			int judgingTime) {
		this.plugin = plugin;
		this.players = new TreeMap<Player, Plot>();
		new GameRunnable(this).runTaskTimerAsynchronously(plugin, 0, 5);
		this.world = world;
		this.timeStarted = System.currentTimeMillis();
		this.gameConfig = new GameConfig(timeLimit, playerLimit, joinTime, theme, judgingTime);
		this.setStage(new PregameStage().beginStage(this));
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
		switch (this.gameStatus) {
		case PREGAME:
			this.setStage(new JoiningStage().beginStage(this));
			break;
		case JOINING:

			break;
		case BUILDING:
			break;
		case JUDGING:
			break;
		case POSTGAME:
			break;
		default:
			break;
		}
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
		if (players.containsKey(player)) {
			return AddPlayerStatus.ALREADY_JOINED;
		}
		if (players.containsValue(plot)) {
			return AddPlayerStatus.DUPLICATE_PLOT;
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

	/**
	 * 
	 * @return Stage The current stage (or null if there is not one).
	 */
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * 
	 * @return BuildBattle The plugin registering the game.
	 */
	public BuildBattle getPlugin() {
		return plugin;
	}

	/**
	 * 
	 * @return GameConfig
	 */
	public GameConfig getGameConfig() {
		return this.gameConfig;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Set<Player> getPlayers() {
		return players.keySet();
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	public Long getCurrentTime() {
		return this.currentTime;
	}

}
