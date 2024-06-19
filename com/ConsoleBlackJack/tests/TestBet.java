import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TestBet {
    private static Game game;

    @Test
    public void testBetSingle() {
        game = new Game(0, 0, 1, 0, 0);
        game.bet(100,null);
        assertEquals(0, Chip.sumChipValue(game.chips));
    }

    @Test
    public void testBetBreakdown() {
        game = new Game(0, 0, 0, 1, 0);
        game.bet(100,null);
        assertEquals(400, Chip.sumChipValue(game.chips));
    }

    @Test
    public void testBetBreakdown2() {
        game = new Game(0, 0, 1, 0, 0);
        game.bet(50,null);
        game.bet(50,null);
        assertEquals(0, Chip.sumChipValue(game.chips));
    }

    @Test
    public void testBetBreakdown3() {
        game = new Game(0, 0, 0, 1, 0);
        game.bet(100,null);
        game.bet(50,null);
        game.bet(50,null);
        assertEquals(300, Chip.sumChipValue(game.chips));
    }

    @Test
    public void testBetBreakdown4() {
        game = new Game(0, 0, 0, 0, 1);
        game.bet(100,null);
        game.bet(50,null);
        game.bet(50,null);
        game.bet(50,null);
        game.bet(50,null);
        assertEquals(700, Chip.sumChipValue(game.chips));
    }

    @Test
    public void testBetBreakdown5() {
        game = new Game(0, 3, 1, 1, 0);
        game.bet(100,null);
        game.bet(100,null);
        game.bet(50,null);
        game.bet(50,null);
        game.bet(50,null);
        game.bet(50,null);
        assertEquals(350, Chip.sumChipValue(game.chips));
    }

    @Test
    public void testBetReport()  {
        game = new Game(0, 0, 0, 0, 1);
        assertEquals(game.bet(500,null), new ArrayList<Chip>(Arrays.asList(Chip.FIVE_HUNDRED)));
    }

    @Test
    public void testBetReport2()  {
        game = new Game(0, 0, 0, 0, 1);
        assertEquals(game.bet(1000,null), new ArrayList<Chip>(Arrays.asList(Chip.THOUSAND)));
    }

    @Test
    public void testBetReport3()  {
        game = new Game(0, 0, 0, 0, 1);
        assertEquals(game.bet(1500,null), null);
    }

    @Test
    public void testBetReport4()  {
        game = new Game(0, 0, 0, 0, 1);
        ArrayList<Chip> chips = new ArrayList<Chip>(Arrays.asList(Chip.FIVE_HUNDRED,Chip.HUNDRED,Chip.HUNDRED,Chip.HUNDRED,Chip.HUNDRED,Chip.FIFTY));
        assertEquals(game.bet(50, null), new ArrayList<Chip>(Arrays.asList(Chip.FIFTY)));
        assertEquals(game.chips, chips);
    }
}
