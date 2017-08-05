package movieDBquiz;

import java.util.List;

import javax.swing.ButtonGroup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
import javafx.stage.Stage;


public class HighLoGame {
	HBox windowLayout;
	VBox gameLayout;
	ImageView faceCard;
	ImageView downCard;
	TextField faceCardTxt;
	TextField downCardTxt;
	Image questionImg = new Image("file:lib/question.jpg");
	
	public HighLoGame() {
		setUpLayout();
		addComponents();
	}
	
	private void setUpLayout(){
		windowLayout = new HBox();
		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setPadding(new Insets(15));
		windowLayout.setMaxSize(800, 800);
		
		gameLayout = new VBox();
		gameLayout.setAlignment(Pos.CENTER);
		gameLayout.setPadding(new Insets(10));
		BackgroundFill color = new BackgroundFill(Color.BEIGE, null, null);
		gameLayout.setBackground(new Background(color));
		gameLayout.setMaxSize(800, 800);
		
		windowLayout.getChildren().add(gameLayout);
	}
	
	private void addComponents(){
		setUpTitle();
		setUpGameLayout();
		setUpToolbar();
	}
	
	private void setUpTitle(){
		Label title = new Label("Hi-Low Guessing Game");
		title.setAlignment(Pos.TOP_CENTER);
		title.setFont(new Font("System", 18));
		title.setMaxSize(300, 30);
		
		gameLayout.getChildren().add(title);
	}
	
	private void setUpGameLayout(){
		GridPane gameGrid = new GridPane();
		gameGrid.setPadding(new Insets(15));
		gameGrid.setAlignment(Pos.CENTER);
		gameGrid.setHgap(10);
		gameGrid.setVgap(10);
		
		faceCard = new ImageView();
		faceCard.setImage(new Image("file:lib/placeholder.png"));
		faceCard.setFitWidth(150);
		faceCard.setFitHeight(250);
		
		downCard = new ImageView();
		downCard.setImage(questionImg);
		downCard.setFitWidth(150);
		downCard.setFitHeight(250);
		
		Label faceCardLabel = new Label("Budget:");
		faceCardLabel.setFont(new Font("System", 16));
		faceCardLabel.setMaxSize(120, 25);
		
		Label downCardLabel = new Label("Budget:");
		downCardLabel.setFont(new Font("System", 16));
		downCardLabel.setMaxSize(120, 25);
		
		faceCardTxt = new TextField("$0");
		faceCardTxt.setMaxSize(150, 25);
		faceCardTxt.setEditable(false);
		
		downCardTxt = new TextField("Budget: $0");
		downCardTxt.setMaxSize(150, 25);
		downCardTxt.setEditable(false);
		
		gameGrid.add(faceCard, 1, 0);
		gameGrid.add(downCard, 3, 0);
		gameGrid.add(faceCardLabel, 0, 1);
		gameGrid.add(downCardLabel, 2, 1);
		gameGrid.add(faceCardTxt, 1, 1);
		gameGrid.add(downCardTxt, 3, 1);
		
		gameLayout.getChildren().add(gameGrid);
	}
	
	private void setUpToolbar(){
		HBox toolBar = new HBox();
		toolBar.setPadding(new Insets(15));
		toolBar.setSpacing(20);
		toolBar.setAlignment(Pos.TOP_CENTER);
		
		Button exitBtn = new Button("Menu");
		Button highBtn = new Button("High");
		Button lowBtn = new Button("Low");
		
		Label scoreLabel = new Label("Score:");
		scoreLabel.setMaxSize(100, 30);
		TextField scoreTxt = new TextField("0");
		scoreTxt.setMaxSize(60, 30);
		scoreTxt.setEditable(false);
		
		toolBar.getChildren().addAll(exitBtn,
			scoreLabel, scoreTxt, highBtn, lowBtn);
		
		gameLayout.getChildren().addAll(toolBar);
	}
	
	public void addToStage(Stage primaryStage){
		Scene scene = new Scene(windowLayout);
		primaryStage.setScene(scene);
	}
	
	public HBox getLayout(){
		return windowLayout;
	}
	
	
}
