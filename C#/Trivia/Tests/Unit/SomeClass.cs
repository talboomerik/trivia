using NUnit.Framework;

namespace Tests.Unit
{
    public class SomeClass
    {
        [Test]
        public void OneIsOdd()
        {
            int input = 1;

            bool isOdd = !IsEven(input);
            
            Assert.AreEqual(true, isOdd);
        }

        [Test]
        public void ThreeIsOdd()
        {
            int input = 3;
            
            bool isOdd = !IsEven(input); 

            Assert.AreEqual(true, isOdd);
        }

        [Test]
        public void FiveIsOdd()
        {
            int input = 5;

            bool isOdd = !IsEven(input); 

            Assert.AreEqual(true, isOdd);
        }

        [Test]
        public void TwoIsNotOdd()
        {
            int input = 2;

            bool isOdd = !IsEven(input);

            Assert.AreEqual(false, isOdd);
        }

        [Test]
        public void FourIsNotOdd()
        {
            int input = 4;

            bool isOdd = !IsEven(input);

            Assert.AreEqual(false, isOdd);
        }

        [Test]
        public void SixIsNotOdd()
        {
            int input = 6;

            bool isOdd = !IsEven(input);

            Assert.AreEqual(false, isOdd);
        }

        [Test]
        public void SixIsEven()
        {
            int input = 6;

            bool isEven = IsEven(input);
            
            Assert.AreEqual(true, isEven);
        }

        private static bool IsEven(int input)
        {
            return ( ((input / 2) * 2) == input);
        }
    }
}