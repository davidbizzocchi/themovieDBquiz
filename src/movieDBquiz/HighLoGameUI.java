package movieDBquiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A class to implement High-Low Movie Budget guessing game with logic.
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class HighLoGameUI extends Scene {
	/** Constant value for insets. */
	private static final int PADDINGSIZE = 15;
	/** Constant value for window height/width. */
	private static final int WINDOWDIMENSION = 800;
	/** Constant for standard font size. */
	private static final int TITLEFONTSIZE = 22;
	/** Constant for standard font size. */
	private static final int BODYFONTSIZE = 16;
	/** Max width of title label. */
	private static final int TITLEMAXWIDTH = 300;
	/** Text height of labels and fields. */
	private static final int STDTEXTHEIGHT = 30;
	/** Standard spacing size. */
	private static final int STDSPACING = 10;
	/** Image view pref. width.*/
	private static final int IMGVIEWWIDTH = 150;
	/** Image view pref. height. */
	private static final int IMGVIEWHEIGHT = 250;
	/** Standard width of card label. */
	private static final int BODYLABELWIDTH = 120;


	/** Field to hold top level layout. */
	private static HBox windowLayout;
	/** Field to hold top level game objects. */
	private VBox gameLayout;
	/** Field to hold face-up image of movie poster. */
	private ImageView faceCard;
	/** Field to hold face-down movie poster. */
	private ImageView downCard;
	/** Field to display face-up movie budget. */
	private TextField faceCardTxt;
	/** Field to display face-down movie budget. */
	private TextField downCardTxt;
	/** Field to display current no. correct answers. */
	private TextField scoreTxt;
	/** Field to hold reference to the game object. */
	private HighLowMovieCardGame game;
	/** Button to return to main menu. */
	private Button menuBtn;
	/** The default image for back of a card. */
	private Image cardBackImg = new Image("file:lib/cardBack.jpg");

	/**
	 * Calls super constructor and adds sets layout. Adds components, object
	 * data and sets up game.
	 */
	public HighLoGameUI() {
		super(windowLayout = new HBox());
		game = new HighLowMovieCardGame();
		setUpLayout();
		addComponents();
	}

	/** Sets up layout constraints. */
	private void setUpLayout() {
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill bg = new BackgroundFill(pattern, null, null);

		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setPadding(new Insets(PADDINGSIZE));
		windowLayout.setMaxSize(WINDOWDIMENSION, WINDOWDIMENSION);

		gameLayout = new VBox();
		gameLayout.setAlignment(Pos.CENTER);
		gameLayout.setPadding(new Insets(PADDINGSIZE));

		gameLayout.setBackground(new Background(bg));
		gameLayout.setMaxSize(WINDOWDIMENSION, WINDOWDIMENSION);

		windowLayout.getChildren().add(gameLayout);
	}

	/** Adds all UI components to layout. */
	private void addComponents() {
		setUpTitle();
		setUpGameLayout();
		setUpToolbar();
	}

	/** Adds title label to layout. */
	private void setUpTitle() {
		Label title = new Label("Hi-Low Guessing Game");
		title.setAlignment(Pos.TOP_CENTER);
		title.setFont(new Font("Commons", TITLEFONTSIZE));
		title.setMaxSize(TITLEMAXWIDTH, STDTEXTHEIGHT);
		title.setTextFill(Color.GHOSTWHITE);

		gameLayout.getChildren().add(title);
	}

	/** Adds game UI components to layout. */
	private void setUpGameLayout() {
		GridPane gameGrid = new GridPane();
		gameGrid.setPadding(new Insets(PADDINGSIZE));
		gameGrid.setAlignment(Pos.CENTER);
		gameGrid.setHgap(STDSPACING);
		gameGrid.setVgap(STDSPACING);

		faceCard = new ImageView();
		faceCard.setFitWidth(IMGVIEWWIDTH);
		faceCard.setFitHeight(IMGVIEWHEIGHT);
		faceCardTxt = new TextField("$???");
		faceCardTxt.setMaxSize(IMGVIEWWIDTH, STDTEXTHEIGHT);
		faceCardTxt.setAlignment(Pos.CENTER);
		setFaceCardData();

		downCard = new ImageView();
		downCard.setFitWidth(IMGVIEWWIDTH);
		downCard.setFitHeight(IMGVIEWHEIGHT);
		downCardTxt = new TextField("");
		downCardTxt.setAlignment(Pos.CENTER);
		downCardTxt.setMaxSize(IMGVIEWWIDTH, STDTEXTHEIGHT);
		downCardTxt.setEditable(false);
		hideFaceDownCard();

		Label faceCardLabel = new Label("Budget:");
		faceCardLabel.setTextFill(Color.GHOSTWHITE);
		faceCardLabel.setFont(new Font("System", BODYFONTSIZE));
		faceCardLabel.setMaxSize(BODYLABELWIDTH, STDTEXTHEIGHT);

		Label downCardLabel = new Label("Budget:");
		downCardLabel.setTextFill(Color.GHOSTWHITE);
		downCardLabel.setFont(new Font("System", BODYFONTSIZE));
		downCardLabel.setMaxSize(BODYLABELWIDTH, STDTEXTHEIGHT);

		gameGrid.add(faceCard, 1, 0);
		gameGrid.add(downCard, 3, 0);
		gameGrid.add(faceCardLabel, 0, 1);
		gameGrid.add(downCardLabel, 2, 1);
		gameGrid.add(faceCardTxt, 1, 1);
		gameGrid.add(downCardTxt, 3, 1);

		gameLayout.getChildren().add(gameGrid);
	}

	/** Sets budget info and card image for face-up card.*/
	private void setFaceCardData() {
		String posterPath = game
				.getMovieWithInfo(
						game.getFaceUpCard().getAssociatedMovie())
				.getPosterPath();

		if (posterPath == null) {
			faceCard.setImage(new Image("file:lib/placeholder.png"));
		} else {
			faceCard.setImage(new Image(
					"http://image.tmdb.org/t/p/w500" + posterPath));
		}

		faceCardTxt.setText("$" + Long.toString(game
				.getMovieWithInfo(
						game.getFaceUpCard().getAssociatedMovie())
				.getBudget()));
		faceCardTxt.setEditable(false);
	}

	/** Sets budget info and card image for face-down card.*/
	private void showFaceDownCardData() {
		String posterPath = game
				.getMovieWithInfo(
						game.getFaceDownCard().getAssociatedMovie())
				.getPosterPath();

		if (posterPath == null) {
			downCard.setImage(new Image("file:lib/placeholder.png"));
		} else {
			downCard.setImage(new Image(
					"http://image.tmdb.org/t/p/w500" + posterPath));
		}

		downCardTxt.setMaxSize(IMGVIEWWIDTH, STDTEXTHEIGHT);
		downCardTxt.setText("$" + Long.toString(game
				.getMovieWithInfo(
						game.getFaceDownCard().getAssociatedMovie())
				.getBudget()));
		downCardTxt.setEditable(false);
	}

	/** Covers face-down cards textfield and hides poster.*/
	private void hideFaceDownCard() {
		downCard.setImage(cardBackImg);
		downCardTxt.setText("$ ???");
	}

	/** Adds UI components for toolbar.*/
	private void setUpToolbar() {
		HBox toolBar = new HBox();
		toolBar.setPadding(new Insets(PADDINGSIZE));
		toolBar.setSpacing(STDSPACING + STDSPACING);
		toolBar.setAlignment(Pos.TOP_CENTER);

		Label scoreLabel = new Label("Score:");
		scoreLabel.setTextFill(Color.GHOSTWHITE);
		scoreLabel.setMaxSize(100, STDTEXTHEIGHT);
		scoreTxt = new TextField("0");
		scoreTxt.setMaxSize(60, STDTEXTHEIGHT);
		scoreTxt.setEditable(false);

		menuBtn = new Button("Menu");
		Button highBtn = new Button("Higher");
		Button lowBtn = new Button("Lower");
		Button nextBtn = new Button("Next");
		highBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				validateAnswer(true);
				showFaceDownCardData();
				scoreTxt.setText(Integer.toString(game.getScore()));
				highBtn.setDisable(true);
				lowBtn.setDisable(true);
			}
		});
		lowBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				validateAnswer(false);
				showFaceDownCardData();
				scoreTxt.setText(Integer.toString(game.getScore()));
				highBtn.setDisable(true);
				lowBtn.setDisable(true);
			}
		});
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				game.drawCards();
				hideFaceDownCard();
				setFaceCardData();
				highBtn.setDisable(false);
				lowBtn.setDisable(false);
			}
		});

		toolBar.getChildren().addAll(menuBtn, scoreLabel, scoreTxt,
				highBtn, lowBtn, nextBtn);

		gameLayout.getChildren().addAll(toolBar);
	}

	/**
	 * Shows a dialogue according to correct high/low user selection.
	 * @param isHigher the choice made by the user.
	 */
	private void validateAnswer(final boolean isHigher) {
		if (game.answer(isHigher)) {
			showDialogue("Correct!");
		} else {
			showDialogue("Incorrect!");
		}
	}

	/**
	 * Shows a dialogue with given text input.
	 * @param info the text to display as dialogue content.
	 */
	private void showDialogue(final String info) {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		Button okBtn = new Button("Ok");
		okBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				dialogStage.close();
			}
		});
		VBox vbox = new VBox(new Text(info), okBtn);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(PADDINGSIZE));

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}

	/**
	 * Sets the scene of the stage parameter to this object.
	 * @param primaryStage the stage to set scene for.
	 */
	public void addToStage(final Stage primaryStage) {
		primaryStage.setScene(this);
	}

	/**
	 * Gets the current layout with components added.
	 * @return an HBox of top-level layout.
	 */
	public HBox getLayout() {
		return windowLayout;
	}

	/**
	 * Adds an external event handler to menu button.
	 * @param handler the handler to provide navigation from menuBtn click.
	 */
	public void addEventHandler(final EventHandler<ActionEvent> handler) {
		menuBtn.setOnAction(handler);
	}
}
