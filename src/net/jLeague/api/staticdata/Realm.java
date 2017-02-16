package net.jLeague.api.staticdata;

import java.util.Map;

/**
 * Object contains realm data
 * 
 * @author Justin Mintzer
 *
 */
public class Realm {

	private String cdn;
	private String css;
	private String dd;
	private String l;
	private String lg;
	private Map<String, String> n;
	private int profileIconMax;
	private String store;
	private String v;

	public String getCdn() {
		return cdn;
	}

	public String getCss() {
		return css;
	}

	public String getDd() {
		return dd;
	}

	public String getL() {
		return l;
	}

	public String getLg() {
		return lg;
	}

	public Map<String, String> getN() {
		return n;
	}

	public int getProfileIconMax() {
		return profileIconMax;
	}

	public String getStore() {
		return store;
	}

	public String getV() {
		return v;
	}

}
