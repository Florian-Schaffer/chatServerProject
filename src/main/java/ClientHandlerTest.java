import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest {
ClientHandler clientHandler;
Thread testThread;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        String name = "Kurt";
        BlockingQueue<String> allmsg = new ArrayBlockingQueue<String>(200);
        PrintWriter pw = new PrintWriter(System.out);
        String clientInput = "SEND#Lone#Hello Lone\n";
        BufferedReader br = new BufferedReader(new StringReader(clientInput));
        clientHandler = new ClientHandler(name,br,pw,allmsg);
        testThread = new Thread(clientHandler);
        //public ClientHandler(String name, BufferedReader br, PrintWriter pw, BlockingQueue<String> allmsg)
    }
    @Test
    void testSendToDispath(){
        testThread.start();
    }
}