package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    public static final int NUMBER_OF_PLAYERS = 6;
    public static final int NUMBER_OF_PURSES = 6;
    public static final int NUMBER_OF_PENALTY_BOXES = 6;
    public static final String POP_QUESTION = "Pop Question ";
    public static final String SCIENCE_QUESTION = "Science Question ";
    public static final String SPORTS_QUESTION = "Sports Question ";
    public static final String ROCK_QUESTION = "Rock Question ";
    public static final int QUESTIONS_COUNT_IN_EACH_CATEGORY = 50;
    public static final int MINIMAL_NUMBER_OF_PLAYERS = 2;
    public static final int NUMBER_OF_PLACES = 12;
    public static final int NUMBER_OF_PLACES_MINUS_ONE = 11;
    public static final String POP = "Pop";
    public static final String SCIENCE = "Science";
    public static final String SPORTS = "Sports";
    public static final String ROCK = "Rock";
    public static final int FIRST_PLAYER = 0;
    public static final boolean GAME_NOT_OVER = true;
    ArrayList players = new ArrayList();

    int[] places = new int[NUMBER_OF_PLAYERS];
    int[] purses  = new int[NUMBER_OF_PURSES];
    boolean[] inPenaltyBox  = new boolean[NUMBER_OF_PENALTY_BOXES];
    LinkedList popQuestions = new LinkedList();

    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    int currentPlayer = 0;

    boolean isGettingOutOfPenaltyBox;

    private final PrintStream output_print_stream;

    public  Game(PrintStream output_print_stream){
    	for (int i = 0; i < QUESTIONS_COUNT_IN_EACH_CATEGORY; i++) {
			popQuestions.addLast(POP_QUESTION + i);
			scienceQuestions.addLast((SCIENCE_QUESTION + i));
			sportsQuestions.addLast((SPORTS_QUESTION + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
        this.output_print_stream = output_print_stream;
    }

	public String createRockQuestion(int index){
		return ROCK_QUESTION + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= MINIMAL_NUMBER_OF_PLAYERS);
	}

	public boolean add(String playerName) {
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    
	    output_print_stream.println(playerName + " was added");
	    output_print_stream.println("They are player number " + players.size());
		return true;
	}
	
	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		output_print_stream.println(players.get(currentPlayer) + " is the current player");
		output_print_stream.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				output_print_stream.println(players.get(currentPlayer) + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > NUMBER_OF_PLACES_MINUS_ONE) places[currentPlayer] = places[currentPlayer] - NUMBER_OF_PLACES;

                output_print_stream.println(players.get(currentPlayer)
                        + "'s new location is "
                        + places[currentPlayer]);
				output_print_stream.println("The category is " + currentCategory());
				askQuestion();
			} else {
				output_print_stream.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > NUMBER_OF_PLACES_MINUS_ONE) places[currentPlayer] = places[currentPlayer] - NUMBER_OF_PLACES;
			
			output_print_stream.println(players.get(currentPlayer)
                    + "'s new location is "
                    + places[currentPlayer]);
			output_print_stream.println("The category is " + currentCategory());
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory() == POP)
			output_print_stream.println(popQuestions.removeFirst());
		if (currentCategory() == SCIENCE)
			output_print_stream.println(scienceQuestions.removeFirst());
		if (currentCategory() == SPORTS)
			output_print_stream.println(sportsQuestions.removeFirst());
		if (currentCategory() == ROCK)
			output_print_stream.println(rockQuestions.removeFirst());
	}
	
	
	private String currentCategory() {
		if (places[currentPlayer] == 0) return POP;
		if (places[currentPlayer] == 4) return POP;
		if (places[currentPlayer] == 8) return POP;
		if (places[currentPlayer] == 1) return SCIENCE;
		if (places[currentPlayer] == 5) return SCIENCE;
		if (places[currentPlayer] == 9) return SCIENCE;
		if (places[currentPlayer] == 2) return SPORTS;
		if (places[currentPlayer] == 6) return SPORTS;
		if (places[currentPlayer] == 10) return SPORTS;
		return ROCK;
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				output_print_stream.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				output_print_stream.println(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");
				
				boolean gameIsOver = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = FIRST_PLAYER;
				
				return gameIsOver;
			} else {
				currentPlayer++;
				if (currentPlayer == players.size()) currentPlayer = FIRST_PLAYER;
				return GAME_NOT_OVER;
			}
			
			
			
		} else {
		
			output_print_stream.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			output_print_stream.println(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()) currentPlayer = FIRST_PLAYER;

			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		output_print_stream.println("Question was incorrectly answered");
		output_print_stream.println(players.get(currentPlayer) + " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()) currentPlayer = FIRST_PLAYER;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
