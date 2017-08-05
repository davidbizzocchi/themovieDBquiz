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

public class MainMenuUI {
//	DbManager manager;
	BorderPane root;
	
	
	public MainMenuUI() {
//		manager = new DbManager();
		createQuizWindow();
	}
	
	public void createQuizWindow(){	
		root = new BorderPane();
		setWindowStyle();
		setUpTop();
		setUpLeft();
		setUpBottom();
		setUpRight();
		setUpCenter();
	}
	
	private void setWindowStyle(){
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		root.setPrefSize(800, 600);    
		root.setStyle("-fx-border-width: 2;" +
			"-fx-border-insets: 5;" +
			"-fx-border-radius: 5;");
		root.setPadding(new Insets(25, 25, 25, 25));
		root.setBackground(new Background(new BackgroundFill(pattern, null, null)));
	}
	
	public void addToStage(Stage stage){
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	private void setUpTop(){
		VBox box = new VBox(10);
		box.setCenterShape(true);
		box.setAlignment(Pos.CENTER);
		box.setStyle("-fx-border-insets: 5;");
		
		ToolBar toolbar = new ToolBar();
		toolbar.setCenterShape(true);
		toolbar.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
		VBox.setMargin(toolbar, new Insets(0, 0, 10, 0));
		
		Label text = new Label("Main Menu");
		text.setFont(new Font("Commons", 20));
		text.setTextFill(Color.WHITE);
		text.setAlignment(Pos.TOP_CENTER);
		
		HBox.setHgrow(text, Priority.ALWAYS);
		HBox.setHgrow(toolbar, Priority.ALWAYS);
		text.setMaxWidth(400.0);
		box.getChildren().addAll(text, toolbar);		
		
		root.setTop(box);
	}
	
	private void setUpLeft(){
		VBox box = new VBox(10);
		box.setCenterShape(true);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(35.0);
		box.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		
		box.setPadding(new Insets(15));
		
		Button quizButton = new Button("Quiz");
		quizButton.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(quizButton);
		Button accountButton = new Button("View Account");
		accountButton.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(accountButton);
		Button movieListButton = new Button("Movie List");
		movieListButton.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(movieListButton);
		Button hiLoGameButton = new Button("High-Low Game");
		hiLoGameButton.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(hiLoGameButton);
		Button trailersButton = new Button("Trailers");
		trailersButton.setStyle("-fx-font: 15 arial; -fx-base: #7FFFD4;");
		setUpMenuButton(trailersButton);
		
		VBox.setVgrow(quizButton, Priority.SOMETIMES);
		VBox.setVgrow(accountButton, Priority.SOMETIMES);
		VBox.setVgrow(movieListButton, Priority.SOMETIMES);
		VBox.setVgrow(hiLoGameButton, Priority.SOMETIMES);
		VBox.setVgrow(trailersButton, Priority.SOMETIMES);
		
		box.getChildren().addAll(quizButton, hiLoGameButton, trailersButton, movieListButton, accountButton);
		
		root.setLeft(box);
	}
	
	private void setUpMenuButton(Button button){
		Font font = new Font("System", 16.0);
		button.setAlignment(Pos.CENTER);
		button.setMaxWidth(150.0);
		button.setMaxHeight(50.0);
		button.setFont(font);
		button.setMinWidth(80.0);
	}
	
	private void setUpBottom(){
		HBox box = new HBox();
		box.setCenterShape(true);
		box.setAlignment(Pos.CENTER_RIGHT);
		box.setPadding(new Insets(15));
		
		Button exit = new Button("Exit");
		setUpMenuButton(exit);
		
		TextField status = new TextField();
		status.setText("Select an option from the menu.");		
		status.setEditable(false);
		status.setAlignment(Pos.TOP_CENTER);
		
		Font statusFont = new Font("System", 16.0);
		status.setFont(statusFont);
		status.setStyle("-fx-text-inner-color: red;");
		
		HBox.setHgrow(status, Priority.SOMETIMES);
		HBox.setHgrow(exit, Priority.SOMETIMES);
		
		box.getChildren().addAll(status, exit);
		
		root.setBottom(box);
	}
	
	private void setUpRight(){
		PosterUI poster = new PosterUI();
		VBox layout = poster.getLayout();
		layout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		root.setRight(layout);
	}
	
	private void setUpCenter(){
		Image centerImg = new Image("file:lib/projector.png");
		ImageView view = new ImageView(centerImg);
		view.setStyle("-fx-border-insets: 30;");
		BorderPane.setAlignment(view, Pos.CENTER);
		BorderPane.setMargin(view, new Insets(25, 80, 25, 80));
		root.setCenter(view);
	}
	
	
}