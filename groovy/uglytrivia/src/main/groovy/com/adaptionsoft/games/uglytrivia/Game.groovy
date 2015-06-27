package com.adaptionsoft.games.uglytrivia

public class Game {
	def players = []
	def places = [0] * 6
	def purses = [0] * 6
	def inPenaltyBox = [false] * 6

	def popQuestions = []
	def scienceQuestions = []
	def sportsQuestions = []
	def rockQuestions = []

	def currentPlayer = 0
	def isGettingOutOfPenaltyBox;

	public Game() {
		for (def i = 0; i < 50; i++) {
			popQuestions.add("Pop Question " + i)
			scienceQuestions.add(("Science Question " + i))
			sportsQuestions.add(("Sports Question " + i))
			rockQuestions.add(createRockQuestion(i))
		}
	}

	public createRockQuestion(index) {
		return "Rock Question " + index
	}

	public isPlayable() {
		return (howManyPlayers() >= 2)
	}

	public add(playerName) {


		players.add(playerName)
		places[howManyPlayers()] = 0
		purses[howManyPlayers()] = 0
		inPenaltyBox[howManyPlayers()] = false

		println(playerName + " was added")
		println("They are player number " + players.size())
		return true
	}

	public howManyPlayers() {
		return players.size()
	}

	public roll(roll) {
		println(players.get(currentPlayer) + " is the current player")
		println("They have rolled a " + roll)

		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true

				println(players.get(currentPlayer) + " is getting out of the penalty box")
				places[currentPlayer] = places[currentPlayer] + roll
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12

				println(players.get(currentPlayer)
						+ "'s new location is "
						+ places[currentPlayer])
				println("The category is " + currentCategory())
				askQuestion()
			} else {
				println(players.get(currentPlayer) + " is not getting out of the penalty box")
				isGettingOutOfPenaltyBox = false
			}

		} else {

			places[currentPlayer] = places[currentPlayer] + roll
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12

			println(players.get(currentPlayer)
					+ "'s new location is "
					+ places[currentPlayer])
			println("The category is " + currentCategory())
			askQuestion()
		}

	}

	private askQuestion() {
		if (currentCategory() == "Pop")
			println(popQuestions.pop())
		if (currentCategory() == "Science")
			println(scienceQuestions.pop())
		if (currentCategory() == "Sports")
			println(sportsQuestions.pop())
		if (currentCategory() == "Rock")
			println(rockQuestions.pop())
	}


	private currentCategory() {
		if (places[currentPlayer] == 0) return "Pop"
		if (places[currentPlayer] == 4) return "Pop"
		if (places[currentPlayer] == 8) return "Pop"
		if (places[currentPlayer] == 1) return "Science"
		if (places[currentPlayer] == 5) return "Science"
		if (places[currentPlayer] == 9) return "Science"
		if (places[currentPlayer] == 2) return "Sports"
		if (places[currentPlayer] == 6) return "Sports"
		if (places[currentPlayer] == 10) return "Sports"
		return "Rock"
	}

	public wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]) {
			if (isGettingOutOfPenaltyBox) {
				println("Answer was correct!!!!")
				purses[currentPlayer]++
				println(players.get(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.")

				def winner = didPlayerWin()
				currentPlayer++
				if (currentPlayer == players.size()) currentPlayer = 0

				return winner
			} else {
				currentPlayer++
				if (currentPlayer == players.size()) currentPlayer = 0
				return true
			}


		} else {

			println("Answer was corrent!!!!")
			purses[currentPlayer]++
			println(players.get(currentPlayer)
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.")

			def winner = didPlayerWin()
			currentPlayer++
			if (currentPlayer == players.size()) currentPlayer = 0

			return winner
		}
	}

	public wrongAnswer() {
		println("Question was incorrectly answered")
		println(players.get(currentPlayer) + " was sent to the penalty box")
		inPenaltyBox[currentPlayer] = true

		currentPlayer++
		if (currentPlayer == players.size()) currentPlayer = 0
		return true
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6)
	}
}
