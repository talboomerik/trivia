using System;
using System.Collections.Generic;
using System.Linq;

namespace UglyTrivia
{
    public class Game
    {
        private const int MinimumNumberOfPlayers = 2;
        private const int PlayerStartPosition = 0;
        private const int PlayerStartAmountOfCoins = 0;
        private const bool PlayerStartsInPenaltyBox = false;
        private const int NumberOfLocationsOnTheBoard = 12;
        private const int NumberOfCoinsNeededToWin = 6;
        private const int FirstPlayerIndex = 0;
        private const string MessageAnswerIsCorrect = "Answer was correct!!!!";
        private const string MessageAnswerIsIncorrect = "Question was incorrectly answered";
        List<string> players = new List<string>();

        int[] playerLocations = new int[6];
        int[] purses = new int[6];

        bool[] playerIsInPenaltyBox = new bool[6];

        LinkedList<string> popQuestions = new LinkedList<string>();
        LinkedList<string> scienceQuestions = new LinkedList<string>();
        LinkedList<string> sportsQuestions = new LinkedList<string>();
        LinkedList<string> rockQuestions = new LinkedList<string>();

        int currentPlayerIndex;
        bool isGettingOutOfPenaltyBox;
        private string PopQuestionCategory = "Pop";
        private string ScienceQuestionCategory = "Science";
        private string SportsQuestionCategory = "Sports";
        private string RockQuestionCategory = "Rock";

        public Game()
        {
            currentPlayerIndex = FirstPlayerIndex;

            for (int i = 0; i < 50; i++)
            {
                popQuestions.AddLast("Pop Question " + i);
                scienceQuestions.AddLast("Science Question " + i);
                sportsQuestions.AddLast("Sports Question " + i);
                rockQuestions.AddLast(createRockQuestion(i));
            }
        }

        public String createRockQuestion(int index)
        {
            return "Rock Question " + index;
        }

        public bool HasEnoughPlayers()
        {
            return (NumberOfPlayers() >= MinimumNumberOfPlayers);
        }

        public bool add(String playerName)
        {
            players.Add(playerName);
            var addedPlayerIndex = NumberOfPlayers();
            playerLocations[addedPlayerIndex] = PlayerStartPosition;
            purses[addedPlayerIndex] = PlayerStartAmountOfCoins;
            playerIsInPenaltyBox[addedPlayerIndex] = PlayerStartsInPenaltyBox;

            Console.WriteLine(playerName + " was added");
            Console.WriteLine("They are player number " + players.Count);
            return true;
        }

        public int NumberOfPlayers()
        {
            return players.Count;
        }

        public void MovePlayerAndAskQuestion(int rolledNumber)
        {
            Console.WriteLine(players[currentPlayerIndex] + " is the current player");
            Console.WriteLine("They have rolled a " + rolledNumber);

            if (playerIsInPenaltyBox[currentPlayerIndex])
            {
                if (rolledNumber % 2 != 0)
                {
                    isGettingOutOfPenaltyBox = true;

                    Console.WriteLine(players[currentPlayerIndex] + " is getting out of the penalty box");
                    playerLocations[currentPlayerIndex] = playerLocations[currentPlayerIndex] + rolledNumber;

                    if (playerLocations[currentPlayerIndex] > 11)
                        playerLocations[currentPlayerIndex] = playerLocations[currentPlayerIndex] - NumberOfLocationsOnTheBoard;

                    Console.WriteLine(players[currentPlayerIndex]
                            + "'s new location is "
                            + playerLocations[currentPlayerIndex]);
                    Console.WriteLine("The category is " + currentCategory());
                    askQuestion();
                }
                else
                {
                    Console.WriteLine(players[currentPlayerIndex] + " is not getting out of the penalty box");
                    isGettingOutOfPenaltyBox = false;
                }
            }
            else
            {
                playerLocations[currentPlayerIndex] = playerLocations[currentPlayerIndex] + rolledNumber;
                if (playerLocations[currentPlayerIndex] > 11) playerLocations[currentPlayerIndex] = playerLocations[currentPlayerIndex] - NumberOfLocationsOnTheBoard;

                Console.WriteLine(players[currentPlayerIndex]
                        + "'s new location is "
                        + playerLocations[currentPlayerIndex]);
                Console.WriteLine("The category is " + currentCategory());
                askQuestion();
            }
        }

