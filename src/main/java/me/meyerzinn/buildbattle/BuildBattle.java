package me.meyerzinn.buildbattle;

import java.util.concurrent.TimeUnit;

import org.bukkit.plugin.java.JavaPlugin;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;

public class BuildBattle extends JavaPlugin {

	public Game game;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		PlaceholderAPI.registerPlaceholder(this, "bbtimeleft", new PlaceholderReplacer() {
			public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
				if (game == null) {
					return "N/A";
				}
				return String.format("%dm %ds",
						TimeUnit.MILLISECONDS.toMinutes(game.getCurrentTime() - game.getTimeStarted()),
						TimeUnit.MILLISECONDS.toSeconds(game.getCurrentTime() - game.getTimeStarted())
								- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
										.toMinutes(game.getCurrentTime() - game.getTimeStarted())));
			}
		});
		PlaceholderAPI.registerPlaceholder(this, "bbstage", new PlaceholderReplacer() {
			public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
				if (game == null) {
					return "N/A";
				}
				return game.getStage().toString();
			}
		});
	}

}
