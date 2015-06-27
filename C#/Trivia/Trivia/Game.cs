using System;
using System.Collections.Generic;
using System.Linq;

namespace Trivia
{
    public class Game
    {
        List<string> players = new List<string>();

        private const Int32 MaximumNumberOfPlayers = 6;
        int[] boardPositions = new int[MaximumNumberOfPlayers];
        int[] victoryPoints = new int[MaximumNumberOfPlayers];

        bool[] inPenaltyBox = new bool[MaximumNumberOfPlayers];

        LinkedList<string> popQuestions = new LinkedList<string>();
        LinkedList<string> scienceQuestions = new LinkedList<string>();
        LinkedList<string> sportsQuestions = new LinkedList<string>();
        LinkedList<string> rockQuestions = new LinkedList<string>();

        int _currentPlayer = 0;
        bool _isGettingOutOfPenaltyBox;

        public Game()
        {
            for (int i = 0; i < 50; i++)
            {
                popQuestions.AddLast("Pop Question " + i);
                scienceQuestions.AddLast(("Science Question " + i));
                sportsQuestions.AddLast(("Sports Question " + i));
                rockQuestions.AddLast(CreateRockQuestion(i));
            }
        }

        public String CreateRockQuestion(int index)
        {
            return "Rock Question " + index;
        }

        public bool IsPlayable()
        {
            const Int32 minimumNumberOfPlayers = 2;
            bool thereAreMinimumNumberOfPlayers = GetNumberOfPlayersParticipating() >= minimumNumberOfPlayers;
            return thereAreMinimumNumberOfPlayers;
        }

        public bool AddPlayer(String playerName)
        {
            players.Add(playerName);
            boardPositions[GetNumberOfPlayersParticipating()] = 0;
            victoryPoints[GetNumberOfPlayersParticipating()] = 0;
            inPenaltyBox[GetNumberOfPlayersParticipating()] = false;

            Console.WriteLine(playerName + " was added");
            Console.WriteLine("They are player number " + players.Count);
            return true;
        }

        public int GetNumberOfPlayersParticipating()
        {
            return players.Count;
        }

        public void MoveCurrentPlayer(int roll)
        {
            Console.WriteLine(players[_currentPlayer] + " is the current player");
            Console.WriteLine("They have rolled a " + roll);

            if (inPenaltyBox[_currentPlayer])
            {
                if (DiceComputation.IsOdd(roll))
                {
                    _isGettingOutOfPenaltyBox = true;

                    Console.WriteLine(players[_currentPlayer] + " is getting out of the penalty box");
                    boardPositions[_currentPlayer] = boardPositions[_currentPlayer] + roll;

                    bool boardHasReachedTheEnd = boardPositions[_currentPlayer] > 11;
                    if (boardHasReachedTheEnd) boardPositions[_currentPlayer] = boardPositions[_currentPlayer] - 12;

                    Console.WriteLine(players[_currentPlayer]
                            + "'s new location is "
                            + boardPositions[_currentPlayer]);
                    Console.WriteLine("The category is " + CurrentCategory());
                    AskQuestion();
                }
                else
                {
                    Console.WriteLine(players[_currentPlayer] + " is not getting out of the penalty box");
                    _isGettingOutOfPenaltyBox = false;
                }
            }
            else
            {

                boardPositions[_currentPlayer] = boardPositions[_currentPlayer] + roll;

                bool boardHasReachedTheEnd = boardPositions[_currentPlayer] > 11;
                if (boardHasReachedTheEnd) boardPositions[_currentPlayer] = boardPositions[_currentPlayer] - 12;

                Console.WriteLine(players[_currentPlayer]
                        + "'s new location is "
                        + boardPositions[_currentPlayer]);
                Console.WriteLine("The category is " + CurrentCategory());
                AskQuestion();
            }
        }

        private void AskQuestion()
        {
            if (CurrentCategory() == "Pop")
            {
                Console.WriteLine(popQuestions.First());
                popQuestions.RemoveFirst();
            }
            if (CurrentCategory() == "Science")
            {
                Console.WriteLine(scienceQuestions.First());
                scienceQuestions.RemoveFirst();
            }
            if (CurrentCategory() == "Sports")
            {
                Console.WriteLine(sportsQuestions.First());
                sportsQuestions.RemoveFirst();
            }
            if (CurrentCategory() == "Rock")
            {
                Console.WriteLine(rockQuestions.First());
                rockQuestions.RemoveFirst();
            }
        }

        private String CurrentCategory()
        {
            if (boardPositions[_currentPlayer] == 0) return "Pop";
            if (boardPositions[_currentPlayer] == 4) return "Pop";
            if (boardPositions[_currentPlayer] == 8) return "Pop";
            if (boardPositions[_currentPlayer] == 1) return "Science";
            if (boardPositions[_currentPlayer] == 5) return "Science";
            if (boardPositions[_currentPlayer] == 9) return "Science";
            if (boardPositions[_currentPlayer] == 2) return "Sports";
            if (boardPositions[_currentPlayer] == 6) return "Sports";
            if (boardPositions[_currentPlayer] == 10) return "Sports";
            return "Rock";
        }

        public bool WasCorrectlyAnswered()
        {
            if (inPenaltyBox[_currentPlayer])
            {
                if (_isGettingOutOfPenaltyBox)
                {
                    Console.WriteLine("Answer was correct!!!!");
                    victoryPoints[_currentPlayer]++;
                    Console.WriteLine(players[_currentPlayer]
                            + " now has "
                            + victoryPoints[_currentPlayer]
                            + " Gold Coins.");

                    bool winner = DidPlayerWin();
                    _currentPlayer++;
                    if (currentPlayerIsLast()) _currentPlayer = 0;

                    return winner;
                }
                else
                {
                    _currentPlayer++;
                    if (currentPlayerIsLast()) _currentPlayer = 0;

                    return true;
                }
            }
            else
            {

                Console.WriteLine("Answer was corrent!!!!");
                victoryPoints[_currentPlayer]++;
                Console.WriteLine(players[_currentPlayer]
                        + " now has "
                        + victoryPoints[_currentPlayer]
                        + " Gold Coins.");

                bool winner = DidPlayerWin();
                _currentPlayer++;
                if (currentPlayerIsLast()) _currentPlayer = 0;

                return winner;
            }
        }

        public bool WrongAnswer()
        {
            Console.WriteLine("Question was incorrectly answered");
            Console.WriteLine(players[_currentPlayer] + " was sent to the penalty box");
            inPenaltyBox[_currentPlayer] = true;

            _currentPlayer++;
            if (currentPlayerIsLast()) _currentPlayer = 0;
            return true;
        }

        private bool currentPlayerIsLast()
        {
            return _currentPlayer == players.Count;
        }

        private bool DidPlayerWin()
        {
            bool currentPlayerWonSixPoints = victoryPoints[_currentPlayer] == 6;
            return !currentPlayerWonSixPoints;
        }
    }

}
