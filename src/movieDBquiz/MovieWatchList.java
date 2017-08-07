package movieDBquiz;

import java.util.List;
import java.util.ArrayList;
import info.movito.themoviedbapi.TmdbAccount;
import info.movito.themoviedbapi.TmdbAccount.MediaType;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.core.AccountID;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.ResponseStatus;

/**
 * Wrapper for tmdb watch list.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public class MovieWatchList {

	/** Variable to access account method.*/
	private DbManager manager;

	/** Users tmdb account.*/
	private TmdbAccount tmdbAccount;

	/** Session token generated account variable.*/
	private Account account;

	/** Identification number associated with session token account.*/
	private AccountID accountID;

	/**
	 * Constructor for MovieWatchList Class.
	 */
	public MovieWatchList() {
		manager = new DbManager();

		if (tmdbAccount == null && manager.isKey()) {
			tmdbAccount = manager.getDbAccount();
		}
		if (account == null && tmdbAccount != null) {
			account = tmdbAccount.getAccount(manager.getSessionToken());
		}
		if (accountID == null && account != null) {
			accountID = new AccountID(account.getId());
		}
	}

	/**
	 * Returns current users movie watch list.
	 * @return users current movie watch list
	 */
	public List<MovieDb> getUserMovieList() {
		List<MovieDb> list = new ArrayList<MovieDb>();
		MovieResultsPage results;
		int i = 0, previousSize;

		do {
			previousSize = list.size();

			results = tmdbAccount.getWatchListMovies(
					manager.getSessionToken(), accountID, i++);

			for (MovieDb movie : results.getResults()) {
				list.add(movie);
			}
		} while (previousSize != list.size());

		return list;
	}

	/**
	 * Adds movie to users tmdb watch list.
	 * @param movie
	 *            to add to watch list
	 * @return status code from addition to watch list
	 */
	public ResponseStatus addToList(final MovieDb movie) {
		return tmdbAccount.addToWatchList(manager.getSessionToken(),
				accountID, movie.getId(), MediaType.MOVIE);
	}

	/**
	 * Removes specified movie from users tmdb watch list.
	 * @param movie to remove from watch list
	 * @return status code from removal from watch list
	 */
	public ResponseStatus removeFromList(final MovieDb movie) {
		return tmdbAccount.removeFromWatchList(
				manager.getSessionToken(), accountID, movie.getId(),
				MediaType.MOVIE);
	}
}
