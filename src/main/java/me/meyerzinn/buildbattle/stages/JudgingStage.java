package me.meyerzinn.buildbattle.stages;

import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.worldcretornica.plotme_core.Plot;

import me.meyerzinn.buildbattle.Game;

public class JudgingStage implements Stage {

	private TreeMap<Plot, TreeMap<Player, Rating>> ratings = new TreeMap<Plot, TreeMap<Player, Rating>>();
	
	public Stage beginStage(Game game) {
		
		Bukkit.getScheduler().runTaskLater(game.getPlugin(), new Runnable() {

			public void run() {
				Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', textToTranslate))
			}
			
		}, game.getGameConfig().getJudgingTime() * 20);
		return this;
	}

	
	
}