        private void askQuestion()
        {
            if (currentCategory() == PopQuestionCategory)
            {
                Console.WriteLine(popQuestions.First());
                popQuestions.RemoveFirst();
            }
            if (currentCategory() == ScienceQuestionCategory)
            {
                Console.WriteLine(scienceQuestions.First());
                scienceQuestions.RemoveFirst();
            }
            if (currentCategory() == SportsQuestionCategory)
            {
                Console.WriteLine(sportsQuestions.First());
                sportsQuestions.RemoveFirst();
            }
            if (currentCategory() == RockQuestionCategory)
            {
                Console.WriteLine(rockQuestions.First());
                rockQuestions.RemoveFirst();
            }
        }

        private String currentCategory()
        {
            if (playerLocations[currentPlayerIndex] == 0) return PopQuestionCategory;
            if (playerLocations[currentPlayerIndex] == 4) return PopQuestionCategory;
            if (playerLocations[currentPlayerIndex] == 8) return PopQuestionCategory;
            if (playerLocations[currentPlayerIndex] == 1) return ScienceQuestionCategory;
            if (playerLocations[currentPlayerIndex] == 5) return ScienceQuestionCategory;
            if (playerLocations[currentPlayerIndex] == 9) return ScienceQuestionCategory;
            if (playerLocations[currentPlayerIndex] == 2) return SportsQuestionCategory;
            if (playerLocations[currentPlayerIndex] == 6) return SportsQuestionCategory;
            if (playerLocations[currentPlayerIndex] == 10) return SportsQuestionCategory;
            return RockQuestionCategory;
        }

        public bool PlayerAnsweredCorrectly()
        {
            if (playerIsInPenaltyBox[currentPlayerIndex])
            {
                if (isGettingOutOfPenaltyBox)
                {
                    Console.WriteLine(MessageAnswerIsCorrect);
                    purses[currentPlayerIndex]++;
                    Console.WriteLine(players[currentPlayerIndex]
                            + " now has "
                            + purses[currentPlayerIndex]
                            + " Gold Coins.");

                    bool isPlayerStillNotVictorious = IsPlayerStillNotVictorious();
                    currentPlayerIndex++;
                    if (currentPlayerIndex == players.Count) currentPlayerIndex = FirstPlayerIndex;

                    return isPlayerStillNotVictorious;
                }
                else
                {
                    currentPlayerIndex++;
                    if (currentPlayerIndex == players.Count) currentPlayerIndex = FirstPlayerIndex;
                    return true;
                }
            }
            else
            {
                Console.WriteLine(MessageAnswerIsCorrect);
                purses[currentPlayerIndex]++;
                Console.WriteLine(players[currentPlayerIndex]
                        + " now has "
                        + purses[currentPlayerIndex]
                        + " Gold Coins.");

                bool winner = IsPlayerStillNotVictorious();
                currentPlayerIndex++;
                if (currentPlayerIndex == players.Count) currentPlayerIndex = FirstPlayerIndex;

                return winner;
            }
        }

        public bool PlayerAnsweredIncorrectly()
        {
            Console.WriteLine(MessageAnswerIsIncorrect);
            Console.WriteLine(players[currentPlayerIndex] + " was sent to the penalty box");
            playerIsInPenaltyBox[currentPlayerIndex] = true;

            currentPlayerIndex++;

            if (currentPlayerIndex == players.Count)
                currentPlayerIndex = FirstPlayerIndex;

            return true;
        }

        private bool IsPlayerStillNotVictorious()
        {
            return purses[currentPlayerIndex] != NumberOfCoinsNeededToWin;
        }
    }
}
