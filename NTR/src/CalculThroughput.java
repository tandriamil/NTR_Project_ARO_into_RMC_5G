
public class CalculThroughput implements Calculation{

	private World world; 
	private int time;
	private int nb_bytes;

	public CalculThroughput(World world){
 		this.world = world;
 		this.time = this.world.getTime();
	}

	public int getWorld(){
    	return this.world;
    }

    public void setUser(World w){
   		this.world = w;
    }

    public void execute(){
    	
    	for(int i = 0; i < world.getNbAccessPoints(); ++i){
    		
    		for(AccessPoint a : world.getAccessPoint(i)){
             	
    		}
    
    }
}