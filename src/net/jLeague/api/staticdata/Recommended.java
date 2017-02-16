package net.jLeague.api.staticdata;

import java.util.List;

/**
 * Contains champion recommended data
 * 
 * @author Justin Mintzer
 *
 */
public class Recommended {
	
	private List<Block> blocks;
	private String champion;
	private String map;
	private String mode;
	private boolean priority;
	private String title;
	private String type;
	
	public List<Block> getBlocks() {
		return blocks;
	}
	public String getChampion() {
		return champion;
	}
	public String getMap() {
		return map;
	}
	public String getMode() {
		return mode;
	}
	public boolean isPriority() {
		return priority;
	}
	public String getTitle() {
		return title;
	}
	public String getType() {
		return type;
	}

}
