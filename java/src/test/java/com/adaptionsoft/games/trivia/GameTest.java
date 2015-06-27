package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void movesToOneAfterNormalTurnStartingFrom0AndRolling1(){
        Game game = gameWithOnePlayerOutsidePenaltyBox();
        game.places[0] = 0;

        game.roll(1);

        assertEquals(1, game.places[0]);
    }

    @Test
    public void movesToSixAfterNormalTurnStartingFrom0AndRolling6(){
        Game game = gameWithOnePlayerOutsidePenaltyBox();
        game.places[0] = 0;

        game.roll(6);

        assertEquals(6, game.places[0]);
    }

    @Test
    public void movesToStartAfterNormalTurnStartingFrom12AndRolling1(){
        Game game = gameWithOnePlayerOutsidePenaltyBox();
        game.places[0] = 12;

        game.roll(1);

        assertEquals(1, game.places[0]);
    }

    private Game gameWithOnePlayerOutsidePenaltyBox() {
        Game game = new Game(myPrintStream);
        game.add(null);
        return game;
    }

    private Game gameWithOnePlayerInPenaltyBox() {
        Game game = new Game(myPrintStream);
        game.add(null);
        game.inPenaltyBox[0] = true;
        return game;
    }

}
