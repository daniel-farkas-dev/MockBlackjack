
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

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Getters for suit and rank

    public Card.Suit getSuit() {
        return suit;
    }

    public Card.Rank getRank() {
        return rank;
    }
}