import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    private final Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException{
        this.client = clientSocket;
        this.clients = clients;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(),true);

    }


    @Override
    public void run() {
        try{
            while(true){
                String request = in.readLine();
                if(request.contains("help")){
                    out.println(EchoServer.getHelp());
                }else if(request.startsWith("/s")){
                    int firstSpace = request.indexOf(" ");
                    if(firstSpace != -1){
                        globalMessage(request.substring(firstSpace+1));
                    }
                }
            }
        }catch (IOException e){
            System.err.println("IO exception in client handler.");
        }finally {
            out.close();
            try{
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }


    }

    private void globalMessage(String msg) {
        for(ClientHandler aClient : clients){
            aClient.out.println(msg);
        }
    }
}