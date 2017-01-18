package net.jLeague.api.stats;

public class PlayerStatsSummary {
	
	private AggregatedStats aggregatedStats;
	private int losses;
	private long modifyDate;
	private String playerStatSummaryType;
	private int wins;

	public AggregatedStats getAggregatedStats() {
		return aggregatedStats;
	}

	public int getLosses() {
		return losses;
	}

	public long getModifyDate() {
		return modifyDate;
	}

	public String getPlayerStatSummaryType() {
		return playerStatSummaryType;
	}

	public int getWins() {
		return wins;
	}

}
