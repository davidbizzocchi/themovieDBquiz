package movieDBquiz;

import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;

/**
 * Wrapper class for different quiz types.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public final class Quiz {
	
	MatchingQuestion question;
	MovieWatchList watchList;
	QuestionTimer timer;
	QuizDemo window;
	
	public Quiz(){
			
		watchList = new MovieWatchList();
		question = new MatchingQuestion();
		
		//watchList.addToList(question.getMovieList().get(0));
		//watchList.removeFromList(watchList.getUserMovieList().get(0));
		
		List<MovieDb> list = watchList.getUserMovieList();
		for(MovieDb movie : list){
			System.out.println(movie.getTitle());
			System.out.println(movie.getReleaseDate());
			System.out.println();
		}
		
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
}
