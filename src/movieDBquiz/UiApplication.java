package movieDBquiz;

import info.movito.themoviedbapi.model.MovieDb;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UiApplication extends Application {
	Stage stage;

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		showLogin();
	    //AppLoginUI app = new AppLoginUI();
	    //app.addToStage(primaryStage);
//	    MainMenuUI app = new MainMenuUI();
		//WatchListUI app = new WatchListUI();

//	    Quiz app = new Quiz(primaryStage);
//		QuizUI app = new QuizUI(false);
//		app.showDialog("Quiz Test");
//		HighLoGame app = new HighLoGame();
		//app.addToStage(primaryStage);
		//DbManager manager = new DbManager();
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void showLogin(){
		stage.setTitle("Login");
		
		AppLoginUI loginForm = new AppLoginUI();
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>(){ 
		    @Override
		    public void handle(ActionEvent e) {
		    	if(loginForm.attemptLogin()){
		    		loginForm.giveLoginSuccessMsg();
		    		showMainMenu();
		    	}
		    	else{
		    		loginForm.giveLoginFailedMsg();
		    	}
		    }
		};
		
		loginForm.addBtnEventHandler(handler);
		stage.setScene(loginForm);
	}
	
	private void showMainMenu(){
		MainMenuUI mainMenu = new MainMenuUI();
		EventHandler<ActionEvent> exitHandler = createExitBtnHandler();
		EventHandler<ActionEvent> startQuizHandler = createQuizBtnHandler();
		EventHandler<ActionEvent> startHiLoHandler = createHiLoBtnHandler();
		EventHandler<ActionEvent> accountBtnHandler = createAccountBtnHandler();
		EventHandler<ActionEvent> movieListBtnHandler = createMovieListBtnHandler();
		EventHandler<ActionEvent> trailerBtnHandler = createTrailersBtnHandler();
		
		mainMenu.addExitBtnHandler(exitHandler);
		mainMenu.addQuizBtnHandler(startQuizHandler);
		mainMenu.addHiLoGameBtnHandler(startHiLoHandler);
		mainMenu.addAcctBtnHandler(accountBtnHandler);
		mainMenu.addMovieListBtnHandler(movieListBtnHandler);
		mainMenu.addTrailerBtnHandler(trailerBtnHandler);
		stage.setScene(mainMenu);
	}
	
	private EventHandler<ActionEvent> createExitBtnHandler(){
		EventHandler<ActionEvent> exitHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		};
		return exitHandler;
	}
	
	private EventHandler<ActionEvent> createQuizBtnHandler(){
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//Launch Quiz
				QuizUI quiz = new QuizUI(false);
				stage.setScene(quiz);
			}
		};
		return handler;
	}
	
	private EventHandler<ActionEvent> createHiLoBtnHandler(){
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				runHiLoQuiz();
			}
		};
		return handler;
	}
	
	private EventHandler<ActionEvent> createAccountBtnHandler(){
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				openAcctWindow();
			}
		};
		return handler;
	}
	
	private EventHandler<ActionEvent> createMovieListBtnHandler(){
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				openWatchList();
			}
		};
		return handler;
	}
	
	private EventHandler<ActionEvent> createMenuBtnHandler(){
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showMainMenu();
			}
		};
		return handler;
	}
	
	private EventHandler<ActionEvent> createTrailersBtnHandler(){
		EventHandler<ActionEvent> trailerHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				openTrailerWindow();
			}
		};
		return trailerHandler;
	}
	
	private void runHiLoQuiz(){
		HighLoGameUI game = new HighLoGameUI();
		EventHandler<ActionEvent> handler = createMenuBtnHandler();
		game.addEventHandler(handler);
		stage.setScene(game);
	}
	
	private void openAcctWindow(){
		AccountInfoUI accountPage = new AccountInfoUI(stage);
		EventHandler<ActionEvent> menuHandler = createMenuBtnHandler();
		
		accountPage.addMenuBtnHandler(menuHandler);
		stage.setScene(accountPage);
	}
	
	private void openWatchList(){
		WatchListUI view = new WatchListUI();
		MovieWatchList watchList = new MovieWatchList();
		
		view.populateListView(watchList.getUserMovieList());
		EventHandler<ActionEvent> menuhandler = createMenuBtnHandler();
		view.addMenuBtnHandler(menuhandler);
		
		EventHandler<ActionEvent> removeHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int selected = view.getSelectedIndex();
				watchList.removeFromList(view.getMovie(selected));
				if(selected >= 0){
					view.removeSelected(selected);
				}
			}
		};
		
		view.addRemoveBtnHandler(removeHandler);
		stage.setScene(view);
	}
	
	private void openTrailerWindow(){
		TrailersUI trailerForm = new TrailersUI();
		EventHandler<ActionEvent> handler = createMenuBtnHandler();
		trailerForm.addExitEventHandler(handler);
		stage.setScene(trailerForm);
	}
	
}
