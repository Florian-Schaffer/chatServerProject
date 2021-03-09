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
    //ConcurrentMap<String,Socket> hashmap = new ConcurrentHashMap<>();
    BlockingQueue<String> allmsg = new ArrayBlockingQueue<String>(200);



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

    private void runProgram() {


        try {
            ServerSocket ss = new ServerSocket(8088);
            while (true) {
                Socket client = ss.accept();

                InputStreamReader ir = new InputStreamReader(client.getInputStream());
                PrintWriter printWriter = new PrintWriter(client.getOutputStream(),true);
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));


                String input = br.readLine();
                System.out.println(input);
                String name = input.substring(8);
                        //CONNECT#Kurt
                        //SEND#Peter#Hello Peter
                printWriter.println("Farvel");
                client.close();


                // skal laves imorgen!!!
                //ClientHandler cl = new ClientHandler(name,client,allmsg);

                //cl.greeting();
                //cl.protocol();
            }

            //hashmap.put("Kurt",client);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}