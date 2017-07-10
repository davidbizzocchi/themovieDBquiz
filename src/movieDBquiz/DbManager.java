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

public class DbManager {
	private static TmdbApi key;
	private static SessionToken sessionToken;
	private static final String user  = "DavidBizzocchi";
	private static final String pwd = "CIS350Project";
	
	
	public DbManager() {
		initSessionToken();
	}
	
	private static void initSessionToken() {
		initApiKey();
		// Generate session id using API calls
		TmdbAuthentication tmdbAuth = key.getAuthentication();
		TokenSession tokenSession = tmdbAuth.getSessionLogin(user, pwd);
		sessionToken = new SessionToken(tokenSession.getSessionId());
	}
	
	private static void initApiKey() {
		key = new TmdbApi("72094b969b9993f31aeea13bb041ee86");
	}

}
