package movieDBquiz;

import java.awt.Desktop.Action;
import java.io.File;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

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
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AccountInfoUI extends Scene{
	static VBox topLayout;
	AccountInfo acct;
	ImageView profileView;
	Button menuBtn;
	Button newNameBtn;
	Stage stage;
	TextField newNameField;
	
	public AccountInfoUI(Stage primaryStage) {
		super(topLayout = new VBox());
		stage = primaryStage;
		acct = new AccountInfo();
		setUpLayout();
		addComponents();
	}
	
	private void addComponents(){
		setUpTitle();
		setUpProfileImage();
		setUpProfileInfo();
		setUpToolbar();
	}
	
	private void setUpLayout(){
		topLayout.setAlignment(Pos.CENTER);
		topLayout.setPadding(new Insets(15));
		topLayout.setSpacing(20);
	}
	
	private void setUpTitle(){
		Label title = new Label("Account Information");
		title.setFont(new Font(16));
		title.setTextAlignment(TextAlignment.CENTER);
		title.setAlignment(Pos.TOP_CENTER);
		
		topLayout.getChildren().add(title);
	}
	
	private void setUpProfileImage(){
		profileView = new ImageView();
		profileView.setImage(new Image("file:lib/question.jpg"));
		profileView.setFitHeight(200);
		profileView.setFitWidth(130);
		
		Button changeProfileBtn = new Button("Change Profile");
		changeProfileBtn.setPadding(new Insets(5));
		addButtonListener(changeProfileBtn);
		
		topLayout.getChildren().addAll(profileView, changeProfileBtn);
	}
	
	private void setUpProfileInfo(){
		HBox nameBox = new HBox();
		nameBox.setAlignment(Pos.CENTER);
		
		Label nameLabel = new Label("User Name:");
		nameLabel.setAlignment(Pos.CENTER);
		nameLabel.setPrefSize(150, 30);
		nameLabel.setFont(new Font(14));
		
		TextField nameField = new TextField(acct.getUserName());
		nameField.setEditable(false);
		nameField.setFont(new Font(14));
		
		nameBox.getChildren().addAll(nameLabel, nameField);
		
		HBox idBox = new HBox();
		idBox.setAlignment(Pos.CENTER);
		
		Label idLabel = new Label("Account ID:");
		idLabel.setAlignment(Pos.CENTER);
		idLabel.setPrefSize(150, 30);
		idLabel.setFont(new Font(14));
		
		TextField idField = new TextField(acct.getUserID());
		idField.setEditable(false);
		idField.setFont(new Font(14));
		
		idBox.getChildren().addAll(idLabel, idField);
		
		HBox nameChangeBox = new HBox();
		nameChangeBox.setAlignment(Pos.CENTER);
		nameChangeBox.setSpacing(15);
		
		Label newNameLabel = new Label("Reset User Name");
		newNameLabel.setPadding(new Insets(15));
		newNameLabel.setFont(new Font(14));
		
		newNameField = new TextField("Enter name");
		newNameField.setFont(new Font(14));
		
		newNameBtn = new Button("Reset");
		newNameBtn.setPadding(new Insets(10));
		addNewNameHandler();
		
		nameChangeBox.getChildren().addAll(newNameLabel, 
			newNameField, newNameBtn);
		
		topLayout.getChildren().addAll(nameBox, idBox, nameChangeBox);
	}
	
	private void setUpToolbar(){
		menuBtn = new Button("Menu");
		menuBtn.setPadding(new Insets(5));
		
		ToolBar toolBar = new ToolBar(menuBtn);
		toolBar.setPadding(new Insets(15));
		toolBar.setPrefHeight(40);
		
		topLayout.getChildren().add(toolBar);
	}
	
	private void addButtonListener(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	FileChooser fileBrowser = new FileChooser();
        		fileBrowser.setTitle("Select Image File");
        		fileBrowser.getExtensionFilters().addAll(
        		         new ExtensionFilter(".jpg Files", "*.jpg"));
        		
        		File file = fileBrowser.showOpenDialog(stage);
        		if( file != null){
        			System.out.println(file.getAbsolutePath());
        			profileView.setImage(new Image("file:" + 
        					file.getAbsolutePath()));
        		}
            }
        });
	}
	
	public void addMenuBtnHandler(EventHandler<ActionEvent> event){
		menuBtn.setOnAction(event);
	}
	
	private void addNewNameHandler(){
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try{
            		acct.changeName(newNameField.getText());
            	}
            	catch(Exception e){
            		String errMsg = "Could Not Alter Username";
            		JOptionPane.showMessageDialog(null, errMsg);
            	}
            	
            }
        };
		newNameBtn.setOnAction(event);
	}
	
	
}


