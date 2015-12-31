package me.meyerzinn.buildbattle;

import java.util.TreeMap;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.worldcretornica.plotme_core.Plot;
/**
 * The main object that runs the show.
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

	public Game(BuildBattle plugin, World world, int timeLimit, int playerLimit, int joinTime, String theme, int judgingTime) {
		this.world = world;
		this.timeStarted = System.currentTimeMillis();
		this.gameConfig = new GameConfig(timeLimit, playerLimit, joinTime, theme, judgingTime);
		startGame(plugin);
	}

	/**
	 * A method to start the game.
	 * @return boolean if it was successful.
	 */
	private void startGame(BuildBattle plugin) {
//		if (BuildBattle.)
		gameStatus = GameStatus.JOINING;
		
	}

	/**
	 * Adds players to the game.
	 * @param player The player who is being added.
	 * @return boolean 
	 */
	
	public boolean addPlayer(Player player) {
		return false;
	}
	
	public World getWorld() {
		return world;
	}

}
