package networking;

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
            while(true){
                Object o = networkUtil.read();

                if(o instanceof String){
                    String name = (String)o;
                    boolean flag = false;
                    for(sample.Club c : parent.clubList){
                        if(c.getName().equalsIgnoreCase(name)){
                            networkUtil.write(new sample.Club(c));
                            flag = true;
                            break;
                        }
                    }
                    if(!flag)
                        networkUtil.write("No such club found.");
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
