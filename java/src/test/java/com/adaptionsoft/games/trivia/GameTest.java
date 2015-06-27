package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream myPrintStream = new PrintStream(byteArrayOutputStream);

    @Test
    public void getsOutOfPenaltyBoxWhenRollingAnOddNumber() {
        Game game = gameWithOnePlayerInPenaltyBox();

        game.roll(1);

        assertTrue(game.isGettingOutOfPenaltyBox);
    }

    @Test
    public void staysInPenaltyBoxWhenRollingAnEvenNumber() {
        Game game = gameWithOnePlayerInPenaltyBox();

        game.roll(2);

        assertFalse(game.isGettingOutOfPenaltyBox);
    }

    private Game gameWithOnePlayerInPenaltyBox() {
        Game game = new Game(myPrintStream);
        game.add(null);
        game.inPenaltyBox[0] = true;
        return game;
    }

}
