import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Game {
    private static final int TEN_CHIP = 00, FIFTY_CHIPS = 0, 
        HUNDRED_CHIPS = 0, FIVE_HUNDRED_CHIPS = 0, THOUSAND_CHIPS = 1;

    public ArrayList<Card> deck;
    public ArrayList<Chip> chips, usedChips = new ArrayList<Chip>();
    public Game() {
        // initialize and set deck
        deck = new ArrayList<Card>();
        for (int i = 0; i < 2; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    deck.add(new Card(suit, rank));
                }
            }
        }
        Collections.shuffle(deck);

        // initialize and set chips
        chips = new ArrayList<Chip>();
        for (int i = 0; i < TEN_CHIP; i++) {
            chips.add(Chip.TEN);
        }
        for (int i = 0; i < FIFTY_CHIPS; i++) {
            chips.add(Chip.FIFTY);
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
    public void setChips() {
        chips.sort(Comparator.comparing(Chip::getValue).reversed());
    }
    public boolean bet(int amount) {
        // System.out.println("Bet: " + amount);
        if (amount > currentMoney() || amount < 10 || amount % 10 != 0) {
            return false;
        }
        // Line to avoid ConcurrentModificationException
        ArrayList<Chip> chipsToRemove = new ArrayList<Chip>();

        for (Chip chip : chips) {
            if (amount >= chip.getValue()) {
                chipsToRemove.add(chip);
                amount -= chip.getValue();
            }
        } // Cont. Line to avoid ConcurrentModificationException
        for (Chip chip : chipsToRemove) {
            chips.remove(chip);
            logUsedChips(chip);
        }
        if (amount == 0) {
            return true;
        } else {
            if (chips.contains(Chip.FIFTY) && amount<50) {breakdown(50);} 
            else if (chips.contains(Chip.HUNDRED) && amount<100) {breakdown(100);}
            else if (chips.contains(Chip.FIVE_HUNDRED) && amount<500) {breakdown(500);}
            else if (chips.contains(Chip.THOUSAND)) {breakdown(1000);}
            bet(amount);
        }
        return true;
    }
    public void breakdown(int value) {
        ArrayList<Chip> breakdown = Chip.breakdown(Chip.getChip(value));
        chips.remove(0);
        chips.addAll(breakdown);
    }
    public void logUsedChips(Chip chip) {
        usedChips.add(chip);
    }
}
