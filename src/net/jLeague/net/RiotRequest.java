package net.jLeague.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import net.jLeague.Constants;

/**
 * <p>
 * Title: Call Riot class
 * </p>
 * <p>
 * Description: This class will be used to call riot and store whatever they
 * reply with
 * </p>
 * 
 * @author Justin Mintzer
 *
 */
public class RiotRequest {

	private String url;
	private String region;
	private String response;

	/**
	 * Creates the base for our URL
	 */
	public RiotRequest() {
		url = new String();
		region = new String();
		response = new String();
	}

	public String now() throws RiotException {
		try {
			if (!url.contains("dragon"))
				url += "api_key=" + Constants.API_KEY;

			System.out.println(url);
			URL url = new URL(this.url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (conn.getResponseCode() != 200)
				throw new RiotException(conn.getResponseCode());
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);
			response = new String(buffer.toString());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return response;
	}

	/**
	 * The region we are calling about
	 * 
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * The request is the url with different attributes. Different requests can
	 * alter the reply of riot
	 * 
	 * @param method
	 * @param args
	 */
	public void modifyRequest(int request, String id) {
		url = "https://" + region + ".api.pvp.net";
		if (!id.isEmpty()) {

			switch (request) {

			case Constants.GET_SUMMONER_BY_NAME:
				try {
					url += "/api/lol/" + region + "/v1.4/summoner/by-name/"
							+ URLEncoder.encode(id, "UTF-8").replace("+", "%20") + "?";
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				break;

			case Constants.GET_RANKED_SUMMARY:
				url += "/api/lol/" + region + "/v1.3/stats/by-summoner/" + id + "/summary?season=SEASON2017&";
				break;

			case Constants.GET_CURRENT_GAME:
				url += "/observer-mode/rest/consumer/getSpectatorGameInfo/NA1/" + id + "?";
				break;

			case Constants.GET_STATIC_CHAMPION:
				url += "/api/lol/static-data/na/v1.2/champion/" + id + "?";
				break;
				
			case Constants.GET_RANKED_STATS:
				url += "/api/lol/na/v1.3/stats/by-summoner/" + id + "/ranked?season=SEASON2017&";
				break;
				
			case Constants.GET_LEAGUE_ENTRY:
				url += "/api/lol/na/v2.5/league/by-summoner/" + id + "/entry?";
				break;
				
			case Constants.GET_PLAYER_SUMMARY:
				url += "/api/lol/na/v1.3/stats/by-summoner/" + id +"/summary?season=SEASON2017&";

			}
		}

	}

}
