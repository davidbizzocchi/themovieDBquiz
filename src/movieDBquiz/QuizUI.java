package movieDBquiz;

import java.util.List;
import javafx.event.ActionEvent;
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


public class QuizUI extends Scene{
	static HBox windowLayout = new HBox();
	VBox quizLayout;
	PosterUI poster;
	TextArea questionText;
	TextField scoreDisplay;
	TextField timerDisplay;
	TextArea optATextArea;
	TextArea optBTextArea;
	TextArea optCTextArea;
	TextArea optDTextArea;
	
	public QuizUI(Boolean showPoster) {
		super(windowLayout);
		setUpLayout();
		addComponents();
		if(showPoster){
			addPoster();
		}
	}
	
	private void setUpLayout(){
		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setPadding(new Insets(15));
		windowLayout.setMaxSize(800, 800);
		
		quizLayout = new VBox();
		quizLayout.setAlignment(Pos.CENTER);
		quizLayout.setPadding(new Insets(10));
		BackgroundFill color = new BackgroundFill(Color.BEIGE, null, null);
		quizLayout.setBackground(new Background(color));
		quizLayout.setMaxSize(800, 800);
		
		windowLayout.getChildren().add(quizLayout);
	}
	
	private GridPane createOptionsGrid(){
		ToggleGroup btnGroup = new ToggleGroup();
		
		RadioButton radioBtnA = new RadioButton();
		RadioButton radioBtnB = new RadioButton();
		RadioButton radioBtnC = new RadioButton();
		RadioButton radioBtnD = new RadioButton();
		
		radioBtnA.setText("A");
		radioBtnB.setText("B");
		radioBtnC.setText("C");
		radioBtnD.setText("D");
		
		radioBtnA.setToggleGroup(btnGroup);
		radioBtnB.setToggleGroup(btnGroup);
		radioBtnC.setToggleGroup(btnGroup);
		radioBtnD.setToggleGroup(btnGroup);
		
		optATextArea = createOptTextArea();
		optBTextArea = createOptTextArea();
		optCTextArea = createOptTextArea();
		optDTextArea = createOptTextArea();
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		
		grid.add(radioBtnA, 0, 0);
		grid.add(radioBtnB, 0, 1);
		grid.add(radioBtnC, 0, 2);
		grid.add(radioBtnD, 0, 3);
		
		grid.add(optATextArea, 1, 0);
		grid.add(optBTextArea, 1, 1);
		grid.add(optCTextArea, 1, 2);
		grid.add(optDTextArea, 1, 3);
		
		return grid;
	}
	
	private TextArea createOptTextArea(){
		TextArea text = new TextArea();
		text.setPrefSize(300, 60);
		text.setEditable(false);
		return text;
	}
	
	private HBox createToolBarBox(){
		HBox toolBar = new HBox();
		toolBar.setAlignment(Pos.TOP_CENTER);
		toolBar.setSpacing(300);
		
		Button menuBtn = new Button("End Quiz");
		menuBtn.setMaxSize(120, 40);
		Button submitBtn = new Button("Next");
		submitBtn.setMaxSize(120, 40);
		
		toolBar.getChildren().addAll(menuBtn, submitBtn);
		return toolBar;
	}
	
	private VBox createHeader(){
		VBox header = new VBox();
		header.setAlignment(Pos.CENTER);
		header.setSpacing(10);
		
		HBox titleBar = new HBox();
		titleBar.setAlignment(Pos.CENTER);
		titleBar.setSpacing(75);
		
		Label title = new Label("Quiz Game");
		title.setAlignment(Pos.TOP_CENTER);
		title.setFont(new Font("System", 20));
		title.setMinSize(100, 30);
		title.setPrefSize(120, 30);
		
		scoreDisplay = new TextField("Score: 0");
		scoreDisplay.setPrefSize(100, 30);
		scoreDisplay.setEditable(false);
		
		timerDisplay = new TextField("00:00");
		timerDisplay.setPrefSize(100, 30);
		timerDisplay.setEditable(false);
		
		titleBar.getChildren().addAll(scoreDisplay, title, timerDisplay);
		
		questionText = new TextArea();
		questionText.setMinSize(400, 100);
		questionText.setPrefSize(400, 120);
		questionText.setFont(new Font("System", 14));
		questionText.setEditable(false);
		
		header.getChildren().addAll(titleBar, questionText);
		return header;
	}
	
	private void addComponents(){
		VBox header = createHeader();
		GridPane options = createOptionsGrid();
		HBox toolBar = createToolBarBox();
		quizLayout.getChildren().addAll(header, options, toolBar);
	}
	
	public void addToStage(Stage stage){
		Scene quiz = new Scene(windowLayout);
		stage.setScene(quiz);
	}
	
	public HBox getLayout(){
		return windowLayout;
	}
	
	public void addPoster(){
		if(poster == null){
			poster = new PosterUI();
			windowLayout.getChildren().add(poster.getLayout());
		}
	}
	
	public void removePoster(){
		if(poster != null){
			windowLayout.getChildren().remove(poster.getLayout());
		}
	}
	
	public void setQuestionTxt(String question){
		questionText.setText(question);
	}
	
	public void populateOptions(List<String> options){
		if(options.get(0) != null){
			optATextArea.setText(options.get(0));
		}
		if(options.get(1) != null){
			optBTextArea.setText(options.get(1));
		}
		if(options.get(2) != null){
			optCTextArea.setText(options.get(2));
		}
		if(options.get(3) != null){
			optDTextArea.setText(options.get(3));
		}
	}
	
	
}