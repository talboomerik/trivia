using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using ApprovalTests;
using ApprovalTests.Reporters;
using NUnit.Framework;

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
            GameRunner.Main(new []{"ANY STRING HERE"});

            Approvals.Verify(sb.ToString());
        }

        [Test]
        [UseReporter(typeof (DiffReporter))]
        public void GoldenMaster2()
        {
            var sb = new StringBuilder();
            var capturedOutput = new StringWriter(sb);
            Console.SetOut(capturedOutput);
            GameRunner.Main(new []{"any other plain old string111!!!&"});

            Approvals.Verify(sb.ToString());
        }
    }
}
