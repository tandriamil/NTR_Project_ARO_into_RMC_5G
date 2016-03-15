
import java.util.List;
import java.util.Iterator;
public class CalculThroughput implements Calculation{

	private World world; 
    private int debitMax;

	public CalculThroughput(World world){
		this.world = world;
        this.debitMax = 0;
	}

	public World getWorld(){
		return this.world;
	}

    public int getDebitMax(){
        return this.debitMax;
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
                this.debitMax += user.getDebitCurrent();
             }       
        }

        this.debitMax = this.debitMax / World.MAX_TIME;
	}


	public void finalize(){ }
}