package net.jLeague.api.stats;

import java.util.List;

public class PlayerStatsSummaryList {
	
	private List<PlayerStatsSummary> playerStatSummaries;
	private long summonerId;

	public List<PlayerStatsSummary> getPlayerStatSummaries() {
		return playerStatSummaries;
	}

	public long getSummonerId() {
		return summonerId;
	}

}
