import org.junit.jupiter.api.BeforeEach;
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
    BlockingQueue<String> allmsg;



    @BeforeEach
    void setUp() {
        String name = "Kurt";
        allmsg = new ArrayBlockingQueue<>(200);
        PrintWriter pw = new PrintWriter(System.out);
        String input = "SEND#Lone#Hej med dig.\n CLOSE#Kurt";
        BufferedReader br = new BufferedReader(new StringReader(input));

        clientHandler = new ClientHandler(name, br, pw, allmsg);
        testThread = new Thread(clientHandler);
    }
    @Test
    void testSendMSGtoLone() throws InterruptedException {
        testThread.start();
        testThread.join();
        System.out.println(allmsg);
    }
    @Test
    void testConnectKurt(){
        String message="CONNECT#Kurt";
        testThread.start();

    }
}