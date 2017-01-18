package net.jLeague.api.currentgame;

import java.util.List;

public class CurrentGameParticipant {
	
	private boolean bot;
	private long championId;
	private List<Mastery> masteries;
	private long profileIconId;
	private List<Rune> runes;
	private long spell1Id;
	private long spell2Id;
	private long summonerId;
	private String summonerName;
	private long teamId;
	
	public boolean getBot() {
		return bot;
	}
	
	public long getChampionId() {
		return championId;
	}
	
	public List<Mastery> getMasteries() {
		return masteries;
	}
	
	public long getProfileIconId() {
		return profileIconId;
	}
	
	public List<Rune> getRunes() {
		return runes;
	}
	
	public long getSpell1Id() {
		return spell1Id;
	}
	
	public long getSpell2Id() {
		return spell2Id;
	}
	
	public long getSummonerId() {
		return summonerId;
	}
	
	public String getSummonerName() {
		return summonerName;
	}
	
	public long getTeamId() {
		return teamId;
	}
	
	

}
