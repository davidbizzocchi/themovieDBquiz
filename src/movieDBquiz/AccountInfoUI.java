package movieDBquiz;

import java.io.File;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Provides fx graphic layout to display account info & image.
 * @author David Bizzocchi, Josh Aitken, Caitlyn Heyn
 * @version 1.1
 */
public class AccountInfoUI extends Scene {
	/** Constant for button, Textfield, etc padding.*/
	private final int paddingSize = 10;
	/** Constant for container spacing.*/
	private final int spacingSize = 20;
	/** Constant for ui font size.*/
	private final int fontSize = 16;
	/** Constant for imageView width.*/
	private final int imageWidth = 200;
	/** Constant for imageView height.*/
	private final int imageHeight = 300;
	/** Constant for label width.*/
	private final int labelWidth = 150;
	/** Constant for label height.*/
	private final int labelHeight = 35;


	/** Field to hold layout with account details.*/
	private static VBox topLayout;
	/** Field used to access Account wrapper methods.*/
	private AccountInfo acct;
	/** A container field for profile image.*/
	private ImageView profileView;
	/** Toolbar button to navigate to menu.*/
	private Button menuBtn;
	/** Button to change user name.*/
	private Button newNameBtn;
	/** Field to hold reference to stage to display alerts.*/
	private Stage stage;
	/** Field for user name entry.*/
	private TextField newNameField;

	/**
	 * Constructor instantiates fields and populates information.
	 * @param primaryStage Reference to main stage.
	 */
	public AccountInfoUI(final Stage primaryStage) {
		super(topLayout = new VBox());
		stage = primaryStage;
		acct = new AccountInfo();
		setUpLayout();
		addComponents();
	}

	/**
	 * Adds all levels of the UI to topLayout.
	 */
	private void addComponents() {
		setUpTitle();
		setUpProfileImage();
		setUpProfileInfo();
		setUpToolbar();
	}

	/**
	 * Sets up layout spacing constraints and alignment.
	 */
	private void setUpLayout(){
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		BackgroundFill bg = new BackgroundFill(pattern, null, null);
		topLayout.setAlignment(Pos.CENTER);
		topLayout.setPadding(new Insets(15));
		topLayout.setSpacing(20);
		topLayout.setBackground(new Background(bg));
	}

	/** Creates title label and adds to layout.*/
	private void setUpTitle() {
		Label title = new Label("Account Information");
		title.setFont(new Font(fontSize));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setAlignment(Pos.TOP_CENTER);

		topLayout.getChildren().add(title);
	}

	/** Sets up imageView for profile and adds to layout.*/
	private void setUpProfileImage() {
		profileView = new ImageView();
		profileView.setImage(new Image("file:lib/question.jpg"));
		profileView.setFitHeight(imageHeight);
		profileView.setFitWidth(imageWidth);

		Button changeProfileBtn = new Button("Change Profile");
		changeProfileBtn.setPadding(new Insets(paddingSize));
		addButtonListener(changeProfileBtn);

		topLayout.getChildren().addAll(profileView, changeProfileBtn);
	}

	/** Sets up fields to display profile info.**/
	private void setUpProfileInfo() {
		HBox nameBox = new HBox();
		nameBox.setAlignment(Pos.CENTER);

		Label nameLabel = createLabel("User Name:");

		TextField nameField = new TextField(acct.getUserName());
		nameField.setEditable(false);
		nameField.setFont(new Font(fontSize));

		nameBox.getChildren().addAll(nameLabel, nameField);

		HBox idBox = new HBox();
		idBox.setAlignment(Pos.CENTER);

		Label idLabel = createLabel("Account ID:");

		TextField idField = new TextField(acct.getUserID());
		idField.setEditable(false);
		idField.setFont(new Font(fontSize));

		idBox.getChildren().addAll(idLabel, idField);

		HBox nameChangeBox = new HBox();
		nameChangeBox.setAlignment(Pos.CENTER);
 		nameChangeBox.setSpacing(spacingSize);

		Label newNameLabel = createLabel("Reset User Name");

		newNameField = new TextField("Enter name");
		newNameField.setFont(new Font(fontSize));

		newNameBtn = new Button("Reset");
		newNameBtn.setPadding(new Insets(paddingSize));
		addNewNameHandler();

		nameChangeBox.getChildren().addAll(newNameLabel,
			newNameField, newNameBtn);

		topLayout.getChildren().addAll(nameBox, idBox, nameChangeBox);
	}

	/**
	 * Creates a label with a standard format.
	 * @param labelTxt the text to be displayed.
	 * @return the label created.
	 */
	private Label createLabel(final String labelTxt) {
		Label label = new Label(labelTxt);
		label.setPadding(new Insets(paddingSize));
		label.setAlignment(Pos.CENTER);
		label.setFont(new Font(fontSize));
		label.setPrefSize(labelWidth, labelHeight);
		return label;
	}

	/** Sets up toolbar menu.*/
	private void setUpToolbar() {
		menuBtn = new Button("Menu");
		menuBtn.setPadding(new Insets(paddingSize));

		BackgroundFill fill = new BackgroundFill(Color.TRANSPARENT, null, null);

		ToolBar toolBar = new ToolBar(menuBtn);
		toolBar.setPadding(new Insets(spacingSize));
		toolBar.setPrefHeight(labelHeight);
		toolBar.setBackground(new Background(fill));

		topLayout.getChildren().add(toolBar);
	}

	/** Adds a listener to a button to call fileBrowser.
	 * @param btn The button to add click listener to.
	 * */
	private void addButtonListener(final Button btn) {
		btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
            	FileChooser fileBrowser = new FileChooser();
        		fileBrowser.setTitle("Select Image File");
        		fileBrowser.getExtensionFilters().addAll(
        		         new ExtensionFilter(".jpg Files", "*.jpg"));

        		File file = fileBrowser.showOpenDialog(stage);
        		if (file != null) {
        			profileView.setImage(new Image("file:"
        			+ file.getAbsolutePath()));
        		}
            }
        });
	}

	/** Adds an EventHandler to navigate back to main menu.
	 * @param handler the Event handler for main menu functionality.
	 * */
	public void addMenuBtnHandler(final EventHandler<ActionEvent> handler) {
		menuBtn.setOnAction(handler);
	}

	/** Adds an event handler when rename button is pressed.*/
	private void addNewNameHandler() {
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
            	try {
            		acct.changeName(newNameField.getText());
            	} catch (Exception e) {
            		String errMsg = "Could Not Alter Username";
            		JOptionPane.showMessageDialog(null, errMsg);
            	}

            }
        };
		newNameBtn.setOnAction(event);
	}
}


