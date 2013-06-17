all: game_runner.res

game_runner: game_runner.c game.c
	gcc $^ -o game_runner
	
game_runner.res: game_runner
	./$< | tee game_runner.res
	
clean:
	rm -f *.o game_runner