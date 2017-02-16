package net.jLeague;

import java.io.FileNotFoundException;
import java.text.Format;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonSyntaxException;

import net.jLeague.api.champion.ChampionMastery;
import net.jLeague.api.game.Game;
import net.jLeague.api.game.RecentGames;
import net.jLeague.api.league.League;
import net.jLeague.api.staticdata.Champion;
import net.jLeague.api.staticdata.ChampionList;
import net.jLeague.api.staticdata.Image;
import net.jLeague.api.stats.AggregatedStats;
import net.jLeague.api.stats.PlayerStatsSummary;
import net.jLeague.api.stats.PlayerStatsSummaryList;
import net.jLeague.api.stats.RankedStats;
import net.jLeague.api.summoner.Summoner;
import net.jLeague.net.RiotException;
import net.jLeague.utils.JSONReader;

public class ApplicationTesting {

	private static JSONReader converter = new JSONReader("NA");
	private static Summoner summoner;

	public static void main(String[] args) {
		// Testing get summoner
		// System.out.println("=========SUMMONER=============\n");
		getSummoner("mintzy", "NA");
		// System.out.println("=========RANKED STATS=============\n");
		// getAllRankedStats();
		// System.out.println("=========PLAYER SUMMARY===========\n");
		// getPlayerSummary();
		// System.out.println("=========LEAGUE STATS===========\n");
		// getLeagues();
		// getChampions();
		// getChampionMastery();
		getRecentChampions();
	}

	private static void getRecentChampions() {
		try {
			RecentGames recent = converter.getRecentGames(summoner.getId(), "NA");
			Set<Game> games = recent.getGames();
			int count = 0;
			for (Game game : games) {
				if (count < 4)
					System.out.println(getChampionById(game.getChampionId()));
				count++;
			}
		} catch (RiotException e) {
			e.printStackTrace();
		}
	}

	private static void getChampionMastery() {
		try {
			List<ChampionMastery> topChamps = converter.getTopChampionMastery(summoner.getId(), "NA");
			for (int i = 0; i < 5; i++) {
				System.out.println(getChampionById((int) topChamps.get(i).getChampionId()) + " : "
						+ topChamps.get(i).getChampionLevel());
			}
		} catch (RiotException e) {
			e.printStackTrace();
		}
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

	private static void getLeagues() {
		if (summoner != null) {
			try {
				List<League> leagues = converter.getLeague(summoner.getId(), "NA");
				for (League league : leagues) {
					System.out.println("QUEUE: " + league.getQueue());
					System.out.println("RANK: " + league.getTier() + " " + league.getEntries().get(0).getDivision());
				}
			} catch (RiotException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getChampionById(int championId) {
		try {
			Champion champion = converter.getChampionById(championId, "NA");
			return champion.getName();
		} catch (RiotException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void getChampions() {
		try {
			ChampionList championList;
			championList = converter.getChampions("NA");

			Map<String, Champion> champions = championList.getData();
			for (Entry<String, Champion> champion : champions.entrySet()) {
				// System.out.println(champion.getValue().getTitle().substring(0,
				// 1).toUpperCase()
				// + champion.getValue().getTitle().substring(1) + " " +
				// champion.getKey());
				// System.out.println(champion.getValue().getName() + " : " +
				// champion.getValue().getId());
				// System.out.println(champion.getValue().);
				Image image = champion.getValue().getImage();

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
				String name = new String();
				int wins = 0;
				int losses = 0;
				for (int i = 0; i < pssl.getPlayerStatSummaries().size(); i++) {

					name = pssl.getPlayerStatSummaries().get(i).getPlayerStatSummaryType();
					wins = pssl.getPlayerStatSummaries().get(i).getWins();
					losses = pssl.getPlayerStatSummaries().get(i).getLosses();
					System.out.println(name + ": wins:" + wins + " | losses: " + losses);

				}
			} catch (RiotException e) {
				e.printStackTrace();
			}
		}
	}

}
