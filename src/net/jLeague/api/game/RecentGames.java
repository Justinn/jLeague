package net.jLeague.api.game;

import java.util.Set;

/**
 * This object contains recent games informatin
 * 
 * @author Justin Mintzer
 *
 */
public class RecentGames {
	
	private Set<Game> games;
	private long summonerId;
	
	public Set<Game> getGames() {
		return games;
	}
	public long getSummonerId() {
		return summonerId;
	}

}
