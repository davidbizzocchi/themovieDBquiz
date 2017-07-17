package movieDBquiz;

/**
 * Driver utility class for running the movie quiz.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public final class Main {
	
	/** private constructor not used because it is a utility class. **/
	private Main() { }
	
	/**
	 * main method that runs the movie quiz.
	 * @param args command line inputs
	 */
	public static void main(final String[] args) {
		//Initializes session token and API key
//		DbManager manager = new DbManager();
//		
//		
//		// generates quiz question with 4 possible answers
//		MatchingQuestion q = new MatchingQuestion();
//		System.out.print(q.toString());
		
		
		
		MatchingQuestion q = new MatchingQuestion();
		q = q.generateQuestion();
		
		QuizDemo window = new QuizDemo();
		window.setAnswers(q.getPossibleAnswers());
		window.setDescription(q.getMovieDesc());
		int answer = q.getAnswerIndex() + 64;
		System.out.print((char) answer);
		window.setAnswer((char) answer);
		window.AppDemo.setVisible(true);
		
		
	}

}
