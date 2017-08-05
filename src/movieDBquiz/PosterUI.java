package movieDBquiz;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PosterUI {
	VBox topLayout;
	ImageView imgContainer;
	
	
	public PosterUI() {
		setUpPosterImg();
		setUpLayout();
	}
	
	private void setUpLayout(){
		topLayout = new VBox();
		topLayout.setAlignment(Pos.CENTER);
		GridPane grid = setUpGrid();
		
		VBox.setVgrow(imgContainer, Priority.SOMETIMES);
		VBox.setVgrow(grid, Priority.SOMETIMES);
		
		topLayout.setPadding(new Insets(15));
		topLayout.getChildren().addAll(imgContainer, grid);
	}
	
	private GridPane setUpGrid(){
		GridPane grid = new GridPane();
		grid.setMinWidth(200.00);
		grid.setMinHeight(300.00);
		
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Button favoritesBtn = createFavoritesBtn();
		Label favoritesLabel = createLabel("Add To Favorites");
		favoritesLabel.setTextFill(Color.LIGHTGRAY);
		Label titleLabel = createLabel("Title:");
		titleLabel.setTextFill(Color.LIGHTGRAY);
		Label releaseLabel = createLabel("Release Date:");
		releaseLabel.setTextFill(Color.LIGHTGRAY);
		Label durationLabel = createLabel("Duration(hrs):");
		durationLabel.setTextFill(Color.LIGHTGRAY);
		Label castLabel = createLabel("Cast:");
		castLabel.setTextFill(Color.LIGHTGRAY);
		
		TextArea titleField = createTextArea("");
		TextArea releaseField = createTextArea("");
		TextArea durationField = createTextArea("");
		TextArea castField = createTextArea("");
		
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
		
		return grid;
	}
	
	private Button createFavoritesBtn(){
		Image image = new Image("file:lib/star.png");
		ImageView view = new ImageView(image);
		Button favBtn = new Button();
		favBtn.setGraphic(view);
		favBtn.setAlignment(Pos.CENTER);
		return favBtn;
	}
	
	private Label createLabel(String text){
		Label label = new Label(text);
		label.setAlignment(Pos.CENTER_LEFT);
		label.setFont(new Font("System", 12));
		return label;
	}
	
	private TextArea createTextArea(String text){
		TextArea field = new TextArea(text);
		field.setPrefSize(150, 25);
		field.setMaxWidth(200.0);
		field.setMinWidth(100.0);
		Font font = new Font("System", 12);
		field.setFont(font);
		field.setEditable(false);
		return field;
	}
	
	private void setUpPosterImg(){
		Image posterImg = new Image("file:lib/placeholder.png");
		imgContainer = new ImageView(posterImg);
		imgContainer.setFitHeight(250);
		imgContainer.setFitWidth(200);
	}
	
	
	public void addToStage(Stage stage){
		Scene scene = new Scene(topLayout);
		stage.setScene(scene);
	}
	
	public VBox getLayout(){
		return topLayout;
	}
	
	
}
