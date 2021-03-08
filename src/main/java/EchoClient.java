import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {

        EchoClient echoClient = new EchoClient();

        echoClient.runProgram();

    }

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
