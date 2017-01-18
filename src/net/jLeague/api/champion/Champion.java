package net.jLeague.api.champion;

public class Champion {

	private boolean active;
	private boolean botEnabled;
	private boolean botMmEnabled;
	private boolean freeToPlay;
	private long id;
	private boolean rankedPlayEnabled;

	public boolean isActive() {
		return active;
	}

	public boolean isBotEnabled() {
		return botEnabled;
	}

	public boolean isBotMmEnabled() {
		return botMmEnabled;
	}

	public boolean isFreeToPlay() {
		return freeToPlay;
	}

	public long getId() {
		return id;
	}

	public boolean rankedPlayEnabled() {
		return rankedPlayEnabled;
	}

	public String toString() {
		return new String("ACTIVE: " + active + " | Bot Enabled: " + botEnabled + " | BotMMEnabled: " + botMmEnabled
				+ " | FreeToPlay: " + freeToPlay + " | ID: " + id + " RankedPlayEnabled: " + rankedPlayEnabled);
	}

}
