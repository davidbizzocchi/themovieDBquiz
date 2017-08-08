package movieDBquiz;

import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.TmdbTV;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.MovieList;
import info.movito.themoviedbapi.model.Video;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.tv.TvSeries;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbAccount;
import info.movito.themoviedbapi.TmdbAccount.MovieListResultsPage;
import info.movito.themoviedbapi.TmdbApi;

/**
 * Class that manages information obtained from the TMDB.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.1
 */
public class DbManager {

	/** API key to use the TMDB. **/
	private static TmdbApi key;

	/**Generate session token for a user session.*/
	private static SessionToken sessionToken;

	/**A variable used to source data for all movies.*/
	private TmdbMovies movies;

	/**Db variable to source data for tv questions.*/
	private TmdbTV tv;

	/** Username to log into TMDB and use the API.**/
	private static final String USER  = "DavidBizzocchi";

	/** Password to log into the TMDB and use the API.**/
	private static final String PWD = "CIS350Project";

	/** Password to log into the TMDB and use the API.**/
	private static final String APIKEYSTRING =
			"72094b969b9993f31aeea13bb041ee86";

	/** Number of results pages for popular movies.*/
	private static final int NUMPOPRESULTSPAGES = 987;

	/** Numbers of results in a MovieResultsPage obj.*/
	private static final int RESULTSPERPAGE = 20;

	/** Numbers of movie results pages to add to a list of MovieDbs.*/
	private static final int NUM_PAGES = 3;

	/** Number of items to return from a list of question options.*/
	private static final int NUMLISTITEMS = 4;

	/** Number of pages to search for title in getMovieFromTitle().*/
	private static final int NUMPAGESTOSEARCH = 50;

	/** Current year to get movie results pages information for.*/
	private static final int SEARCHYEAR = 2017;



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
		if (movies == null) {
			initMovies();
		}
		if (tv == null) {
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

	/** initializes movies using the defined Tmdbapi key.*/
	private void initMovies() {
		movies = key.getMovies();
	}

	/** initializes tv shows using the defined Tmdbapi key.*/
	private void initTvShows() {
		tv = key.getTvSeries();
	}

	/** Calls tmdb to get a list of 60 MovieDb objects.
	 * @return the generic type list of currently playing movies.
	 * */
	public List<MovieDb> getPlayingMovies() {
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

	/** Calls tmdb to get a list of 60 TvSeries objects.
	 * @return the generic type list of currently playing tv shows.
	 * */
	public List<TvSeries> getTvSeries() {
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

	/**
	 * Calls tmdb to get a list of 4 actors from different movies.
	 * @return an ArrayList of String names of actors.
	 */
	public ArrayList<String> getRandomActors() {
		ArrayList<String> actors = new ArrayList<String>();
		MovieDb movie;
		int i = 0;

		while (i < NUMLISTITEMS) {
			movie = getRandomMovie();

			if (movie.getCast() != null) {
				if (movie.getCast().get(0) == null) {
					continue;
				}
				actors.add(movie.getCast().get(0).getName());
				i++;
			}
		}
		return actors;
	}

	/**
	 * Gets a random movie with accessible data.
	 * @return MovieDb type object from popular movie selection.
	 */
	public MovieDb getRandomMovie() {
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

	/**
	 * Validates the login attempt with supplied parameters.
	 * @param username the tmdb login username
	 * @param password the tmdb login password
	 * @return True if login is successful
	 */
	public Boolean attemptLogin(final String username, final String password) {
		TmdbAuthentication tmdbAuth = key.getAuthentication();
		TokenSession tokenSession;
		try {
			tokenSession = tmdbAuth.getSessionLogin(username, password);
			sessionToken = new SessionToken(tokenSession.getSessionId());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * tmdb API call to get movie with budget information available.
	 * @param movie to receive information from.
	 * @return movie that has budget information available.
	 */
	public MovieDb getMovieWithInfo(final MovieDb movie) {
		return movies.getMovie(movie.getId(), "en", MovieMethod.values());
	}

	/**
	 * Accessor method for TmdbMovies class field.
	 * @return the class field, movies, used to source movie data.
	 */
	public TmdbMovies getMovies() {
		return movies;
	}

	/**
	 * Gets the current tmdb api key class member.
	 * @return a TmdbApi object which can be used to get an database info.
	 */
	public TmdbApi getKey() {
		return key;
	}

	/**
	 * Used to determine if tmdb api key has been constructed or not.
	 * @return true, if the key is not null.
	 */
	public Boolean isKey() {
		return key != null;
	}

	/**
	 * Gets the account object corresponding to current user.
	 * @return a TmdbAccount of current user or null.
	 */
	public TmdbAccount getDbAccount() {
		return key.getAccount();
	}

	/**
	 * Gets the current token for an active session.
	 * @return the current SessionToken or null.
	 */
	public SessionToken getSessionToken() {
		return sessionToken;
	}

	/**
	 * Gets the current user account.
	 * @return the Account object associated with the current user or null.
	 */
	public Account getAccount() {
		return key.getAccount().getAccount(sessionToken);
	}

	/**
	 * Gets the movie based on exact match with 'title'.
	 * @param title the name of movie
	 * @return a MovieDb object with a matching title or null.
	 */
	public MovieDb getMovieFromTitle(final String title) {
		TmdbSearch search = key.getSearch();
		MovieResultsPage results;

		for (int i = 0; i < NUMPAGESTOSEARCH; i++) {
			results = search.searchMovie(title, new Integer(SEARCHYEAR),
				"en", false, i);
			for (MovieDb movie: results.getResults()) {
				if (movie.getTitle().equals(title)) {
					return movie;
				}
			}
		}

		return null;
	}

	/**
	 * Gets a random movie object from currently playing titles.
	 * @return a MovieDb object with accessible data.
	 */
	public MovieDb getRandomPlayingMovie() {
		List<MovieDb> playingList = getPlayingMovies();
		int randIndex = Randomize.randomInt(0, playingList.size() - 1);
		MovieDb movie = getMovieWithInfo(playingList.get(randIndex));
		return movie;
	}

}
