package movieDBquiz;

import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.Video;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.tv.TvSeries;

import java.util.ArrayList;
import java.util.List;
import info.movito.themoviedbapi.TmdbApi;

/**
 * Class that manages information obtained from the TMDB.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public class DbManager {

	/** API key to use the TMDB. **/
	private static TmdbApi key;

	/**Generate session token for a user session*/
	private static SessionToken sessionToken;
	
	/**A variable used to source data for all movies */
	private TmdbMovies movies;
	
	/**Db variable to source data for tv questions */
	private TmdbTV tv;
	
	/** Username to log into TMDB and use the API. **/
	private static final String USER  = "DavidBizzocchi";

	/** Password to log into the TMDB and use the API. **/
	private static final String PWD = "CIS350Project";

	/** Password to log into the TMDB and use the API. **/
	private static final String APIKEYSTRING =
			"72094b969b9993f31aeea13bb041ee86";

	/** Number of results pages for popular movies */
	private static final int NUMPOPRESULTSPAGES = 987;
	
	private static final int RESULTSPERPAGE = 20;
	
	private static final int NUM_PAGES = 3;

	/**
	 * Creates the manager for the db and initializes the session token
	 * to connect to the API.
	 */
	public DbManager() {
		if (key == null) {
			initApiKey();
		}
		if (sessionToken == null) {
			initSessionToken();
		}
		if(movies == null){
			initMovies();
		}
		if(tv == null){
			initTvShows();
		}
	}

	/**
	 * Initializes the API key and log in session to connect to the API.
	 */
	private void initSessionToken() {
		// Generate session id using API calls
		TmdbAuthentication tmdbAuth = key.getAuthentication();
		TokenSession tokenSession = tmdbAuth.getSessionLogin(USER, PWD);
		sessionToken = new SessionToken(tokenSession.getSessionId());
	}

	/**
	 * Initializes the API key generated from the account information.
	 */
	private static void initApiKey() {
		key = new TmdbApi(APIKEYSTRING);
	}
	
	private void initMovies() {
		movies = key.getMovies();
	}
	
	private void initTvShows(){
		tv = key.getTvSeries();
	}
	
	public List<MovieDb> getPlayingMovies(){
		List<MovieDb> currentMovieList = new ArrayList<MovieDb>();
		MovieResultsPage results;
		int i = 0;
	
		do {
			results = movies.getNowPlayingMovies("en", i++);
			
			for (MovieDb movie : results.getResults()) {
				currentMovieList.add(movie);
			}
		} while (i <= NUM_PAGES);
		
		return currentMovieList;
	}
	
	public List<TvSeries> getTvSeries(){
		List<TvSeries> currentTvList = new ArrayList<TvSeries>();
		TvResultsPage results;
		int i = 0;
	
		do {
			results = tv.getOnTheAir("en", i++);
			
			for (TvSeries series : results.getResults()) {
				currentTvList.add(series);
			}
		} while (i <= NUM_PAGES);
		
		return currentTvList;
	}
	
	public ArrayList<String> getRandomActors(){
		ArrayList<String> actors = new ArrayList<String>();
		MovieDb movie;
		int i = 0;
		
		while(i < 4){
			movie = getRandomMovie();
			
			if(movie.getCast() != null){
				if( movie.getCast().get(0) == null){
					continue;
				}
				actors.add(movie.getCast().get(0).getName());
				i++;
			}
		}
		return actors;
	}
	
	public MovieDb getRandomMovie(){
		MovieDb movie;
		MovieResultsPage results;
		int pageNo, movieId, resultNo;
		
		pageNo = Randomize.randomInt(0, NUMPOPRESULTSPAGES);
		results = movies.getPopularMovies("en", pageNo);
		resultNo = Randomize.randomInt(0, RESULTSPERPAGE - 1);
		movie = results.getResults().get(resultNo);
		movieId = movie.getId();
		return movies.getMovie(movieId, "en", MovieMethod.credits);
	}
	
	public Boolean attemptLogin(String username, String password){
		TmdbAuthentication tmdbAuth = key.getAuthentication();
		TokenSession tokenSession;
		try{
			tokenSession = tmdbAuth.getSessionLogin(username, password);
			sessionToken = new SessionToken(tokenSession.getSessionId());
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * tmdb API call to get movie with budget information available.
	 * @param movie to receive information from.
	 * @return movie that has budget information available.
	 */
	public MovieDb getMovieWithInfo(MovieDb movie){
		return movies.getMovie(movie.getId(), "en", MovieMethod.values());
	}
	
	public TmdbMovies getMovies(){
		return movies;
	}

	public TmdbApi getKey(){
		return key;
	}
	
	public SessionToken getSessionToken(){
		return sessionToken;
	}
	
	public Account getAccount(){
		return key.getAccount().getAccount(sessionToken);
	}

}
