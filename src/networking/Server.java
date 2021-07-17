package networking;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static final String INPUT_FILE_NAME = "players.txt";
    public static final String OUTPUT_FILE_NAME = "players.txt";

    List<sample.Player> playerList;
    List<sample.Club> clubList;
    List<sample.Player> transferList;
    private ServerSocket serverSocket;

    public Server(){
        try{
            playerList = sample.IOControl.readFromFile(INPUT_FILE_NAME);
            clubList = sample.Club.createClubList(playerList);
            serverSocket = new ServerSocket(33333);
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected");
                serve(clientSocket);
            }
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public void serve(Socket clientSocket) throws Exception{
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(networkUtil, this);
    }

    public static void main(String[] args) {
        new Server();
    }
}
