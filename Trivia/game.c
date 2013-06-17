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

bool game_was_correctly_answered(game_t *self)
{
	return false;
}

bool game_wrong_answer(game_t *self)
{
	printf("Question was incorrectly answered\n");
	printf("%s was sent to the penalty box\n", self->players[self->current_player]);
	self->in_penalty_box[self->current_player] = true;

	self->current_player++;
	if (self->current_player == self->cur)
	{
		self->current_player = 0;
	}
	return true;
}

bool game_did_player_win(game_t *self)
{
	return !(self->purses[self->current_player] == 6);
}

const char * game_current_category(game_t *self)
{
	if (self->places[self->current_player] == 0) return "Pop";
	if (self->places[self->current_player] == 4) return "Pop";
	if (self->places[self->current_player] == 8) return "Pop";
	if (self->places[self->current_player] == 1) return "Science";
	if (self->places[self->current_player] == 5) return "Science";
	if (self->places[self->current_player] == 9) return "Science";
	if (self->places[self->current_player] == 2) return "Sports";
	if (self->places[self->current_player] == 6) return "Sports";
	if (self->places[self->current_player] == 10) return "Sports";
	return "Rock";
}

