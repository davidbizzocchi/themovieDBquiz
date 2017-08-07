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

public class HighLoGameUI extends Scene{
	private static HBox windowLayout;
	private VBox gameLayout;
	private ImageView faceCard;
	private ImageView downCard;
	private TextField faceCardTxt;
	private TextField downCardTxt;
	private TextField scoreTxt;
	private HighLowMovieCardGame game;
	private Scene scene;
	private Button menuBtn;
	private Image cardBackImg = new Image("file:lib/cardBack.jpg");
	
	public HighLoGameUI() {
		super(windowLayout = new HBox());
		game = new HighLowMovieCardGame();
		setUpLayout();
		addComponents();
	}
	
	private void setUpLayout(){
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill bg = new BackgroundFill(pattern, null, null);
		
		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setPadding(new Insets(15));
		windowLayout.setMaxSize(800, 800);
		
		gameLayout = new VBox();
		gameLayout.setAlignment(Pos.CENTER);
		gameLayout.setPadding(new Insets(10));
		
		gameLayout.setBackground(new Background(bg));
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
		title.setFont(new Font("Commons", 22));
		title.setMaxSize(300, 30);
		title.setTextFill(Color.GHOSTWHITE);
		
		gameLayout.getChildren().add(title);
	}
	
	private void setUpGameLayout(){
		GridPane gameGrid = new GridPane();
		gameGrid.setPadding(new Insets(15));
		gameGrid.setAlignment(Pos.CENTER);
		gameGrid.setHgap(10);
		gameGrid.setVgap(10);
		
		faceCard = new ImageView();
		faceCard.setFitWidth(150);
		faceCard.setFitHeight(250);
		faceCardTxt = new TextField("$???");
		faceCardTxt.setMaxSize(150, 25);
		faceCardTxt.setAlignment(Pos.CENTER);
		setFaceCardData();
		
		downCard = new ImageView();
		downCard.setFitWidth(150);
		downCard.setFitHeight(250);
		downCardTxt = new TextField("");
		downCardTxt.setAlignment(Pos.CENTER);
		downCardTxt.setMaxSize(150, 25);
		downCardTxt.setEditable(false);
		hideFaceDownCard();
		
		Label faceCardLabel = new Label("Budget:");
		faceCardLabel.setTextFill(Color.GHOSTWHITE);
		faceCardLabel.setFont(new Font("System", 16));
		faceCardLabel.setMaxSize(120, 25);
		
		Label downCardLabel = new Label("Budget:");
		downCardLabel.setTextFill(Color.GHOSTWHITE);
		downCardLabel.setFont(new Font("System", 16));
		downCardLabel.setMaxSize(120, 25);
		
		gameGrid.add(faceCard, 1, 0);
		gameGrid.add(downCard, 3, 0);
		gameGrid.add(faceCardLabel, 0, 1);
		gameGrid.add(downCardLabel, 2, 1);
		gameGrid.add(faceCardTxt, 1, 1);
		gameGrid.add(downCardTxt, 3, 1);
		
		gameLayout.getChildren().add(gameGrid);
	}
	
	private void setFaceCardData(){
		String posterPath = game.getMovieWithInfo(game.getFaceUpCard().getAssociatedMovie()).getPosterPath();
		
		if(posterPath == null){
			faceCard.setImage(new Image("file:lib/placeholder.png"));
		} else {
			faceCard.setImage(new Image("http://image.tmdb.org/t/p/w500" + posterPath));
		}
		
		faceCardTxt.setText("$" + Long.toString
				(game.getMovieWithInfo(game.getFaceUpCard().getAssociatedMovie()).getBudget()));
		faceCardTxt.setEditable(false);
	}
	
	private void showFaceDownCardData(){
		String posterPath = game.getMovieWithInfo(game.getFaceDownCard().getAssociatedMovie()).getPosterPath();
		
		if(posterPath == null){
			downCard.setImage(new Image("file:lib/placeholder.png"));
		} else {
			downCard.setImage(new Image("http://image.tmdb.org/t/p/w500" + posterPath));
		}
		
		downCardTxt.setMaxSize(150, 25);
		downCardTxt.setText("$" + Long.toString
				(game.getMovieWithInfo(game.getFaceDownCard().getAssociatedMovie()).getBudget()));
		downCardTxt.setEditable(false);
	}
	
	private void hideFaceDownCard(){
		downCard.setImage(cardBackImg);
		downCardTxt.setText("$ ???");
	}
	
	private void setUpToolbar(){
		HBox toolBar = new HBox();
		toolBar.setPadding(new Insets(15));
		toolBar.setSpacing(20);
		toolBar.setAlignment(Pos.TOP_CENTER);
		
		Label scoreLabel = new Label("Score:");
		scoreLabel.setTextFill(Color.GHOSTWHITE);
		scoreLabel.setMaxSize(100, 30);
		scoreTxt = new TextField("0");
		scoreTxt.setMaxSize(60, 30);
		scoreTxt.setEditable(false);
		
		menuBtn = new Button("Menu");
		Button highBtn = new Button("Higher");
		Button lowBtn = new Button("Lower");
		Button nextBtn = new Button("Next");
		highBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        validateAnswer(true);
		        showFaceDownCardData();
		        scoreTxt.setText(Integer.toString(game.getScore()));
		        highBtn.setDisable(true);
		        lowBtn.setDisable(true);
		    }
		});
		lowBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        validateAnswer(false);
		        showFaceDownCardData();
		        scoreTxt.setText(Integer.toString(game.getScore()));
		        highBtn.setDisable(true);
		        lowBtn.setDisable(true);
		    }
		});
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        game.drawCards();
		        hideFaceDownCard();
		        setFaceCardData();
		        highBtn.setDisable(false);
		        lowBtn.setDisable(false);
		    }
		});
		
		toolBar.getChildren().addAll(menuBtn,
			scoreLabel, scoreTxt, highBtn, lowBtn, nextBtn);
		
		gameLayout.getChildren().addAll(toolBar);
	}
	
	private void validateAnswer(boolean isHigher){
		if(game.answer(isHigher)){
			showDialogue("Correct!");
		} else {
			showDialogue("Incorrect!");
		}
	}
	
	private void showDialogue(String info){
		Stage dialogStage = new Stage();
	    dialogStage.initModality(Modality.WINDOW_MODAL);
	    Button okBtn = new Button("Ok");
	    okBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        dialogStage.close();
		    }
		});
	    VBox vbox = new VBox(new Text(info), okBtn);
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(15));

	    dialogStage.setScene(new Scene(vbox));
	    dialogStage.show();
	}
	
	public void addToStage(Stage primaryStage){
		scene = new Scene(windowLayout);
		primaryStage.setScene(scene);
	}
	
	public HBox getLayout(){
		return windowLayout;
	}
	
	public void addEventHandler(EventHandler<ActionEvent> event){
		menuBtn.setOnAction(event);
	}
}
