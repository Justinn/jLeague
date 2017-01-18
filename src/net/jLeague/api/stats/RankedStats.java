package net.jLeague.api.stats;

import java.util.List;

public class RankedStats {
	
	private List<ChampionStats> champions;
	private long modifyDate;
	private long summonerId;

	public List<ChampionStats> getChampions() {
		return champions;
	}

	public long getModifyDate() {
		return modifyDate;
	}

	public long getSummonerId() {
		return summonerId;
	}

}
