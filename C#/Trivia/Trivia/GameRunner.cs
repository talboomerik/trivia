using System;
using UglyTrivia;

namespace Trivia
{
    public class GameRunner
    {
        private static bool noPlayerIsVictorious;

        public static void Main(String[] args)
        {
            var aGame = new Game();

            aGame.add("Chet");
            aGame.add("Pat");
            aGame.add("Sue");
            
            var randomizer = (args.Length == 0 ? new Random() : new Random(args[0].GetHashCode()));

            do
            {
                aGame.MovePlayerAndAskQuestion(randomizer.Next(5) + 1);

                if (randomizer.Next(9) == 7)
                {
                    noPlayerIsVictorious = aGame.wrongAnswer();
                }
                else
                {
                    noPlayerIsVictorious = aGame.wasCorrectlyAnswered();
                }
            } while (noPlayerIsVictorious);
            Console.ReadLine();
        }
    }
}
