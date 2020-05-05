#include <iostream>
#include <list>
#include <vector>
using namespace std;

#ifndef GAME_H_
#define GAME_H_


class Game{

		private:
      static const int MIN_NUMBER_OF_PLAYERS = 2;
		  static const int MAX_NUMBER_OF_PLAYERS = 6;
			vector<string> players;

			int places[MAX_NUMBER_OF_PLAYERS] = {};
			int purses[MAX_NUMBER_OF_PLAYERS] = {};

			bool inPenaltyBox[MAX_NUMBER_OF_PLAYERS];

			list<string> popQuestions;
			list<string> scienceQuestions;
			list<string> sportsQuestions;
			list<string> rockQuestions;

			int currentPlayer;
			bool isGettingOutOfPenaltyBox;

public:
	Game();
	string createRockQuestion(int index);
	bool isPlayable();
	bool add(string playerName);

	int howManyPlayers();
	void roll(int roll);

	private:
		void askQuestion();
		string currentCategory();

				public:
					bool wasCorrectlyAnswered();
					bool wrongAnswer();

private:
	bool didPlayerWin();
};

#endif /* GAME_H_ */
