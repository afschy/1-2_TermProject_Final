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
                        parentClient.parentMain.login(loginAnswer.getClub());
                    }
                    else{
                        parentClient.parentMain.loginFailed();
                    }
                    System.out.println("Login answer sent");
                }
            }
        }catch (Exception e){
            System.out.println("Client read thread error");
            System.out.println(e);
        }
    }
}
