

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class serverController implements Initializable {
	

	@FXML
	private Label titleLabel;
	
	@FXML
	private VBox buttonVBox;
	
	@FXML
	private VBox rightVBox;
	
	@FXML
	private BorderPane root;
    
    @FXML
    private TextField portNumberTF;
    
    @FXML
    private Button turnOffServerBtn;
    
    @FXML
    private Button turnOnServerBtn;
    
	@FXML
	private Label serverInfoLabel;
	
	@FXML
	private ListView<String> listItems;
	
	Server serverConnection;
	private int portNumber = -1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listItems = new ListView<String>();
	}
	
	public void serverOnMethod(ActionEvent e) throws IOException{
		
		portNumber = Integer.parseInt(portNumberTF.getText());
		FXMLLoader loaderOne = new FXMLLoader(getClass().getResource("/FXML/fxmlServer.fxml"));
        Parent rootTwo = loaderOne.load();
        root.getScene().setRoot(rootTwo);
        serverConnection = new Server(data -> {Platform.runLater(()->{listItems.getItems().add(data.toString());});}, portNumber);
        
	}
	
	public void serverOffMethod(ActionEvent e) throws IOException{
		System.out.println("Server Closed");
		Platform.exit();
        System.exit(0);
	}

}
