package me.meyerzinn.buildbattle;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.worldcretornica.plotme_core.PlotMeCoreManager;
import com.worldcretornica.plotme_core.bukkit.PlotMe_CorePlugin;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;

public class BuildBattle extends JavaPlugin {

	public static Game game;
	public static PlotMe_CorePlugin plotme;
	public static PlotMeCoreManager plotApi;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		plotme = (PlotMe_CorePlugin) Bukkit.getPluginManager().getPlugin("PlotMe");
		plotApi = PlotMeCoreManager.getInstance();
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
