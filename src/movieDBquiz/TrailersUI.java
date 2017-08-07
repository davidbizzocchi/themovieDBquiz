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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TrailersUI extends Scene{
	final String baseUrl = "https://www.youtube.com/embed/";
	final String placeHolderImg = "file:lib/placeholder.png";
	
	private WebView viewer;
	private static VBox layout;
	private static DbManager manager = new DbManager();
	private Button exitBtn;
	private Button shuffleBtn;
	private List<String> trailerURLs;
	private MovieDb movie;
	
	ImageView trailerView1;
	ImageView trailerView2;
	ImageView trailerView3;
	ImageView trailerView4;
	
	public TrailersUI(MovieDb movie) {
		super(layout = new VBox());
		this.movie = movie;
		trailerURLs = new ArrayList<String>();
		setUpLayout();
		addComponents();
	}
	
	public TrailersUI(){
		this(manager.getRandomPlayingMovie());
	}
	
	private void setUpLayout(){
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill bg = new BackgroundFill(pattern, null, null);
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20));
		layout.setSpacing(30);
		layout.setBackground(new Background(bg));
	}
	
	private void addComponents(){
		addTitle();
		//addSelector();
		addViewer();
		addToolBar();
	}
	
	private void addTitle(){
		Label title = new Label("Featured Trailers");
		title.setAlignment(Pos.TOP_CENTER);
		title.setMaxSize(250, 30);
		title.setFont(new Font("System", 18));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setTextFill(Color.GHOSTWHITE);
		
		layout.getChildren().add(title);
	}
	
/*	private void addSelector(){
		selectorPane = new HBox();
		selectorPane.setAlignment(Pos.CENTER);
		selectorPane.setSpacing(25);
		
		trailerView1 = createImageView();
		trailerView2 = createImageView();
		trailerView3 = createImageView();
		trailerView4 = createImageView();
		
		addToSelectorPane(trailerView1);
		addToSelectorPane(trailerView2);
		addToSelectorPane(trailerView3);
		addToSelectorPane(trailerView4);
		
		layout.getChildren().add(selectorPane);
	}*/
	
	private void addViewer(){
		
		if(movie.getVideos() == null){
			Image centerImg = new Image(placeHolderImg);
			ImageView view = new ImageView(centerImg);
			layout.getChildren().add(view);
		}else{
			Video video = movie.getVideos().get(0);
			viewer = new WebView();
			viewer.setPrefSize(560, 315);
			viewer.getEngine().load(baseUrl + video.getKey());
			layout.getChildren().add(viewer);
		}
	}
	
	private void addToolBar(){
		exitBtn = new Button("Exit");
		shuffleBtn = new Button("Shuffle");
		addShuffleEventHandler();
		
		ToolBar toolbar = new ToolBar(exitBtn, shuffleBtn);
		toolbar.setPadding(new Insets(30));
		
		toolbar.setPrefSize(400, 75);
		toolbar.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		
		layout.getChildren().add(toolbar);
	}
	
	
/*	private ImageView createImageView() {
		ImageView view = new ImageView();
		view.setFitHeight(150);
		view.setFitWidth(100);
		
		DropShadow shadow = new DropShadow(20, Color.BLACK);
		view.setEffect(shadow);
		
		return view;
	}
	
	private void addToSelectorPane(ImageView view){
//		BorderPane border = new BorderPane(view);
//		border.getStyleClass().add("image-view-wrapper");
		
		selectorPane.getChildren().add(view);
	}*/
	
	public void setTrailerImages(List<Image> imgList){
		if(imgList.size() >= 4){
			trailerView1.setImage(imgList.get(0));
			trailerView2.setImage(imgList.get(1));
			trailerView3.setImage(imgList.get(2));
			trailerView4.setImage(imgList.get(3));
		}
	}
	
	public void setTrailerURLs(List<String> urlList){
		if(urlList.size() >= 4){
			trailerURLs.set(0, urlList.get(0));
			trailerURLs.set(1, urlList.get(1));
			trailerURLs.set(2, urlList.get(2));
			trailerURLs.set(3, urlList.get(3));
		}
	}
	
	public void setTrailer(String urlStr){
		try{
			viewer.getEngine().load(urlStr);
		}
		catch(Exception e){
			//Handle error
		}
	}
	
//	public void addEventHandler(EventHandler<MouseEvent> event){
//		trailerView1.addEventHandler(MouseEvent.MOUSE_CLICKED, event);
//	}

	public void addExitEventHandler(EventHandler<ActionEvent> handler){
		exitBtn.setOnAction(handler);
	}
	
	private void addShuffleEventHandler(){
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DbManager manager = new DbManager();
				MovieDb movie = manager.getRandomPlayingMovie();
				Video video = movie.getVideos().get(0);
				setTrailer(baseUrl + video.getKey());
			}
		};
		shuffleBtn.setOnAction(handler);
	}
	
	
}
