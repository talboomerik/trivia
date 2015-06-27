package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    public static final int NUMBER_OF_PLAYERS = 6;
    public static final int FIRST_PLAYER = 0;
    public static final int NUMBER_OF_QUESTIONS_PER_CATEGORY = 50;
    public static final String QUESTION_CATEGORY_POP = "Pop";
    public static final String QUESTION_CATEGORY_SCIENCE = "Science";
    public static final String QUESTION_CATEGORY_SPORTS = "Sports";
    public static final String QUESTION_CATEGORY_ROCK = "Rock";
    public static final int WINNING_AMOUNT = 6;

    ArrayList players = new ArrayList();
    int[] places = new int[NUMBER_OF_PLAYERS];
    int[] purses = new int[NUMBER_OF_PLAYERS];
    boolean[] inPenaltyBox = new boolean[NUMBER_OF_PLAYERS];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = FIRST_PLAYER;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < NUMBER_OF_QUESTIONS_PER_CATEGORY; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int questionNumber) {
        return "Rock Question " + questionNumber;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {


        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                places[currentPlayer] = places[currentPlayer] + roll;
                if (places[currentPlayer] > 11)
                    places[currentPlayer] = places[currentPlayer] - 12;

                System.out.println(players.get(currentPlayer)
                        + "'s new location is "
                        + places[currentPlayer]);
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11)
                places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(players.get(currentPlayer)
                    + "'s new location is "
                    + places[currentPlayer]);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == QUESTION_CATEGORY_POP)
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == QUESTION_CATEGORY_SCIENCE)
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == QUESTION_CATEGORY_SPORTS)
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == QUESTION_CATEGORY_ROCK)
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (places[currentPlayer] == 0) return QUESTION_CATEGORY_POP;
        if (places[currentPlayer] == 4) return QUESTION_CATEGORY_POP;
        if (places[currentPlayer] == 8) return QUESTION_CATEGORY_POP;
        if (places[currentPlayer] == 1) return QUESTION_CATEGORY_SCIENCE;
        if (places[currentPlayer] == 5) return QUESTION_CATEGORY_SCIENCE;
        if (places[currentPlayer] == 9) return QUESTION_CATEGORY_SCIENCE;
        if (places[currentPlayer] == 2) return QUESTION_CATEGORY_SPORTS;
        if (places[currentPlayer] == 6) return QUESTION_CATEGORY_SPORTS;
        if (places[currentPlayer] == 10) return QUESTION_CATEGORY_SPORTS;
        return QUESTION_CATEGORY_ROCK;
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                System.out.println(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = FIRST_PLAYER;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = FIRST_PLAYER;
                return true;
            }


        } else {

            System.out.println("Answer was correct!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer)
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
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = FIRST_PLAYER;
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == WINNING_AMOUNT);
    }
}
