package networking;

import messages.*;
import sample.Club;

import java.util.Map;

public class ReadThreadServer implements Runnable{
    private Thread thr;
    private NetworkUtil networkUtil;
    private Server parent;
    volatile private boolean isRunning;

    public ReadThreadServer(NetworkUtil networkUtil, Server parent){
        this.networkUtil = networkUtil;
        this.parent = parent;
        thr = new Thread(this);
        isRunning = true;
        thr.start();
    }

    @Override
    public void run() {
        try{
            while(isRunning) {
                Object o = networkUtil.read();

                if(o instanceof LoginRequest){
                    LoginRequest loginMessage = (LoginRequest) o;
                    boolean flag = false;
                    for(sample.Club c: parent.clubList){
                        if(c.getName().equalsIgnoreCase(loginMessage.getClubName())){
                            networkUtil.write(new LoginAnswer(new sample.Club(c), true));
                            flag = true;
                            parent.clientMap.put(networkUtil, c);
                            //System.out.println("true");
                            break;
                        }
                    }
                    if(!flag)
                        networkUtil.write(new LoginAnswer(null, false));
                }

                else if(o instanceof TransferListRequest){
                    TransferListRequest request = (TransferListRequest)o;
                    sample.Club relevantClub = sample.SearchClubs.getRelevantClub(parent.clubList, request.getClubName());
                    networkUtil.write(new TransferListAnswer(parent.transferList, relevantClub));
                }

                else if(o instanceof SellRequest){
                    SellRequest sellRequest = (SellRequest) o;
                    if(parent.transferList.contains(sellRequest.getPlayerToSell()))
                        parent.transferList.remove(sellRequest.getPlayerToSell());
                    parent.transferList.add(sellRequest.getPlayerToSell());
                    broadcast();
                }

                else if(o instanceof BuyRequest){
                    //System.out.println("Buy request received");
                    BuyRequest buyRequest = (BuyRequest) o;
                    parent.transfer(buyRequest.getPlayerToBuy(), buyRequest.getBuyerClubName());
                }

                else if(o instanceof SellCancelRequest){
                    SellCancelRequest sellCancelRequest = (SellCancelRequest) o;
                    parent.transferList.remove(sellCancelRequest.getPlayer());
                    broadcast();
                }

                else if(o instanceof CloseRequest){
                    isRunning = false;
                    parent.clientMap.remove(networkUtil);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private void broadcast(){
        for(Map.Entry element: parent.clientMap.entrySet()){
            NetworkUtil destination = (NetworkUtil) element.getKey();
            Club club = (Club) element.getValue();
            try {
                destination.write(new TransferListAnswer(parent.transferList, club));
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
