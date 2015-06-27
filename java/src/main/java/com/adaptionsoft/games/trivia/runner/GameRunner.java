
package com.adaptionsoft.games.trivia.runner;
import com.adaptionsoft.games.uglytrivia.Game;

import java.io.PrintStream;
import java.util.Random;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
        long randomSeed = System.currentTimeMillis();
        PrintStream output_print_stream = System.out;
        runGame(randomSeed, output_print_stream);
	}

    public static void runGame(long randomSeed, PrintStream output_print_stream) {
        Game aGame = new Game(output_print_stream);

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");


        Random rand = new Random(randomSeed);

        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }



        } while (notAWinner);
    }
}
