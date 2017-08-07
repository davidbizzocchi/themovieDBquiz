package movieDBquiz;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class QuizUI extends Scene{
	static HBox windowLayout = new HBox();
	private VBox quizLayout;
	private PosterUI poster;
	private TextArea questionText;
	private TextField scoreDisplay;
	private TextField timerDisplay;
	private Label quizTypeDisplay;
	private int numSeconds;
	private int numQuestions;
	private boolean isTimed;
	private boolean isSetNumQuestion;
	private TextArea optATextArea;
	private TextArea optBTextArea;
	private TextArea optCTextArea;
	private TextArea optDTextArea;
	private Button submitBtn;
	private ToggleGroup btnGroup;
	
	public QuizUI(Boolean showPoster) {
		super(windowLayout);
		setUpLayout();
		addComponents();
		if(showPoster){
			addPoster();
		}
	}
	
	private void setUpLayout(){
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill color = new BackgroundFill(pattern, null, null);
		
		windowLayout = new HBox();
		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setPadding(new Insets(15));
		windowLayout.setMaxSize(800, 800);
		windowLayout.setBackground(new Background(color));
		
		quizLayout = new VBox();
		quizLayout.setAlignment(Pos.CENTER);
		quizLayout.setPadding(new Insets(10));
		quizLayout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
		quizLayout.setMaxSize(800, 800);
		
		windowLayout.getChildren().add(quizLayout);
	}
	
	private GridPane createOptionsGrid(){
		btnGroup = new ToggleGroup();
		
		RadioButton radioBtnA = new RadioButton();
		RadioButton radioBtnB = new RadioButton();
		RadioButton radioBtnC = new RadioButton();
		RadioButton radioBtnD = new RadioButton();
		
		radioBtnA.setText("A");
		radioBtnA.setUserData(1);
		radioBtnA.setTextFill(Color.GHOSTWHITE);
		radioBtnB.setText("B");
		radioBtnB.setUserData(2);
		radioBtnB.setTextFill(Color.GHOSTWHITE);
		radioBtnC.setText("C");
		radioBtnC.setUserData(3);
		radioBtnC.setTextFill(Color.GHOSTWHITE);
		radioBtnD.setText("D");
		radioBtnD.setUserData(4);
		radioBtnD.setTextFill(Color.GHOSTWHITE);
		
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
		text.setPrefSize(330, 60);
		text.setEditable(false);
		return text;
	}
	
	private HBox createToolBarBox(){
		HBox toolBar = new HBox();
		toolBar.setAlignment(Pos.TOP_CENTER);
		toolBar.setSpacing(300);
		
		Button menuBtn = new Button("End Quiz");
		menuBtn.setMaxSize(120, 40);
		submitBtn = new Button("Next");
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
		
		HBox currentQuizInfo = new HBox();
		currentQuizInfo.setAlignment(Pos.CENTER_RIGHT);
		currentQuizInfo.setSpacing(0);
		
		Label title = new Label("Quiz Game");
		title.setAlignment(Pos.TOP_CENTER);
		title.setFont(new Font("Common", 22));
		title.setTextFill(Color.GHOSTWHITE);
		title.setMinSize(110, 30);
		title.setPrefSize(130, 30);
		
		scoreDisplay = new TextField("Score: 0");
		scoreDisplay.setPrefSize(100, 30);
		scoreDisplay.setEditable(false);
		
		timerDisplay = new TextField("");
		timerDisplay.setPrefSize(100, 30);
		timerDisplay.setEditable(false);
		
		quizTypeDisplay = new Label("");
		quizTypeDisplay.setPrefSize(100, 30);
		quizTypeDisplay.setTextFill(Color.GHOSTWHITE);
		
		currentQuizInfo.getChildren().addAll(quizTypeDisplay, timerDisplay);
		titleBar.getChildren().addAll(scoreDisplay, title, currentQuizInfo);
		
		questionText = new TextArea();
		questionText.setMinSize(400, 100);
		questionText.setPrefSize(400, 120);
		questionText.setFont(new Font("System", 14));
		questionText.setWrapText(true);
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
	
	public void setScore(int score){
		scoreDisplay.setText("Score: 0" + score);
	}
	
	public void addToStage(Stage stage){
		getQuizOptionsDialog();
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

	public void addNextButtonEventHandler(EventHandler<MouseEvent> event){
		submitBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event);
	}
	
	public int getAnswerButton(){
		if(btnGroup.getSelectedToggle() != null){
			return (int)btnGroup.getSelectedToggle().getUserData();
		}
		return 0;
	}
	
	private void getQuizOptionsDialog() {
		ButtonGroup group = new ButtonGroup();
		JRadioButton timedButton = new JRadioButton();
		timedButton.setText("Timed Quiz");
		JRadioButton numQuestionsButton = new JRadioButton();
		numQuestionsButton.setText("Number Questions Quiz");
		JSpinner minSpinner = new JSpinner(new SpinnerNumberModel(1,0,99,1));
		minSpinner.setEditor(new JSpinner.NumberEditor(minSpinner, "00"));
		JSpinner secSpinner = new JSpinner(new SpinnerNumberModel(0,0,99,1));
		secSpinner.setEditor(new JSpinner.NumberEditor(secSpinner, "00"));
		JSpinner questionSpinner = new JSpinner(new SpinnerNumberModel(10,0,99,1));
		questionSpinner.setEditor(new JSpinner.NumberEditor(questionSpinner, "00"));
		
		JPanel myPanel = new JPanel();
		myPanel.add(timedButton);
		myPanel.add(minSpinner);
		myPanel.add(new JLabel(":"));
		myPanel.add(secSpinner);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(numQuestionsButton);
		myPanel.add(questionSpinner);

		group.add(timedButton);
		group.add(numQuestionsButton);
		
		timedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				minSpinner.setEnabled(true);
				secSpinner.setEnabled(true);
				questionSpinner.setEnabled(false);
			}
		});
		numQuestionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				minSpinner.setEnabled(false);
				secSpinner.setEnabled(false);
				questionSpinner.setEnabled(true);
			}
		});
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Select Quiz Type",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			if(timedButton.isSelected()){
				numSeconds = (int) secSpinner.getValue();
				numSeconds += ((int) minSpinner.getValue()) * 60;
				isSetNumQuestion = false;
				isTimed = true;
			}
			else if(numQuestionsButton.isSelected()){
				numQuestions = (int) questionSpinner.getValue();
				isTimed = false;
				isSetNumQuestion = true;
			}
		}
	}
	
	public void showDialog(String info){
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

	/**
	 * @return the seconds desired to take quiz
	 */
	public int getNumSeconds() {
		return numSeconds;
	}

	/**
	 * @return the numQuestions
	 */
	public int getNumQuestions() {
		return numQuestions;
	}

	/**
	 * @return true if quiz is timed
	 */
	public boolean isTimed() {
		return isTimed;
	}
	
	/**
	 * @return true if quiz is based on a set number of questions
	 */
	public boolean isSetNumQuestion() {
		return isSetNumQuestion;
	}

	public void setQuizTypeLabel(String label){
		quizTypeDisplay.setText(label);
	}
	/**
	 * @param numSeconds the numSeconds to set
	 */
	public void setTimeRemaining(int secondsRemaining) {
		timerDisplay.setText(String.format("%02d", secondsRemaining / 60)
				+ ":" + String.format("%02d", secondsRemaining % 60));
	}
	
	/**
	 * @param numSeconds the numSeconds to set
	 */
	public void setQuestionsRemaining(int questionsRemaining) {
		timerDisplay.setText(String.format("%02d", questionsRemaining));
	}

	/**
	 * @param numQuestions the numQuestions to set
	 */
	public void setNumQuestions(int numQuestions) {
		this.numQuestions = numQuestions;
	}
}