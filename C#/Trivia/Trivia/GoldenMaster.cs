using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;

namespace Trivia
{
    [TestFixture]
    class GoldenMaster
    {
        private const string c_Seed = "SOME_SEED";

        private const string c_ExpectedOutput = "";

        [TestCase(c_Seed, Result = c_ExpectedOutput)]
        public string CharacterizationTest(string seedArgument)
        {
            using (var output = new StringWriter()) 
            {
                Console.SetOut(output);
                GameRunner.Main(new string[]{c_Seed});
                return output.ToString();
            }
        }
    }
}
