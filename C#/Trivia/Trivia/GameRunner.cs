using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using UglyTrivia;

namespace Trivia
{
    public class GameRunner
    {
        private const int CorrectGuessProbability = 9;
        private const int WrongGuess = 7;
        private const int NumberOfDieSides = 5;

        private static bool gameIsRunning;

        public static void Main(String[] args)
        {
            Game aGame = new Game(new ConsoleWriter());

            aGame.AddPlayerWithName("Chet");
            aGame.AddPlayerWithName("Pat");
            aGame.AddPlayerWithName("Sue");
            
            Random rand = (args.Length == 0 ? new Random() : new Random(args[0].GetHashCode()));

            do
            {

                aGame.Roll(rand.Next(NumberOfDieSides) + 1);

                if (rand.Next(CorrectGuessProbability) == WrongGuess)
                {
                    gameIsRunning = aGame.wrongAnswer();
                }
                else
                {
                    gameIsRunning = aGame.wasCorrectlyAnswered();
                }



            } while (gameIsRunning);

        }


    }

}

