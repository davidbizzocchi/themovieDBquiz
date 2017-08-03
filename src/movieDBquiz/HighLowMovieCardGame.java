package movieDBquiz;

import java.util.List;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.MovieDb;

/**
 * Class to manage High Low Game type cards
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public class HighLowMovieCardGame {

	/** tmdb manager instance */
	private DbManager manager;
	
	/** list of movies to create cards from */
	private List<MovieDb> movieList;
	
	/** Index of card location in list */
	private int movieListIndex;
	
	/** Number of questions answered correctly */
	private int score;
	
	/** Instance of card shown to user */
	private MoviePlayingCard faceUpCard;
	
	/** Instance of card user is to guess budget */
	private MoviePlayingCard faceDownCard;
	
	/**
	 * Constructor, creates first draw of two comparable cards
	 */
	public HighLowMovieCardGame(){
		movieListIndex = 0;
		score = 0;
		manager = new DbManager();
		populateAndShuffleMovies();
		faceUpCard = new MoviePlayingCard(getNextMovie());
		faceDownCard = new MoviePlayingCard(getNextMovie());
	}
	
	/**
	 * Creates two new cards for user to play new round
	 */
	public void drawCards(){
		if(movieListIndex + 2 >= movieList.size()){
			populateAndShuffleMovies();
			movieListIndex = 0;
		}
		do{
			popCards();
		}while(!verifyCardBudgets());
	}
	
	/**
	 * User answer verification
	 * @param isHigher true if user guessed that face down card budget will be lower than face up card
	 * @return if user answer is correct
	 */
	public boolean answer(boolean isHigher){
		if(isHigher && (faceDownCard.getBudget() > faceUpCard.getBudget())){
			score++;
			return true;
		}
		else if(!isHigher && (faceDownCard.getBudget() < faceUpCard.getBudget())){
			score++;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns next movie in list, uses method to return MovieDb variable with information
	 * @return next movie in list
	 */
	public MovieDb getNextMovie(){
		MovieDb movie = getMovieWithInfo(movieList.get(movieListIndex++));
		
		while(movie.getBudget() == 0){
			movie = getMovieWithInfo(movieList.get(movieListIndex++));
		}
		
		return movie;
	}
	
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @return the faceUpCard
	 */
	public MoviePlayingCard getFaceUpCard() {
		return faceUpCard;
	}

	/**
	 * @return the faceDownCard
	 */
	public MoviePlayingCard getFaceDownCard() {
		return faceDownCard;
	}
	
	/**
	 * tmdb API call to get movie with budget information available.
	 * @param movie to receive information from.
	 * @return movie that has budget information available.
	 */
	private MovieDb getMovieWithInfo(MovieDb movie){
		return manager.getMovies().getMovie(movie.getId(), "en", MovieMethod.values());
	}
	
	/**
	 * Verifies that the two cards do not have the same budget
	 * @return if cards have the same budget
	 */
	private boolean verifyCardBudgets(){
		if(faceUpCard.getBudget() == faceDownCard.getBudget()){
			return false;
		}
		return true;
	}
	
	/**
	 * Generates new face up and face down cards
	 */
	private void popCards(){
		faceUpCard.generateNewCard(getNextMovie());
		faceDownCard.generateNewCard(getNextMovie());
	}
	
	/**
	 * Generates and randomizes new movie list, populates if needed
	 */
	private void populateAndShuffleMovies(){
		if(movieList.size() == 0){
			movieList = manager.getPlayingMovies();
		}
		movieList = Randomize.shuffleList(movieList);
	}
}
