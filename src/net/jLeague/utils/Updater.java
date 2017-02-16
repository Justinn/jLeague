package net.jLeague.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import net.jLeague.api.staticdata.Realm;
import net.jLeague.net.RiotException;

/**
 * Updates the static information
 * 
 * @author Justin Mintzer
 *
 */
public class Updater {

	private Gson gson;
	
	public Updater() {
		gson = new Gson();
	}

	public void updateAll() {
		
	}

	private boolean checkNA() {
		try {
			JsonReader json = new JsonReader(new FileReader("utils/realmversions/na.json"));
			Realm current = gson.fromJson(json, Realm.class);

			JSONReader reader = new JSONReader("NA");
			Realm live = reader.getRealm("NA");

			System.out.printf("Checking base CDN url..");
			if (current.getCdn().equalsIgnoreCase(live.getCdn())) {
				System.out.printf("good\n");
				System.out.printf("Checking version of Dragon Magic's css file..");
				if (current.getCss().equalsIgnoreCase(live.getCss())) {
					System.out.printf("good\n");
					System.out.printf("Checking version of Dragon Magic...");
					if (current.getDd().equalsIgnoreCase(live.getDd())) {
						System.out.printf("good\n");
						System.out.printf("Checking n versions...");
						Map<String, String> n = current.getN();
						Map<String, String> n2 = live.getN();
						Set<String> values1 = new HashSet<>(n.values());
						Set<String> values2 = new HashSet<>(n2.values());
						if (values1.equals(values2)) {
							System.out.printf("good\n");
							return true;
						} else
							return false;
					} else
						return false;
				}
				return false;
			} else
				return false;
		} catch (FileNotFoundException | RiotException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean needsUpdating() {
		if (!checkNA())
			return true;
		return false;
	}

}