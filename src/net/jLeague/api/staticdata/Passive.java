package net.jLeague.api.staticdata;

/**
 * Contains champion passive data
 * 
 * @author Justin Mintzer
 *
 */
public class Passive {
	
	private String description;
	private Image image;
	private String name;
	private String sanitizedDescription;
	
	public String getDescription() {
		return description;
	}
	public Image getImage() {
		return image;
	}
	public String getName() {
		return name;
	}
	public String getSanitizedDescription() {
		return sanitizedDescription;
	}

}
