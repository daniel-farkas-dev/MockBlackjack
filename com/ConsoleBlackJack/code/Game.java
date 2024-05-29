import java.util.ArrayList;
import java.util.Collections;
public class Game {
    private static final int ONE_CHIP=10, TEN_CHIP = 10, TWENTY_FIVE_CHIPS = 8, 
        HUNDRED_CHIPS = 4, FIVE_HUNDRED_CHIPS = 2, THOUSAND_CHIPS = 1;

    public ArrayList<Card> deck;
    public ArrayList<Chip> chips;
    public Game() {
        deck = new ArrayList<Card>();
        for (int i = 0; i < 2; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    deck.add(new Card(suit, rank));
                }
            }
        }
        Collections.shuffle(deck);
        for (Card card : deck) {
            System.out.println(card.getRank() + " of " + card.getSuit());
        }
        chips = new ArrayList<Chip>();
        for (int i = 0; i < ONE_CHIP; i++) {
            chips.add(Chip.ONE);
        }
        for (int i = 0; i < TEN_CHIP; i++) {
            chips.add(Chip.TEN);
        }
        for (int i = 0; i < TWENTY_FIVE_CHIPS; i++) {
            chips.add(Chip.TWENTY_FIVE);
        }
        for (int i = 0; i < HUNDRED_CHIPS; i++) {
            chips.add(Chip.HUNDRED);
        }
        for (int i = 0; i < FIVE_HUNDRED_CHIPS; i++) {
            chips.add(Chip.FIVE_HUNDRED);
        }
        for (int i = 0; i < THOUSAND_CHIPS; i++) {
            chips.add(Chip.THOUSAND);
        }
    }
    public int currentMoney() {
        int total = 0;
        for (Chip chip : chips) {
            total += chip.getValue();
        }
        return total;
    }
}
