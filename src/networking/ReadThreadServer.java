package networking;

import messages.*;

public class ReadThreadServer implements Runnable{
    private Thread thr;
    private NetworkUtil networkUtil;
    private Server parent;

    public ReadThreadServer(NetworkUtil networkUtil, Server parent){
        this.networkUtil = networkUtil;
        this.parent = parent;
        thr = new Thread(this);
        thr.start();
    }

    @Override
    public void run() {
        try{
            while(true) {
                Object o = networkUtil.read();

                if(o instanceof LoginRequest){
                    LoginRequest loginMessage = (LoginRequest) o;
                    boolean flag = false;
                    for(sample.Club c: parent.clubList){
                        if(c.getName().equalsIgnoreCase(loginMessage.getClubName())){
                            networkUtil.write(new LoginAnswer(new sample.Club(c), true));
                            flag = true;
                            //System.out.println("true");
                            break;
                        }
                    }
                    if(!flag)
                        networkUtil.write(new LoginAnswer(null, false));
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
