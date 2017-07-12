package moviedbquiz;

import java.util.ArrayList;
/**
 * Class that creates matching questsions for a movie trivia quiz using the TMDB API.
 * 
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1 Summer 2017
 */
public class MatchingQuestion {
	
	/** Contains the description for the movie 
	 * to be used in the current quiz question. **/
	private String movieDesc;
	
	/** List of possible movie titles to be the answer 
	 * to the current question. **/
	private ArrayList<String> possibleAnswers;
	
	/** The index of the correct answer to the current question. **/
	private int answerIndex;
	
	/** (TEMP?) true if the correct answer is selected. **/
	private boolean correctAnswer;
	
	
	/** 
	 * Constructs a matching question object to be used in the quiz. 
	 * It instantiates the array containing all possible answers to
	 *  the question.
	 *  **/
	public MatchingQuestion() {
		possibleAnswers = new ArrayList<String>();
	}
	
	/**
	 * Adds a movie title to the list of possible answers 
	 * to the current question.
	 * @param title the movie title to be added to the possible answers.
	 */
	public void addAnswerTitle(final String title) {
		possibleAnswers.add(title);
	}
	
	/**
	 * Gets the description of the movie to be used in the question.
	 * @return String movieDesc the description from TMDB API of the movie.
	 */
	public String getMovieDesc() {
		return movieDesc;
	}
	
	public ArrayList<String> getPossibleAnswers() {
		return possibleAnswers;
	}
	
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
	
	public void setAnswerIndex(int answerIndex) {
		this.answerIndex = answerIndex;
	}
	
	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	} 
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(String title : possibleAnswers){
			sb.append(title + '\n');
		}
		return sb.toString();
	}
	
}
