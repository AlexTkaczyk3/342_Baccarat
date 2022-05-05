import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ClientGUI extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/FXML/fxmlClientIntro.fxml"));
            // -- to access different scenes edit above fxml file to one of those listed below:
            //fxmlClientIntro -- 230x330
            //fxmlClientMain -- 700x1000
            //fxmlClientNaturalWinEnd -- 700x700
            //fxmlClientNonNaturalContinue -- 530x460
            //fxmlClientNonNaturalEnd -- 700x760
 
            primaryStage.setTitle("Tkaczyk Project 3 Client");
            Scene clientSceneOne = new Scene(root, 1000, 700);
            clientSceneOne.getStylesheets().add("/styles/styleClient.css");
            primaryStage.setScene(clientSceneOne);
            primaryStage.show();
            System.out.println(primaryStage.getHeight() + " " + primaryStage.getWidth());
        
        } catch(Exception e) {
        	System.out.println("Exception caught: Client GUI Launch Failure");
            e.printStackTrace();
            System.exit(1);
        }
	}
}