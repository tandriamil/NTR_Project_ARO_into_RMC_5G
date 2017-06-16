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


/**
 * Class to compute the whole percentage of UR used
 */
public class CalculPercentageURUsed implements Calculation {

	// Parameters
	private World world;
	private Map<AccessPoint, Double> urUsed;


	/**
	 * Constructor for this computation class
	 *
	 * @param worl The world on which we will do the computations
	 */
	public CalculPercentageURUsed(World world){

		// Put the world
		this.world = world;

		// Get a list to put all the results
		this.urUsed = new HashMap<AccessPoint, Double>();

		// Add all the access points of this world
		for (AccessPoint ap : world.getAccessPoints()) {
			this.urUsed.put(ap, 0.0);
		}
	}


	/**
	 * Function launched at every step to compute the datas
	 */
	public void execute() {

		// Variables used here
		int counter;
		User user;

		// For each access point
		for (AccessPoint ap : this.urUsed.keySet()) {

			// Initialize the counter
			counter = 0;

			// Get the list of URs
			for (UR ur_current : ap.getUr()) {

				// Get the user put on each UR
				user = ur_current.getUser();

				// If there is one
				if (user != null) counter++;
			}

			this.urUsed.put(ap, this.urUsed.get(ap) + (counter / AccessPoint.NB_UR));
		}
	}


	/**
	 * Method to finalize the computation
	 *
	 * @param nbUsers The number of users
	 */
	public void finalize(int nbUsers) {

		// Parameters used here
		int finalResult, accessPointId = 1;
		File file = null;
		BufferedWriter writer = null;
		String nameFile;

		// For each access point
		for (AccessPoint ap : this.urUsed.keySet()) {

			// Get the final value
			finalResult = (int)((this.urUsed.get(ap) / World.MAX_TIME) * 100);

			// The file writing
			nameFile = world.getNbAccessPoints() + "_cell_" + world.getResAllocAlg().getName() + "_percentage_ur_used_of_cell_number_" + accessPointId + ".csv";

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
