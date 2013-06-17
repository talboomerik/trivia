#include <stdio.h>
#include <string.h>
#include "game.h"

void game_initialize(game_t *self)
{
	memset(self, 0, sizeof(game_t));
}


bool game_add(game_t *self, const char *name)
{
	self->players[self->cur++] = strdup(name);
	self->places[self->cur] = 0;
	self->purses[self->cur] = 0;
	self->in_penalty_box[self->cur] = false;

	printf("%s was added\n", name);
	printf("They are player number %d\n", self->cur);

	return true;
}

void game_roll(game_t *self, int die)
{

}

int game_how_many_players(game_t *self)
{
	return self->cur;
}

bool game_wrong_answer(game_t *self)
{
	return false;
}

bool game_was_correctly_answered(game_t *self)
{
	return false;
}

bool game_did_player_win(game_t *self)
{
	return !(self->purses[self->current_player] == 6);
}
