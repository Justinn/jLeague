package net.jLeague.api.game;

import java.util.List;

/**
 * This object contains game information
 * 
 * @author Justin Mintzer
 *
 */
public class Game {
	
	private int championId;
	private long createDate;
	private List<Player> fellowPlayers;
	private long gameId;
	private String gameMode;
	private String gameType;
	private boolean invalid;
	private int ipEarned;
	private int level;
	private int mapId;
	private int spell1;
	private int spell2;
	private RawStats stats;
	private String subType;
	private int teamId;
	
	public int getChampionId() {
		return championId;
	}
	public long getCreateDate() {
		return createDate;
	}
	public List<Player> getFellowPlayers() {
		return fellowPlayers;
	}
	public long getGameId() {
		return gameId;
	}
	public String getGameMode() {
		return gameMode;
	}
	public String getGameType() {
		return gameType;
	}
	public boolean isInvalid() {
		return invalid;
	}
	public int getIpEarned() {
		return ipEarned;
	}
	public int getLevel() {
		return level;
	}
	public int getMapId() {
		return mapId;
	}
	public int getSpell1() {
		return spell1;
	}
	public int getSpell2() {
		return spell2;
	}
	public RawStats getStats() {
		return stats;
	}
	public String getSubType() {
		return subType;
	}
	public int getTeamId() {
		return teamId;
	}

}
