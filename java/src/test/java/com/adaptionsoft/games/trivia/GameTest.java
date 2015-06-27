package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    public static final int FIRST_SQUARE = 0;
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
    public void movesToStartAfterNormalTurnStartingFrom11AndRolling1(){
        Game game = gameWithOnePlayerOutsidePenaltyBox();
        game.places[0] = 11;

        game.roll(1);

        assertEquals(0, game.places[0]);
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

    @Test
    public void new_movesToOneAfterNormalTurnStartingFrom0AndRolling1(){
        int currentSquare = FIRST_SQUARE;
        currentSquare  = calculateNextPosition(currentSquare, 1);
        assertEquals(1, currentSquare);
    }

    @Test
    public void new_movesTwoPlacesAfterNormalTurnStartingFrom0AndRolling2(){
        int currentSquare = FIRST_SQUARE;
        currentSquare = calculateNextPosition(currentSquare, 2);
        assertEquals(2, currentSquare);
    }

    @Test
    public void new_movesAtTheEndAfterNormalTurnStartingFrom11AndRolling1(){
        int currentSquare = 10;
        currentSquare = calculateNextPosition(currentSquare, 1);
        assertEquals(11, currentSquare);
    }

    @Test
    public void new_movesAtTheStartAfterNormalTurnStartingFrom13AndRolling1(){
        int currentSquare = 11;
        currentSquare = calculateNextPosition(currentSquare, 1);
        assertEquals(FIRST_SQUARE, currentSquare);
    }

    @Test
    public void newx_movesAtTheStartAfterNormalTurnStartingFrom13AndRolling1(){
        int currentSquare = 2;
        currentSquare = calculateNextPosition(currentSquare, 3);
        assertEquals(5, currentSquare);
    }

    final int BOARD_SIZE = 12;
    private int calculateNextPosition(int currentSquare, int move) {
        return (currentSquare + move) % BOARD_SIZE;
    }
}
