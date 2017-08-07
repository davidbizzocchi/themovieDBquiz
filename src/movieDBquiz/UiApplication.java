package movieDBquiz;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;


/**
 * A class to run and organize main UI application.
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class UiApplication extends Application {
	/** Field to hold reference to stage of application.*/
	private Stage stage;

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(final Stage primaryStage) {
		stage = primaryStage;
		showLogin();
		primaryStage.show();
	}

	/**
	 * Main method entry point for application.
	 * @param args the arguments which will start this TMDB app.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Shows login form, which requires authentication to log on.
	 */
	private void showLogin() {
		stage.setTitle("The Movie Database App");
		AppLoginUI loginForm = new AppLoginUI();
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				if (loginForm.attemptLogin()) {
					loginForm.giveLoginSuccessMsg();
					showMainMenu();
				} else {
					loginForm.giveLoginFailedMsg();
				}
			}
		};

		loginForm.addBtnEventHandler(handler);
		stage.setScene(loginForm);
	}

	/**
	 * Sets the main menu to current scene.
	 */
	private void showMainMenu() {
		MainMenuUI mainMenu = new MainMenuUI();
		EventHandler<ActionEvent> exitHandler = createExitBtnHandler();
		EventHandler<ActionEvent> startQuizHandler = createQuizBtnHandler();
		EventHandler<ActionEvent> startHiLoHandler = createHiLoBtnHandler();
		EventHandler<ActionEvent> accountBtnHandler = createAccountBtnHandler();
		EventHandler<ActionEvent> movieListBtnHandler =
			createMovieListBtnHandler();
		EventHandler<ActionEvent> trailerBtnHandler =
			createTrailersBtnHandler();

		mainMenu.addExitBtnHandler(exitHandler);
		mainMenu.addQuizBtnHandler(startQuizHandler);
		mainMenu.addHiLoGameBtnHandler(startHiLoHandler);
		mainMenu.addAcctBtnHandler(accountBtnHandler);
		mainMenu.addMovieListBtnHandler(movieListBtnHandler);
		mainMenu.addTrailerBtnHandler(trailerBtnHandler);
		stage.setScene(mainMenu);
	}

	/**
	 * Creates a handler which will cause app to exit on ActionEvent.
	 * @return the EventHandler created.
	 */
	private EventHandler<ActionEvent> createExitBtnHandler() {
		EventHandler<ActionEvent> exitHandler =
			new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				System.exit(0);
			}
		};
		return exitHandler;
	}

	/**
	 * Creates a handler which will set run quiz upon ActionEvent.
	 * @return the EventHandler created.
	 */
	private EventHandler<ActionEvent> createQuizBtnHandler() {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				// //Launch Quiz
				// QuizUI quiz = new QuizUI(false);
				// stage.setScene(quiz);
				openQuizWindow();
			}
		};
		return handler;
	}

	/**
	 * Creates a handler which will set run HiLo quiz upon ActionEvent.
	 * @return the EventHandler created.
	 */
	private EventHandler<ActionEvent> createHiLoBtnHandler() {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				runHiLoQuiz();
			}
		};
		return handler;
	}

	/**
	 * Creates a handler which will open account page upon ActionEvent.
	 * @return the EventHandler created.
	 */
	private EventHandler<ActionEvent> createAccountBtnHandler() {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				openAcctWindow();
			}
		};
		return handler;
	}

	/**
	 * Creates a handler which open Watch List page on ActionEvent.
	 * @return the EventHandler created.
	 */
	private EventHandler<ActionEvent> createMovieListBtnHandler() {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				openWatchList();
			}
		};
		return handler;
	}

	/**
	 * Creates a handler which will show main menu upon ActionEvent.
	 * @return the EventHandler created.
	 */
	private EventHandler<ActionEvent> createMenuBtnHandler() {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				showMainMenu();
			}
		};
		return handler;
	}

	/**
	 * Creates a handler which will open trailer viewer upon ActionEvent.
	 * @return the EventHandler created.
	 */
	private EventHandler<ActionEvent> createTrailersBtnHandler() {
		EventHandler<ActionEvent> trailerHandler =
			new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				openTrailerWindow();
			}
		};
		return trailerHandler;
	}

	/**
	 * Sets scene to run High-Low card game.
	 */
	private void runHiLoQuiz() {
		HighLoGameUI game = new HighLoGameUI();
		EventHandler<ActionEvent> handler = createMenuBtnHandler();
		game.addEventHandler(handler);
		stage.setScene(game);
	}

	/**
	 * Sets scene to open account page.
	 */
	private void openAcctWindow() {
		AccountInfoUI accountPage = new AccountInfoUI(stage);
		EventHandler<ActionEvent> menuHandler = createMenuBtnHandler();

		accountPage.addMenuBtnHandler(menuHandler);
		stage.setScene(accountPage);
	}

	/**
	 * Sets scene to open WatchList page.
	 */
	private void openWatchList() {
		WatchListUI view = new WatchListUI();
		MovieWatchList watchList = new MovieWatchList();

		view.populateListView(watchList.getUserMovieList());
		EventHandler<ActionEvent> menuhandler = createMenuBtnHandler();
		view.addMenuBtnHandler(menuhandler);

		EventHandler<ActionEvent> removeHandler =
			new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				int selected = view.getSelectedIndex();
				watchList.removeFromList(view.getMovie(selected));
				if (selected >= 0) {
					view.removeSelected(selected);
				}
			}
		};

		view.addRemoveBtnHandler(removeHandler);
		stage.setScene(view);
	}

	/**
	 * Sets scene to open trailer window.
	 */
	private void openTrailerWindow() {
		TrailersUI trailerForm = new TrailersUI();
		EventHandler<ActionEvent> handler = createMenuBtnHandler();
		trailerForm.addExitEventHandler(handler);
		stage.setScene(trailerForm);
	}

	/**
	 * Sets scene to open MatchingQuestion quiz window.
	 */
	private void openQuizWindow() {
		EventHandler<ActionEvent> menuhandler = createMenuBtnHandler();
		Quiz quiz = new Quiz(stage);
		quiz.addExitButtonEventHandler(menuhandler);
		quiz.runQuiz();
	}

}
