import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {
	
	// BaccaratGameLogic Test Cases
	@Test
	void whoWon1() {
		ArrayList<Card> playerList = new ArrayList<Card>();
		ArrayList<Card> bankerList = new ArrayList<Card>();
		
		Card newCard1 = new Card("heart", 4);
		playerList.add(newCard1);
		Card newCard2 = new Card("heart", 5);
		playerList.add(newCard2);
		Card newCard3 = new Card("spade", 3);
		bankerList.add(newCard3);
		Card newCard4 = new Card("diamond", 2);
		bankerList.add(newCard4);
		
		assertEquals("Player", BaccaratGameLogic.whoWon(playerList, bankerList));
	}
	
	@Test
	void whoWon2() {
		ArrayList<Card> playerList = new ArrayList<Card>();
		ArrayList<Card> bankerList = new ArrayList<Card>();
		
		Card newCard1 = new Card("club", 7);
		playerList.add(newCard1);
		Card newCard2 = new Card("heart", 2);
		playerList.add(newCard2);
		Card newCard5 = new Card("spade", 2);
		playerList.add(newCard5);
		Card newCard3 = new Card("spade", 3);
		bankerList.add(newCard3);
		Card newCard4 = new Card("diamond", 2);
		bankerList.add(newCard4);
		Card newCard6 = new Card("club", 6);
		bankerList.add(newCard6);
		
		assertEquals("Draw", BaccaratGameLogic.whoWon(playerList, bankerList));
	}
	
	@Test
	void handTotal1() {
		ArrayList<Card> list = new ArrayList<Card>();
		Card newCard1 = new Card("club", 7);
		list.add(newCard1);
		Card newCard2 = new Card("heart", 2);
		list.add(newCard2);
		Card newCard5 = new Card("spade", 2);
		list.add(newCard5);
		assertEquals(1, BaccaratGameLogic.handTotal(list));
	}
	
	@Test
	void handTotal2() {
		ArrayList<Card> list = new ArrayList<Card>();
		Card newCard1 = new Card("diamond", 3);
		list.add(newCard1);
		Card newCard2 = new Card("heart", 2);
		list.add(newCard2);
		Card newCard5 = new Card("club", 1);
		list.add(newCard5);
		assertEquals(6, BaccaratGameLogic.handTotal(list));
	}
	
	@Test
	void evaluateBankerDraw1() {
		ArrayList<Card> list = new ArrayList<Card>();
		Card newCard1 = new Card("diamond", 4);
		list.add(newCard1);
		Card newCard2 = new Card("heart", 1);
		list.add(newCard2);
		Card newCard3 = new Card("spade", 5);
		assertEquals(true, BaccaratGameLogic.evaluateBankerDraw(list, newCard3));
	}
	
	@Test
	void evaluateBankerDraw2() {
		ArrayList<Card> list = new ArrayList<Card>();
		Card newCard1 = new Card("diamond", 1);
		list.add(newCard1);
		Card newCard2 = new Card("heart", 2);
		list.add(newCard2);
		Card newCard3 = new Card("spade", 8);
		assertEquals(false, BaccaratGameLogic.evaluateBankerDraw(list, newCard3));
	}
	
	@Test
	void evaluatePlayerDraw1() {
		ArrayList<Card> list = new ArrayList<Card>();
		Card newCard1 = new Card("club", 4);
		list.add(newCard1);
		Card newCard2 = new Card("heart", 1);
		list.add(newCard2);
		assertEquals(true, BaccaratGameLogic.evaluatePlayerDraw(list));
	}
	
	@Test
	void evaluatePlayerDraw2() {
		ArrayList<Card> list = new ArrayList<Card>();
		Card newCard1 = new Card("club", 4);
		list.add(newCard1);
		Card newCard2 = new Card("spade", 4);
		list.add(newCard2);
		assertEquals(false, BaccaratGameLogic.evaluatePlayerDraw(list));
	}
	
	// BaccaratDealer Test Cases
	@Test
	void generateDeck1() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		assertEquals(52, dealer.deckSize());
	}
	
	@Test
	void generateDeck2() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		ArrayList<Card> deal = dealer.getDeck();
		deal.remove(4);
		deal.remove(5);
		deal.remove(7);
		assertEquals(49, deal.size());
	}
	
	@Test
	void dealHand1() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		ArrayList<Card> cards = dealer.dealHand();
		assertEquals(2, cards.size());
	}
	
	@Test
	void dealHand2() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		ArrayList<Card> cards = dealer.dealHand();
		cards.remove(0);
		assertEquals(1, cards.size());
		cards.remove(0);
		assertEquals(0, cards.size());
	}
	
	@Test
	void drawOne1() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		Card card = dealer.drawOne();
		Card newCard = new Card("heart", 1);
		assertEquals(card.getSuite(), newCard.getSuite());
	}
	
	@Test
	void drawOne2() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		Card card = dealer.drawOne();
		assertEquals(card.getValue(), 1);
		card = dealer.drawOne();
		assertEquals(card.getValue(), 2);
	}
	
	@Test
	void shuffleDeck1() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.shuffleDeck();
		ArrayList<Card> cards = dealer.getDeck();
	
		Card card = cards.get(0);
//		assertEquals(card.getValue(), 1);
//		card = dealer.drawOne();
//		assertEquals(card.getValue(), 2);
		assertFalse((card.getValue() == 1));
	}
	
	@Test
	void shuffleDeck2() {
		BaccaratDealer dealer = new BaccaratDealer();
		dealer.generateDeck();
		dealer.shuffleDeck();
		ArrayList<Card> cards = dealer.getDeck();
	
		Card card = cards.get(0);
		Card card2 = cards.get(3);
		assertFalse((card.getValue() == 1));
		assertFalse((card2.getSuite() == "heart"));
	}
	
	// Card test
	
	@Test
	void cardConstructor() {
		Card card = new Card("spade", 2);
		Card card2 = new Card("club", 7);
		assertEquals(card.getValue(), 2);
		assertEquals(card.getSuite(), "spade");
		assertEquals(card2.getValue(), 7);
		assertEquals(card2.getSuite(), "club");
	}
	
	// BaccaratGame tests
	
	@Test
	void evaluateWinnings1() {
		ArrayList<Card> playerList = new ArrayList<Card>();
		ArrayList<Card> bankerList = new ArrayList<Card>();
		
		Card newCard1 = new Card("heart", 4);
		playerList.add(newCard1);
		Card newCard2 = new Card("heart", 5);
		playerList.add(newCard2);
		Card newCard3 = new Card("spade", 3);
		bankerList.add(newCard3);
		Card newCard4 = new Card("diamond", 2);
		bankerList.add(newCard4);
		
		BaccaratGame game = new BaccaratGame();
		
		game.playGame();
		assertEquals(-0.0, game.evaluateWinnings());
	}
	
	@Test
	void evaluateWinnings2() {
		BaccaratGame game = new BaccaratGame();
		
		game.playGame();
		game.playGame();
		assertEquals(-0.0, game.evaluateWinnings());
	}
	
}
