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
    String messages;
    String[] commandArray;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException{
        this.client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);

    }
    /*public ClientHandler(Socket clientSocket, BlockingQueue<String> allmsg) throws IOException{
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);

    }*/
    public ClientHandler(String name, BufferedReader br, PrintWriter pw, BlockingQueue<String> allmsg){
        this.name = name;
        this.in = br;
        this.out = pw;
        this.allmsg = allmsg;

    }
    public void Protocol() throws IOException {
        while(true){
            String command = in.readLine();
            System.out.println(command);

            commandArray = command.split("#");

                switch(commandArray[0]) {
                    case "CONNECT":
                        handleConnect();

                        break;
                    case "SEND":
                        handleSend(command);
                        break;
                    case "CLOSE":
                        handleClose(0);
                        break;
                    default:
                        handleClose(1);
                        break;
                }
            }
        }
            /*if (command.equals("CLOSE#")) break;

            if (command.equals("ONLINE#")) {
                System.out.println(es.getAllClients());
            }

            if (command.startsWith("SEND")) {
                int firstSpace = command.indexOf("#");
                if (firstSpace != -1) {
                    dispatcher.sendMessageToAll(command.substring(firstSpace + 1));
                }
                out.println(command);  }*/


                //TODO: SEND#Lone#Hej med dig (Send fra Kurt)
                //TODO: "SEND#Kurt,Lone#Hej med dig" (Send fra Kurt)

                //TODO: "MESSAGE#Kurt#Hej med dig" (Find Lones printwriter sådan at: Lonepw.printLn("MESSAGE#Kurt#Hej med dig"))

                // TODO: Sendes til dispatcheren
               // String messages = "SEND#Kurt,Lone#Hej med dig";




            /*if(request.contains("help")){
                out.println(EchoServer.getHelp());
            }else if(request.startsWith("/s")){
                int firstSpace = request.indexOf(" ");
                if(firstSpace != -1){
                    globalMessage(request.substring(firstSpace+1));
                }
            }*/
       // }


    private void handleConnect() {
        String out = "ONLINE#";
        allmsg.add(out);
    }

    private void handleSend(String msg){
        String out = commandArray[0]+"#"+name+","+commandArray[1]+"#"+commandArray[2];
        allmsg.add(out);
    }

    private void handleClose(int i){
        String close = "CLOSE#"+name+"#"+i;
        allmsg.add(close);
    }


    @Override
    public void run() {
        try {
            Protocol();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
