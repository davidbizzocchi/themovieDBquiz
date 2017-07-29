package movieDBquiz;

/**
 * Wrapper class for different quiz types.
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public final class Quiz {
	
	MatchingQuestion question;
	QuestionTimer timer;
	QuizDemo window;
	
	public Quiz(){
			
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
			
	}
}