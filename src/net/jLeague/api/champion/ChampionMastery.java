package net.jLeague.api.champion;

/**
 * Contains single champion mastery information for player and champion
 * combination
 * 
 * @author Justin Mintzer
 *
 */
public class ChampionMastery {
	
	private long championId;
	private int championLevel;
	private int championPoints;
	private long championPointsSinceLastlevel;
	private long championPointsUntilNextLevel;
	private boolean chestGranted;
	private long lastPlayTime;
	private long playerId;
	
	public long getChampionId() {
		return championId;
	}
	public int getChampionLevel() {
		return championLevel;
	}
	public int getChampionPoints() {
		return championPoints;
	}
	public long getChampionPointsSinceLastlevel() {
		return championPointsSinceLastlevel;
	}
	public long getChampionPointsUntilNextLevel() {
		return championPointsUntilNextLevel;
	}
	public boolean isChestGranted() {
		return chestGranted;
	}
	public long getLastPlayTime() {
		return lastPlayTime;
	}
	public long getPlayerId() {
		return playerId;
	}

}
