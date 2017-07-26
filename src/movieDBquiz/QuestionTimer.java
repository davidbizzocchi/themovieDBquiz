package movieDBquiz;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer class for timed quizzes.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public class QuestionTimer {

	/** Instance of java.util.Timer Object. **/
	private Timer timer;
	
	/** Variable to determine whether timer has time remaining. **/
	private boolean timedOut;
	
	/** Variable to keep track of seconds elapsed since the time started. **/
	private int secondsElapsed;
	
	/** Time specified when calling constructor to time out the timer. **/
	private int timeoutSeconds;
	
	/** Task to increment elapsed seconds variable and determine if timer is finished. **/
	private TimerTask task;
	
	/**
	 * Constructor for Question Timer class.
	 * @param timeoutSeconds seconds until timer finishes.
	 */
	public QuestionTimer(int timeoutSeconds){
	
		timedOut = false;
		this.timeoutSeconds = timeoutSeconds;
		timer = new Timer();
		task = new TimerTask(){
			@Override
			public void run() {
				secondsElapsed++;
				if(secondsElapsed >= timeoutSeconds){
					timedOut = true;
					timer.cancel();
				}
			}
		};
		
		timer.schedule(task, 0, 1000);
	}
	
	/**
	 * Resets timer variable and timed out variable.
	 * @param timeoutSeconds seconds until timer finishes.
	 */
	public void resetTimer(long timeoutSeconds){
		timedOut = false;
		timer.schedule(task, 1000);
	}
	
	/**
	 * Returns minutes remaining until timer is finished.
	 * @return minutes remaining on timer.
	 */
	public int getTimeRemainingMin(){
		return (timeoutSeconds - secondsElapsed) / 60;
	}
	
	/**
	 * Returns seconds remaining until timer is finished.
	 * @return seconds remaining on timer.
	 */
	public int getTimeRemainingSec(){
		return (timeoutSeconds - secondsElapsed) % 60;
	}
	
	/**
	 * Determines if timer has reached specified time limit.
	 * @return whether timer has time remaining.
	 */
	public boolean timedOut(){
		return timedOut;
	}
	
}
