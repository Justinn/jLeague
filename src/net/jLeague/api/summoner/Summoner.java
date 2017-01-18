package net.jLeague.api.summoner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Summoner {

	private long id;
	private String name;
	private int profileIconId;
	private long revisionDate;
	private long summonerLevel;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getProfileIconId() {
		return profileIconId;
	}

	public long getRevisionDate() {
		return revisionDate;
	}

	public long getSummonerLevel() {
		return summonerLevel;
	}

	public String toString() {
		String date = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(new Date(revisionDate));
		String str = new String(
				"\nID: " + id + "\nName: " + name + "\nprofileIconId: " + profileIconId + "\nrevisionDate: " + date + "\nSummoner Level: " + summonerLevel);
		return str;
	}

}
