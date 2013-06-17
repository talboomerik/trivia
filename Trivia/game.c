#include <stdio.h>
#include <string.h>
#include <malloc.h>
#include "game.h"

void game_initialize(game_t *self)
{
	int i;
	memset(self, 0, sizeof(game_t));

	for (i = 0; i < 50; ++i)
	{
		sprintf(self->pop_questions[i], "Pop Question %d", i);
		sprintf(self->science_questions[i], "Science Question %d", i);
		sprintf(self->sports_questions[i], "Sports Question %d", i);

		char *rock_question = game_create_rock_question(i);
		strcpy(self->rock_questions[i], rock_question);
		free(rock_question);
	}
}

char* game_create_rock_question(int index)
{
	char index_str[127];
	sprintf(index_str, "Rock Question %d", index);
	return strdup(index_str);
}

bool game_is_playable(game_t *self)
{
	return (game_how_many_players(self) >= 2);
}

void game_ask_question(game_t *self)
{
	if (strcmp(game_current_category(self), "Pop") == 0)
	{
		printf("%s\n", self->pop_questions[self->cur_pop++]);

	}
	if (strcmp(game_current_category(self), "Science") == 0)
	{
		printf("%s\n", self->science_questions[self->cur_sci++]);

	}
	if (strcmp(game_current_category(self), "Sports") == 0)
	{
		printf("%s\n", self->sports_questions[self->cur_spo++]);

	}
	if (strcmp(game_current_category(self), "Rock") == 0)
	{
		printf("%s\n", self->rock_questions[self->cur_roc++]);

	}
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
	if (self->in_penalty_box[self->current_player])
	{
		if (self->out_of_penalty_box)
		{
			printf("Answer was correct!!!!\n");
			self->purses[self->current_player]++;
			printf("%s now has %d Gold Coins.\n",
					self->players[self->current_player],
					self->purses[self->current_player]);

			bool winner = game_did_player_win(self);
			self->current_player++;
			if (self->current_player == self->cur)
			{
				self->current_player = 0;
			}

			return winner;
		}
		else
		{
			self->current_player++;
			if (self->current_player == self->cur)
			{
				self->current_player = 0;
			}

			return true;
		}
	}
	else
	{
		printf("Answer was correct!!!!\n");
		self->purses[self->current_player]++;
		printf("%s now has %d Gold Coins.\n",
				self->players[self->current_player],
				self->purses[self->current_player]);

		bool winner = game_did_player_win(self);
		self->current_player++;
		if (self->current_player == self->cur)
		{
			self->current_player = 0;
		}

		return winner;
	}
}

bool game_wrong_answer(game_t *self)
{
	printf("Question was incorrectly answered\n");
	printf("%s was sent to the penalty box\n",
			self->players[self->current_player]);
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
	if (self->places[self->current_player] == 0)
		return "Pop";
	if (self->places[self->current_player] == 4)
		return "Pop";
	if (self->places[self->current_player] == 8)
		return "Pop";
	if (self->places[self->current_player] == 1)
		return "Science";
	if (self->places[self->current_player] == 5)
		return "Science";
	if (self->places[self->current_player] == 9)
		return "Science";
	if (self->places[self->current_player] == 2)
		return "Sports";
	if (self->places[self->current_player] == 6)
		return "Sports";
	if (self->places[self->current_player] == 10)
		return "Sports";
	return "Rock";
}

