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

public class AppLoginUI {
	GridPane grid;
	Scene scene;
	PasswordField pwBox;
	TextField userTextField;
	
	DbManager manager;
	
	public AppLoginUI(){
		manager = new DbManager();
		createLoginForm();
	}
	
	public void createLoginForm(){
		createGrid();
		createScene();
		addBtn();
	}
	
	public void createGrid(){
		Image image = new Image("file:lib/background.jpg");
		ImagePattern pattern = new ImagePattern(image);
		grid = new GridPane();
		grid.setBackground(new Background(new BackgroundFill(pattern,null,null)));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
	}
	
	private void createScene(){
		scene = new Scene(grid, 350, 275);
		//primaryStage.setScene(scene);
		
		Text scenetitle = new Text("The Movie Database Quiz");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
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
	
	public void addToStage(Stage primaryStage){
		primaryStage.setScene(scene);
	}
	
	private void addBtn(){
		final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
		    	Boolean valid = manager.attemptLogin(userTextField.getText(), 
		    			pwBox.getText());
		    	if(valid){
		    		actiontarget.setFill(Color.GREEN);
			        actiontarget.setText("Logging in as \'" + userTextField.getText());
		    	}
		    	else{
		    		actiontarget.setFill(Color.FIREBRICK);
			        actiontarget.setText("Login failed.");
		    	}
		    	
		    }
		});
	}
	
}
