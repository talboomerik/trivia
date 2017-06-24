using System;
using UglyTrivia;

namespace Trivia
{
    public class GameRunner
    {
        private static bool notAWinner;

        public static void Main(String[] args)
        {
            var aGame = new Game();

            aGame.add("Chet");
            aGame.add("Pat");
            aGame.add("Sue");
            
            var rolledNumber = (args.Length == 0 ? new Random() : new Random(args[0].GetHashCode()));

            do
            {
                aGame.MovePlayerAndAskQuestion(rolledNumber.Next(5) + 1);

                if (rolledNumber.Next(9) == 7)
                {
                    notAWinner = aGame.wrongAnswer();
                }
                else
                {
                    notAWinner = aGame.wasCorrectlyAnswered();
                }
            } while (notAWinner);
            Console.ReadLine();
        }
    }
}
