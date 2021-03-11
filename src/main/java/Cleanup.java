import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class Cleanup extends Thread {
    BlockingQueue<String> input;
    ConcurrentMap<String, Socket> allClients;
    public Cleanup(BlockingQueue<String> input, ConcurrentMap<String, Socket> allClients) {
        this.input = input;
        this.allClients = allClients;
    }
    public void run() {
        while(true) {
            String inputString = null;
            try {
                inputString = input.take();
                handleClose(inputString);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void handleClose(String inputString) {
        try {
            allClients.get(inputString).close();
            System.out.println("Killing client: " + inputString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
