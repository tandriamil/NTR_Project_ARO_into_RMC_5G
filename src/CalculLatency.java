import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

public class CalculLatency implements Calculation{

	private World world;
	private Map<AccessPoint, Integer> latency;


	/**
	 * Constructor for this computation class
	 *
	 * @param worl The world on which we will do the computations
	 */
	public CalculLatency(World world){

		//Put the world
		this.world = world;

        // Get a list to put all the results
		this.latency = new HashMap<AccessPoint, Integer>();

		// Add all the access points of this world
		for (AccessPoint ap : world.getAccessPoints()) {
			this.latency.put(ap, 0);
		}
	}

	/**
	 * Function launched at every step to compute the datas
	 */
	public void execute(){

		int nbUser, latency_current;
        User user;

     	for (AccessPoint ap : this.latency.keySet()){

     		nbUser = 0;
     		latency_current = 0;

     		for(UR ur_current : ap.getUr()){

     			// Get the user put on each UR
				user = ur_current.getUser();

				// If there is one
				if (user != null){
					nbUser++;
             		List<Packet> packets = user.getPacket_send();
             		Iterator<Packet> it2 = packets.iterator();
             		while(it2.hasNext()){

             			Packet packet_send = it2.next();
             			latency_current += (packet_send.getEndSend() - packet_send.getBeginSend());
               		}

               		if (packets.size() > 0)
             			latency_current = latency_current / packets.size();

				}

				if (nbUser > 0) {
					latency_current = latency_current / nbUser;
					this.latency.put(ap, this.latency.get(ap) + latency_current);
				}  // Do nothing else
        	}

     	}

	}

	public void finalize(int nbUsers){

		// Parameters used here
		int finalResult, accessPointId = 1;
		File file = null;
		BufferedWriter writer = null;
		String nameFile;

		// For each access point
		for (AccessPoint ap : this.latency.keySet()) {

			// Get the final value
			finalResult = this.latency.get(ap) / (World.MAX_TIME * 2);

			// The file writing
			nameFile = world.getNbAccessPoints() + "_cell_" + world.getResAllocAlg().getName() + "_latency_of_cell_number_" + accessPointId + ".csv";

			// Care, exceptions can occur here
			try {

				// Get a new pointer on the file
				file = new File(nameFile);

				// Try to create it if it doesn't exist
				file.createNewFile();

				// Write by appending the results
				writer = new BufferedWriter(new FileWriter(nameFile, true));
				writer.write(nbUsers + ";" + finalResult + "\n");

			// All the exception handling
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}

			// Increment the access point id
			accessPointId++;
		}
	}
}
