using NUnit.Framework;
using Trivia;

namespace Tests.Unit
{
    public class SomeClass
    {
        [Test]
        public void OneIsOdd()
        {
            int input = 1;

            Assert.IsTrue(DiceComputation.IsOdd(input));
        }

        [Test]
        public void ThreeIsOdd()
        {
            int input = 3;

            Assert.IsTrue(DiceComputation.IsOdd(input));
        }

        [Test]
        public void FiveIsOdd()
        {
            int input = 5;

            Assert.IsTrue(DiceComputation.IsOdd(input));
        }

        [Test]
        public void TwoIsNotOdd()
        {
            int input = 2;

            Assert.IsFalse(DiceComputation.IsOdd(input));
        }

        [Test]
        public void FourIsNotOdd()
        {
            int input = 4;

            Assert.IsFalse(DiceComputation.IsOdd(input));
        }

        [Test]
        public void SixIsEven()
        {
            int input = 6;

            Assert.IsFalse(DiceComputation.IsOdd(input));
        }
    }
}