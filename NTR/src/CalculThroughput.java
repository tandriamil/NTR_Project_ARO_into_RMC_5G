
import java.util.List;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

public class CalculThroughput implements Calculation{

	private World world; 
    private int throughputMax;

	public CalculThroughput(World world){
		this.world = world;
        this.throughputMax = 0;
	}

	public World getWorld(){
		return this.world;
	}

    public int getThroughputMax(){
        return this.throughputMax;
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
                this.throughputMax += user.getDebitCurrent();
             }       
        }
	}

	public void finalize(int nb_Users){

		this.throughputMax = this.throughputMax / World.MAX_TIME;
		String nameFile = "throughput.csv"; 
		File file = null;
        BufferedWriter writer = null;
		
		try{

			file = new File(nameFile);

			if(file.createNewFile()){

				System.out.println("The file was created");

			}else{

				System.out.println("Error Can not create this file");

			}

        	writer = new BufferedWriter(new FileWriter(nameFile));
        	writer.write(nb_Users + "," + this.throughputMax + "\n"); 
        	writer.close(); 

		}catch(IOException e){
			e.printStackTrace();
		} 

	}
}