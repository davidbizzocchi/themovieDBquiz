package movieDBquiz;

import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.Video;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TrailersUI extends Scene{
	WebView viewer;
	static VBox layout = new VBox();
	HBox selectorPane;
	Button exitBtn;
	Button shuffleBtn;
	List<String> trailerURLs;
	MovieDb movie;
	
	ImageView trailerView1;
	ImageView trailerView2;
	ImageView trailerView3;
	ImageView trailerView4;
	
	public TrailersUI(MovieDb movie) {
		super(layout);
		this.movie = movie;
		trailerURLs = new ArrayList<String>();
		setUpLayout();
		addComponents();
	}
	
	private void setUpLayout(){
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(20));
		layout.setSpacing(30);
	}
	
	private void addComponents(){
		addTitle();
		addSelector();
		addViewer();
		addToolBar();
	}
	
	private void addTitle(){
		Label title = new Label("Featured Trailers");
		title.setAlignment(Pos.TOP_CENTER);
		title.setMaxSize(250, 30);
		title.setFont(new Font("System", 18));
		title.setTextAlignment(TextAlignment.CENTER);
		
		layout.getChildren().add(title);
	}
	
	private void addSelector(){
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
	}
	
	private void addViewer(){
		
		if(movie.getVideos().isEmpty()){
			Image centerImg = new Image("file:lib/placeholder.png");
			ImageView view = new ImageView(centerImg);
			layout.getChildren().add(view);
		}else{
			Video video = movie.getVideos().get(0);
			viewer = new WebView();
			viewer.setPrefSize(560, 315);
			viewer.getEngine().load("https://www.youtube.com/embed/" + video.getKey());
			layout.getChildren().add(viewer);
		}
	}
	
	private void addToolBar(){
		exitBtn = new Button("Exit");
		shuffleBtn = new Button("Shuffle");
		
		ToolBar toolbar = new ToolBar(exitBtn, shuffleBtn);
		toolbar.setPadding(new Insets(30));
		
		toolbar.setPrefSize(400, 75);
		
		layout.getChildren().add(toolbar);
	}
	
	
	private ImageView createImageView() {
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
	}
	
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
	
	public void addEventHandler(EventHandler<MouseEvent> event){
		trailerView1.addEventHandler(MouseEvent.MOUSE_CLICKED, event);
	}
}
