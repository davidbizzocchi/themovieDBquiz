package movieDBquiz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests all methods in the MatchingQuestion Class.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017 
 */
public class TestMatchingQuestion {
	
	/** 
	 * Tests if the constructor has successfully instantiated.
	 * **/
	@Test
	public void testConstructor() {
		MatchingQuestion q = new MatchingQuestion();
		assertEquals(q.getPossibleAnswers().size(), 0);
		assertTrue(q != null);
	}
	
	/**
	 * Tests if a title is successfully added into the ArrayList 
	 * of possible Answers.
	 */
	@Test
	public void testAddAnswerTitleValid() {
		MatchingQuestion q = new MatchingQuestion();
		q.addAnswerTitle("UP");
		assertEquals(q.getPossibleAnswers().get(0), "UP");
		
	}
	
	/**
	 * Tests that an error is thrown when the movie title is given an
	 * invalid null argument.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testAddAnswerTitleNull() {
		MatchingQuestion q = new MatchingQuestion();
		q.addAnswerTitle(null);
	}
	
	/**
	 * Tests that an error is thrown when the movie title is given an
	 * invalid whitespace argument.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testAddAnswerTitleWhiteSpace() {
		MatchingQuestion q = new MatchingQuestion();
		q.addAnswerTitle("     ");
	}
	
	/**
	 * Tests that the populated movie list is retrievable.
	 */
	@Test 
	public void testGetMovieList() {
		MatchingQuestion q = new MatchingQuestion();
		assertTrue(q.getMovieList() != null);
		assertTrue(q.getMovieList().size() > 0);
	}
	
	/**
	 * Tests that the array list of answers for the current 
	 * question has exactly 4 answers.
	 */
	@Test
	public void testGetPossibleAnswers() {
		MatchingQuestion q = new MatchingQuestion();
		q = q.generateQuestion();
		assertTrue(q.getPossibleAnswers().size() == 4);
	}
	
	/** 
	 * Test that setting the index of the answer to a valid number is correct.
	 */
	@Test
	public void testCorrectAnswerValid() {
		MatchingQuestion q = new MatchingQuestion();
		q.setAnswerIndex(4);
		assertTrue(q.getAnswerIndex() == 4);
		q.setAnswerIndex(1);
		assertEquals(q.getAnswerIndex(), 1);
		q.setAnswerIndex(2);
		assertEquals(q.getAnswerIndex(), 2);
		q.setAnswerIndex(3);
		assertEquals(q.getAnswerIndex(), 3);
	}
	
	/**
	 * Test that an error is thrown when negative index is set for the correct
	 * answer.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testCorrectAnserIndexNeg() {
		MatchingQuestion q = new MatchingQuestion();
		q.setAnswerIndex(-1);
	}
	
	/**
	 * Test that an error is thrown when too large of a an index is set for 
	 * the correct answer.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testCorrectAnswerIndexLarge() {
		MatchingQuestion q = new MatchingQuestion();
		q.setAnswerIndex(5);
	}
	
	/**
	 * Tests that a question and answers are generated.
	 */
	@Test
	public void testGenerateMatchingQuestion() {
		MatchingQuestion q = new MatchingQuestion();
		q = q.generateQuestion();
		assertTrue(q.getPossibleAnswers().size() != 0);
		assertTrue(!q.getMovieDesc().trim().isEmpty());
	}
	
	/**
	 * Tests that the toString is not an empty string.
	 */
	public void testToString() {
		MatchingQuestion q = new MatchingQuestion();
		q = q.generateQuestion();
		assertTrue(!q.toString().trim().isEmpty());
	}
}
