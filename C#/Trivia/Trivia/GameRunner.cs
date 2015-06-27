using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using UglyTrivia;

namespace Trivia
{
    public class GameRunner
    {

        private static bool gameIsRunning;

        public static void Main(String[] args)
        {
            Game aGame = new Game();

            aGame.add("Chet");
            aGame.add("Pat");
            aGame.add("Sue");
            
            Random rand = (args.Length == 0 ? new Random() : new Random(args[0].GetHashCode()));

            do
            {

                aGame.roll(rand.Next(5) + 1);

                if (rand.Next(9) == 7)
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

