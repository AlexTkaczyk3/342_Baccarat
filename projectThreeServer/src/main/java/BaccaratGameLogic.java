import java.util.ArrayList;

public class BaccaratGameLogic {

	/* double check / go over winners/conditions
	 * I may have messed up who won and when conditions --Alex
	 */
	// handOne == PlayerHand && handTwo == BankerHand
	public static String whoWon(ArrayList<Card> handOne, ArrayList<Card> handTwo) {
		int playerTotal = handTotal(handOne);
		int bankerTotal = handTotal(handTwo);
		if (playerTotal >= 8 && bankerTotal < 8) {
			return "Player";
		} else if (bankerTotal >= 8 && playerTotal < 8) {
			return "Banker";
		} else if (playerTotal == 9 && bankerTotal == 8) {
			return "Player";
		} else if (playerTotal == 8 && bankerTotal == 9) {
			return "Banker";
		} else if (playerTotal == 8 && bankerTotal == 8) {
			return "Draw";
		} else if (playerTotal == 9 && bankerTotal == 9) {
			return "Draw";
		} else if (playerTotal > bankerTotal) {
			return "Player";
		} else if (bankerTotal > playerTotal) {
			return "Banker";
		} else {
			System.out.println("Error: Unexpected/unaccounted for behavior in whoWon");
			return "Draw";
		}
	}
	
	public static int handTotal(ArrayList<Card> hand) {
		int totalPoints = 0;
		for (Card indivCard : hand) {
			int cardValue = indivCard.getValue();
			if (cardValue < 10) {
				totalPoints += cardValue;
			} else {
				totalPoints += 0;
			}
		}
		if (totalPoints > 9) { // to truncate ten's digit i.e. handTotal = 15 turns into 5
			totalPoints %= 10;
		}
		return totalPoints;
	}
	
	public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
		int playerCardVal;
		if (playerCard == null) { // player didn't draw
			playerCardVal = -1;
		} else {
			playerCardVal = playerCard.getValue();
		}
		if (handTotal(hand) < 3) {
			return true;
		} else if (handTotal(hand) == 3) {
			if (playerCardVal == 8) {
				return false;
			} else {
				return true;
			}
		} else if (handTotal(hand) == 4) {
			if (playerCardVal == -1 || (playerCardVal > 1 && playerCardVal < 8)) {
				return true;
			} else {
				return false;
			}
		} else if (handTotal(hand) == 5) {
			if (playerCardVal == -1 || (playerCardVal > 3 && playerCardVal < 8)) {
				return true;
			} else {
				return false;
			}
		} else if (handTotal(hand) == 6) {
			if (playerCardVal == 6 || playerCardVal == 7) {
				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
		if (handTotal(hand) < 6) {
			return true;
		} else {
			return false;
		}
	}

}
