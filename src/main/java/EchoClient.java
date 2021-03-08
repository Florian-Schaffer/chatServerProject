import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    Socket socket;
    PrintWriter pw;
    Scanner scanner;

    //String serverIP = "123. 123.0.4";
    // int serverPort = 8088;


    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
        echoClient.runProgram();
    }
        /*try {
            new EchoClient().connect("localhost",8088);
        } catch (IOException e) {
            e.printStackTrace();
        }
        echoClient.runProgram();/*

    }

    /*public void connect (String address, int port) throws IOException {
        socket = new Socket(address,port);
        pw = new PrintWriter(socket.getOutputStream(),true);
        scanner = new Scanner(socket.getInputStream());
        System.out.println(scanner.nextLine());
    }*/

    private void runProgram() {
        try {
            Socket socket = new Socket("localhost",8088);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
            pw.println("CONNECT#Kurt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
