namespace Trivia.Tests
{
    using System.IO;
    using System.Text;
    using Xunit;

    public class GoldenMaster
    {
        [Fact]
        public void Test1()
        {
            var goldenMaster = File.ReadAllText("golden_master.txt");

            Assert.True(goldenMaster.Length > 0);

            var stringBuilder = new StringBuilder();
            using(var writer = new StringWriter(stringBuilder)){
                System.Console.SetOut(writer);
                
                GameRunner.Main(new []{"12"});
            }

            Assert.True(stringBuilder.ToString().Length > 0);
            Assert.Equal(goldenMaster, stringBuilder.ToString());
        }
    }
}
