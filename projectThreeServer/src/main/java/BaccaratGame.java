import java.util.ArrayList;

public class BaccaratGame {

	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	double currentBet;
	double totalWinnings;
	
	String winnerBetOnByPlayer;
	
	public double evaluateWinnings() {
		String gameWinner = BaccaratGameLogic.whoWon(playerHand, bankerHand);
		if (gameWinner == "Player" && winnerBetOnByPlayer == "Player") {
			return currentBet;
		} else if (gameWinner == "Banker" && winnerBetOnByPlayer == "Banker") {
			return currentBet;
		} else if (gameWinner == "Draw" && winnerBetOnByPlayer == "Draw") {
			return currentBet * 8;
		} else if (gameWinner == "Draw" && winnerBetOnByPlayer != "Draw") {
			return 0.0;
		} else {
			return -currentBet;
		}
	}

	// Discuss whether we want the game played out through functions in class
	public void playGame() {
		theDealer = new BaccaratDealer();
		theDealer.shuffleDeck();
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();
		if (BaccaratGameLogic.handTotal(playerHand) >= 8 || BaccaratGameLogic.handTotal(bankerHand) >= 8) { // Natural win
			totalWinnings = evaluateWinnings();
			return;
		}
		boolean didPlayerDraw = BaccaratGameLogic.evaluatePlayerDraw(playerHand);
		Card playerCardThree = null;
		if (didPlayerDraw == true) { // Giving the player a third card
			playerCardThree = theDealer.drawOne();
			playerHand.add(playerCardThree);
		}
		boolean didBankerDraw = BaccaratGameLogic.evaluateBankerDraw(bankerHand, playerCardThree);
		Card bankerCardThree = null;
		if (didBankerDraw == true) { // Giving the banker a third card
			bankerCardThree = theDealer.drawOne();
			bankerHand.add(bankerCardThree);
		}
		totalWinnings = evaluateWinnings();
		return;
	}
}
