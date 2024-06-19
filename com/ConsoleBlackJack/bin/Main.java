// import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Game game = new Game(0,0,0,0,1);
        // @SuppressWarnings("resource")
        // Scanner scanner = new Scanner(System.in);

        // System.out.println("Enable FrameTest? (y/n)");

        // String input = scanner.nextLine();

        // if (input.equals("y")) {
        //     FrameTest.createFrame();
        // }
        game.start();
    }
}