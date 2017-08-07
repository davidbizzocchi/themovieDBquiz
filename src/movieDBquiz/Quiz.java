package movieDBquiz;

import java.util.List;
import info.movito.themoviedbapi.model.MovieDb;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * Wrapper class for different quiz types.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public final class Quiz {
	
	MatchingQuestion question;
	MovieWatchList watchList;
	QuestionTimer timer;
	QuizUI window;
	Timeline updateTimerTimeline;
	Stage primaryStage;
	int secondsElapsed;
	int score;
	int questionsAnswered;
	
	public Quiz(Stage primaryStage){
		score = 0;
		this.primaryStage = primaryStage;
		window = new QuizUI(true);
		window.addToStage(primaryStage);
	}
	
	public void runQuiz(){
		
		question = new MatchingQuestion();
		populateMovieQuestion();
		
		if(window.isTimed()){
			secondsElapsed = 0;
			window.setQuizTypeLabel("Time");
			updateTimerTimeline = new Timeline(new KeyFrame(
			        Duration.millis(1000),
			        ae -> updateTimeRemaining()));
			updateTimerTimeline.setCycleCount(Timeline.INDEFINITE);
			updateTimerTimeline.play();
		}
		else if(window.isSetNumQuestion()){
			window.setQuizTypeLabel("Questions");
			window.setQuestionsRemaining(window.getNumQuestions());
		}
		
		window.addNextButtonEventHandler(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				++questionsAnswered;
				if(question.getAnswerIndex() == window.getAnswerButton()){
					window.showDialog("Correct!");
					window.setScore(++score);
				} else {
					window.showDialog("Incorrect!");
				}
				if(window.isSetNumQuestion()){
					updateQuizQuestionNumber();
				}
				populateMovieQuestion();
			}
		});
	}
	
	public void addExitButtonEventHandler(EventHandler<MouseEvent> event){
		if(window != null){
			window.addExitButtonEventHandler(event);
		}
	}
	
	private void updateQuizQuestionNumber(){
		int questionsRemaining = window.getNumQuestions() - questionsAnswered;
		window.setQuestionsRemaining(questionsRemaining);
		if(questionsRemaining <= 0){
			Float result = new Float(score)/new Float(window.getNumQuestions());
			window.showDialog("Results: " + Integer.toString(score) + "/" + 
					Integer.toString(window.getNumQuestions()) + " -> " + Float.toString(result*100) + "%");
		}
	}
	
	private void updateTimeRemaining(){
		int timeRemaining = window.getNumSeconds() - secondsElapsed++;
		window.setTimeRemaining(timeRemaining);
		if(timeRemaining <= 0){
			window.showDialog("TIMES UP!");
			updateTimerTimeline.stop();
		}
	}
	
	private void populateMovieQuestion(){
		question = question.generateQuestion();
		window.setQuestionTxt(question.getQuestionText());
		window.populateOptions(question.getPossibleAnswers());
	}
}