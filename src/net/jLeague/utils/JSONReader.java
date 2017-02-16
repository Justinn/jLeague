package net.jLeague.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import net.jLeague.Constants;
import net.jLeague.api.champion.ChampionMastery;
import net.jLeague.api.currentgame.CurrentGameInfo;
import net.jLeague.api.game.RecentGames;
import net.jLeague.api.league.League;
import net.jLeague.api.staticdata.Champion;
import net.jLeague.api.staticdata.ChampionList;
import net.jLeague.api.staticdata.Realm;
import net.jLeague.api.stats.PlayerStatsSummaryList;
import net.jLeague.api.stats.RankedStats;
import net.jLeague.api.summoner.Summoner;
import net.jLeague.net.RiotException;
import net.jLeague.net.RiotRequest;

/**
 * Converter - This class makes a request to riot and reads the json response,
 * translating it into a java object using gson
 * 
 * @author Justin Mintzer
 *
 */
public class JSONReader {

	private static Gson gson;
	private RiotRequest request;
	
	private String profileVersion;
	private String championVersion;
	
	private String region;

	public JSONReader(String region) {
		gson = new Gson();
		request = new RiotRequest();
		
	}

	/*
	 * This method takes in a list of players and the region. It will call riot,
	 * get the json strings and convert it into a Map of Summoner objects using
	 * gson
	 * 
	 * @return a map of players
	 */
	public Map<String, Summoner> getSummoners(List<String> name, String region) throws RiotException {
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

	/**
	 * getLeagueEntries - gets a Map of lists of summoners
	 * 
	 * @param summonerIds
	 * @param region
	 * @return
	 * @throws RiotException
	 */
	public Map<String, List<League>> getLeagues(List<Long> summonerIds, String region) throws RiotException {
		request.setRegion(region);

		if (summonerIds.size() > 1) {
			String ids = new String("");

			for (Long i : summonerIds) {
				ids = ids + i + ",";
			}
			request.modifyRequest(Constants.GET_LEAGUE_ENTRY, ids);
		} else
			request.modifyRequest(Constants.GET_LEAGUE_ENTRY, summonerIds.toString().replace("[", "").replace("]", ""));

		String json = request.now();
		System.out.println(json);
		return gson.fromJson(json, new TypeToken<Map<String, List<League>>>() {
		}.getType());
	}

	/**
	 * getTopChampionMastery - Returns a list of champion mastery objects
	 * 
	 * @param summonerId
	 * @param region
	 * @return
	 * @throws RiotException
	 */
	public List<ChampionMastery> getTopChampionMastery(long summonerId, String region) throws RiotException {
		request.setRegion(region);
		request.modifyRequest(Constants.GET_TOP_CHAMPION_MASTERY, Long.toString(summonerId));
		return gson.fromJson(request.now(), new TypeToken<List<ChampionMastery>>() {
		}.getType());
	}

	/**
	 * getCHampionById - Returns a Champion object by their id
	 * 
	 * @param championId
	 * @param region
	 * @return
	 * @throws RiotException
	 */
	public Champion getChampionById(int championId, String region) throws RiotException {
		request.setRegion(region);
		request.modifyRequest(Constants.GET_CHAMPION_BY_ID, Integer.toString(championId));
		
		return gson.fromJson(request.now(), Champion.class);
	}

	/**
	 * This method takes in a name of a summoner and the region. It will return
	 * a single summoner object using getSummoners method.
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
	public Summoner getSummoner(String summonerName, String region) throws RiotException {
		ArrayList<String> name = new ArrayList<String>();
		name.add(summonerName);
		Map<String, Summoner> summoner = getSummoners(name, region);
		return summoner.get(summonerName.replace(" ", ""));
	}

	/**
	 * This method takes in a name of a summoner and the region. It will return
	 * a list of the players leagues using getLeagues method.
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
	public List<League> getLeague(long summonerId, String region) throws RiotException {
		ArrayList<Long> id = new ArrayList<Long>();
		id.add(summonerId);
		Map<String, List<League>> leagues = getLeagues(id, region);
		return leagues.get(Long.toString(summonerId));
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
	 * obtainProfileIcon - This method will take in a profileiconid and return
	 * the icon
	 * 
	 * @param id
	 * @return
	 * @throws RiotException 
	 */
	public ImageIcon obtainProfileIcon(long id, String region) throws RiotException {
		ImageIcon icon = null;
		try {
			URL url = new URL("http://ddragon.leagueoflegends.com/cdn/7.1.1/img/profileicon/" + id + ".png");
			BufferedImage image = ImageIO.read(url);
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	}
	
	public Realm getRealm(String region) throws RiotException {
		request.setRegion(region);
		request.modifyRequest(Constants.GET_STATIC_REALM_DATA, "");
		return gson.fromJson(request.now(), Realm.class);
	}

	/**
	 * getChampionIcon - THis method will take in a champion name and return the
	 * icon
	 * 
	 * @param championName
	 * @return
	 * @throws RiotException 
	 */
	public ImageIcon getChampionIcon(String championName, String region) throws RiotException {
		ImageIcon icon = null;
		try {
			URL url = new URL("http://ddragon.leagueoflegends.com/cdn/7.2.1/img/champion/" + championName + ".png");
			BufferedImage image = ImageIO.read(url);
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return icon;
	}

	/**
	 * getRankedStats - this method takes in a summonerId and region and returns
	 * a RankedStats object
	 * 
	 * @param summonerId
	 * @param region
	 * @return
	 * @throws RiotException
	 */
	public RankedStats getRankedStats(long summonerId, String region) throws RiotException {
		request.setRegion(region);
		request.modifyRequest(Constants.GET_RANKED_STATS, Long.toString(summonerId));
		return gson.fromJson(request.now(), RankedStats.class);
	}

	/**
	 * getPlayerStatsSummaryList - This method takes in a summonerId and region
	 * and returns a PlayerStatsSummaryList object
	 * 
	 * @param summonerId
	 * @param region
	 * @return
	 * @throws RiotException
	 */
	public PlayerStatsSummaryList getPlayerStatsSummaryList(long summonerId, String region) throws RiotException {
		request.setRegion(region);
		request.modifyRequest(Constants.GET_PLAYER_SUMMARY, Long.toString(summonerId));
		return gson.fromJson(request.now(), PlayerStatsSummaryList.class);
	}

	/**
	 * getChampions - This method takes in a region and returns a ChampionList
	 * object
	 * 
	 * @param region
	 * @return
	 * @throws RiotException
	 * @throws FileNotFoundException 
	 */
	public ChampionList getChampions(String region) throws FileNotFoundException {
		request.setRegion(region);
		request.modifyRequest(Constants.GET_STATIC_CHAMPION_DATA, "");
		JsonReader json = new JsonReader(new FileReader("utils/patches/en_US/champion.json"));
		return gson.fromJson(json, ChampionList.class);
	}

	/**
	 * getRecentGames - This method takes in a summonerId and their region.
	 * Returns a RecentGames object
	 * 
	 * @param summonerId
	 * @param region
	 * @return
	 * @throws RiotException
	 */
	public RecentGames getRecentGames(long summonerId, String region) throws RiotException {
		request.setRegion(region);
		request.modifyRequest(Constants.GET_RECENT_GAMES, Long.toString(summonerId));
		return gson.fromJson(request.now(), RecentGames.class);
	}

}
