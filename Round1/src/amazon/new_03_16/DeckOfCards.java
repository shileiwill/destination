package amazon.new_03_16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {

	public static void main(String[] args) {
		Face face = Face.CLUB;
		int val = face.getValue();
		
		System.out.println(val);
	}

}

enum Face {
	SPADE(0), HEART(1), DIAMOND(2), CLUB(3);
	private int value;
	private Face(int val) {
		this.value = val;
	}
	
	public int getValue() {
		return value;
	}
}

abstract class Card {
	Face face;
	int value;
	
	Card (Face face, int value) {
		this.face = face;
		this.value = value;
	}
}

class Deck <T extends Card> {
	List<T> cards = new ArrayList<T>(); // This can be a queue, first in first out
	int dealtIndex = 0;
	
	void shuffle() {
		Collections.shuffle(cards); // Be careful to shuffle only the available cards
	}
	
	void newDeck() {
		// Do initialization
		// Add all cards, and reset index to 0
	}
	
	T nextCard() {
		if (!hasNext()) {
			return null;
		}
		return cards.get(dealtIndex++);
	}
	
	boolean hasNext() {
		return dealtIndex != cards.size(); 
	}
}

class Player <T extends Card> { // Hand
	Deck<T> deck = null; // 1 Player can be only on 1 Deck
	List<T> cards = null; // The cards this Hand holds
	
	Player(Deck<T> deck) {
		this.deck = deck;
		cards = new ArrayList<T>();
	}
	
	int score() {
		int score = 0;
		for (T card : cards) {
			score += card.value;
		}
		return score;
	}
	
	void addCard() {
		T card = deck.nextCard();
		if (card != null) {
			cards.add(card);
		} else {
			throw new RuntimeException("No card on deck any more");
		}
	}
}

class BlackJackPlayer extends Player<BlackJackCard> {
	List<Integer> scores = possibleScores();
	
	int score() {
		
	}
	
	List<Integer> possibleScores() {
		// Iterate through all cards in hand, deal with Ace separately
	}
}

// Extend to a specific game, like BlackJack
class BlackJackCard extends Card {
	BlackJackCard(Face face, int value) {
		super(face, value);
	}
	
	boolean isAce() {
		return value == 1;
	}
	
	int value() {
		if (isAce()) { // A
			return 1;
		} else if (value >= 11 && value <= 13) {
			return 10;
		} else {
			return value;
		}
	}
	
	int getMinValue() {
		if (isAce()) {
			return 1;
		} else {
			return value();
		}
	}
	
	int getMaxValue() {
		if (isAce()) {
			return 11;
		} else {
			return value();
		}
	}
}