package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameRunner {

	private static boolean notAWinner = true

	public static void main(String[] args) {
		def aGame = new Game()

		aGame.add("Chet")
		aGame.add("Pat")
		aGame.add("Sue")

		def rand = new Random()

		while (notAWinner) {
			aGame.roll(rand.nextInt(5) + 1)

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer()
			} else {
				notAWinner = aGame.wasCorrectlyAnswered()
			}
		}
	}
}
