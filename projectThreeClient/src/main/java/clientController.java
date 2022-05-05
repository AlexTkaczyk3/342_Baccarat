

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class clientController implements Initializable {
	
	@FXML
	private BorderPane root;
	
	//intro scene
	@FXML
	private VBox introVBox;
    
	@FXML
	private Label titleLabel;
		
    @FXML
    private TextField ipAddressTF;
    
    @FXML
    private TextField portInputTF;
    
    @FXML
    private Button serverConnectButton;
    
    // main scene
    @FXML
    private VBox topLeftVBox;
    
    @FXML
    private TextField betAmountTF;
    
    @FXML
    private Button playRoundBtn;
    
    @FXML
    private VBox topRightVBox;
    
    @FXML
    private HBox winnerBetButtonHBox;
    
    @FXML
    private Button playerWinsBtn;
    
    @FXML
    private Button drawBtn;
    
    @FXML
    private Button bankerWinsBtn;
    
    @FXML
    private Button exitButton;
	
	@FXML
	private Label pickWinnerLabel;
		
	@FXML
	private GridPane cardInfoGrid;

	@FXML
	private Label cardOneLabel;

	@FXML
	private Label cardTwoLabel;
		
	@FXML
	private Label cardThreeLabel;
		
	@FXML
	private Label cardFourLabel;
	
	//natural end scene
	
	@FXML
    private VBox rightVBox;
	
	@FXML
    private VBox topVBox;
	
	@FXML
	private Label whoWonLabel;
	
	@FXML
	private Label currentWinningsLabel;
	
	@FXML
    private HBox winnerInfoHBox;
    
    @FXML
    private Button playAgainBtn;
    
	@FXML
    private HBox nextBtnsHBox;

	@FXML
	private Label roundResultsLabel;
	
	@FXML
	private ListView<Label> roundResultsList;
	
	//non-natural end scene
	@FXML
	private Label cardFiveLabel;
		
	@FXML
	private Label cardSixLabel;
	
	//Non-natural main continuation
	@FXML
	private Label newCardNotifLabel;
	
	Client clientConnection;
	BaccaratInfo reader = new BaccaratInfo();
	String playerBetOn = "";
	double totalWinnings = 0.0;
	ListView<String> listItems = new ListView<String>();
	
	// find way to un-focus all text fields on start
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titleLabel.setStyle("-fx-font-weight: bold;" + "-fx-font-size: 25px;");
		//roundResultsList = new  ListView<Label>();
	}
	//intro methods
	public void connectMethod(ActionEvent e) throws IOException{
		try {
			clientConnection = new Client(data->{Platform.runLater(()->{listItems.getItems().add(data.toString());});}, ipAddressTF.getText(), Integer.parseInt(portInputTF.getText()));
			clientConnection.start();
	        FXMLLoader loaderOne = new FXMLLoader(getClass().getResource("/FXML/fxmlClientMain.fxml"));
	        Parent rootTwo = loaderOne.load();
	        root.getScene().setRoot(rootTwo);
	        clientConnection.send("Player Connected");
		} catch (Exception error) {
			System.out.println("Failed to connect to server");
		}
	}
	
	//main methods
	public void playRoundBtnMethod(ActionEvent e) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		playerWinsBtn.setDisable(true);
		drawBtn.setDisable(true);
		bankerWinsBtn.setDisable(true);
		clientConnection.send("Player Bet: $" + betAmountTF.getText() + " on " + playerBetOn); // (betAmountTF.getText() + " " + playerBetOn)
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.play();
		pause.setOnFinished(t->{
			clientConnection.setUpdates(reader);
			ArrayList<String> allCardsInPlay = reader.getCards();
			String s = reader.getGameStatus();
			
			cardOneLabel.setText(allCardsInPlay.get(0));
			cardTwoLabel.setText(allCardsInPlay.get(1));
			cardThreeLabel.setText(allCardsInPlay.get(2));
			cardFourLabel.setText(allCardsInPlay.get(3));
			
			PauseTransition pauseForNaturalCheck = new PauseTransition(Duration.seconds(2.5));
			if (s.equals("Natural Win")) {
				pauseForNaturalCheck.setOnFinished(z->{
					loader.getClass().getResource("/FXML/fxmlClientNaturalWinEnd.fxml");
					Parent rootThree = null;
			        try {
			        	rootThree = loader.load();
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Error loading fxmlClientNaturalWinEnd");
					}
			        root.getScene().setRoot(rootThree);

					String winner = reader.getRoundWinner();
					double currentWinnings = reader.getRoundWinnings();
					
					whoWonLabel.setText(whoWonLabel.getText() + winner);
					currentWinningsLabel.setText(currentWinningsLabel.getText() + currentWinnings);
					totalWinnings += currentWinnings;
					
					Label newRoundResults = new Label("Winner: " + winner + "Round Winnings: " + currentWinnings);
					roundResultsList.getItems().add(newRoundResults);
					});
				pauseForNaturalCheck.play();
				
			} else if (s.equals("Non-Natural Win")) {
				allCardsInPlay = reader.getCards();
				cardFiveLabel.setText(allCardsInPlay.get(4));
				cardSixLabel.setText(allCardsInPlay.get(5));
				
				pauseForNaturalCheck.setOnFinished(z->{
					loader.getClass().getResource("/FXML/fxmlClientNonNaturalContinue.fxml");
					Parent rootFour = null;
			        try {
			        	rootFour = loader.load();
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Error loading fxmlClientNonNaturalContinue");
					}
			        root.getScene().setRoot(rootFour);
					});
				pauseForNaturalCheck.play();
				PauseTransition pauseForNonNatty = new PauseTransition(Duration.seconds(2.5));
				pauseForNonNatty.setOnFinished(z->{
					loader.getClass().getResource("/FXML/fxmlClientNonNaturalEnd.fxml");
					Parent rootFive = null;
			        try {
			        	rootFive = loader.load();
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Error loading fxmlClientNonNaturalEnd");
					}
			        root.getScene().setRoot(rootFive);
			        
			        String winner = reader.getRoundWinner();
					double currentWinnings = reader.getRoundWinnings();
					whoWonLabel.setText(whoWonLabel.getText() + winner);
					currentWinningsLabel.setText(currentWinningsLabel.getText() + currentWinnings);
					totalWinnings += currentWinnings;
					
					Label newRoundResults = new Label("Winner: " + winner + "Round Winnings: " + currentWinnings);
					roundResultsList.getItems().add(newRoundResults);
					});
				pauseForNonNatty.play();
			} else {
				System.out.println("Unexpected behavior in clientController -> post scene change");
			}
			
		});
		
	}
	
	public void playerBetOnBtnMethod(ActionEvent e) throws IOException{
	    playerBetOn = "Player";
	    playerWinsBtn.setDisable(true);
	    drawBtn.setDisable(false);
	    bankerWinsBtn.setDisable(false);
	    playRoundBtn.setDisable(false);
	}
	
	public void drawBetOnBtnMethod(ActionEvent e) throws IOException{
		playerBetOn = "Tie";
		drawBtn.setDisable(true);
		playerWinsBtn.setDisable(false);
		bankerWinsBtn.setDisable(false);
		playRoundBtn.setDisable(false);
	}
	
	public void bankerBetOnBtnMethod(ActionEvent e) throws IOException{
		playerBetOn = "Banker";
		bankerWinsBtn.setDisable(true);
		playerWinsBtn.setDisable(false);
		drawBtn.setDisable(false);
		playRoundBtn.setDisable(false);
	}
	
	public void exitBtnMethod(ActionEvent e) throws IOException{
		Platform.exit();
	}
	
	public void playAgainBtnMethod(ActionEvent e) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/fxmlClientMain.fxml"));
        Parent root2 = loader.load();
        root.getScene().setRoot(root2);
	}
	
}
