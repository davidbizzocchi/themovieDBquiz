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
		//WatchListUI app = new WatchListUI();
	    Quiz app = new Quiz(primaryStage);
//		HighLoGame app = new HighLoGame();
		//app.addToStage(primaryStage);
		//DbManager manager = new DbManager();
		//primaryStage.setScene(new TrailersUI(manager.getMovieWithInfo(manager.getPlayingMovies().get(3))));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	
}
