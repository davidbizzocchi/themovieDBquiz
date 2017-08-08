package movieDBquiz;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import info.movito.themoviedbapi.model.MovieDb;

public class TestDbManager {

	/** Instance of DbManager class for test to use */
	private static DbManager manager;
	
	/**
	 * Constructs DbManager object for class to use.
	 */
	@BeforeClass
	public static void Construtor() {
		manager = new DbManager();
	}
	
	/**
	 * Tests that constructor values are set properly.
	 */
	@Test
	public void testConstructor(){
		assertTrue(manager.getKey() != null);
		assertTrue(manager.getSessionToken() != null);
		assertTrue(manager.getMovies() != null);
		assertTrue(manager.getTvSeries() != null);
		assertTrue(manager.getAccount() != null);
	}
	
	/**
	 * Tests DbManager can get movies
	 */
	@Test
	public void testGetMovies(){
		assertTrue(manager.getPlayingMovies().size() != 0);
	}
	
	/**
	 * Tests DbManager can get TV series
	 */
	@Test
	public void testGetTVSeries(){
		assertTrue(manager.getTvSeries().size() != 0);
	}

	/**
	 * Tests DbManager can get random actors
	 */
	@Test
	public void testGetRandomActors(){
		assertTrue(manager.getRandomActors() != null);
	}
	
	/**
	 * Tests DbManager can get a random movie.
	 */
	@Test
	public void testGetRandomMovie(){
		Object movie = manager.getRandomMovie();
		assertTrue(movie != null);
		assertTrue(movie instanceof MovieDb);
		
		movie = manager.getRandomPlayingMovie();
		assertTrue(movie != null);
		assertTrue(movie instanceof MovieDb);
	}
	
	/**
	 * Tests DbManager can login properly and not accept incorrect info.
	 */
	@Test
	public void testLogin(){
		assertTrue(manager.attemptLogin("DavidBizzocchi", "CIS350Project"));
		assertFalse(manager.attemptLogin("user", "password"));
	}
	
	/**
	 * Tests DbManager can get a movie that has valid information
	 */
	@Test
	public void testGetMovieWithInfo(){
		MovieDb movie = manager.getRandomMovie();
		MovieDb infoMovie = manager.getMovieWithInfo(movie);
		assertTrue(infoMovie.getPosterPath() != null);
		assertTrue(infoMovie.getVideos().size() != 0);
	}
	
	/**
	 * Tests DbManager can get movie based on title parameter
	 */
	@Test
	public void testGetMovieFromTitle(){
		Object movie = manager.getMovieFromTitle("Detroit");
		assertTrue(movie != null);
		assertTrue(movie instanceof MovieDb);
	}
}
