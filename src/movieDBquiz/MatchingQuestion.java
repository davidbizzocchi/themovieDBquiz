package movieDBquiz;

import java.util.ArrayList;
import java.util.List;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Class that creates matching questions for a movie trivia quiz using the TMDB
 * API.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public class MatchingQuestion {
	/** Constant for number of answer options.*/
	private static final int NUMOPTIONS = 4;

	/**
	 * Contains the description for the movie to be used in the current quiz
	 * question.
	 **/
	private String questionText;

	/**
	 * List of possible movie titles to be the answer to the current question.
	 **/
	private ArrayList<String> possibleAnswers;

	/** List that holds the movies to be used in movie description question. **/
	private List<MovieDb> movieList;

	/** List that holds the TvSeries to be used in tv matching question.**/
	private List<TvSeries> tvList;

	/** The index of the correct answer to the current question. **/
	private int answerIndex;

	/** The index of the movie used in the current question. **/
	private int movieIndex;

	/** The index in the tvList, used to start populating question. **/
	private int tvIndex;

	/** Provides database access wrapper methods for generating questions. **/
	private DbManager manager;

	/**
	 * Constructs a matching question object to be used in the quiz. It
	 * instantiates necessary variables and creates a new session with TMDB. It
	 * also randomizes movies to be used.
	 **/
	public MatchingQuestion() {
		movieList = new ArrayList<MovieDb>();
		tvList = new ArrayList<TvSeries>();
		possibleAnswers = new ArrayList<String>();

		manager = new DbManager();
		movieIndex = 0;
		tvIndex = 0;

		populateMovieList();
		populateTvList();
	}

	/**
	 * Retrieves the list of movies to be used on the quiz.
	 * @return movieList the list of movies to be used.
	 */
	public List<MovieDb> getMovieList() {
		return movieList;
	}

	/**
	 * Adds a movie title to the list of possible answers to the current
	 * question.
	 * @param title
	 *            the movie title to be added to the possible answers.
	 * @throws IllegalArgumentException
	 *             if the title given doesn't exist or is only whitespace.
	 */
	public void addAnswerTitle(final String title) {
		if (title == null || title.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		possibleAnswers.add(title);
	}

	/**
	 * Gets the description of the movie to be used in the question.
	 * @return String questionText the description from TMDB API of the movie.
	 */
	public String getQuestionText() {
		return questionText;
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
	 * @param description The description of the movie from TMDB API.
	 * @throws IllegalArgumentException
	 *             if the string given is null or contains only whitespace.
	 */
	public void setQuestionText(final String description) {
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.questionText = description;
	}

	/**
	 * Sets the index of the correct answer in possibleAnswers.
	 * @param ansIndex the correct answer for the question.
	 * @throws IllegalArgumentException
	 *             if the index is invalid.
	 */
	public void setAnswerIndex(final int ansIndex) {
		if (ansIndex < 1 || ansIndex > NUMOPTIONS) {
			throw new IllegalArgumentException();
		}
		this.answerIndex = ansIndex;
	}

	/**
	 * Generates a randomized question with a corresponding randomized set of
	 * answers one of which is the correct answer.
	 * @return MatchingQuestion the question that was generated.
	 */
	public MatchingQuestion generateQuestion() {
		if (!possibleAnswers.isEmpty()) {
			possibleAnswers.clear();
		}

		if (movieIndex + NUMOPTIONS >= movieList.size()) {
			randomizeMovieList();
			movieIndex = 0;
		}

		for (int i = 0; i < NUMOPTIONS; i++) {
			addAnswerTitle(movieList.get(movieIndex + i).getTitle());
		}

		setAnswerIndex(Randomize.randomInt(1, NUMOPTIONS));
		setQuestionText(movieList.get(getAnswerIndex() - 1 + movieIndex)
				.getOverview());
		movieIndex += NUMOPTIONS;

		return this;
	}

	/**
	 * Generate a random tv series-matching question. Takes tv series from
	 * randomized pre-set list Tv list is reshuffled if the questions have run
	 * through entire list.
	 * @return MatchingQuestion the question generated
	 */
	public MatchingQuestion generateTvQuestion() {
		if (!possibleAnswers.isEmpty()) {
			possibleAnswers.clear();
		}

		if (tvIndex + NUMOPTIONS >= tvList.size()) {
			randomizeTvList();
			tvIndex = 0;
		}

		for (int i = 0; i < NUMOPTIONS; i++) {
			addAnswerTitle(tvList.get(tvIndex + i).getName());
		}

		setAnswerIndex(Randomize.randomInt(1, NUMOPTIONS));
		setQuestionText(tvList.get(getAnswerIndex() - 1 + tvIndex)
				.getOverview());
		tvIndex += NUMOPTIONS;

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
		randomizeMovieList();
	}

	/**
	 * Generates the list of tvSeries to be used for questions.
	 */
	private void populateTvList() {
		tvList = manager.getTvSeries();
		randomizeTvList();
	}

	/**
	 * Randomizes the movie list generated so that questions and answers may be
	 * randomized.
	 */
	private void randomizeMovieList() {
		movieList = Randomize.shuffleList(movieList);
	}

	/**
	 * Randomizes the tv list generated so that questions are not predictable
	 * for each instance.
	 */
	private void randomizeTvList() {
		tvList = Randomize.shuffleList(tvList);
	}

	/**
	 * Sets the list of possible answers to NUMOPTIONS random actors.
	 */
	private void setAnswersActors() {
		possibleAnswers = manager.getRandomActors();
	}

	/**
	 * Generates a matching question based on actors and movie roles.
	 * @return an instance of question with populated data.
	 */
	public final MatchingQuestion generateCharacterQuestion() {
		setAnswersActors();
		int correctAnswer = Randomize.randomInt(1, NUMOPTIONS);
		MovieDb movie = manager.getRandomMovie();

		while (movie.getCast().size() == 0) {
			movie = manager.getRandomMovie();
		}
		PersonCast person = movie.getCast().get(0);
		String question = getCharacterQuestion(person, movie);
		setQuestionText(question);
		possibleAnswers.set(correctAnswer - 1, person.getName());
		setAnswerIndex(correctAnswer);

		return this;
	}

	/**
	 * Gets the question text for a character style question.
	 * @param actor the tmdb object for a cast member.
	 * @param movie the tmdb object for related movie.
	 * @return the full question string.
	 */
	private String getCharacterQuestion(final PersonCast actor,
			final MovieDb movie) {
		String question = "The actor who played the character "
				+ actor.getCharacter() + " in the "
				+ movie.getReleaseDate() + " movie \'"
				+ movie.getTitle() + "\' was:";
		return question;
	}

}
