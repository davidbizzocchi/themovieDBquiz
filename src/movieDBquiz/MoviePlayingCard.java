package movieDBquiz;

import info.movito.themoviedbapi.model.MovieDb;

/**
 * Class to create and manage card object for High Low card game
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public class MoviePlayingCard {
	
	/** Budget for associated movie */
	private long budget;
	
	/** Path to poster image of associated movie */
	private String posterPath;
	
	/** Movie associated with card values and poster */
	private MovieDb associatedMovie;
	
	/**
	 * Constructor for playing card in High Low game.
	 * @param movie to associate with card values.
	 */
	public MoviePlayingCard(MovieDb movie){
		generateNewCard(movie);
	}
	
	/**
	 * Sets all card values to parameter movie.
	 * @param movie to set card values to.
	 */
	public void generateNewCard(MovieDb movie){
		associatedMovie = movie;
		posterPath = movie.getPosterPath();
		budget = movie.getBudget();
	}
	
	/**
	 * Gets budget for associated movie card.
	 * @return budget of movie.
	 */
	public long getBudget() {
		return budget;
	}

	/**
	 * Gets URL path to poster of movie associated with card.
	 * @return URL of poster path.
	 */
	public String getPosterPath() {
		return posterPath;
	}

	/**
	 * Gets movie associated with card.
	 * @return associated movie with card.
	 */
	public MovieDb getAssociatedMovie() {
		return associatedMovie;
	}
}
