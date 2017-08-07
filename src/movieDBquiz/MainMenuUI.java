package movieDBquiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class MainMenuUI extends Scene {
	/** The top level app-style layout.*/
	private static final int WINDOWWIDTH = 800;
	/** The top level app-style layout.*/
	private static final int WINDOWHEIGHT = 600;
	/** The top level app-style layout.*/
	private static final int PADDINGSIZE = 25;
	/** The top level app-style layout.*/
	private static final int SPACINGSIZE = 10;
	/** The top level app-style layout.*/
	private static final int MAXTEXTWIDTH = 400;
	/** The top level app-style layout.*/
	private static final int STDFONTSIZE = 16;
	/** The top level app-style layout.*/
	private static final int TITLEFONTSIZE = 20;
	/** The top level app-style layout.*/
	private static final int RTLTMARGINSIZE = 80;

	/** The top level app-style layout.*/
	private static BorderPane root;
	/** A button to return to menu.*/
	private Button exitButton;
	/** A button to launch quiz.*/
	private Button quizButton;
	/** A button to view account page.*/
	private Button accountButton;
	/** A button to view movie list page.*/
	private Button movieListButton;
	/** A button to launch High-Low card game.*/
	private Button hiLoGameButton;
	/** A button to open trailer viewer.*/
	private Button trailersButton;

	/**
	 * calls super constructor to set layout and adds all components.
	 */
	public MainMenuUI() {
		super(root = new BorderPane());
		// manager = new DbManager();
		createQuizWindow();
	}

	/**
	 * Sets up layout and adds all Ui components.
	 */
	public final void createQuizWindow() {
		setWindowStyle();
		setUpTop();
		setUpLeft();
		setUpBottom();
		setUpRight();
		setUpCenter();
	}

	/**
	 * Sets up layout size/spacing/format/color-scheme.
	 */
	private void setWindowStyle() {
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		root.setPrefSize(WINDOWWIDTH, WINDOWHEIGHT);
		root.setStyle("-fx-border-width: 2;" + "-fx-border-insets: 5;"
				+ "-fx-border-radius: 5;");
		root.setPadding(new Insets(PADDINGSIZE, PADDINGSIZE, PADDINGSIZE,
			PADDINGSIZE));
		root.setBackground(new Background(
				new BackgroundFill(pattern, null, null)));
	}

	/**
	 * Constructs a scene using layout and adds to stage.
	 * @param stage the Stage to set the scene for.
	 */
	public final void addToStage(final Stage stage) {
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	/**
	 * Sets up the top layout with title and toolbar.
	 */
	private void setUpTop() {
		VBox box = new VBox(SPACINGSIZE);
		box.setCenterShape(true);
		box.setAlignment(Pos.CENTER);
		box.setStyle("-fx-border-insets: 5;");

		ToolBar toolbar = new ToolBar();
		toolbar.setCenterShape(true);
		toolbar.setBackground(new Background(
				new BackgroundFill(Color.AQUAMARINE, null, null)));
		VBox.setMargin(toolbar, new Insets(0, 0, SPACINGSIZE, 0));

		Label text = new Label("Main Menu");
		text.setFont(new Font("Commons", TITLEFONTSIZE));
		text.setTextFill(Color.WHITE);
		text.setAlignment(Pos.TOP_CENTER);

		HBox.setHgrow(text, Priority.ALWAYS);
		HBox.setHgrow(toolbar, Priority.ALWAYS);
		text.setMaxWidth(MAXTEXTWIDTH);
		box.getChildren().addAll(text, toolbar);

		root.setTop(box);
	}

	/**
	 * Sets up the left menu layout.
	 */
	private void setUpLeft() {
		VBox box = new VBox(SPACINGSIZE);
		box.setCenterShape(true);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(35.0);
		box.setBackground(new Background(
				new BackgroundFill(Color.TRANSPARENT, null, null)));

		box.setPadding(new Insets(PADDINGSIZE));

		quizButton = new Button("Quiz");
		quizButton.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(quizButton);
		accountButton = new Button("View Account");
		accountButton
				.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(accountButton);
		movieListButton = new Button("Movie List");
		movieListButton
				.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(movieListButton);
		hiLoGameButton = new Button("High-Low Game");
		hiLoGameButton
				.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(hiLoGameButton);
		trailersButton = new Button("Trailers");
		trailersButton
				.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(trailersButton);

		VBox.setVgrow(quizButton, Priority.SOMETIMES);
		VBox.setVgrow(accountButton, Priority.SOMETIMES);
		VBox.setVgrow(movieListButton, Priority.SOMETIMES);
		VBox.setVgrow(hiLoGameButton, Priority.SOMETIMES);
		VBox.setVgrow(trailersButton, Priority.SOMETIMES);

		box.getChildren().addAll(quizButton, hiLoGameButton,
				trailersButton, movieListButton, accountButton);

		root.setLeft(box);
	}

	/**
	 * Sets up a generic menu button.
	 * @param button the button to be formatted.
	 */
	private void setUpMenuButton(final Button button) {
		Font font = new Font("System", STDFONTSIZE);
		button.setAlignment(Pos.CENTER);
		button.setMaxWidth(150.0);
		button.setMaxHeight(50.0);
		button.setFont(font);
		button.setMinWidth(80.0);
	}

	/**
	 * Sets up bottom layout with status bar.
	 */
	private void setUpBottom() {
		HBox box = new HBox();
		box.setCenterShape(true);
		box.setAlignment(Pos.CENTER_RIGHT);
		box.setPadding(new Insets(PADDINGSIZE));

		exitButton = new Button("Exit");
		setUpMenuButton(exitButton);

		TextField status = new TextField();
		status.setText("Select an option from the menu.");
		status.setEditable(false);
		status.setAlignment(Pos.TOP_CENTER);

		Font statusFont = new Font("System", STDFONTSIZE);
		status.setFont(statusFont);
		status.setStyle("-fx-text-inner-color: red;");

		HBox.setHgrow(status, Priority.SOMETIMES);
		HBox.setHgrow(exitButton, Priority.SOMETIMES);

		box.getChildren().addAll(status, exitButton);

		root.setBottom(box);
	}

	/**
	 * Sets up the right layout to hold movie poster.
	 */
	private void setUpRight() {
		PosterUI poster = new PosterUI();
		VBox layout = poster.getLayout();
		layout.setBackground(new Background(
				new BackgroundFill(Color.TRANSPARENT, null, null)));
		root.setRight(layout);
	}

	/** Sets up center cell of top layout to hold an image icon.*/
	private void setUpCenter() {
		Image centerImg = new Image("file:lib/projector.png");
		ImageView view = new ImageView(centerImg);
		view.setStyle("-fx-border-insets: 30;");
		BorderPane.setAlignment(view, Pos.CENTER);
		BorderPane.setMargin(view, new Insets(PADDINGSIZE,
			RTLTMARGINSIZE, PADDINGSIZE, RTLTMARGINSIZE));
		root.setCenter(view);
	}

	/**
	 * Sets a handler to the exit button upon click event.
	 * @param handler the handler to assign to exitButton.
	 */
	public final void addExitBtnHandler(
		final EventHandler<ActionEvent> handler) {
		exitButton.setOnAction(handler);
	}

	/**
	 * Sets a handler to the quiz button upon click event.
	 * @param handler the handler to assign to quizButton.
	 */
	public final void addQuizBtnHandler(
		final EventHandler<ActionEvent> handler) {
		quizButton.setOnAction(handler);
	}

	/**
	 * Sets a handler to movie List menu button.
	 * @param handler the EventHandler to assign to movieListButton
	 */
	public final void addMovieListBtnHandler(
			final EventHandler<ActionEvent> handler) {
		movieListButton.setOnAction(handler);
	}

	/**
	 * Sets a handler to account menu button.
	 * @param handler EventHandler to assign to accountButton
	 */
	public final void addAcctBtnHandler(
		final EventHandler<ActionEvent> handler) {
		accountButton.setOnAction(handler);
	}

	/**
	 * Sets a handler to Hi-Low game button.
	 * @param handler EventHandler to assign to hiLoGameButton
	 */
	public final void addHiLoGameBtnHandler(
			final EventHandler<ActionEvent> handler) {
		hiLoGameButton.setOnAction(handler);
	}

	/**
	 * Sets a handler to trailer button.
	 * @param handler EventHandler to assign to trailersButton.
	 */
	public final void addTrailerBtnHandler(
			final EventHandler<ActionEvent> handler) {
		trailersButton.setOnAction(handler);
	}

}