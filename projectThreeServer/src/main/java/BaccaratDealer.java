import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {

	// Potentially initialize deck in class or generate method if needed
	ArrayList<Card> deck;
	
	// Possible Improvements for after we finish:
	// later can enumerate suites rather than using if else statements -- Alex
	public void generateDeck() {
		for (int i = 0; i < 4; i++) {
			String cardSuite = "";
			if (i == 0) {
				cardSuite = "heart";
			} else if (i == 1) {
				cardSuite = "club";
			} else if (i == 2) {
				cardSuite = "spade";
			} else if (i == 3) {
				cardSuite = "diamond";
			} else {
				System.out.println("ERROR: Something went wrong in suite assignment -> generateDeck()");
			}
			for (int j = 1; j < 14; j++) { // starts at 1 for easier point assignment
				Card newCard = new Card(cardSuite, j);
				deck.add(newCard);
			}
		}
	}
	
	public ArrayList<Card> dealHand() {
		ArrayList<Card> newHand = new ArrayList<Card>();
		newHand.add(drawOne());
		newHand.add(drawOne());
		return newHand;
	}
	
	public Card drawOne() {
		return deck.remove(0);
	}
	
	public void shuffleDeck() {
		generateDeck();
		Collections.shuffle(deck);
		/* If we want to shuffle using a specific random seed, we can do:
		 * import -> "import java.util.Random;"
		Collections.shuffle(deck, new Random(5);
		 * can also change the "5" from above to a different seed value for different types of shuffles
		 */
	}
	
	public int deckSize() {
		return this.deck.size();
	}
	
	// for testing purposes
	public void printDeck() {
		int counter = 0;
		for (Card indivCard : this.deck) {
			System.out.println("Card " + counter + " is the " + indivCard.getValue() + " of " + indivCard.getSuite());
			counter++;
		}
	}

}
