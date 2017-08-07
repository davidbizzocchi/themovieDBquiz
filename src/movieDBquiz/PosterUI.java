package movieDBquiz;

import info.movito.themoviedbapi.model.MovieDb;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class PosterUI {
	/** The padding size for ui components.*/
	private static final int PADDINGSIZE = 15;
	/** The spacing size for ui components.*/
	private static final int SPACINGSIZE = 15;
	/** The spacing size for ui components.*/
	private static final int GRIDWIDTH = 200;
	/** The spacing size for ui components.*/
	private static final int GRIDHEIGHT = 300;
	/** The spacing size for ui components.*/
	private static final int STDFONTSIZE = 12;
	
	/** Constant string base url for accessing poster images from tmdb.*/
	private final String baseImgUrl = "http://image.tmdb.org/t/p/w185/";
	/** Image path for favorites icon.*/
	private final String favImgPath = "file:lib/star.png";

	/** The top-level layout to add to a scene.*/
	private VBox topLayout;
	/** The container for the movie poster img.*/
	private ImageView imgContainer;
	/** Button to add currentMovie to watchlist.*/
	private Button favoritesBtn;
	/** Button to get another random title.*/
	private Button randomBtn;
	/** A MovieWatchList to add favorited movies.*/
	private MovieWatchList watchList;
	/** Reference for current movie being displayed.*/
	private MovieDb currentMovie;
	/** Object to provide information from tmdb api.*/
	private DbManager manager;
	/** Field to hold movie title.*/
	private TextArea titleField;
	/** Field to hold movie release date.*/
	private TextArea releaseField;
	/** Field to hold movie duration (mins).*/
	private TextArea durationField;
	/** Field to hold movie cast list.*/
	private TextArea castField;

	/**
	 * Creates class instance with UI and fields populated.
	 */
	public PosterUI() {
		manager = new DbManager();
		watchList = new MovieWatchList();
		setUpPosterImg();
		setUpLayout();
		setRandomMovie();
	}

	/**
	 * Sets up layout size/spacing/alignment.
	 */
	private void setUpLayout() {
		topLayout = new VBox();
		topLayout.setAlignment(Pos.CENTER);
		GridPane grid = setUpGrid();

		VBox.setVgrow(imgContainer, Priority.SOMETIMES);
		VBox.setVgrow(grid, Priority.SOMETIMES);

		topLayout.setPadding(new Insets(PADDINGSIZE));
		topLayout.getChildren().addAll(imgContainer, grid);
	}

	/**
	 * Sets up ui components for labels and text fields.
	 * @return the layout with components added.
	 */
	private GridPane setUpGrid() {
		GridPane grid = new GridPane();
		grid.setMinWidth(GRIDWIDTH);
		grid.setMinHeight(GRIDHEIGHT);

		grid.setHgap(SPACINGSIZE);
		grid.setVgap(SPACINGSIZE);
		grid.setPadding(new Insets(SPACINGSIZE, SPACINGSIZE,
			SPACINGSIZE, SPACINGSIZE));

		favoritesBtn = createFavoritesBtn();
		Label favoritesLabel = createLabel("Add To WatchList");
		favoritesLabel.setTextFill(Color.LIGHTGRAY);
		Label titleLabel = createLabel("Title:");
		titleLabel.setTextFill(Color.LIGHTGRAY);
		Label releaseLabel = createLabel("Release Date:");
		releaseLabel.setTextFill(Color.LIGHTGRAY);
		Label durationLabel = createLabel("Duration(min):");
		durationLabel.setTextFill(Color.LIGHTGRAY);
		Label castLabel = createLabel("Cast:");
		castLabel.setTextFill(Color.LIGHTGRAY);
		addFavoritesListener();

		titleField = createTextArea("");
		releaseField = createTextArea("");
		durationField = createTextArea("");
		castField = createTextArea("");

		randomBtn = createRandomBtn();

		grid.add(titleLabel, 0, 0);
		grid.add(titleField, 1, 0);
		grid.add(releaseLabel, 0, 1);
		grid.add(releaseField, 1, 1);
		grid.add(durationLabel, 0, 2);
		grid.add(durationField, 1, 2);
		grid.add(castLabel, 0, 3);
		grid.add(castField, 1, 3);
		grid.add(favoritesBtn, 0, 4);
		grid.add(favoritesLabel, 1, 4);
		grid.add(randomBtn, 0, 5);

		return grid;
	}

	/**
	 * Creates formatted favorites button with image.
	 * @return the Button created.
	 */
	private Button createFavoritesBtn() {
		Image image = new Image(favImgPath);
		ImageView view = new ImageView(image);
		Button favBtn = new Button();
		favBtn.setGraphic(view);
		favBtn.setAlignment(Pos.CENTER);
		return favBtn;
	}

	/**
	 * Creates a formatted label.
	 * @param text the text of label
	 * @return the created label.
	 */
	private Label createLabel(final String text) {
		Label label = new Label(text);
		label.setAlignment(Pos.CENTER_LEFT);
		label.setFont(new Font("System", STDFONTSIZE));
		return label;
	}

	/**
	 * Creates a text area for each movie info. field.
	 * @param text the content of the TextArea.
	 * @return the created TextArea.
	 */
	private TextArea createTextArea(final String text) {
		TextArea field = new TextArea(text);
		field.setPrefSize(150, 25);
		field.setMaxWidth(200.0);
		field.setMinWidth(100.0);
		Font font = new Font("System", STDFONTSIZE);
		field.setFont(font);
		field.setEditable(false);
		return field;
	}

	/**
	 * Sets up image container constraints and dummy image.
	 */
	private void setUpPosterImg() {
		Image posterImg = new Image("file:lib/placeholder.png");
		imgContainer = new ImageView(posterImg);
		imgContainer.setFitHeight(250);
		imgContainer.setFitWidth(200);
	}

	/**
	 * Adds layout to a scene, which is assigned to stage.
	 * @param stage the stage to set scene for.
	 */
	public final void addToStage(final Stage stage) {
		Scene scene = new Scene(topLayout);
		stage.setScene(scene);
	}

	/**
	 * Get the current top-level layout.
	 * @return the VBox layout.
	 */
	public final VBox getLayout() {
		return topLayout;
	}

	/**
	 * Adds listener to handle watch list action for favoritesBtn.
	 */
	private void addFavoritesListener() {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				if (currentMovie != null) {
					watchList.addToList(currentMovie);
					Alert message = new Alert(AlertType.CONFIRMATION);
					message.setContentText("Movie Added.");
					message.show();
				}
				setRandomMovie();
			}
		};
		favoritesBtn.setOnAction(handler);
	}

	/**
	 * Sets the poster to a new random movie.
	 */
	private void setRandomMovie() {
		try {
			currentMovie = manager.getRandomMovie();
			String posterUrl = baseImgUrl
					+ currentMovie.getPosterPath();

			Image posterImg = new Image(posterUrl);
			imgContainer.setImage(posterImg);

			titleField.setText(currentMovie.getTitle());
			releaseField.setText(currentMovie.getReleaseDate());
			durationField.setText(
					Integer.toString(currentMovie.getRuntime()));
			String cast = currentMovie.getCast().toString();
			castField.setText(cast);
		} catch (Exception e) {
			showErrorAlert(e.getMessage());
		}
	}

	/**
	 * Shows a message dialog for error.
	 * @param message the dialog message.
	 */
	private void showErrorAlert(final String message) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setContentText(message);
		errAlert.setTitle("Poster Panel Error");
		errAlert.show();
	}

	/**
	 * Creates a random button with EventHandler when clicked.
	 * @return Button created.
	 */
	private Button createRandomBtn() {
		Button randBtn = new Button("Random");
		randBtn.setPadding(new Insets(PADDINGSIZE));
		randBtn.setAlignment(Pos.CENTER);

		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				setRandomMovie();
			}
		};
		randBtn.setOnAction(handler);
		return randBtn;
	}

}
