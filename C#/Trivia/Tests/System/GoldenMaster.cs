using System;
using System.IO;
using ApprovalTests;
using NUnit.Framework;
using Trivia;

namespace Tests.System
{
    public class GoldenMaster
    {
        [Test]
        public void GameWhenRandomIsOne()
        {
            StringWriter sw = new StringWriter();
            Console.SetOut(sw);

            GameRunner.Main(new string[]{"1"});

            Approvals.Verify(sw.ToString());
        }

        [Test]
        public void GameWhenRandomIsTwo()
        {
            StringWriter sw = new StringWriter();
            Console.SetOut(sw);

            GameRunner.Main(new string[] { "2" });

            Approvals.Verify(sw.ToString());
        }

        [Test]
        public void GameWhenRandomIsThree()
        {
            StringWriter sw = new StringWriter();
            Console.SetOut(sw);

            GameRunner.Main(new string[] { "3" });

            Approvals.Verify(sw.ToString());
        }
    }
}