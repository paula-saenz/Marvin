package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import controller.Login;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login_View.fxml"));
			
			Login control = new Login();
			
			loader.setController(control);

			Parent root = loader.load();
			
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			primaryStage.setTitle("Login");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
