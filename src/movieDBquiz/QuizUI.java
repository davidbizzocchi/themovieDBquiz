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
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A class to provide the graphic content for a quiz.
 * 
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class QuizUI extends Scene {
	/**
	 * Constant padding size.
	 */
	private static final int PADDINGSIZE = 15;
	/**
	 * Constant spacing size.
	 */
	private static final int SPACINGSIZE = 10;
	/**
	 * Constant window dim. size.
	 */
	private static final int WINDOWSIZE = 800;
	/**
	 * Toolbar elem. spacing.
	 */
	private static final int TOOLBARSPACING = 300;
	/**
	 * Max button width.
	 */
	private static final int MAXBTNWIDTH = 120;
	/**
	 * Max button height.
	 */
	private static final int MAXBTNHEIGHT = 40;
	/**
	 * Pref. Display Width.
	 */
	private static final int DISPLAYWIDTH = 100;
	/**
	 * Pref. Display Height.
	 */
	private static final int DISPLAYHEIGHT = 40;


	/**
	 * top level layout.
	 */
	private static HBox windowLayout;

	/**
	 * quiz game layout.
	 */
	private VBox quizLayout;

	/**
	 * reference to poster for sidebar.
	 */
	private PosterUI poster;

	/**
	 * the question description TextArea.
	 */
	private TextArea questionText;

	/**
	 * area to display current score.
	 */
	private TextField scoreDisplay;

	/**
	 * Field to display rem. time.
	 */
	private TextField timerDisplay;

	/**
	 * Field to display timed/numbered quiz.
	 */
	private Label quizTypeDisplay;

	/**
	 * Field to hold secs rem.
	 */
	private int numSeconds;

	/**
	 * Field to hold questions rem.
	 */
	private int numQuestions;

	/**
	 * field to hold if quiz timed/not.
	 */
	private boolean isTimed;

	/**
	 * Indicates whether quiz is continuous or limited.
	 */
	private boolean isSetNumQuestion;

	/**
	 * Choice 1 txt fields.
	 */
	private TextArea optATextArea;

	/**
	 * Choice 2 txt fields.
	 */
	private TextArea optBTextArea;

	/**
	 * Choice 3 txt fields.
	 */
	private TextArea optCTextArea;

	/**
	 * Choice 4 txt fields.
	 */
	private TextArea optDTextArea;

	/**
	 * Submit button.
	 */
	private Button submitBtn;

	/**
	 * Main menu button.
	 */
	private Button menuBtn;

	/**
	 * Group for radio buttons.
	 */
	private ToggleGroup btnGroup;

	/**
	 * Constructs UI layout and adds components.
	 * @param showPoster indicates whether movie poster sidebar shown.
	 */
	public QuizUI(final Boolean showPoster) {
		super(windowLayout = new HBox());
		setUpLayout();
		addComponents();
		if (showPoster) {
			addPoster();
		}
	}

	/**
	 * Sets up top-level layout.
	 */
	private void setUpLayout() {
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill color = new BackgroundFill(pattern, null, null);

		windowLayout = new HBox();
		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setPadding(new Insets(PADDINGSIZE));
		windowLayout.setMaxSize(WINDOWSIZE, WINDOWSIZE);
		windowLayout.setBackground(new Background(color));

		quizLayout = new VBox();
		quizLayout.setAlignment(Pos.CENTER);
		quizLayout.setPadding(new Insets(PADDINGSIZE));
		quizLayout.setBackground(new Background(
				new BackgroundFill(Color.TRANSPARENT, null, null)));
		quizLayout.setMaxSize(WINDOWSIZE, WINDOWSIZE);

		windowLayout.getChildren().add(quizLayout);
	}

	/**
	 * Creates layout for quiz answer options.
	 * @return a GridPane with components added.
	 */
	private GridPane createOptionsGrid() {
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
		grid.setPadding(new Insets(PADDINGSIZE));

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

	/**
	 * Creates a generic answer option TextArea.
	 * @return the TextArea created.
	 */
	private TextArea createOptTextArea() {
		TextArea text = new TextArea();
		text.setPrefSize(330, 60);
		text.setEditable(false);
		return text;
	}

	/**
	 * Creates toolbar layout.
	 * @return the HBox layout created.
	 */
	private HBox createToolBarBox() {
		HBox toolBar = new HBox();
		toolBar.setAlignment(Pos.TOP_CENTER);
		toolBar.setSpacing(TOOLBARSPACING);

		menuBtn = new Button("End Quiz");
		menuBtn.setMaxSize(MAXBTNWIDTH, MAXBTNHEIGHT);
		submitBtn = new Button("Next");
		submitBtn.setMaxSize(MAXBTNWIDTH, MAXBTNHEIGHT);

		toolBar.getChildren().addAll(menuBtn, submitBtn);
		return toolBar;
	}

	/**
	 * Creates the header layout.
	 * @return VBox layout created with title and score/time displays.
	 */
	private VBox createHeader() {
		VBox header = new VBox();
		header.setAlignment(Pos.CENTER);
		header.setSpacing(SPACINGSIZE);

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
		title.setMinSize(MAXBTNWIDTH, DISPLAYHEIGHT);
		title.setPrefSize(MAXBTNWIDTH, DISPLAYHEIGHT);

		scoreDisplay = new TextField("Score: 0");
		scoreDisplay.setPrefSize(DISPLAYWIDTH, DISPLAYHEIGHT);
		scoreDisplay.setEditable(false);

		timerDisplay = new TextField("");
		timerDisplay.setPrefSize(DISPLAYWIDTH, DISPLAYHEIGHT);
		timerDisplay.setEditable(false);

		quizTypeDisplay = new Label("");
		quizTypeDisplay.setPrefSize(DISPLAYWIDTH, DISPLAYHEIGHT);
		quizTypeDisplay.setTextFill(Color.GHOSTWHITE);

		currentQuizInfo.getChildren().addAll(quizTypeDisplay,
				timerDisplay);
		titleBar.getChildren().addAll(scoreDisplay, title,
				currentQuizInfo);

		questionText = new TextArea();
		questionText.setMinSize(400, 100);
		questionText.setPrefSize(400, 120);
		questionText.setFont(new Font("System", 14));
		questionText.setWrapText(true);
		questionText.setEditable(false);

		header.getChildren().addAll(titleBar, questionText);
		return header;
	}

	/**
	 * Adds all ui components.
	 */
	private void addComponents() {
		VBox header = createHeader();
		GridPane options = createOptionsGrid();
		HBox toolBar = createToolBarBox();
		quizLayout.getChildren().addAll(header, options, toolBar);
	}

	/**
	 * Updates the scoreDisplay to 'score'.
	 * @param score the new score value.
	 */
	public final void setScore(final int score) {
		scoreDisplay.setText("Score: 0" + score);
	}

	/**
	 * Adds the top-level layout to a scene, which is added to stage.
	 * @param stage the stage which the QuizUI scene is set to.
	 */
	public final void addToStage(final Stage stage) {
		Scene quiz = new Scene(windowLayout);
		stage.setScene(quiz);
	}

	/**
	 * Gets the top-level layout.
	 * @return the HBox layout with all components.
	 */
	public final HBox getLayout() {
		return windowLayout;
	}

	/**
	 * Constructs a posterUi and adds to top layout.
	 */
	public final void addPoster() {
		if (poster == null) {
			poster = new PosterUI();
			windowLayout.getChildren().add(poster.getLayout());
		}
	}

	/**
	 * Removes the posterUI from layout.
	 */
	public final void removePoster() {
		if (poster != null) {
			windowLayout.getChildren().remove(poster.getLayout());
		}
	}

	/**
	 * Changes text of question.
	 * @param question the new text.
	 */
	public final void setQuestionTxt(final String question) {
		questionText.setText(question);
	}

	/**
	 * Populates options textareas with possibleanswers.
	 * @param options the possible answers to populate with.
	 */
	public final void populateOptions(final List<String> options) {
		if (options.get(0) != null) {
			optATextArea.setText(options.get(0));
		}
		if (options.get(1) != null) {
			optBTextArea.setText(options.get(1));
		}
		if (options.get(2) != null) {
			optCTextArea.setText(options.get(2));
		}
		if (options.get(3) != null) {
			optDTextArea.setText(options.get(3));
		}
	}

	/**
	 * Add Event handler for next button.
	 * @param event the new handler.
	 */
	public final void addNextButtonEventHandler(
			final EventHandler<MouseEvent> event) {
		submitBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, event);
	}

	/**
	 * Sets the EventHandler for exit/menu button.
	 * @param menuhandler the new handler.
	 */
	public final void addExitButtonEventHandler(
			final EventHandler<ActionEvent> menuhandler) {
		menuBtn.setOnAction(menuhandler);
	}

	/**
	 * Gets the index of button corresponding to answer.
	 * @return the correct index.
	 */
	public final int getAnswerButton() {
		if (btnGroup.getSelectedToggle() != null) {
			return (int) btnGroup.getSelectedToggle().getUserData();
		}
		return 0;
	}

	/**
	 * Gets a dialog which sets the quiz type and parameters.
	 */
	public final void getQuizOptionsDialog() {
		final Stage dialog = new Stage();
		dialog.setTitle("Quiz Selection");

		Button timedQuizBtn = new Button("Timed");
		Button questionQuizBtn = new Button("Numbered");
		Spinner<Integer> timeSecSpinner = new Spinner<Integer>(1, 99,
				30, 1);
		timeSecSpinner.setPrefWidth(80);
		Spinner<Integer> timeMinSpinner = new Spinner<Integer>(0, 99, 0,
				1);
		timeMinSpinner.setPrefWidth(80);
		Spinner<Integer> questionSpinner = new Spinner<Integer>(1, 99,
				20, 1);
		questionSpinner.setPrefWidth(125);

		Label displayLabel = new Label("Select Quiz Type");
		displayLabel.setFont(Font.font(null, FontWeight.BOLD, 22));
		displayLabel.setTextFill(Color.MEDIUMSEAGREEN);

		dialog.initModality(Modality.NONE);
		dialog.setMinHeight(200);
		dialog.setMinWidth(600);
		// dialog.initOwner((Stage) tableview.getScene().getWindow());

		HBox dialogHbox = new HBox(20);
		dialogHbox.setAlignment(Pos.CENTER);

		HBox timeSelectHBox = new HBox(2);
		timeSelectHBox.setAlignment(Pos.CENTER);

		VBox dialogVbox1 = new VBox(20);
		dialogVbox1.setAlignment(Pos.CENTER);

		VBox dialogVbox2 = new VBox(20);
		dialogVbox2.setAlignment(Pos.CENTER);

		Label seperator = new Label(":");
		seperator.setTextFill(Color.ANTIQUEWHITE);
		timeSelectHBox.getChildren().addAll(timeMinSpinner, seperator,
				timeSecSpinner);
		dialogHbox.getChildren().add(displayLabel);
		dialogVbox1.getChildren().addAll(timeSelectHBox, timedQuizBtn);
		dialogVbox2.getChildren().addAll(questionSpinner,
				questionQuizBtn);

		timedQuizBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent e) {
						numSeconds = timeSecSpinner.getValue();
						numSeconds += timeMinSpinner.getValue() * 60;
						isSetNumQuestion = false;
						isTimed = true;
						dialog.close();
					}
				});
		questionQuizBtn.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(final MouseEvent e) {
						numQuestions = questionSpinner.getValue();
						isSetNumQuestion = true;
						isTimed = false;
						dialog.close();
					}
				});

		dialogHbox.getChildren().addAll(dialogVbox1, dialogVbox2);
		dialogHbox.setBackground(new Background(
				new BackgroundFill(Color.BLACK, null, null)));
		Scene dialogScene = new Scene(dialogHbox, 500, 40);
		dialog.setScene(dialogScene);
		dialog.showAndWait();
	}

	/**
	 * @param info
	 */
	public final void showDialog(final String info) {
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Quiz Info");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		Button okBtn = new Button("Ok");
		okBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
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
	 * Gets total seconds selected for quiz.
	 * @return the seconds desired to take quiz
	 */
	public int getNumSeconds() {
		return numSeconds;
	}

	/**
	 * Gets Total questions selected for quiz.
	 * @return the numQuestions
	 */
	public int getNumQuestions() {
		return numQuestions;
	}

	/**
	 * Gets whether timed.
	 * @return true if quiz is timed
	 */
	public boolean isTimed() {
		return isTimed;
	}

	/**
	 * Gets if open quiz or set  no. questions.
	 * @return true if quiz is based on a set number of questions
	 */
	public boolean isSetNumQuestion() {
		return isSetNumQuestion;
	}

	/**
	 * Sets the label displaying type of quiz.
	 * @param label the text to display.
	 */
	public final void setQuizTypeLabel(final String label) {
		quizTypeDisplay.setText(label);
	}

	/**
	 * Sets total remaining time.
	 * @param secondsRemaining the numSeconds to set
	 */
	public void setTimeRemaining(final int secondsRemaining) {
		timerDisplay.setText(
				String.format("%02d", secondsRemaining / 60) + ":"
						+ String.format("%02d", secondsRemaining % 60));
	}

	/**
	 * Sets the number of questions rem.
	 * @param questionsRemaining
	 *            the number questions total.
	 */
	public void setQuestionsRemaining(final int questionsRemaining) {
		timerDisplay.setText(String.format("%02d", questionsRemaining));
	}

	/**
	 * Sets the number of questions rem.
	 * @param amtQuestions
	 *            the numQuestions to set
	 */
	public void setNumQuestions(final int amtQuestions) {
		this.numQuestions = amtQuestions;
	}
}