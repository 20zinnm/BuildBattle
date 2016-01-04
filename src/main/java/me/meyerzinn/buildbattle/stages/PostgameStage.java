package me.meyerzinn.buildbattle.stages;

import me.meyerzinn.buildbattle.Game;
import me.meyerzinn.buildbattle.status.GameStatus;

public class PostgameStage implements Stage {

	public void beginStage(Game game) {
		game.setGameStatus(GameStatus.NO_GAME);
//		System.out.println("Here");
	}

}
