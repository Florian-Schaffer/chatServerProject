import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class ClientHandler implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;
    BlockingQueue<String> allmsg;
    String name;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException{
        this.client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);

    }
    public ClientHandler(Socket clientSocket, BlockingQueue<String> allmsg) throws IOException{
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);

    }
    public ClientHandler(String name, BufferedReader br, PrintWriter pw, BlockingQueue<String> allmsg){
        this.name = name;
        this.in = br;
        this.out = pw;
        this.allmsg = allmsg;

    }
    public void Protocol(){
        //while(true){
            String request = null;
            try {
                request = in.readLine();
                //TODO: SEND#Lone#Hej med dig (Send fra Kurt)
                // TODO: Sendes til dispatcheren
                String messages = "SEND#Kurt,Lone#Hej med dig";
                allmsg.add(messages);






            } catch (IOException e) {
                e.printStackTrace();
            }
            /*if(request.contains("help")){
                out.println(EchoServer.getHelp());
            }else if(request.startsWith("/s")){
                int firstSpace = request.indexOf(" ");
                if(firstSpace != -1){
                    globalMessage(request.substring(firstSpace+1));
                }
            }*/
       // }
    }


    @Override
    public void run() {
        Protocol();

    }
}
