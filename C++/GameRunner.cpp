#include <stdlib.h>
#include <iostream>
#include <time.h>
#include "Game.h"

static bool notAWinner;

int main(int argc, char* argv[])
{
  auto seed = time(NULL);
  if (argc == 2)
  {
    seed = atoi(argv[1]); 
  }

	Game aGame;

	aGame.add("Chet");
	aGame.add("Pat");
	aGame.add("Sue");

  srand(seed);

	do
	{

		aGame.roll(rand() % 5 + 1);

		if (rand() % 9 == 7)
		{
			notAWinner = aGame.wasIncorrectlyAnsweredAndGoToNextPlayer();
		}
		else
		{
			notAWinner = aGame.wasCorrectlyAnswered();
		}
	} while (notAWinner);

}
