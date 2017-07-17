package movieDBquiz;

import java.security.SecureRandom;
import java.util.Random;
import java.util.List;
import java.util.Collections;
import info.movito.themoviedbapi.model.MovieDb;

/**
 * This class is used to randomize questions and answers for the movie quiz.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public final class Randomize {
	
	/** Random number generator for the randomization process. **/
	private static Random rand = new SecureRandom();
	
	/**
	 * Private constructor that isn't used due to ultility class.
	 */
	private Randomize() { }
	
	/**
	 * Retrieves a seed based on the current time and retrieves a random number
	 * between the indexes. 
	 * @param min the minimum value the number can be (inclusive)
	 * @param max the maximum value the number can be (inclusive) 
	 * @return static int the randomized number that was generated.
	 **/
	public static int randomInt(final int min, final int max) {
		reSeedTime();
		return min + rand.nextInt(max);
	}
	
	/**
	 * Generates a random decimal number seeded from the current time
	 *  that falls between the given parameters (inclusive).
	 * @param min the minimum value the number can be (inclusive) 
	 * @param max the maximum value the number can be (inclusive)
	 * @return static float the random decimal number that was generated
	 */
	public static float randomFloat(final float min, final float max) {
		reSeedTime();
		return rand.nextFloat() * (max - min) + min;
	}
	
	/**
	 * Randomizes the order of a given list.
	 * @param list the list to have its order randomized.
	 * @return static List<MovieDb> the list with the contents in a random order
	 */
	public static List<MovieDb> shuffleList(final List<MovieDb> list) {
		Collections.shuffle(list, rand);
		return list;
	}
	
	/**
	 * Sets the seed to the current time from the operating system.
	 */
	private static void reSeedTime() {
		rand.setSeed(System.currentTimeMillis());
	}
	
}
