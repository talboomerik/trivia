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
	bool out_of_penalty_box;

	char pop_questions[50][255];
	int cur_pop;
	char science_questions[50][255];
	int cur_sci;
	char sports_questions[50][255];
	int cur_spo;
	char rock_questions[50][255];
	int cur_roc;

}
game_t;


void game_initialize(game_t *self);

bool game_add(game_t *self, const char *name);

void game_roll(game_t *self, int die);

char* game_create_rock_question(int index);

int game_how_many_players(game_t *self);

bool game_wrong_answer(game_t *self);

bool game_was_correctly_answered(game_t *self);

bool game_did_player_win(game_t *self);

const char * game_current_category(game_t *self);

bool game_is_playable(game_t *self);

void game_ask_question(game_t *self);

#endif /* GAME_H_ */
