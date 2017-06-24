using System;
using System.Collections.Generic;
using System.Linq;

namespace UglyTrivia
{
    public class Game
    {
        List<string> players = new List<string>();

        int[] playerLocations = new int[6];
        int[] purses = new int[6];

        bool[] inPenaltyBox = new bool[6];

        LinkedList<string> popQuestions = new LinkedList<string>();
        LinkedList<string> scienceQuestions = new LinkedList<string>();
        LinkedList<string> sportsQuestions = new LinkedList<string>();
        LinkedList<string> rockQuestions = new LinkedList<string>();

        int currentPlayerIndex = 0;
        bool isGettingOutOfPenaltyBox;

        public Game()
        {
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
            return (howManyPlayers() >= 2);
        }

        public bool add(String playerName)
        {
            players.Add(playerName);
            var addedPlayerIndex = howManyPlayers();
            playerLocations[addedPlayerIndex] = 0;
            purses[addedPlayerIndex] = 0;
            inPenaltyBox[addedPlayerIndex] = false;

            Console.WriteLine(playerName + " was added");
            Console.WriteLine("They are player number " + players.Count);
            return true;
        }

        public int howManyPlayers()
        {
            return players.Count;
        }

        public void MovePlayerAndAskQuestion(int rolledNumber)
        {
            Console.WriteLine(players[currentPlayerIndex] + " is the current player");
            Console.WriteLine("They have rolled a " + rolledNumber);

            if (inPenaltyBox[currentPlayerIndex])
            {
                if (rolledNumber % 2 != 0)
                {
                    isGettingOutOfPenaltyBox = true;

                    Console.WriteLine(players[currentPlayerIndex] + " is getting out of the penalty box");
                    playerLocations[currentPlayerIndex] = playerLocations[currentPlayerIndex] + rolledNumber;
                    if (playerLocations[currentPlayerIndex] > 11) playerLocations[currentPlayerIndex] = playerLocations[currentPlayerIndex] - 12;

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
                if (playerLocations[currentPlayerIndex] > 11) playerLocations[currentPlayerIndex] = playerLocations[currentPlayerIndex] - 12;

                Console.WriteLine(players[currentPlayerIndex]
                        + "'s new location is "
                        + playerLocations[currentPlayerIndex]);
                Console.WriteLine("The category is " + currentCategory());
                askQuestion();
            }
        }

        private void askQuestion()
        {
            if (currentCategory() == "Pop")
            {
                Console.WriteLine(popQuestions.First());
                popQuestions.RemoveFirst();
            }
            if (currentCategory() == "Science")
            {
                Console.WriteLine(scienceQuestions.First());
                scienceQuestions.RemoveFirst();
            }
            if (currentCategory() == "Sports")
            {
                Console.WriteLine(sportsQuestions.First());
                sportsQuestions.RemoveFirst();
            }
            if (currentCategory() == "Rock")
            {
                Console.WriteLine(rockQuestions.First());
                rockQuestions.RemoveFirst();
            }
        }

        private String currentCategory()
        {
            if (playerLocations[currentPlayerIndex] == 0) return "Pop";
            if (playerLocations[currentPlayerIndex] == 4) return "Pop";
            if (playerLocations[currentPlayerIndex] == 8) return "Pop";
            if (playerLocations[currentPlayerIndex] == 1) return "Science";
            if (playerLocations[currentPlayerIndex] == 5) return "Science";
            if (playerLocations[currentPlayerIndex] == 9) return "Science";
            if (playerLocations[currentPlayerIndex] == 2) return "Sports";
            if (playerLocations[currentPlayerIndex] == 6) return "Sports";
            if (playerLocations[currentPlayerIndex] == 10) return "Sports";
            return "Rock";
        }

        public bool wasCorrectlyAnswered()
        {
            if (inPenaltyBox[currentPlayerIndex])
            {
                if (isGettingOutOfPenaltyBox)
                {
                    Console.WriteLine("Answer was correct!!!!");
                    purses[currentPlayerIndex]++;
                    Console.WriteLine(players[currentPlayerIndex]
                            + " now has "
                            + purses[currentPlayerIndex]
                            + " Gold Coins.");

                    bool winner = didPlayerWin();
                    currentPlayerIndex++;
                    if (currentPlayerIndex == players.Count) currentPlayerIndex = 0;

                    return winner;
                }
                else
                {
                    currentPlayerIndex++;
                    if (currentPlayerIndex == players.Count) currentPlayerIndex = 0;
                    return true;
                }
            }
            else
            {
                Console.WriteLine("Answer was corrent!!!!");
                purses[currentPlayerIndex]++;
                Console.WriteLine(players[currentPlayerIndex]
                        + " now has "
                        + purses[currentPlayerIndex]
                        + " Gold Coins.");

                bool winner = didPlayerWin();
                currentPlayerIndex++;
                if (currentPlayerIndex == players.Count) currentPlayerIndex = 0;

                return winner;
            }
        }

        public bool wrongAnswer()
        {
            Console.WriteLine("Question was incorrectly answered");
            Console.WriteLine(players[currentPlayerIndex] + " was sent to the penalty box");
            inPenaltyBox[currentPlayerIndex] = true;

            currentPlayerIndex++;
            if (currentPlayerIndex == players.Count) currentPlayerIndex = 0;
            return true;
        }

        private bool didPlayerWin()
        {
            return !(purses[currentPlayerIndex] == 6);
        }
    }
}
