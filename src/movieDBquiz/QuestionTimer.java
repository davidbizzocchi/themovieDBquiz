package movieDBquiz;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer class for timed quizzes.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.1
 */
public class QuestionTimer {
	/** Max number of timeout seconds.*/
	private static final int MAXSECONDS = 1000;
	/** Seconds in minute.*/
	private static final int MINUTESSECS = 60;


	/** Instance of java.util.Timer Object. **/
	private Timer timer;

	/** Variable to determine whether timer has time remaining. **/
	private boolean timedOut;

	/** Variable to keep track of seconds elapsed since the time started. **/
	private int secondsElapsed;

	/** Time specified when calling constructor to time out the timer. **/
	private int timeoutSeconds;

	/**
	 * Task to increment elapsed seconds variable and determine if timer is
	 * finished.
	 **/
	private TimerTask task;

	/**
	 * Constructor for Question Timer class.
	 * @param timeoutSecs
	 *            seconds until timer finishes.
	 */
	public QuestionTimer(final int timeoutSecs) {

		timedOut = false;
		this.timeoutSeconds = timeoutSecs;
		timer = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				secondsElapsed++;
				if (secondsElapsed >= timeoutSecs) {
					timedOut = true;
					timer.cancel();
				}
			}
		};

		timer.schedule(task, 0, MAXSECONDS);
	}

	/**
	 * Resets timer variable and timed out variable.
	 * @param timeoutSecs
	 *            seconds until timer finishes.
	 */
	public void resetTimer(final long timeoutSecs) {
		timedOut = false;
		timer.schedule(task, MAXSECONDS);
	}

	/**
	 * Returns minutes remaining until timer is finished.
	 * @return minutes remaining on timer.
	 */
	public int getTimeRemainingMin() {
		return (timeoutSeconds - secondsElapsed) / MINUTESSECS;
	}

	/**
	 * Returns seconds remaining until timer is finished.
	 * @return seconds remaining on timer.
	 */
	public int getTimeRemainingSec() {
		return (timeoutSeconds - secondsElapsed) % MINUTESSECS;
	}

	/**
	 * Returns total seconds remaining until timer is finished.
	 * @return total seconds remaining on timer.
	 */
	public int getTotalSecondsRemaining() {
		return timeoutSeconds - secondsElapsed;
	}

	/**
	 * Determines if timer has reached specified time limit.
	 * @return whether timer has time remaining.
	 */
	public boolean timedOut() {
		return timedOut;
	}

}
