package networking;

import sample.UIUpdater;

public class Client {
    NetworkUtil networkUtil;
    ReadThreadClient readThread;
    sample.UIUpdater parentUI;

    public Client(String address, int port, UIUpdater parentUI){
        try{
            networkUtil = new NetworkUtil(address, port);
            this.parentUI = parentUI;
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
    public UIUpdater getParentUI() {
        return parentUI;
    }
    public void setParentUI(UIUpdater parentUI) {
        this.parentUI = parentUI;
    }
}
