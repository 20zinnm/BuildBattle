package me.meyerzinn.buildbattle;

public class GameConfig {
	
	private int timeLimit;
	private int playerLimit;
	private int joinTime;
	private String theme;
	private int judgingTime;

	public GameConfig(int timeLimit, int playerLimit, int joinTime, String theme, int judgingTime) {
		setTimeLimit(timeLimit);
		setPlayerLimit(playerLimit);
		setJoinTime(joinTime);
		setTheme(theme);
		setJudgingTime(judgingTime);
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public int getPlayerLimit() {
		return playerLimit;
	}

	public void setPlayerLimit(int playerLimit) {
		this.playerLimit = playerLimit;
	}

	public int getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(int joinTime) {
		this.joinTime = joinTime;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getJudgingTime() {
		return judgingTime;
	}

	public void setJudgingTime(int judgingTime) {
		this.judgingTime = judgingTime;
	}
}
