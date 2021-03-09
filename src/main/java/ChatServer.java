import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {

    private static int PORT_ID = 9090;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);


    public static void main (String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT_ID);

        while(true){
            System.out.println("Server waiting for connection.");
            Socket client = listener.accept();
            System.out.println("Server connected to a client.");
            ClientHandler clientThread = new ClientHandler(client,clients);
            clients.add(clientThread);

            pool.execute(clientThread);
        }

    }




}