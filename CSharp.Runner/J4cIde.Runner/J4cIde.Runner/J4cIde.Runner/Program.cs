

namespace J4cIde.Runner
{
    class Program
    {
        static void Main(string[] args)
        {
            WindowUtility.MoveWindowToCenter();
            J4cIdeRunnerServer runnerServer = new J4cIdeRunnerServer();
            runnerServer.StartTCPChannel();
        }
    }
}
