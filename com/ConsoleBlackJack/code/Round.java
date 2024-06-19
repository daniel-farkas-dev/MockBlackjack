import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Round {
    int turn = 0;
    Integer wager; ArrayList<Chip> usedChips;
    Game game;
    ArrayList<Card> dealerHand, playerHand;
    int dealerTotal = 0, playerTotal = 0;
    boolean dealerSoft = false, playerSoft = false;
    int indicator = 0;
    boolean[] playerActions;
    public enum Actions {
        HIT, STAND, DOUBLE_DOWN, SPLIT, INSURANCE
    }

    public Round (Game game) {
        this.game = game;
    }

    private void validateShoe() {
        if (game.shoe.isEmpty() || game.shoe == null) {
            game.shoe = Card.makeShoe(); Collections.shuffle(game.shoe);
        }
    }

    private void burnCard() {
        Card burn = game.shoe.remove(0);
        System.out.println("Burned: " + burn);
    }

    private ArrayList<Chip> getUserBet() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true) {
            wager = 0;
            System.out.print("\nEnter your bet: ");
            try {wager = scanner.nextInt();}
            catch (Exception e) {System.out.println("Invalid bet"); continue;}
            ArrayList<Chip> a = game.bet(wager, usedChips);
            if (a.equals(null)) {System.out.println("Invalid bet"); continue;}
            return a;
        }
    }

    public Game.Results playerTurn() {
        
        printBoard();

        // Ask for bet
        usedChips = getUserBet();

        // Burn card
        burnCard();

        // Deal cards
        dealerHand = new ArrayList<Card>(); playerHand = new ArrayList<Card>();
        dealerHand.add(game.shoe.remove(0)); playerHand.add(game.shoe.remove(0));
        Card hidden = game.shoe.remove(0); hidden.isFaceUp = false;
        dealerHand.add(hidden); playerHand.add(game.shoe.remove(0));        

        // Player's Actions
        playerActions = new boolean[]{true, true, false, false, false};
        if (turn == 0) {
            if (wager <= Chip.sumChipValue(game.chips)) playerActions[2] = true;
            if (playerHand.get(0).getRank() == playerHand.get(1).getRank() && wager <= Chip.sumChipValue(game.chips)) playerActions[3] = true;
            if (dealerHand.get(0).getRank() == Card.Rank.ACE) playerActions[4] = true;
        }

        while (true) {
            validateShoe(); //Initialize shoe if empty or null
            printBoard();

            if (playerTotal == 21) return Game.Results.BLACKJACK;
            if (playerTotal > 21) return Game.Results.PLAYER_BUST;

            // Get user input
            @SuppressWarnings("resource")
            String userInput = new Scanner(System.in).nextLine();

            if (userInput.equals(">")) {
                indicator++;
                if (indicator > 4) indicator = 0;
                while (!playerActions[indicator]) {
                    indicator++;
                    if (indicator > 4) indicator = 0;
                }
            } else if (userInput.equals("<")) {
                indicator--;
                if (indicator < 0) indicator = 4;
                while (!playerActions[indicator]) {
                    indicator--;
                    if (indicator < 0) indicator = 4;
                }
            } else {
                turn++;
                switch (Actions.values()[indicator]) {
                    case HIT:
                        playerHand.add(game.shoe.remove(0));
                        break;
                    case STAND:
                        return Game.Results.DEALER_TURN;
                    case DOUBLE_DOWN:
                    case SPLIT:
                    case INSURANCE:
                        System.out.println("Not implemented yet, sry");
                        break;
                }
            }
        }
    }

    public Game.Results dealerTurn() {
        dealerHand.get(1).isFaceUp = true;
        printBoard();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (dealerTotal == 21) return Game.Results.DEALER_BLACKJACK;
        while (dealerTotal < 17) {
            dealerHand.add(game.shoe.remove(0));
            printBoard();
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (dealerSoft && dealerTotal <= 11) dealerTotal += 10;
        if (playerSoft && playerTotal <= 11) playerTotal += 10;
        if (dealerTotal == 21) return Game.Results.DEALER_BLACKJACK;
        if (dealerTotal > 21) return Game.Results.DEALER_BUST;
        if (dealerTotal > playerTotal) return Game.Results.DEALER_WIN;
        if (dealerTotal < playerTotal) return Game.Results.PLAYER_WIN;
        return Game.Results.TIE;
    } 

    private void printBoard() {
        dealerHand = dealerHand == null ? new ArrayList<Card>() : dealerHand;
        playerHand = playerHand == null ? new ArrayList<Card>() : playerHand;
        usedChips = usedChips == null ? new ArrayList<Chip>() : usedChips;
        playerActions = playerActions == null ? new boolean[5] : playerActions;
        dealerTotal = 0; playerTotal = 0;

        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("\nDealer's Hand: ");
        for (Card card : dealerHand) {
            System.out.print(card + " ");
            if (card.isFaceUp) {
                dealerTotal += card.getValue();
                if (card.getRank() == Card.Rank.ACE) dealerSoft = true;
            }
        }
        if (dealerSoft) System.out.println(" | Dealer's Total: " + determineSoft(dealerTotal) + "\n");
        else System.out.println(" | Dealer's Total: " + dealerTotal + "\n");
        System.out.println("Player's Hand: ");
        for (Card card : playerHand) {
            System.out.print(card + " ");
            playerTotal += card.getValue();
            if (card.getRank() == Card.Rank.ACE) playerSoft = true;
        }
        if (playerSoft) System.out.println(" | Player's Total: " + determineSoft(playerTotal) + "\n");
        else System.out.print(" | Player's Total: " + playerTotal + "\n");
        System.out.print("\nCurrent wager: "); usedChips.forEach((Chip c) -> {System.out.print(c+" ");});
        System.out.print("($" + Chip.sumChipValue(usedChips) + ")");
        System.out.print("\n\nPlayer Actions: ");
        System.out.print("\n\n[" +
            ((playerActions[0])? ((indicator==0)?"*":" ") : "/") + "] HIT | [" +
            ((playerActions[1])? ((indicator==1)?"*":" ") : "/") + "] STAND | [" +
            ((playerActions[2])? ((indicator==2)?"*":" ") : "/") + "] DOUBLE DOWN | [" +
            ((playerActions[3])? ((indicator==3)?"*":" ") : "/") + "] SPLIT | [" + 
            ((playerActions[4])? ((indicator==4)?"*":" ") : "/") + "] INSURANCE");
        System.out.print("\n\nPlayer's Chips: THOUSAND ["+Collections.frequency(game.chips, Chip.THOUSAND)+"] | FIVE HUNDRED ["+
            Collections.frequency(game.chips, Chip.FIVE_HUNDRED)+"] | HUNDRED ["+Collections.frequency(game.chips, Chip.HUNDRED)+"] | FIFTY ["+
            Collections.frequency(game.chips, Chip.FIFTY)+"] | TEN ["+Collections.frequency(game.chips, Chip.TEN)+"] ");
        System.out.print("||| Total Money: $" + Chip.sumChipValue(game.chips));
        if (wager != null) System.out.print("\n\nEnter > to move right, < to move left, or any other key to select.\n");
    }

    private String determineSoft(int total) {
        if (total + 10 <= 21) return (String) (total + " or " + (total + 10));
        else return (String) (total+"");
    }
} 
