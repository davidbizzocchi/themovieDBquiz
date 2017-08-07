package movieDBquiz;

import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.Video;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * A class to provide a trailer viewing window.
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class TrailersUI extends Scene {
	/** The number of trailer opts. at once.*/
	private static final int NUMOPTIONS = 4;

	/** The base URl to append key for an embedded trailer.*/
	private final String baseUrl = "https://www.youtube.com/embed/";
	/** The placeholder image to go in trailer selector options.*/
	private final String placeHolderImg = "file:lib/placeholder.png";
	/** The viewer for trailer video.*/
	private WebView viewer;
	/** the top level layout.*/
	private static VBox layout;
	/** A manager to provide tmdb api access methods.*/
	private static DbManager manager = new DbManager();
	/** A button to return to menu.*/
	private Button exitBtn;
	/** A button to get new movie.*/
	private Button shuffleBtn;
	/** Holds a list of urls for selector options.*/
	private List<String> trailerURLs;
	/** The current trailer movie.*/
	private MovieDb movie;
	/** Selector image container 1.*/
	private ImageView trailerView1;
	/** Selector image container 2.*/
	private ImageView trailerView2;
	/** Selector image container 3.*/
	private ImageView trailerView3;
	/** Selector image container 4.*/
	private ImageView trailerView4;

	/**
	 * Constructs window to show trailer of 'movie'.
	 * @param trailerMovie the movie to find trailer for.
	 */
	public TrailersUI(final MovieDb trailerMovie) {
		super(layout = new VBox());
		this.movie = trailerMovie;
		trailerURLs = new ArrayList<String>();
		setUpLayout();
		addComponents();
	}

	/**
	 * Constructs a trailer window with random movie.
	 */
	public TrailersUI() {
		this(manager.getRandomPlayingMovie());
	}

	/**
	 * Sets up layout appearance and constrainsts.
	 */
	private void setUpLayout() {
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill bg = new BackgroundFill(pattern, null, null);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20));
		layout.setSpacing(30);
		layout.setBackground(new Background(bg));
	}

	/**
	 * Adds UI components to top layout.
	 */
	private void addComponents() {
		addTitle();
		// addSelector();
		addViewer();
		addToolBar();
	}

	/**
	 * Adds title to layout.
	 */
	private void addTitle() {
		Label title = new Label("Featured Trailers");
		title.setAlignment(Pos.TOP_CENTER);
		title.setMaxSize(250, 30);
		title.setFont(new Font("System", 18));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setTextFill(Color.GHOSTWHITE);

		layout.getChildren().add(title);
	}

	/*
	 * private void addSelector(){ selectorPane = new HBox();
	 * selectorPane.setAlignment(Pos.CENTER); selectorPane.setSpacing(25);
	 *
	 * trailerView1 = createImageView(); trailerView2 = createImageView();
	 * trailerView3 = createImageView(); trailerView4 = createImageView();
	 *
	 * addToSelectorPane(trailerView1); addToSelectorPane(trailerView2);
	 * addToSelectorPane(trailerView3); addToSelectorPane(trailerView4);
	 *
	 * layout.getChildren().add(selectorPane); }
	 */

	/**
	 * Adds movie trailer viewer to layout.
	 */
	private void addViewer() {

		if (movie.getVideos() == null) {
			Image centerImg = new Image(placeHolderImg);
			ImageView view = new ImageView(centerImg);
			layout.getChildren().add(view);
		} else {
			Video video = movie.getVideos().get(0);
			viewer = new WebView();
			viewer.setPrefSize(560, 315);
			viewer.getEngine().load(baseUrl + video.getKey());
			layout.getChildren().add(viewer);
		}
	}

	/**
	 * Adds menu toolbar to layout.
	 */
	private void addToolBar() {
		exitBtn = new Button("Exit");
		shuffleBtn = new Button("Shuffle");
		addShuffleEventHandler();

		ToolBar toolbar = new ToolBar(exitBtn, shuffleBtn);
		toolbar.setPadding(new Insets(30));

		toolbar.setPrefSize(400, 75);
		toolbar.setBackground(new Background(
				new BackgroundFill(Color.TRANSPARENT, null, null)));

		layout.getChildren().add(toolbar);
	}

	/*
	 * private ImageView createImageView() { ImageView view = new ImageView();
	 * view.setFitHeight(150); view.setFitWidth(100);
	 *
	 * DropShadow shadow = new DropShadow(20, Color.BLACK);
	 * view.setEffect(shadow);
	 *
	 * return view; }
	 *
	 * private void addToSelectorPane(ImageView view){ // BorderPane border =
	 * new BorderPane(view); //
	 * border.getStyleClass().add("image-view-wrapper");
	 *
	 * selectorPane.getChildren().add(view); }
	 */

	/**
	 * Sets trailer selectors options images.
	 * @param imgList ordered list of movie posters for options.
	 */
	public void setTrailerImages(final List<Image> imgList) {
		if (imgList.size() >= NUMOPTIONS) {
			trailerView1.setImage(imgList.get(0));
			trailerView2.setImage(imgList.get(1));
			trailerView3.setImage(imgList.get(2));
			trailerView4.setImage(imgList.get(3));
		}
	}

	/**
	 * Sets the current Urls to youtube trailers for selector.
	 * @param urlList the list of urls
	 */
	public final void setTrailerURLs(final List<String> urlList) {
		if (urlList.size() >= NUMOPTIONS) {
			trailerURLs.set(0, urlList.get(0));
			trailerURLs.set(1, urlList.get(1));
			trailerURLs.set(2, urlList.get(2));
			trailerURLs.set(3, urlList.get(3));
		}
	}

	/**
	 * Sets the current trailer based on url.
	 * @param urlStr the url to load trailer with.
	 */
	public final void setTrailer(final String urlStr) {
		try {
			viewer.getEngine().load(urlStr);
		} catch (Exception e) {
			// Handle error
		}
	}

	// public void addEventHandler(EventHandler<MouseEvent> event){
	// trailerView1.addEventHandler(MouseEvent.MOUSE_CLICKED, event);
	// }

	/**
	 * Adds EventHandler for menu navigation.
	 * @param handler the handler to add to exitBtn.
	 */
	public final void addExitEventHandler(
		final EventHandler<ActionEvent> handler) {
		exitBtn.setOnAction(handler);
	}

	/**
	 * Adds EventHandler to get random movie when shuffleBtn clicked.
	 */
	private void addShuffleEventHandler() {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent event) {
				DbManager mgr = new DbManager();
				MovieDb mv = mgr.getRandomPlayingMovie();
				Video video = mv.getVideos().get(0);
				setTrailer(baseUrl + video.getKey());
			}
		};
		shuffleBtn.setOnAction(handler);
	}

}
