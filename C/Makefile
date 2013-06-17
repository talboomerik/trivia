all: game_runner

game_runner: game_runner.c game.c
	gcc $^ -o game_runner

clean:
	rm -f *.o game_runner