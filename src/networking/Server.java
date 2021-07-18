package networking;

import sample.Club;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    public static final String INPUT_FILE_NAME = "players.txt";
    public static final String OUTPUT_FILE_NAME = "players.txt";

    List<sample.Player> playerList;
    List<sample.Club> clubList;
    List<sample.Player> transferList;
    HashMap<NetworkUtil, Club> clientMap;
    private ServerSocket serverSocket;

    public Server(){
        try{
            clientMap = new HashMap<>();
            playerList = sample.IOControl.readFromFile(INPUT_FILE_NAME);
            clubList = sample.Club.createClubList(playerList);
            transferList = new ArrayList<>();
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

    public void serve(Socket clientSocket) {
        NetworkUtil networkUtil = null;
        try{
            networkUtil = new NetworkUtil(clientSocket);
            //clientList.add(networkUtil);
        }catch (Exception e){
            System.out.println("NetworkUtil Creation Failed");
            System.out.println(e);
        }

        new ReadThreadServer(networkUtil, this);
    }

    public static void main(String[] args) {
        new Server();
    }
}
