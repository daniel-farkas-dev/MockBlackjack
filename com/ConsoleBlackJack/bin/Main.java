public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        // game.start();
        // System.out.println("Current Money: "+game.currentMoney());
        // System.out.println(game.bet(330));
        // System.out.print("Chips Used:");
        // for (Chip chip : game.usedChips) {
        //     System.out.print(" "+chip.getValue());
        // }
        // //TODO: This broken
        // System.out.println("\nCurrent Money: "+game.currentMoney());
        while (game.currentMoney() > 0) {
            game.startRound();
            game.setChips();
            
        }
    }
}