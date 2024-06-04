import java.util.ArrayList;

public class Card {

    // Enum for suits and ranks
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    private Suit suit;
    private Rank rank;
    public boolean isFaceUp = true;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
    // Getters for card values

    public Suit getSuit() {
        return suit;
    }
    public Card.Rank getRank() {
        return rank;
    }

    public static ArrayList<Card> makeShoe() {
        ArrayList<Card> shoe = new ArrayList<Card>();
        for (int i = 0; i < 6; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    shoe.add(new Card(suit, rank));
                }
            }
        }
        return shoe; 
    }
}