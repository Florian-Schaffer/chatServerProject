import java.io.PrintWriter;
import java.util.Set;
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
        if(msg.contains("SEND")){
                    String[] msgArray = msg.split("#");

        String[] modtager = msgArray[1].split(",");
        //SEND#Kurt,Lone,Florian#Hej med jer to.
        String afsender = "MESSAGE#"+modtager[0]+"#"+msgArray[2];


        for(int i=1;i<modtager.length;i++){


            getPrintWriter(modtager[i]).println(afsender);
        }}
        else if(msg.contains("CLOSE")){
            String[] clientArray = msg.split("#");
            getPrintWriter(clientArray[1]).println(clientArray[0]+"#"+clientArray[2]);
            allNamedPrintwriters.remove(clientArray[1]);
        }
        else if(msg.contains("ONLINE")){
            Set<String> setKeys = allNamedPrintwriters.keySet();
            String output = "";
            StringBuilder sb = new StringBuilder();
            sb.append("ONLINE#");
            for (String key: setKeys) {
                sb.append(key+","); }
            for (String key: setKeys) {
                allNamedPrintwriters.get(key).println(sb.toString());}
        }
        else{
        Set<String> setKeys = allNamedPrintwriters.keySet();
        for (String key: setKeys) {
            allNamedPrintwriters.get(key).println(msg);
            //TODO; past message and find reciperens name;
            //TODO; find recipents printwriter in hashmap
        }
        }
    }

    private PrintWriter getPrintWriter(String name){
        PrintWriter out = null;
        out=allNamedPrintwriters.get(name);
                return out;
    }



    public void addWriterToList(PrintWriter pw){
        allWriters.add(pw);
    }
}


