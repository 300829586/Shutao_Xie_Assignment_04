package gameApp;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("Game.fxml"));
			Scene scene = new Scene(root,650,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
