import javax.swing.plaf.TableHeaderUI;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;
    Main() throws IOException {
        serverSocket = new ServerSocket(1234);
    }
    void  send(String msg){
        for (Client client:clients){
            client.receive(msg);
        }
    }
    public void run(){
        while (true){
            System.out.println("Waiting...");
            try {
                Socket socket = serverSocket.accept();
            System.out.println("Client connected!");
            clients.add(new Client(socket, this));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {

        new Main().run();


    }
}