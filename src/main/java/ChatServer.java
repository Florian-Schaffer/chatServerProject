import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ChatServer {

    private static int PORT_ID = 8088;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);


    public static void main (String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT_ID);
        WriteLogEntriesToLogFile logger = new WriteLogEntriesToLogFile();

        while(true){
            logger.logEventInfo("Server is running and waiting for a connection to be established");
            System.out.println("Server waiting for connection...");
            Socket client = listener.accept();
            logger.logEventInfo("A client has connected to the server");
            System.out.println("Server connected to a client...");
            ClientHandler clientThread = new ClientHandler(client,clients);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
}

