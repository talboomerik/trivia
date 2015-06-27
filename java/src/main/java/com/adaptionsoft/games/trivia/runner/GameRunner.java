
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

import java.util.Random;


public class GameRunner {

    public static final int BOUND = 9;
    public static final int LOOSE = 7;
    public static final int MAXIMUM_EYES_ON_DICE = 5;

    private static Random rand = new Random();

    private static boolean notAWinner;

    public GameRunner(Random rand) {
        this.rand = rand;
    }

    public static void main(String[] args) {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");


        do {

            aGame.roll(rand.nextInt(MAXIMUM_EYES_ON_DICE) + 1);

            if (rand.nextInt(BOUND) == LOOSE) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }


        } while (notAWinner);

    }
}
