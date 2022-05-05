import java.io.Serializable;
import java.util.ArrayList;

public class BaccaratInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	String gamePhase = "";
	String roundWinner = "";
	double roundWinnings = 0.0;
	ArrayList<String> gameCards= new ArrayList<String>();
	
	public void setGameStatus(String status) {
		gamePhase = status;
		
	}
	public String getGameStatus() {
		return gamePhase;
	}
	
	public void setRoundWinner(String winner) {
		roundWinner = winner;
		
	}
	public String getRoundWinner() {
		return roundWinner;
	}
	
	public void setRoundWinnings(String winner) {
		roundWinner = winner;
		
	}
	public double getRoundWinnings() {
		return roundWinnings;
	}
	
	public void setCards(ArrayList<String> cards) {
		gameCards = cards;
	}
	
	public ArrayList<String> getCards() {
		return gameCards;
	}

}
