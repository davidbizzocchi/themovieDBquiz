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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * A class to provide a login form.
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class AppLoginUI extends Scene {
	/** Constant int for login form width.*/
	private static final int FORMWIDTH = 350;
	/** Constant int for login form height.*/
	private static final int FORMHEIGHT = 275;
	/** Constant to hold layout spacing.*/
	private final int spacingSize = 10;
	/** Constant to hold layout padding.*/
	private final int paddingSize = 25;
	/** Constant to set title font size.*/
	private final int fontSize = 20;
	/** Constant to hold layout padding.*/
	private final int actionTextYPos = 6;
	/** Constant to hold layout padding.*/
	private final int btnLayoutYPos = 4;


	/** Field to hold top-level layout.*/
	private static GridPane grid = new GridPane();
	/** Field to display login feedback.*/
	private final Text actiontarget = new Text();
	/** Secure textfield for password.*/
	private PasswordField pwBox;
	/** Field for taking user name input.*/
	private TextField userTextField;
	/** Field to reference the login button.*/
	private Button loginBtn;
	/** Field to get tmdb api data.*/
	private DbManager manager;

	/** Constructor to create the form ui.*/
	public AppLoginUI() {
		super(grid, FORMWIDTH, FORMHEIGHT);
		manager = new DbManager();
		createLoginForm();
	}

	/** Creates the form layout and contents.*/
	public void createLoginForm() {
		createGrid();
		createScene();
		addBtn();
	}

	/** Sets up the grid layout.*/
	public void createGrid() {
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill fill = new BackgroundFill(pattern, null, null);
		grid.setBackground(new Background(fill));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(spacingSize);
		grid.setVgap(spacingSize);
		grid.setPadding(new Insets(paddingSize, paddingSize,
			paddingSize, paddingSize));
	}

	/** Creates ui labels and text fields and adds to layout.*/
	private void createScene() {
		Text scenetitle = new Text("The Movie Database Quiz");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, fontSize));
		scenetitle.setFill(Color.LIGHTGREY);
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		userName.setTextFill(Color.WHITE);
		grid.add(userName, 0, 1);

		userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		pw.setTextFill(Color.WHITE);
		grid.add(pw, 0, 2);

		pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);
	}

	/** Adds the login to a stage.
	 * @param primaryStage the stage to add the app login scene to.
	 * */
	public void addToStage(final Stage primaryStage) {
		primaryStage.setScene(this);
	}

	/** Adds button to validate login.*/
	private void addBtn() {
        grid.add(actiontarget, 1, actionTextYPos);

		loginBtn = new Button("Sign in");
		HBox hbBtn = new HBox(spacingSize);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(loginBtn);
		grid.add(hbBtn, 1, btnLayoutYPos);
	}

	/** Sets the actionTarget to indicate invalid credentials.*/
	public void giveLoginFailedMsg() {
		actiontarget.setFill(Color.FIREBRICK);
        actiontarget.setText("Login failed.");
	}

	/** Sets the actionTarget to indicate valid credentials.*/
	public void giveLoginSuccessMsg() {
		actiontarget.setFill(Color.GREEN);
        actiontarget.setText("Login Successful.");
	}

	/** gets the text from username field.
	 * @return the current value entered.
	 * */
	public String getUserText() {
		return userTextField.getText();
	}

	/** Validate login credentials with DbManager.
	 * @return true, if valid login attempt.
	 * */
	public Boolean attemptLogin() {
		return manager.attemptLogin(userTextField.getText(),
    			pwBox.getText());
	}

	/** Sets event handler to login button click.
	 * @param handler the event handler to add to loginBtn.
	 * */
	public void addBtnEventHandler(final EventHandler<ActionEvent> handler) {
		loginBtn.setOnAction(handler);
	}

}
