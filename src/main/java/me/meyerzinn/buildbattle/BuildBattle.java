package me.meyerzinn.buildbattle;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.meyerzinn.buildbattle.commands.JoinCmd;
import me.meyerzinn.buildbattle.commands.StartCommand;
import me.meyerzinn.buildbattle.listeners.Listeners;
import me.meyerzinn.buildbattle.status.GameStatus;

public class BuildBattle extends JavaPlugin {

	public static Game game;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		PlaceholderAPI.registerPlaceholder(this, "bbtimeleft", new PlaceholderReplacer() {
			public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
				if (game == null || game.getGameStatus() == GameStatus.NO_GAME) {
					return "...";
				}
				switch (game.getGameStatus()) {
				case JOINING:
					return String.format("%dm %ds",
							TimeUnit.MILLISECONDS.toMinutes((game.getTimeStarted() + game.getGameConfig().getJoinTime())
									- System.currentTimeMillis()),
							TimeUnit.MILLISECONDS.toSeconds((game.getTimeStarted() + game.getGameConfig().getJoinTime())
									- System.currentTimeMillis())
									- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
											.toMinutes((game.getTimeStarted() + game.getGameConfig().getJoinTime())
													- System.currentTimeMillis())));
				case BUILDING:
					// return String.format("%dm %ds",
					// TimeUnit.MILLISECONDS.toMinutes((game.getTimeStarted() +
					// (game.getGameConfig()
					// .getTimeLimit()
					// - (game.getGameConfig().getJoinTime() +
					// game.getGameConfig().getJudgingTime())))
					// - game.getCurrentTime()),
					// TimeUnit.MILLISECONDS.toSeconds((game.getTimeStarted() +
					// game.getGameConfig().getJoinTime())
					// - game.getCurrentTime())
					// -
					// TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((game.getTimeStarted()
					// + (game.getGameConfig().getTimeLimit() -
					// (game.getGameConfig().getJoinTime()
					// + game.getGameConfig().getJudgingTime())))
					// - game.getCurrentTime())));
					// System.out.println((game.getTimeStarted() +
					// game.getGameConfig().getBuildingTime())
					// - System.currentTimeMillis());
					return String.format("%dm %ds",
							TimeUnit.MILLISECONDS
									.toMinutes((game.getTimeStarted() + game.getGameConfig().getBuildingTime())
											- System.currentTimeMillis()),
							TimeUnit.MILLISECONDS
									.toSeconds((game.getTimeStarted() + game.getGameConfig().getBuildingTime())
											- System.currentTimeMillis())
									- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
											.toMinutes((game.getTimeStarted() + game.getGameConfig().getBuildingTime())
													- System.currentTimeMillis())));

				case JUDGING:
					return String.format("%dm %ds",
							TimeUnit.MILLISECONDS
									.toMinutes((game.getTimeStarted() + game.getGameConfig().getJudgingTime())
											- System.currentTimeMillis()),
							TimeUnit.MILLISECONDS
									.toSeconds((game.getTimeStarted() + game.getGameConfig().getJudgingTime())
											- System.currentTimeMillis())
									- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
											.toMinutes((game.getTimeStarted() + game.getGameConfig().getJudgingTime())
													- System.currentTimeMillis())));
				case NO_GAME:
					return "...";
				case PREGAME:
					return "...";
				default:
					return "...";
				}
				// return String.format("%dm %ds",
				// TimeUnit.MILLISECONDS.toMinutes(
				// (game.getTimeStarted() + game.getGameConfig().getTimeLimit())
				// - game.getCurrentTime()),
				// TimeUnit.MILLISECONDS.toSeconds(
				// (game.getTimeStarted() + game.getGameConfig().getTimeLimit())
				// - game.getCurrentTime())
				// - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
				// .toMinutes((game.getTimeStarted() +
				// game.getGameConfig().getTimeLimit())
				// - game.getCurrentTime())));
			}
		});
		PlaceholderAPI.registerPlaceholder(this, "bbstage", new PlaceholderReplacer() {
			public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
				if (game == null || game.getGameStatus() == GameStatus.NO_GAME) {
					return "No game!";
				}
				return game.getGameStatus().toString();
			}
		});
		PlaceholderAPI.registerPlaceholder(this, "bbtheme", new PlaceholderReplacer() {

			public String onPlaceholderReplace(PlaceholderReplaceEvent arg0) {
				if (game == null || game.getGameStatus() == GameStatus.NO_GAME) {
					return "...";
				} else {
					return game.getGameConfig().getTheme();
				}
			}

		});
		getCommand("bbjoin").setExecutor(new JoinCmd(this));
		getCommand("bbstart").setExecutor(new StartCommand(this));
		Bukkit.getPluginManager().registerEvents(new Listeners(this), this);
	}

}
