import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private static Socket socket;
    PrintWriter pw;
    Scanner scanner;

    //123. 123.0.4
    private static String serverIP = "127.0.0.1";
    private static int serverPort = 9090;


    public static void main(String[] args) throws IOException {
       /* EchoClient echoClient = new EchoClient();
        echoClient.runProgram();*/

        Socket socket = new Socket(serverIP,serverPort);
        ServerConnection serverConn = new ServerConnection(socket);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        new Thread(serverConn).start();



        while(true) {
            System.out.println("> ");
            String command = keyboard.readLine();

            if(command.equals("exit"))break;

            out.println(command);
            
        }

        socket.close();
        System.exit(0);

    }

   /* private void runProgram() {
        try {
            Socket socket = new Socket("localhost",8088);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            pw.println("CONNECT#Kurt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
