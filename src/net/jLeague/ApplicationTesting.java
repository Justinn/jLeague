package net.jLeague;

import com.google.gson.JsonSyntaxException;

import net.jLeague.api.stats.AggregatedStats;
import net.jLeague.api.stats.PlayerStatsSummary;
import net.jLeague.api.stats.PlayerStatsSummaryList;
import net.jLeague.api.stats.RankedStats;
import net.jLeague.api.summoner.Summoner;
import net.jLeague.net.RiotException;

public class ApplicationTesting {

	private static Converter converter = new Converter();
	private static Summoner summoner;

	public static void main(String[] args) {
		// Testing get summoner
		getSummoner("mintzy", "NA");
		System.out.println("=========RANKED STATS=============");
		getAllRankedStats();
		System.out.println("=========PLAYER SUMMARY===========");
		getPlayerSummary();
	}

	private static void getSummoner(String name, String region) {
		try {

			summoner = converter.getSummoner("mintzy", "NA");
			if (summoner != null) {
				System.out.println("ID: " + Long.toString(summoner.getId()));
				System.out.println("Name: " + summoner.getName());
				System.out.println("ProfileIconId: " + Long.toString(summoner.getProfileIconId()));
				System.out.println("Summoner Level:" + summoner.getSummonerLevel());
			} else
				System.out.println("Couldn't find summoner");
		} catch (RiotException e) {
			e.printStackTrace();
		}
	}

	private static void getCurrentGame() {

	}

	private static void getAllRankedStats() {
		if (summoner != null) {
			if (summoner.getSummonerLevel() == 30) {
				RankedStats rankedStats;
				try {
					rankedStats = converter.getRankedStats(summoner.getId(), "NA");
					for (int i = 1; i < rankedStats.getChampions().size(); i++) {
						if (rankedStats.getChampions().get(i).getId() == 0) {
							AggregatedStats allStats = rankedStats.getChampions().get(i).getStats();
							System.out.println("Wins: " + allStats.getTotalSessionsWon());
							System.out.println("Losses: " + allStats.getTotalSessionsLost());
							System.out.println("Champs killed: " + allStats.getTotalChampionKills());
							System.out.println("Deaths: " + allStats.getTotalDeathsPerSession());
							System.out.println("Player Score: " + allStats.getMaxTotalPlayerScore());
						}
					}
				} catch (JsonSyntaxException e) {
					e.printStackTrace();
				} catch (RiotException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private static void getPlayerSummary() {
		if (summoner != null) {
			try {
				PlayerStatsSummaryList pssl = converter.getPlayerStatsSummaryList(summoner.getId(), "NA");
				for (int i = 0; i < pssl.getPlayerStatSummaries().size(); i++) {
					System.out.println(pssl.getPlayerStatSummaries().get(i).getPlayerStatSummaryType());
					
					if (pssl.getPlayerStatSummaries().get(i).getPlayerStatSummaryType().equalsIgnoreCase("RankedFlexSR")) {
						System.out.println("RANKED FLEX WINS: " + pssl.getPlayerStatSummaries().get(i).getWins());
						//For some reason, we can't get flex losses?
					}
					if (pssl.getPlayerStatSummaries().get(i).getPlayerStatSummaryType().equalsIgnoreCase("RankedSolo5x5")) {
						System.out.println("RANKED SOLO WINS: " + pssl.getPlayerStatSummaries().get(i).getWins());
						System.out.println("RANKED SOLO LOSSES: " + pssl.getPlayerStatSummaries().get(i).getLosses());
						System.out.println("==========================================");
					}
					if (pssl.getPlayerStatSummaries().get(i).getPlayerStatSummaryType().equalsIgnoreCase("AramUnranked5x5")) {
						System.out.println("AramUnranked5x5 WINS: " + pssl.getPlayerStatSummaries().get(i).getWins());
						System.out.println("AramUnranked5x5 LOSSES: " + pssl.getPlayerStatSummaries().get(i).getLosses());
					}
					if (pssl.getPlayerStatSummaries().get(i).getPlayerStatSummaryType().equalsIgnoreCase("CoopVsAI")) {
						System.out.println("CoopVsAI WINS: " + pssl.getPlayerStatSummaries().get(i).getWins());
						System.out.println("CoopVsAI LOSSES: " + pssl.getPlayerStatSummaries().get(i).getLosses());
					}
					if (pssl.getPlayerStatSummaries().get(i).getPlayerStatSummaryType().equalsIgnoreCase("KingPoro")) {
						System.out.println("KING PORO WINS: " + pssl.getPlayerStatSummaries().get(i).getWins());
						System.out.println("KING PORO LOSSES: " + pssl.getPlayerStatSummaries().get(i).getLosses());
					}
				}
			} catch (RiotException e) {
				e.printStackTrace();
			}
		}
	}

}
