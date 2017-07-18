package movieDBquiz;

import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.TmdbApi;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Class that manages information obtained from the TMDB.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public class DbManager {
	
	/** API key to use the TMDB. **/
	private static TmdbApi key;
	
	/** Username to log into TMDB and use the API. **/
	private static final String USER  = "DavidBizzocchi";
	
	/** Password to log into the TMDB and use the API. **/
	private static final String PWD = "CIS350Project";
	
	/**
	 * Creates the manager for teh db and initializes the session token
	 * to connect to the API.
	 */
	public DbManager() {
		initSessionToken();
	}
	
	/**
	 * Initializes the API key and log in session to connect to the API.
	 */
	private void initSessionToken() {
		initApiKey();
		// Generate session id using API calls
		TmdbAuthentication tmdbAuth = key.getAuthentication();
		TokenSession tokenSession = tmdbAuth.getSessionLogin(USER, PWD);
		new SessionToken(tokenSession.getSessionId());
	}
	
	/**
	 * Initializes the API key generated from the account information.
	 */
	private void initApiKey() {
		key = new TmdbApi("72094b969b9993f31aeea13bb041ee86");
	}

}
