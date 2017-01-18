package net.jLeague.api.currentgame;

import java.util.List;

public class CurrentGameInfo {
	
	private List<BannedChampion> bannedChampions;
	private long gameId;
	private long gameLength;
	private String gameMode;
	private long gameQueueConfigId;
	private long gameStartTime;
	private String gameType;
	private long mapId;
	private Observer observers;
	private List<CurrentGameParticipant> participants;
	private String platformId;
	
	public List<BannedChampion> getBannedChampions() {
		return bannedChampions;
	}

	public long getGameId() {
		return gameId;
	}

	public long getGameLength() {
		return gameLength;
	}

	public String getGameMode() {
		return gameMode;
	}

	public long getGameQueueConfigId() {
		return gameQueueConfigId;
	}
	
	public long getGameStartTime() {
		return gameStartTime;
	}
	
	public String getGameType() {
		return gameType;
	}

	public long getMapId() {
		return mapId;
	}

	public Observer getObservers() {
		return observers;
	}

	public List<CurrentGameParticipant> getParticipants() {
		return participants;
	}

	public String getPlatformId() {
		return platformId;
	}
	
	public String toString() {
		return new String("BannedChampions: " + bannedChampions);
	}

}
