package movieDBquiz;

import java.util.ArrayList;
import java.util.List;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.PersonCast;

/**
 * Class that creates matching questions for a movie trivia quiz 
 * using the TMDB API.
 * 
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public class MatchingQuestion {
	
	
	/** Contains the description for the movie 
	 * to be used in the current quiz question. **/
	private String movieDesc;
	
	/** List of possible movie titles to be the answer 
	 * to the current question. **/
	private ArrayList<String> possibleAnswers;
	
	/** List that holds the movies to be used in movie description question. **/
	private List<MovieDb> movieList;
	
	/** The index of the correct answer to the current question. **/
	private int answerIndex;
	
	/** The index of the movie used in the current question. **/
	private int movieIndex;
	
	private DbManager manager;
	
	/** The index to select the next movie. **/
	private int movieSelectorIndex = 0;
	
	/** 
	 * Constructs a matching question object to be used in the quiz. 
	 * It instantiates necessary variables and creates a new session
	 * with TMDB. It also randomizes movies to be used.
	 *  **/
	public MatchingQuestion() {
		movieList = new ArrayList<MovieDb>();
		possibleAnswers = new ArrayList<String>();

		manager = new DbManager();
		movieIndex = 0;

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
		if (answerIndex < 1 || answerIndex > 4) {
			throw new IllegalArgumentException();
		}
		this.answerIndex = answerIndex;
	}
	
	/**
	 * Generates a randomized question with a corresponding randomized 
	 * set of answers one of which is the correct answer.
	 * @return MatchingQuestion the question that was generated.
	 */
	public MatchingQuestion generateQuestion() {
		if (!possibleAnswers.isEmpty()) {
			possibleAnswers.clear();
		}
		
		if (movieIndex + 4 >= movieList.size()) {
			randomizeMovieList();
			movieIndex = 0;
		}
		
		for (int i = 0; i < 4; i++) {
			addAnswerTitle(movieList.get(movieIndex++).getTitle());
		}
		
		setAnswerIndex(Randomize.randomInt(1, 4));
		setMovieDesc(movieList.get(getAnswerIndex() - 1 
				+ movieSelectorIndex).getOverview());
		movieSelectorIndex += 4;
		
		return this;
	}
	
	@Override
	/**
	 * Creates a String that displays the possible answers to the question.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String title : possibleAnswers) {
			sb.append(title + '\n');
		}
		return sb.toString();
	}
	
	/**
	 * Generates the list of movies to be used for questions.
	 */
	private void populateMovieList() {
		movieList = manager.getPlayingMovies();
	}

	/**
	 * Randomizes the movie list generated so that questions 
	 * and answers may be randomized.
	 */
	private void randomizeMovieList() {
		movieList = Randomize.shuffleList(movieList);
	}
	
	private void setAnswersActors() {
		possibleAnswers = manager.getRandomActors();
	}
	
	public MatchingQuestion generateCharacterQuestion(){
		setAnswersActors();
		int correctAnswer = Randomize.randomInt(1, 4);
		MovieDb movie = manager.getRandomMovie();

		while(movie.getCast().size() == 0 ) {
			movie = manager.getRandomMovie();
		}
		PersonCast person = movie.getCast().get(0);
		String question = getCharacterQuestion(person, movie);
		setMovieDesc(question);
		possibleAnswers.set(correctAnswer - 1, person.getName());
		setAnswerIndex(correctAnswer);
		
		return this;
	}
	
	private String getCharacterQuestion(PersonCast actor, MovieDb movie){
		String question = "The actor who played the character " +
				actor.getCharacter() + " in the " + movie.getReleaseDate() +
				" movie \'" + movie.getTitle() + "\' was:";
		return question;
	}
	
	
}

