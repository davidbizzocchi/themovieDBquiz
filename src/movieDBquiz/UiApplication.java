package movieDBquiz;

import javafx.application.Application;
import javafx.stage.Stage;

public class UiApplication extends Application {

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Login");
	    //AppLoginUI app = new AppLoginUI();
	    //app.addToStage(primaryStage);
//	    MainMenuUI app = new MainMenuUI();
		WatchListUI app = new WatchListUI();
//	    QuizUI app = new QuizUI(true);
//		HighLoGame app = new HighLoGame();
//		app.addToStage(primaryStage);
		primaryStage.setScene(app);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}
