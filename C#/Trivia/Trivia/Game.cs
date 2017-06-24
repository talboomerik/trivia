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

        private readonly List<string> _players = new List<string>();

        readonly int[] _playerLocations = new int[6];
        private readonly int[] _purses = new int[6];

        readonly bool[] _playerIsInPenaltyBox = new bool[6];

        private readonly LinkedList<string> _popQuestions = new LinkedList<string>();
        readonly LinkedList<string> _scienceQuestions = new LinkedList<string>();
        readonly LinkedList<string> _sportsQuestions = new LinkedList<string>();
        readonly LinkedList<string> _rockQuestions = new LinkedList<string>();

        int _currentPlayerIndex;
        bool _isGettingOutOfPenaltyBox;

        private const string PopQuestionCategory = "Pop";
        private const string ScienceQuestionCategory = "Science";
        private const string SportsQuestionCategory = "Sports";
        private const string RockQuestionCategory = "Rock";

        public Game()
        {
            _currentPlayerIndex = FirstPlayerIndex;

            for (int i = 0; i < 50; i++)
            {
                _popQuestions.AddLast("Pop Question " + i);
                _scienceQuestions.AddLast("Science Question " + i);
                _sportsQuestions.AddLast("Sports Question " + i);
                _rockQuestions.AddLast(CreateRockQuestion(i));
            }
        }

        public String CreateRockQuestion(int index)
        {
            return "Rock Question " + index;
        }

        public bool HasEnoughPlayers()
        {
            return (NumberOfPlayers() >= MinimumNumberOfPlayers);
        }

        public bool Add(String playerName)
        {
            _players.Add(playerName);
            var addedPlayerIndex = NumberOfPlayers();
            _playerLocations[addedPlayerIndex] = PlayerStartPosition;
            _purses[addedPlayerIndex] = PlayerStartAmountOfCoins;
            _playerIsInPenaltyBox[addedPlayerIndex] = PlayerStartsInPenaltyBox;

            Console.WriteLine(playerName + " was added");
            Console.WriteLine("They are player number " + _players.Count);
            return true;
        }

        public int NumberOfPlayers()
        {
            return _players.Count;
        }

        public void MovePlayerAndAskQuestion(int rolledNumber)
        {
            Console.WriteLine(_players[_currentPlayerIndex] + " is the current player");
            Console.WriteLine("They have rolled a " + rolledNumber);

            if (_playerIsInPenaltyBox[_currentPlayerIndex])
            {
                if (rolledNumber % 2 != 0)
                {
                    _isGettingOutOfPenaltyBox = true;

                    Console.WriteLine(_players[_currentPlayerIndex] + " is getting out of the penalty box");
                    _playerLocations[_currentPlayerIndex] = _playerLocations[_currentPlayerIndex] + rolledNumber;

                    if (_playerLocations[_currentPlayerIndex] > 11)
                        _playerLocations[_currentPlayerIndex] = _playerLocations[_currentPlayerIndex] - NumberOfLocationsOnTheBoard;

                    Console.WriteLine(_players[_currentPlayerIndex]
                            + "'s new location is "
                            + _playerLocations[_currentPlayerIndex]);
                    Console.WriteLine("The category is " + CurrentCategory());
                    AskQuestion();
                }
                else
                {
                    Console.WriteLine(_players[_currentPlayerIndex] + " is not getting out of the penalty box");
                    _isGettingOutOfPenaltyBox = false;
                }
            }
            else
            {
                _playerLocations[_currentPlayerIndex] = _playerLocations[_currentPlayerIndex] + rolledNumber;
                if (_playerLocations[_currentPlayerIndex] > 11) _playerLocations[_currentPlayerIndex] = _playerLocations[_currentPlayerIndex] - NumberOfLocationsOnTheBoard;

                Console.WriteLine(_players[_currentPlayerIndex]
                        + "'s new location is "
                        + _playerLocations[_currentPlayerIndex]);
                Console.WriteLine("The category is " + CurrentCategory());
                AskQuestion();
            }
        }

        private void AskQuestion()
        {
            if (CurrentCategory() == PopQuestionCategory)
            {
                Console.WriteLine(_popQuestions.First());
                _popQuestions.RemoveFirst();
            }
            if (CurrentCategory() == ScienceQuestionCategory)
            {
                Console.WriteLine(_scienceQuestions.First());
                _scienceQuestions.RemoveFirst();
            }
            if (CurrentCategory() == SportsQuestionCategory)
            {
                Console.WriteLine(_sportsQuestions.First());
                _sportsQuestions.RemoveFirst();
            }
            if (CurrentCategory() == RockQuestionCategory)
            {
                Console.WriteLine(_rockQuestions.First());
                _rockQuestions.RemoveFirst();
            }
        }

        private String CurrentCategory()
        {
            if (_playerLocations[_currentPlayerIndex] == 0) return PopQuestionCategory;
            if (_playerLocations[_currentPlayerIndex] == 4) return PopQuestionCategory;
            if (_playerLocations[_currentPlayerIndex] == 8) return PopQuestionCategory;
            if (_playerLocations[_currentPlayerIndex] == 1) return ScienceQuestionCategory;
            if (_playerLocations[_currentPlayerIndex] == 5) return ScienceQuestionCategory;
            if (_playerLocations[_currentPlayerIndex] == 9) return ScienceQuestionCategory;
            if (_playerLocations[_currentPlayerIndex] == 2) return SportsQuestionCategory;
            if (_playerLocations[_currentPlayerIndex] == 6) return SportsQuestionCategory;
            if (_playerLocations[_currentPlayerIndex] == 10) return SportsQuestionCategory;
            return RockQuestionCategory;
        }

        public bool PlayerAnsweredCorrectly()
        {
            if (_playerIsInPenaltyBox[_currentPlayerIndex])
            {
                if (_isGettingOutOfPenaltyBox)
                {
                    Console.WriteLine(MessageAnswerIsCorrect);
                    _purses[_currentPlayerIndex]++;
                    Console.WriteLine(_players[_currentPlayerIndex]
                            + " now has "
                            + _purses[_currentPlayerIndex]
                            + " Gold Coins.");

                    bool isPlayerStillNotVictorious = IsPlayerStillNotVictorious();
                    _currentPlayerIndex++;
                    if (_currentPlayerIndex == _players.Count) _currentPlayerIndex = FirstPlayerIndex;

                    return isPlayerStillNotVictorious;
                }
                else
                {
                    _currentPlayerIndex++;
                    if (_currentPlayerIndex == _players.Count) _currentPlayerIndex = FirstPlayerIndex;
                    return true;
                }
            }
            else
            {
                Console.WriteLine(MessageAnswerIsCorrect);
                _purses[_currentPlayerIndex]++;
                Console.WriteLine(_players[_currentPlayerIndex]
                        + " now has "
                        + _purses[_currentPlayerIndex]
                        + " Gold Coins.");

                bool winner = IsPlayerStillNotVictorious();
                _currentPlayerIndex++;
                if (_currentPlayerIndex == _players.Count) _currentPlayerIndex = FirstPlayerIndex;

                return winner;
            }
        }

        public bool PlayerAnsweredIncorrectly()
        {
            Console.WriteLine(MessageAnswerIsIncorrect);
            Console.WriteLine(_players[_currentPlayerIndex] + " was sent to the penalty box");
            _playerIsInPenaltyBox[_currentPlayerIndex] = true;

            _currentPlayerIndex++;

            if (_currentPlayerIndex == _players.Count)
                _currentPlayerIndex = FirstPlayerIndex;

            return true;
        }

        private bool IsPlayerStillNotVictorious()
        {
            return _purses[_currentPlayerIndex] != NumberOfCoinsNeededToWin;
        }
    }
}
