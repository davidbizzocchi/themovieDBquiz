package movieDBquiz;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Wrapper class for different quiz types.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public final class Quiz {
	/** Number of ms in a sec.*/
	private static final int MILLSECONDS = 1000;
	/** Percentage quotient.*/
	private static final int TOTALPERCENT = 100;

	/** .*/
	private MatchingQuestion question;
	/** .*/
	private QuizUI window;
	/** .*/
	private Timeline updateTimerTimeline;
	/** .*/
	private int secondsElapsed;
	/** .*/
	private int score;
	/** .*/
	private int questionsAnswered;

	/**
	 * Constructs quiz fields and UI.
	 * @param primaryStage the stage to add the quizUi to.
	 */
	public Quiz(final Stage primaryStage) {
		score = 0;
		window = new QuizUI(true);
		window.addToStage(primaryStage);
	}

	/**
	 * Begins the quiz by prompting user with quiz type selection.
	 */
	public void runQuiz() {

		window.getQuizOptionsDialog();

		question = new MatchingQuestion();
		populateMovieQuestion();

		if (window.isTimed()) {
			secondsElapsed = 0;
			window.setQuizTypeLabel("Time");
			updateTimerTimeline = new Timeline(
					new KeyFrame(Duration.millis(MILLSECONDS),
							ae -> updateTimeRemaining()));
			updateTimerTimeline.setCycleCount(Animation.INDEFINITE);
			updateTimerTimeline.play();
		} else if (window.isSetNumQuestion()) {
			window.setQuizTypeLabel("Questions");
			window.setQuestionsRemaining(window.getNumQuestions());
		}

		window.addNextButtonEventHandler(
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent arg0) {
						++questionsAnswered;
						if (question.getAnswerIndex() == window
								.getAnswerButton()) {
							window.showDialog("Correct!");
							window.setScore(++score);
						} else {
							window.showDialog("Incorrect!");
						}
						if (window.isSetNumQuestion()) {
							updateQuizQuestionNumber();
						}
						populateMovieQuestion();
					}
				});
	}

	/**
	 * Adds a handler to the menu/exit button.
	 * @param menuhandler the EventHandler to be added.
	 */
	public void addExitButtonEventHandler(
			final EventHandler<ActionEvent> menuhandler) {
		if (window != null) {
			window.addExitButtonEventHandler(menuhandler);
		}
	}

	/**
	 * Updates display field for rem. question counter.
	 */
	private void updateQuizQuestionNumber() {
		int questionsRemaining = window.getNumQuestions()
				- questionsAnswered;
		window.setQuestionsRemaining(questionsRemaining);

		if (questionsRemaining <= 0) {
			Float result = new Float(score)
				/ new Float(window.getNumQuestions());
			window.showDialog("Results: " + Integer.toString(score) + "/"
				+ Integer.toString(window.getNumQuestions())
				+ " -> " + String.format("%.2f", result * TOTALPERCENT) + "%");
			window.disableNextButton();
		}
	}

	/**
	 * Updates the display field for rem. time.
	 */
	private void updateTimeRemaining() {
		int timeRemaining = window.getNumSeconds() - secondsElapsed++;
		window.setTimeRemaining(timeRemaining);
		if (timeRemaining <= 0) {
			Float result = new Float(score)
					/ new Float(questionsAnswered);
			window.showDialog("Time's Up!\n" + "Results: "
					+ Integer.toString(score) + "/" + questionsAnswered
					+ " -> " + String.format("%.2f", result * TOTALPERCENT)
					+ "%");
			updateTimerTimeline.stop();
			window.disableNextButton();
		}
	}

	/**
	 * Populates data into ui fields for new question.
	 */
	private void populateMovieQuestion() {
		question = question.generateQuestion();
		window.setQuestionTxt(question.getQuestionText());
		window.populateOptions(question.getPossibleAnswers());
	}
}