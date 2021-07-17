package networking;

import sample.Main;

public class Client {
    NetworkUtil networkUtil;
    ReadThreadClient readThread;
    sample.Main parentMain;

    public Client(String address, int port, Main parentMain){
        try{
            networkUtil = new NetworkUtil(address, port);
            this.parentMain = parentMain;
            readThread = new ReadThreadClient(networkUtil, this);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
    public void setNetworkUtil(NetworkUtil networkUtil) {
        this.networkUtil = networkUtil;
    }
    public ReadThreadClient getReadThread() {
        return readThread;
    }
    public void setReadThread(ReadThreadClient readThread) {
        this.readThread = readThread;
    }
    public Main getParentMain() {
        return parentMain;
    }
    public void setParentMain(Main parentMain) {
        this.parentMain = parentMain;
    }
}
