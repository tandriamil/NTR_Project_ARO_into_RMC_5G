
import java.util.List;
import java.util.Iterator;

public class CalculPourcentageURUsed implements Calculation{

	private World world; 
    private int urUsed;

	public CalculPourcentageURUsed(World world){
		this.world = world;
        this.urUsed = 0;
	}

	public World getWorld(){
		return this.world;
	}

    public int getUrUsed(){
        return this.urUsed;
    }

	public void setUser(World w){
		this.world = w;
	}

	public void execute(){
		AccessPoint a = world.getAccessPoint(0);
        List<UR> ur = a.getUr();
        Iterator<UR> it = ur.iterator();
        int nbr_user = 0;

        while(it.hasNext()){
             UR ur_current = it.next();
             User user = ur_current.getUser();
             if (user != null){
             	this.urUsed += 1;
             }       
        }

        this.urUsed = this.urUsed / 128;			
	}

	public void finalize(){ }
}