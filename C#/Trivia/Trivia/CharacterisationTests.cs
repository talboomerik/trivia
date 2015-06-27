using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using ApprovalTests;
using ApprovalTests.Reporters;
using NUnit.Framework;
using UglyTrivia;

namespace Trivia
{
    [TestFixture]
    public class CharacterisationTests
    {
        [Test]
        [UseReporter(typeof (DiffReporter))]
        public void GoldenMaster()
        {
            var sb = new StringBuilder();
            var capturedOutput = new StringWriter(sb);
            Console.SetOut(capturedOutput);
            GameRunner.GameLoop(new []{"ANY STRING HERE"}, new ConsoleWriter());

            Approvals.Verify(sb.ToString());
        }

        [Test]
        [UseReporter(typeof (DiffReporter))]
        public void GoldenMaster2()
        {
            var sb = new StringBuilder();
            var capturedOutput = new StringWriter(sb);
            Console.SetOut(capturedOutput);
            GameRunner.GameLoop(new []{"any other plain old string111!!!&"}, new ConsoleWriter());

            Approvals.Verify(sb.ToString());
        }
    }
}
