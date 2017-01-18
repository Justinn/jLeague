package net.jLeague.api.staticdata;

import java.util.List;

public class Champion {

	private List<String> allytips;
	private String blurb;
	private List<String> enemytips;
	private int id;
	private Image image;
	private String key;
	private String lore;
	private String name;
	private String partype;
	private Passive passive;
	private List<Recommended> recommended;
	private List<ChampionSpell> spells;
	private Stats stats;
	private List<String> tags;
	private String title;

	public List<String> getAllytips() {
		return allytips;
	}

	public String getBlurb() {
		return blurb;
	}

	public List<String> enemytips() {
		return enemytips;
	}

	public int getId() {
		return id;
	}

	public Image getImage() {
		return image;
	}

	public String getKey() {
		return key;
	}

	public String getLore() {
		return lore;
	}

	public String getName() {
		return name;
	}

	public String getPartype() {
		return partype;
	}

	public Passive getPassive() {
		return passive;
	}

	public List<Recommended> getRecommended() {
		return recommended;
	}

	public List<ChampionSpell> getSpells() {
		return spells;
	}

	public Stats getStats() {
		return stats;
	}

	public List<String> getTags() {
		return tags;
	}

	public String getTitle() {
		return title;
	}

}
