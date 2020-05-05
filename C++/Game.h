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
		  static const int NUMBER_OF_COINS_TO_WIN = 6;
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
	string getCurrentPlayerName() const;
	int const & getCurrentPlayerPurse() const;
	bool add(string playerName);

	int howManyPlayers();
	void roll(int roll);

private:
  void askQuestion();
  string currentCategory();

public:
  bool wasCorrectlyAnsweredAndGoToNextPlayerAndReturnIfWeHaveAWinner();
  bool wasIncorrectlyAnsweredAndGoToNextPlayer();

private:
	bool didPlayerWin();
    void goToNextPlayer();
    void RewardPlayer(int playerIndex);
};

#endif /* GAME_H_ */
