package movieDBquiz;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * A class to provide a display for tmdb watchlist.
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class WatchListUI extends Scene {
	/** Size of component spacing.*/
	private static final int SPACINGSIZE = 35;
	/** Size of component padding.*/
	private static final int PADDINGSIZE = 10;
	/** Size of component padding.*/
	private static final int LISTVIEWDIM = 350;

	/** Top level layout.*/
	private static HBox windowLayout;
	/** The layout for the watch list.*/
	private VBox watchListLayout;
	/** Reference to an instance of a posterUI.*/
	private PosterUI poster;
	/** Button to return to menu.*/
	private Button menuBtn;
	/** Button to remove an item from watchlist.*/
	private Button removeBtn;
	/** Button to get random poster movie.*/
	private Button randomBtn;
	/** List to track items to display for watchListView.*/
	private ListView<String> watchListView;
	/** list of MovieDbs in current tmdb watchlist.*/
	private List<MovieDb> watchList;

	/**
	 * Constructs WatchListUi components and layout.
	 */
	public WatchListUI() {
		super(windowLayout = new HBox());
		setUpLayout();
		addComponents();
	}

	/**
	 * Sets up top layout appearance/constraints.
	 */
	private void setUpLayout() {
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill bg = new BackgroundFill(pattern, null, null);
		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setSpacing(SPACINGSIZE);
		windowLayout.setPadding(new Insets(PADDINGSIZE));

		watchListLayout = new VBox();
		windowLayout.setBackground(new Background(bg));
		windowLayout.getChildren().add(watchListLayout);
	}

	/**
	 * Adds all UI components layers.
	 */
	private void addComponents() {
		setUpTitle();
		setUpList();
		setUpToolBar();
		addPoster();
	}

	/**
	 * Adds Title Label to layout.
	 */
	private void setUpTitle() {
		watchListLayout.setAlignment(Pos.CENTER);
		watchListLayout.setPadding(new Insets(PADDINGSIZE));

		Label title = new Label("Watch List");
		title.setTextAlignment(TextAlignment.CENTER);
		title.setPrefSize(300, 30);
		title.setFont(new Font("System", 16));

		watchListLayout.getChildren().add(title);
	}

	/**
	 * Sets up list view with watch list items.
	 */
	private void setUpList() {
		watchList = new ArrayList<MovieDb>();
		watchListView = new ListView<String>();
		watchListView.setPrefSize(LISTVIEWDIM, LISTVIEWDIM);
		watchListView.setEditable(false);

		watchListLayout.getChildren().add(watchListView);
	}

	/**
	 * Adds tool bar to top layout with buttons.
	 */
	private void setUpToolBar() {
		menuBtn = new Button("Menu");
		removeBtn = new Button("Remove");
		randomBtn = new Button("Get Random");

		ToolBar toolBar = new ToolBar(menuBtn, removeBtn, randomBtn);

		toolBar.setPadding(new Insets(PADDINGSIZE * 3));

		watchListLayout.getChildren().add(toolBar);
	}

	/**
	 * Adds posterUI as sidebar.
	 */
	private void addPoster() {
		poster = new PosterUI();
		windowLayout.getChildren().add(poster.getLayout());
	}

	/**
	 * Resets list view to items contained in parameter.
	 * @param movieList the list of movies to display.
	 */
	public final void populateListView(final List<MovieDb> movieList) {
		watchList.clear();
		watchListView.getItems().remove(0,
				watchListView.getItems().size() - 1);
		for (MovieDb movie : movieList) {
			watchList.add(movie);
			watchListView.getItems().add(movie.getTitle());
		}
	}

	/**
	 * Adds an external EventHandler to the menuBtn.
	 * @param handler the handler to be added.
	 */
	public final void addMenuBtnHandler(
		final EventHandler<ActionEvent> handler) {
		menuBtn.setOnAction(handler);
	}

	/**
	 * Adds an external EventHandler to the removeBtn.
	 * @param handler the handler to be added.
	 */
	public final void addRemoveBtnHandler(
		final EventHandler<ActionEvent> handler) {
		removeBtn.setOnAction(handler);
	}

	/**
	 * Adds an external EventHandler to the randomBtn.
	 * @param handler the handler to be added.
	 */
	public final void addRandomBtnHandler(
		final EventHandler<ActionEvent> handler) {
		randomBtn.setOnAction(handler);
	}

	/**
	 * Gets index of item selected in listview.
	 * @return the selected index.
	 */
	public final int getSelectedIndex() {
		return watchListView.getSelectionModel().getSelectedIndex();
	}

	/**
	 * Removes the listview item based on index.
	 * @param index the index of item to be removed.
	 */
	public final void removeSelected(final int index) {
		watchList.remove(index);
		watchListView.getItems().remove(index);
	}

	/**
	 * Gets the movie at the specified index of tmdb watchlist.
	 * @param index the index of movie in list.
	 * @return the MovieDb of movie at index.
	 */
	public final MovieDb getMovie(final int index) {
		return watchList.get(index);
	}

}
