#include <string.h>
#include "game.h"

void game_initialize(game_t *self)
{
	memset(self, 0, sizeof(game_t));
}


void game_add(game_t *self, const char *name)
{
	self->n[self->cur++] = strdup(name);
}
