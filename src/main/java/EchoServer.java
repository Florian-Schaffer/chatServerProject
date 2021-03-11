//import com.sun.security.ntlm.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


public class EchoServer {
    //public static final int DEFAULT_PORT = 2345;
    WriteLogEntriesToLogFile logger = new WriteLogEntriesToLogFile();
    ConcurrentMap<String,Socket> allClients = new ConcurrentHashMap<>();
    BlockingQueue<String> allmsg = new ArrayBlockingQueue<String>(200);
    BlockingQueue<String> clientsToBeRemoved = new ArrayBlockingQueue<String>(200);
    ConcurrentMap<String,PrintWriter> allNamedPrintwriters = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = 8088;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        EchoServer echoServer = new EchoServer();
        echoServer.runProgram();
    }

    public static String getHelp() {
        String returnValue ="";
        return returnValue;
    }


    //we dont know about this
    private void runProgram() {

        try {
            ServerSocket ss = new ServerSocket(8088);
            Dispatcher dispatcher = new Dispatcher(allmsg,allNamedPrintwriters,clientsToBeRemoved);
            Cleanup cleanup = new Cleanup(clientsToBeRemoved,allClients);
            cleanup.start();
            dispatcher.start();
            logger.initializeLogger();

            while (true) {
                Socket client = ss.accept();
                InputStreamReader ir = new InputStreamReader(client.getInputStream());
                PrintWriter printWriter = new PrintWriter(client.getOutputStream(),true);
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String input = br.readLine();
                System.out.println(input);
                String name = input.substring(8);
                logger.logEventInfo(name + " has connected to the chatserver!");
                allClients.put(name,client);
                allNamedPrintwriters.put(name,printWriter);
                ClientHandler cl = new ClientHandler(name,br, printWriter, allmsg);
                Thread t = new Thread(cl);
                t.start();
                allmsg.add("ONLINE#");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void online(ConcurrentMap<String,Socket> allClients){
        EchoClient ec = new EchoClient();
        while(true){
            if(ec.getCommand.equals("CONNECT")){
                System.out.println(getAllClients());
            }
            else if(ecdisconnect){
                System.out.println(getAllClients());
            }
        }

    }*/

    public ConcurrentMap<String, Socket> getAllClients() {
        return allClients;
    }

    public BlockingQueue<String> getAllmsg() {
        return allmsg;
    }

    public ConcurrentMap<String, PrintWriter> getAllNamedPrintwriters() {
        return allNamedPrintwriters;
    }
}