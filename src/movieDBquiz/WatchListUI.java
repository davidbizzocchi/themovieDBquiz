package movieDBquiz;

import java.util.ArrayList;
import java.util.List;

import com.sun.webkit.network.about.Handler;

import info.movito.themoviedbapi.model.MovieDb;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class WatchListUI extends Scene {
	static HBox windowLayout;
	VBox watchListLayout;
	PosterUI poster;
	Button menuBtn;
	Button removeBtn;
	Button randomBtn;
	ListView<String> watchListView;
	List<MovieDb> watchList;
	
	public WatchListUI() {
		super(windowLayout = new HBox());
		setUpLayout();
		addComponents();
	}
	
	private void setUpLayout(){
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill bg = new BackgroundFill(pattern, null, null);
		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setSpacing(35);
		windowLayout.setPadding(new Insets(10));
		
		watchListLayout = new VBox();
		windowLayout.setBackground(new Background(bg));
		windowLayout.getChildren().add(watchListLayout);
	}
	
	private void addComponents(){
		setUpTitle();
		setUpList();
		setUpToolBar();
		addPoster();
	}
	
	private void setUpTitle(){
		watchListLayout.setAlignment(Pos.CENTER);
		watchListLayout.setPadding(new Insets(15));
		
		Label title = new Label("Watch List");
		title.setTextAlignment(TextAlignment.CENTER);
		title.setPrefSize(300, 30);
		title.setFont(new Font("System", 16));
		
		watchListLayout.getChildren().add(title);
	}
	
	private void setUpList(){
		watchList = new ArrayList<MovieDb>();
		watchListView = new ListView<String>();
		watchListView.setPrefSize(350, 350);
		watchListView.setEditable(false);

		watchListLayout.getChildren().add(watchListView);
	}
	
	private void setUpToolBar(){
		menuBtn = new Button("Menu");
		removeBtn = new Button("Remove");
		randomBtn = new Button("Get Random");
		
		ToolBar toolBar = new ToolBar(
			menuBtn,
			removeBtn,
			randomBtn
		);
		
		toolBar.setPadding(new Insets(30));
		
		watchListLayout.getChildren().add(toolBar);
	}
	
	private void addPoster(){
		poster = new PosterUI();
		windowLayout.getChildren().add(poster.getLayout());
	}
	
	public void populateListView(List<MovieDb> movieList){
		watchList.clear();
		watchListView.getItems().remove(0, watchListView.getItems().size() - 1);
		for(MovieDb movie : movieList){
			watchList.add(movie);
			watchListView.getItems().add(movie.getTitle());
		}
	}
	
	public void addMenuBtnHandler(EventHandler<ActionEvent> handler){
		menuBtn.setOnAction(handler);
	}
	
	public void addRemoveBtnHandler(EventHandler<ActionEvent> handler){
		removeBtn.setOnAction(handler);
	}
	
	public void addRandomBtnHandler(EventHandler<ActionEvent> handler){
		randomBtn.setOnAction(handler);
	}
	
	public int getSelectedIndex(){
		return watchListView.getSelectionModel().getSelectedIndex();
	}
	
	public void removeSelected(int index){
		watchList.remove(index);
		watchListView.getItems().remove(index);
	}
	
	public MovieDb getMovie(int index){
		return watchList.get(index);
	}
	
}
