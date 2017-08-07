package movieDBquiz;

/**
 * Driver utility class for running the movie quiz.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.1
 */
public final class Main {

	/** private constructor not used because it is a utility class. **/
	private Main() {
	}

	/**
	 * main method that runs the movie quiz.
	 * @param args command line inputs
	 */
	public static void main(final String[] args) {
		// Initializes session token and API key
		// DbManager manager = new DbManager();

		// CARD GAME EXAMPLE
		HighLowMovieCardGame game = new HighLowMovieCardGame();
		System.out.println(
				"Up Card: " + game.getFaceUpCard().getAssociatedMovie()
						+ " -> " + game.getFaceUpCard().getBudget());
		System.out.println("Down Card: "
				+ game.getFaceDownCard().getAssociatedMovie() + " -> "
				+ game.getFaceDownCard().getBudget());

		System.out.println("Guess is Higher: " + game.answer(true));

		System.out.println("===== Deal new cards =====");
		game.drawCards();

		System.out.println(
				"Up Card: " + game.getFaceUpCard().getAssociatedMovie()
						+ " -> " + game.getFaceUpCard().getBudget());
		System.out.println("Down Card: "
				+ game.getFaceDownCard().getAssociatedMovie() + " -> "
				+ game.getFaceDownCard().getBudget());

		System.out.println("Guess is Lower: " + game.answer(false));
	}

}
