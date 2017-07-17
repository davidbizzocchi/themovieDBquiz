package movieDBquiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Class that creates matching questsions for a movie trivia quiz 
 * using the TMDB API.
 * 
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */

	
	/** Contains the description for the movie to be used in the current quiz question. **/
public class MatchingQuestion {
	private String movieDesc;
	
	/** List of possible movie titles to be the answer 
	 * to the current question. **/
	private ArrayList<String> possibleAnswers;
	
	/** List that holds the movies to be used in the quiz. **/
	private List<MovieDb> movieList;
	
	/** The index of the correct answer to the current question. **/
	private int answerIndex;
	
	/** The index of the movie used in the current question. **/
	private int movieIndex;
	
	/** (TEMP?) true if the correct answer is selected. **/
	private boolean correctAnswer;
	
	/** Instantiates TMDB API for use in quiz. **/
	private TmdbApi tmdbApi;
	
	/** Instantiates TMDB movies to be used in quiz. **/
	private TmdbMovies tmdbMovies;
	
	/** Number of pages to pull from tmdb API to generate questions */
	private final int NUM_PAGES  = 3;
	
	/** 
	 * Constructs a matching question object to be used in the quiz. 
	 * It instantiates necessary variables and creates a new session
	 * with TMDB. It also randomizes movies to be used.
	 *  **/
	public MatchingQuestion() {
		movieList = new ArrayList<MovieDb>();
		possibleAnswers = new ArrayList<String>();
		tmdbApi = new TmdbApi("72094b969b9993f31aeea13bb041ee86");
		movieIndex = 0;
		tmdbMovies = tmdbApi.getMovies();
		populateMovieList();
		randomizeMovieList();
	}
	
	/**
	 * Retrieves the list of movies to be used on the quiz.
	 * @return movieList the list of movies to be used.
	 */
	public List<MovieDb> getMovieList() {
		return movieList;
	}
	
	/**
	 * Adds a movie title to the list of possible answers 
	 * to the current question.
	 * @param title the movie title to be added to the possible answers.
	 * @throws IllegalArgumentException if the title given doesn't exist
	 * 	          or is only whitespace.
	 */
	public void addAnswerTitle(final String title) {
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		possibleAnswers.add(title);
	}
	
	/**
	 * Gets the description of the movie to be used in the question.
	 * @return String movieDesc the description from TMDB API of the movie.
	 */
	public String getMovieDesc() {
		return movieDesc;
	}
	
	/**
	 * Returns the array of possible answers for the current question.
	 * @return ArrayList<String> of possible answers to the current question
	 */
	public ArrayList<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	
	/**
	 * Retrieves the index of the answer selected by the user.
	 * @return Integer of the answer in the ArrayList possibleAnswers
	 */
	public int getAnswerIndex() {
		return answerIndex;
	}
	
	public boolean isCorrectAnswer() {
		return correctAnswer;
	}
	
	/**
	 * Sets the description of the movie for the question.
	 * @param movieDesc The description of the movie from TMDB API.
	 * @throws IllegalArgumentException if the string given is null
	 * 				or contains only whitespace.
	 */
	public void setMovieDesc(final String movieDesc) {
		if (movieDesc == null || movieDesc.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.movieDesc = movieDesc;
	}
	
	/**
	 * Sets the index of the correct answer in possibleAnswers.
	 * @param answerIndex the correct answer for the question.
	 * @throws IllegalArgumentException if the index is invalid.
	 */
	public void setAnswerIndex(final int answerIndex) {
		if(answerIndex < 0 || answerIndex >= 4) {
			throw new IllegalArgumentException();
		}
		this.answerIndex = answerIndex;
	}
	
	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	} 
	
	public MatchingQuestion generateQuestion(){
		if(!possibleAnswers.isEmpty()){
			possibleAnswers.clear();
		}
		
		if(movieIndex+4 >= movieList.size()){
			randomizeMovieList();
			movieIndex = 0;
		}
			
		for(int i = 0; i < 4; i++){
			addAnswerTitle(movieList.get(movieIndex++).getTitle());
		}
		
		setAnswerIndex(Randomize.randomInt(1, 4));
		setMovieDesc(movieList.get(getAnswerIndex()-1).getOverview());
		
		return this;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(String title : possibleAnswers){
			sb.append(title + '\n');
		}
		return sb.toString();
	}
	
	private void populateMovieList(){
		int i = 0;
		int previousSize = 0;
		MovieResultsPage results;
		
		do{
			previousSize = movieList.size();
			
			results = tmdbMovies.getNowPlayingMovies("en", i++);
			
			for( MovieDb movie : results.getResults()){
				movieList.add(movie);
			}
			
		}while(i <= NUM_PAGES);
	}
	
	private void randomizeMovieList(){
		movieList = Randomize.shuffleList(movieList);
	}
}

