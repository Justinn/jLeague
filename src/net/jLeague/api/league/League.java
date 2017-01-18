package net.jLeague.api.league;

import java.util.List;

public class League {
	
	private List<LeagueEntry> entries;
	private String name;
	private String participantId;
	private String queue;
	private String tier;
	
	public List<LeagueEntry> getEntries() {
		return entries;
	}
	
	public String getName() {
		return name;
	}
	
	public String getParticipantId() {
		return participantId;
	}
	
	public String getQueue() {
		return queue;
	}
	
	public String getTier() {
		return tier;
	}
	
	

}
