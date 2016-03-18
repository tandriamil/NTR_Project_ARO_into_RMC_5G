
import java.util.List;
import java.util.Iterator;

public class CalculLatency implements Calculation{

	private World world; 
    private int latency;

	public CalculLatency(World world){
		this.world = world;
        this.latency = 0;
	}

	public World getWorld(){
		return this.world;
	}

    public int getLatency(){
        return this.latency;
    }

	public void setUser(World w){
		this.world = w;
	}

	public void execute(){
		AccessPoint a = world.getAccessPoint(0);
        List<UR> ur = a.getUr();
        Iterator<UR> it = ur.iterator();

        while(it.hasNext()){
             UR ur_current = it.next();
             User user = ur_current.getUser();
             if (user != null){
             	List<Packet> packets = user.getPacket_send();
             	Iterator<Packet> it2 = packets.iterator();
             	while(it2.hasNext()){

             	}

             }       
        }
				
	}

	public void finalize(){ }
}