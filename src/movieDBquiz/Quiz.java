package movieDBquiz;

import java.util.List;
import info.movito.themoviedbapi.model.MovieDb;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
	int score;
	
	public Quiz(Stage primaryStage){
		score = 0;
		window = new QuizUI(true);
		window.addToStage(primaryStage);
		
		question = new MatchingQuestion();
		populateMovieQuestion();
		
		window.addNextButtonEventHandler(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent arg0) {
				if(question.getAnswerIndex() == window.getAnswerButton()){
					window.showDialog("Correct!");
					window.setScore(++score);
				} else {
					window.showDialog("Incorrect!");
				}
				
				populateMovieQuestion();
			}
		});
		
		/**
		QuestionTimer t = new QuestionTimer(30);
		question = new MatchingQuestion();
		window = new QuizDemo(question);
		window.runQuiz();
		window.getAppDemo().setVisible(true);
		
		while(!t.timedOut()){
			System.out.println(t.getTimeRemainingMin() + ":" +  String.format("%02d", t.getTimeRemainingSec()));
		}
		System.out.println("Time's up!");
		window.close();
		
		**/
	}
	
	private void populateMovieQuestion(){
		question = question.generateQuestion();
		window.setQuestionTxt(question.getQuestionText());
		window.populateOptions(question.getPossibleAnswers());
	}
}