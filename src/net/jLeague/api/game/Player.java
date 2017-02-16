package net.jLeague.api.game;

/**
 * This object contains player information
 * 
 * @author Justin Mintzer
 *
 */
public class Player {
	
	private int championId;
	private long summonerId;
	private int teamId;
	
	public int getChampionId() {
		return championId;
	}
	public long getSummonerId() {
		return summonerId;
	}
	public int getTeamId() {
		return teamId;
	}

}
