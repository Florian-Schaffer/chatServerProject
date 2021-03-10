import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class Dispatcher  extends Thread{
    BlockingQueue<String> allMsg;
    BlockingQueue<PrintWriter> allWriters;
    ConcurrentMap<String,PrintWriter> allNamedPrintwriters;

    public Dispatcher(BlockingQueue<String> allMsg,ConcurrentMap<String,PrintWriter> allNamedPrintwriters){
        this.allMsg = allMsg;
        this.allNamedPrintwriters = allNamedPrintwriters;

    }


    @Override
    public void run() {
        // this checks and send message to all
        while(true) {
            try{
                String msg = allMsg.take();
                sendMessageToAll(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToAll(String msg) {
        for (PrintWriter pw: allWriters) {
            pw.println(msg);
            //TODO; past message and find reciperens name;
            //TODO; find recipents printwriter in hashmap
            allNamedPrintwriters.get("Lone").println(msg);
            
        }
    }
    public void addWriterToList(PrintWriter pw){
        allWriters.add(pw);
    }
}


