using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.IO;
using System.Diagnostics;
using System.Threading;

namespace J4cIde.Runner
{
    public class J4cIdeRunnerServer
    {

        private static readonly IPAddress INET_ADDRESS = IPAddress.Any;
        private static readonly int BUFFER_MAX_SIZE = 1024*4;
        private static readonly int DEFAULT_PORT = 10799;
 
        private Stopwatch stopWatch = new Stopwatch();
        private TcpListener Listener;

        public J4cIdeRunnerServer() : this(INET_ADDRESS, DEFAULT_PORT) { }

        public J4cIdeRunnerServer(IPAddress address, int port)
        {
            Listener = new TcpListener(address, port);
        }

        public void StartTcpLinkServer()
        {
            /* começando socket tcp */
            Listener.Start();
            Console.WriteLine("Aguardando dados para inicialização da aplicação.");
            Console.WriteLine("Escutando na porta {0} no endereço {1} por" +
                " comunicação TCP.", DEFAULT_PORT, INET_ADDRESS.ToString());
            /* aguardando conexão com a ide */
            TcpClient ideClient = Listener.AcceptTcpClient();
            Console.WriteLine("Sincronizando com a IDE");
            NetworkStream stream = ideClient.GetStream();
            /* Recebe o arquivo para rodar */
            string FilePath = ReceivedFromClient(ref stream);
            SendToClient(ref stream, string.Format("O arquvido \"{0}\" foi recebido" +
                " pelo Runner e será executado.", Path.GetFileName(FilePath)));
            Console.Clear();
            Execute(ref stream, FilePath);
            Listener.Stop();
            Environment.Exit(1000);
        }

        private void Execute(ref NetworkStream stream, string command)
        {
            String name = Path.GetFileName(command);
            Console.Title = "J4cIde Runner 1.0.0 (Running " + name + ")";
            ProcessStartInfo StartInfo = new ProcessStartInfo();
            StartInfo.UseShellExecute = false;
            StartInfo.WindowStyle = ProcessWindowStyle.Normal;
            StartInfo.WorkingDirectory = @"C:\Windows\System32";
            StartInfo.FileName = @"C:\Windows\System32\cmd.exe";
            StartInfo.Verb = "runas";
            StartInfo.Arguments = "/c " + command;
            stopWatch.Start();
            Process process = Process.Start(StartInfo);
            stopWatch.Stop();
            process.EnableRaisingEvents = true;
            process.WaitForExit();
            SendToClient(ref stream, string.Format("A aplicação encerrou com o código {0}.", process.ExitCode));
        }

        private string ReceivedFromClient(ref NetworkStream stream)
        {
            byte[] buffer = new byte[BUFFER_MAX_SIZE];
            int bytesRead = stream.Read(buffer, 0, buffer.Length);
            string dataReceived = Encoding.ASCII.GetString(buffer, 0, bytesRead).Trim();
            return dataReceived;
        }

        private void SendToClient(ref NetworkStream stream, String message)
        {
            byte[] responseData = Encoding.UTF8.GetBytes(message);
            stream.Write(responseData, 0, responseData.Length);
        }
    }
}
