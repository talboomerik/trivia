
#ifndef GAME_H_
#define GAME_H_

typedef struct
{
	char *n[6];
	int cur;

}
game_t;


void game_initialize(game_t *self);

void game_add(game_t *self, const char *name);

#endif /* GAME_H_ */
