#include <stdio.h>
#include <stdbool.h>
#include "game.h"

static bool not_a_winner;

int main()
{
	game_t game;

	game_initialize(&game);

	game_add(&game, "Chet");
	game_add(&game, "Pat");
	game_add(&game, "Sue");

	do
	{
		game_roll(&game, rand() % 5 + 1);

		if (rand() % 9==7)
		{
			not_a_winner = game_wrong_answer(&game);
		}
		else
		{
			not_a_winner = game_was_correctly_answered(&game);
		}
	} while (not_a_winner);
}

