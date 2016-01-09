package me.meyerzinn.buildbattle;

import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.util.MainUtil;

import me.meyerzinn.buildbattle.stages.BuildingStage;
import me.meyerzinn.buildbattle.stages.JoiningStage;
import me.meyerzinn.buildbattle.stages.JudgingStage;
import me.meyerzinn.buildbattle.stages.PostgameStage;
import me.meyerzinn.buildbattle.stages.PregameStage;
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

	private World worl;
	private Long timeStageStarted;
	private GameStatus gameStatus;
	private GameConfig gameConfig;
	private TreeMap<UUID, Plot> players;

	/*
	 * Get the game going.
	 */

	public Game(BuildBattle plugin, World world, int buildingLimit, int playerLimit, int joinTime, String theme,
			int judgingTime) {
		this.plugin = plugin;
		this.players = new TreeMap<UUID, Plot>();
		this.world = world;
		this.timeStageStarted = System.currentTimeMillis();
		this.gameConfig = new GameConfig(buildingLimit, playerLimit, joinTime, theme, judgingTime);
		new PregameStage().beginStage(this);
		// this.gameStatus = GameStatus.PREGAME;
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
			new JoiningStage().beginStage(this);
			break;
		case JOINING:
			new BuildingStage().beginStage(this);
			break;
		case BUILDING:
			new JudgingStage().beginStage(this);
			break;
		case JUDGING:
			new PostgameStage().beginStage(this);
			break;
		default:
			break;
		}
		this.timeStageStarted = System.currentTimeMillis();
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
		if (players.containsKey(player.getUniqueId())) {
			return AddPlayerStatus.ALREADY_JOINED;
		}
		if (players.containsValue(plot)) {
			return AddPlayerStatus.DUPLICATE_PLOT;
		}
		MainUtil.clear(plot, false, new Runnable() {
			public void run() {
			}
		});
		this.players.put(player.getUniqueId(), plot);
		player.teleport((Location) plot.getHome().toBukkitLocation());
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
		return this.timeStageStarted;
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

	public Set<UUID> getPlayers() {
		return players.keySet();
	}

}
