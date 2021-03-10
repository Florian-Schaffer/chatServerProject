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
    private static int serverPort = 8088;
    static String command;


    public static void main(String[] args) throws IOException {
       /* EchoClient echoClient = new EchoClient();
        echoClient.runProgram();*/

        EchoServer es = new EchoServer();
        Socket socket = new Socket(serverIP, serverPort);
        ServerConnection serverConn = new ServerConnection(socket);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        command = keyboard.readLine();


        new Thread(serverConn).start();
        Dispatcher dispatcher = new Dispatcher(es.allmsg, es.allNamedPrintwriters);



        while (true) {
            System.out.println("> ");


            if(command.equals("CONNECT")){

            if (command.equals("CLOSE#")) break;

            if (command.equals("ONLINE#")) {
                System.out.println(es.getAllClients());
            }

            if (command.startsWith("SEND")) {
                int firstSpace = command.indexOf("#");
                if (firstSpace != -1) {
                    dispatcher.sendMessageToAll(command.substring(firstSpace + 1));
                }
                out.println(command);  }

            socket.close();
            System.exit(0);

        }}

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
    public static String getCommand() {
        return command;
    }
}
