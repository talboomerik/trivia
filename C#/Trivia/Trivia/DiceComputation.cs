namespace Trivia
{
    public class DiceComputation
    {
        public static bool IsOdd(int input)
        {
            return  !((input / 2) * 2 == input);
        }
    }
}