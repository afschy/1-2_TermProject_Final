package networking;

import messages.TransferListAnswer;
import sample.Club;
import sample.Player;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        }catch (Exception e){
            System.out.println("NetworkUtil Creation Failed");
            System.out.println(e);
        }

        new ReadThreadServer(networkUtil, this);
    }

    synchronized void transfer(Player playerToTransfer, String buyerName){
        if(!transferList.contains(playerToTransfer)){
            System.out.println("Players isn't available for transfer");
            return;
        }

        System.out.println("In transfer function");
        Club seller = sample.SearchClubs.getRelevantClub(clubList, playerToTransfer.getClubName());
        Club buyer = sample.SearchClubs.getRelevantClub(clubList, buyerName);
        transferList.remove(playerToTransfer);
        playerList.remove(playerToTransfer);
        seller.getPlayers().remove(playerToTransfer);

        playerToTransfer.setClubName(buyerName);
        buyer.getPlayers().add(playerToTransfer);
        playerList.add(playerToTransfer);

        for(Map.Entry element: clientMap.entrySet()){
            NetworkUtil destination = (NetworkUtil) element.getKey();
            Club club = (Club) element.getValue();
            try {
                destination.write(new TransferListAnswer(transferList, club));
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
