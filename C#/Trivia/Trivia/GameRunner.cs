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
                var rolledNumber = randomizer.Next(5) + 1;
                aGame.MovePlayerAndAskQuestion(rolledNumber);

                var playerAnswerNumber = randomizer.Next(9);
                if (playerAnswerNumber == 7)
                {
                    noPlayerIsVictorious = aGame.PlayerAnsweredIncorrectly();
                }
                else
                {
                    noPlayerIsVictorious = aGame.PlayerAnsweredCorrectly();
                }
            } while (noPlayerIsVictorious);
            Console.ReadLine();
        }
    }
}
