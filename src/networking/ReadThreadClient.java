package networking;

import messages.*;

public class ReadThreadClient implements Runnable{
    NetworkUtil networkUtil;
    Client parentClient;
    public Thread thr;

    public ReadThreadClient(NetworkUtil networkUtil, Client parentClient) {
        this.networkUtil = networkUtil;
        this.parentClient = parentClient;
        thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try{
            while(true){
                Object o = networkUtil.read();

                if(o instanceof LoginAnswer){
                    System.out.println("Login request received");
                    LoginAnswer loginAnswer = (LoginAnswer) o;
                    if(loginAnswer.getStatus()){
                        parentClient.parentUI.login(loginAnswer.getClub());
                    }
                    else{
                        parentClient.parentUI.loginFailed();
                    }
                    //System.out.println("Login answer sent");
                }

                else if(o instanceof TransferListAnswer){
                    //System.out.println("Transfer list answer received");
                    TransferListAnswer transferListAnswer = (TransferListAnswer) o;
                    parentClient.parentUI.initTransferList(transferListAnswer.getTransferList(), transferListAnswer.getClub());
                }
            }
        }catch (Exception e){
            System.out.println("Client read thread error");
            System.out.println(e);
        }
    }
}
