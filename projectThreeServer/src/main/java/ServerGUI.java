import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ServerGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/FXML/fxmlServerIntro.fxml"));
 
            primaryStage.setTitle("Tkaczyk Project 3 Server");
            Scene serverSceneOne = new Scene(root, 700, 700);
            serverSceneOne.getStylesheets().add("/styles/styleServer.css");
            primaryStage.setScene(serverSceneOne);
            primaryStage.setResizable(false);
            primaryStage.show();
         
        } catch(Exception e) {
        	System.out.println("Exception caught: Server GUI Launch Failure");
            e.printStackTrace();
            System.exit(1);
        }
	}
}