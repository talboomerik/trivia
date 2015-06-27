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
    public static final String POP = "Pop";
    public static final String SCIENCE = "Science";
    public static final String SPORTS = "Sports";
    public static final String ROCK = "Rock";
    public static final int FIRST_PLAYER = 0;
    public static final boolean GAME_NOT_OVER = true;
    ArrayList players = new ArrayList();

    public int[] places = new int[NUMBER_OF_PLAYERS];
    int[] purses = new int[NUMBER_OF_PURSES];
    public boolean[] inPenaltyBox = new boolean[NUMBER_OF_PENALTY_BOXES];
    LinkedList popQuestions = new LinkedList();

    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    int currentPlayer = 0;

    public boolean isGettingOutOfPenaltyBox;

    private final PrintStream outputPrintStream;

    public Game(PrintStream outputPrintStream) {
        for (int i = 0; i < QUESTIONS_COUNT_IN_EACH_CATEGORY; i++) {
            popQuestions.addLast(POP_QUESTION + i);
            scienceQuestions.addLast((SCIENCE_QUESTION + i));
            sportsQuestions.addLast((SPORTS_QUESTION + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
        this.outputPrintStream = outputPrintStream;
    }

    public String createRockQuestion(int index) {
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

        outputPrintStream.println(playerName + " was added");
        outputPrintStream.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        outputPrintStream.println(players.get(currentPlayer) + " is the current player");
        outputPrintStream.println("They have rolled a " + roll);

        boolean playerIsInPenaltyBox = inPenaltyBox[currentPlayer];
        if (playerIsInPenaltyBox)
            startTurnInPenaltyBox(roll);
        else
            startNormalTurn(roll);
    }

    private void startTurnInPenaltyBox(int roll) {
        boolean getOutOfPenaltyBox = (roll % 2 != 0);
        if (getOutOfPenaltyBox) {
            isGettingOutOfPenaltyBox = true;

            outputPrintStream.println(players.get(currentPlayer) + " is getting out of the penalty box");
            startNormalTurn(roll);
        } else {
            outputPrintStream.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
        }
    }

    private void startNormalTurn(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11)
            places[currentPlayer] = places[currentPlayer] - 12;

        outputPrintStream.println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
        outputPrintStream.println("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        if (currentCategory() == POP)
            outputPrintStream.println(popQuestions.removeFirst());
        if (currentCategory() == SCIENCE)
            outputPrintStream.println(scienceQuestions.removeFirst());
        if (currentCategory() == SPORTS)
            outputPrintStream.println(sportsQuestions.removeFirst());
        if (currentCategory() == ROCK)
            outputPrintStream.println(rockQuestions.removeFirst());
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
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                outputPrintStream.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                outputPrintStream.println(players.get(currentPlayer)
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

            outputPrintStream.println("Answer was corrent!!!!");
            purses[currentPlayer]++;
            outputPrintStream.println(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = FIRST_PLAYER;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        outputPrintStream.println("Question was incorrectly answered");
        outputPrintStream.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = FIRST_PLAYER;
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
