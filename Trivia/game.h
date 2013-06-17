#include <stdbool.h>

#ifndef GAME_H_
#define GAME_H_

typedef struct
{
	char *players[6];
	int places[6];
	int purses[6];
	bool in_penalty_box[6];
	int cur;

	int current_player;

}
game_t;


void game_initialize(game_t *self);

bool game_add(game_t *self, const char *name);

void game_roll(game_t *self, int die);

int game_how_many_players(game_t *self);

bool game_wrong_answer(game_t *self);

bool game_was_correctly_answered(game_t *self);

bool game_did_player_win(game_t *self);

#endif /* GAME_H_ */
