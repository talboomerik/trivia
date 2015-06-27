using System;

namespace Trivia
{
    public class ConsoleWrapper : IConsole
    {
        public void ConsoleWriteLine(string text)
        {
            Console.WriteLine(text);
        }
    }
}