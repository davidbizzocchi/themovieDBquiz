package movieDBquiz;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class WatchListUI extends Scene {
	static HBox windowLayout = new HBox();
	VBox watchListLayout;
	PosterUI poster;
	Button exitBtn;
	Button addBtn;
	Button removeBtn;
	Button randomBtn;
	ListView<String> watchList;
	
	public WatchListUI() {
		super(windowLayout);
		setUpLayout();
		addComponents();
	}
	
	private void setUpLayout(){
		windowLayout.setAlignment(Pos.CENTER);
		windowLayout.setSpacing(35);
		windowLayout.setPadding(new Insets(10));
		
		watchListLayout = new VBox();
		windowLayout.getChildren().add(watchListLayout);
	}
	
	private void addComponents(){
		setUpTitle();
		setUpList();
		setUpToolBar();
		addPoster();
	}
	
	private void setUpTitle(){
		watchListLayout.setAlignment(Pos.CENTER);
		watchListLayout.setPadding(new Insets(15));
		
		Label title = new Label("Watch List");
		title.setTextAlignment(TextAlignment.CENTER);
		title.setPrefSize(300, 30);
		title.setFont(new Font("System", 16));
		
		watchListLayout.getChildren().add(title);
	}
	
	private void setUpList(){
		watchList = new ListView<String>();
		watchList.setPrefSize(350, 350);
		watchList.setEditable(false);
		
		watchListLayout.getChildren().add(watchList);
	}
	
	private void setUpToolBar(){
		exitBtn = new Button("Menu");
		removeBtn = new Button("Remove");
		addBtn = new Button("Add");
		randomBtn = new Button("Get Random");
		
		ToolBar toolBar = new ToolBar(
			exitBtn,
			addBtn,
			removeBtn,
			randomBtn
		);
		
		toolBar.setPadding(new Insets(30));
		
		watchListLayout.getChildren().add(toolBar);
	}
	
	private void addPoster(){
		poster = new PosterUI();
		windowLayout.getChildren().add(poster.getLayout());
	}
	
	
}
