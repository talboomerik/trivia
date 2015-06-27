using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Trivia
{
    public class GameRunner
    {

        private static bool _gameHasNoWinner;

        public static void Main(String[] args)
        {
            Game aGame = new Game();

            aGame.AddPlayer("Chet");
            aGame.AddPlayer("Pat");
            aGame.AddPlayer("Sue");
            
            Random rand = (args.Length == 0 ? new Random() : new Random(args[0].GetHashCode()));

            do
            {

                aGame.MoveCurrentPlayer(rand.Next(5) + 1);

                if (rand.Next(9) == 7)
                {
                    _gameHasNoWinner = aGame.WrongAnswer();
                }
                else
                {
                    _gameHasNoWinner = aGame.WasCorrectlyAnswered();
                }



            } while (_gameHasNoWinner);

        }
    }

}

