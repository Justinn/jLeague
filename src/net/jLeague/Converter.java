package net.jLeague;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.jLeague.api.currentgame.CurrentGameInfo;
import net.jLeague.api.league.League;
import net.jLeague.api.staticdata.Champion;
import net.jLeague.api.stats.PlayerStatsSummary;
import net.jLeague.api.stats.PlayerStatsSummaryList;
import net.jLeague.api.stats.RankedStats;
import net.jLeague.api.summoner.Summoner;
import net.jLeague.net.RiotException;
import net.jLeague.net.RiotRequest;

public class Converter {

	private static Gson gson;
	private RiotRequest request;

	public Converter() {
		gson = new Gson();
		request = new RiotRequest();
	}

	/*
	 * This method takes in a list of players and the region. It will call riot,
	 * get the json strings and convert it into a Map of Summoner objects using gson
	 * 
	 * @return a map of players
	 */
	public Map<String, Summoner> toSummoners(List<String> name, String region) throws RiotException {
		request.setRegion(region);
		String names = new String("");
		for (String i : name) {
			names = names + i + ",";
		}

		request.modifyRequest(Constants.GET_SUMMONER_BY_NAME, names);
		String json = request.now();
		return gson.fromJson(json, new TypeToken<Map<String, Summoner>>() {
		}.getType());

	}

	public Map<String, List<League>> getLeagueEntry(long summonerId) throws RiotException {
		request.setRegion("NA");
		request.modifyRequest(Constants.GET_LEAGUE_ENTRY, Long.toString(summonerId));
		String json = request.now();
		System.out.println(json);
		return gson.fromJson(json, new TypeToken<Map<String, List<League>>>() {
		}.getType());
	}

	/**
	 * This method takes in a name of a summoner and the region. It will return
	 * a single summoner object using toSummoners method.
	 * 
	 * @param name
	 *            - name of summoner you want
	 * @param region
	 *            - the region
	 * @return
	 * @throws CallException
	 *             if we cant find the summoner or whatever else can go wrong in
	 *             a call
	 */
	public Summoner getSummoner(String input, String region) throws RiotException {
		ArrayList<String> name = new ArrayList<String>();
		name.add(input);
		Map<String, Summoner> summoner = toSummoners(name, region);
		return summoner.get(input.replace(" ", ""));
	}

	/**
	 * This method will convert a json string of a PlayerStatsSummaryList and
	 * store into an object using gson
	 * 
	 * @param input
	 * @return a PlayerStatsSummaryList object
	 */
	public PlayerStatsSummaryList toSummaryList(String json) {
		PlayerStatsSummaryList summaryList = gson.fromJson(json, PlayerStatsSummaryList.class);
		return summaryList;
	}

	/**
	 * This method will convert a json string of a CurrentGameInfo object and
	 * store it into a java object
	 * 
	 * @param json
	 * @return CurrentGameInfo object
	 */
	public CurrentGameInfo getCurrentGameInfo(String json) {
		CurrentGameInfo cgi = gson.fromJson(json, CurrentGameInfo.class);
		return cgi;
	}

	/**
	 * This method will take in a profileiconid
	 * 
	 * @param id
	 * @return
	 */
	public ImageIcon obtainProfileIcon(long id) {
		ImageIcon icon = null;
		try {
			URL url = new URL("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/" + id + ".png");
			BufferedImage image = ImageIO.read(url);
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	}

	/**
	 * This method will take in a json string and create/return a champion
	 * object from it using gson
	 * 
	 * @param json
	 * @return
	 */
	public Champion getChampion(String json) {
		return gson.fromJson(json, Champion.class);
	}

	public RankedStats getRankedStats(long summonerId, String region) throws JsonSyntaxException, RiotException {
		request.setRegion(region);
		request.modifyRequest(Constants.GET_RANKED_STATS, Long.toString(summonerId));
		return gson.fromJson(request.now(), RankedStats.class);
	}

	public PlayerStatsSummaryList getPlayerStatsSummaryList(long summonerId, String region) throws  RiotException {
		request.setRegion(region);
		request.modifyRequest(Constants.GET_PLAYER_SUMMARY, Long.toString(summonerId));
		return gson.fromJson(request.now(), PlayerStatsSummaryList.class);
	}

}
