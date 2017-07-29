package movieDBquiz;

import info.movito.themoviedbapi.TmdbAccount;
import info.movito.themoviedbapi.TmdbAccount.MediaType;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.core.AccountID;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.ResponseStatus;

public class MovieWatchList {

	/** Variable to access account methods */
	private DbManager manager;
	
	/** Users tmdb account */
	private TmdbAccount tmdbAccount;
	
	/** Session token generated account variable */
	private Account account;
	
	/** Identification number associated with session token account */
	private AccountID accountID;

	public MovieWatchList(){
		manager = new DbManager();
		
		if (tmdbAccount == null && manager.getKey() != null){
			tmdbAccount = manager.getKey().getAccount();
		}
		if (account == null && tmdbAccount != null){
			account = tmdbAccount.getAccount(manager.getSessionToken());
		}
		if(accountID == null && account != null){
			accountID = new AccountID(account.getId());
		}
	}
	
	public MovieResultsPage getUserMovieList(){
		return tmdbAccount.getFavoriteMovies(manager.getSessionToken(), accountID);
	}
	
	public ResponseStatus addToList(MovieDb movie){
		return tmdbAccount.addToWatchList(manager.getSessionToken(), accountID, movie.getId(), MediaType.MOVIE);
	}
	
	public ResponseStatus removeFromList(MovieDb movie){
		return tmdbAccount.removeFromWatchList(manager.getSessionToken(), accountID, movie.getId(), MediaType.MOVIE);
	}
}
