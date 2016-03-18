
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

	public void finalize(){
		//Only for fix unimplements bug
	}

	public void finalize(int nb_Users){
		this.throughputMax = this.throughputMax / World.MAX_TIME;
		//Nom du fichier ou écrire et lire les résultats
		String nameFile = "throughput.csv"; 
		File file = new File(nameFile);

		try{
			//Création du fichier
			if(file.createNewFile()){
				System.out.println("Le fichier a été créé");
			}else{
				 System.out.println("Erreur, Impossible de créer ce fichier");
			}
			//Ouvrir le fichier en lecture et écriture
			BufferedReader buf = new BufferedReader(new FileReader(nameFile));
        	BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile));

		}catch(IOException e){
			e.printStackTrace();
		} 

	}
}