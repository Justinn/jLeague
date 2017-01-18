package net.jLeague.net;

import net.jLeague.Constants;

@SuppressWarnings("serial")
public class RiotException extends Exception {
	
	public RiotException(int error) {
		super(determineError(error));
	}

	private static String determineError(int error) {
		switch (error) {
		case Constants.BAD_REQUEST:
			return "Bad Request";
		case Constants.INTERNAL_SERVER_ERROR:
			return "Internal server error";
		case Constants.RATE_LIMIT_EXCEEDED:
			return "Rate limit exceeded";
		case Constants.SERVICE_UNAVAILABLE:
			return "Service unavailable";
		case Constants.STATS_DATA_NOT_FOUND:
			return "Stats data not found";
		case Constants.UNAUTHORIZED:
			return "Unauthorized. Bad API key";
		case Constants.NOT_FOUND:
			return "Not found.";
		}
		return null;
	}

}
