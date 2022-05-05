
public class Card {

	String suite;
	int value;
	
	Card(String theSuite, int theValue) {
		this.suite = theSuite;
		this.value = theValue;
	}
	
	public String getSuite() {
		return this.suite;
	}
	
	public int getValue() {
		return this.value;
	}

}
