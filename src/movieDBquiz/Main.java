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
//		MatchingQuestion q = new MatchingQuestion();
//		
//		QuizDemo window = new QuizDemo(q);
//		window.runQuiz();
//		window.getAppDemo().setVisible(true);
		

//		Quiz q = new Quiz();
//		
		DbManager manager = new DbManager();
//		manager.getRandomActors();
		System.out.print(manager.attemptLogin("aitkenj", "Pepper123"));
		
	}

}
