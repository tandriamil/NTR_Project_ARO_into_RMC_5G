import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Class for the world in which we'll simulate the cells
 */
public class World {

	// Constants
	public static final int MAX_TIME = 200;
	public static final int RR_ALLOCATION_ALGORITHM = 1;
	public static final int MAX_SNR_ALLOCATION_ALGORITHM = 2;

	// Attributes
	private int time;
	private int nbAccessPoints;
	private Algorithm alg;
	private List<AccessPoint> aps;
	private List<Calculation> calculations;


	/**
	 * Constructor for the World
	 */
	public World() {

		// Initialize the attributes
		time = 0;
		nbAccessPoints = 0;
		aps = new ArrayList<AccessPoint>();
		calculations = new ArrayList<Calculation>();
	}


	/**
	 * Accesor for the time
	 *
	 * @return The current time in this world
	 */
	public int getTime() {
		return time;
	}


	/**
	 * Accesor for the number of access points
	 *
	 * @return The current number of access points
	 */
	public int getNbAccessPoints() {
		return nbAccessPoints;
	}


	/**
	 * Method to add access points
	 *
	 * @return true if everything is right
	 */
	public boolean addAccessPoint() {

		// Adds the access point
		aps.add(new AccessPoint(this));

		// Increment the number of nbAccessPoints
		++nbAccessPoints;

		// Check if the AP is really added to the list
		return (aps.get(nbAccessPoints-1) != null);
	}


	/**
	 * Accesor for the access points
	 *
	 * @return The list of access points
	 */
	public List<AccessPoint> getAccessPoints() {
		return aps;
	}


	/**
	 * Get the ressource allocation used here
	 *
	 * @return The ressource allocation algorithm used here
	 */
	public Algorithm getResAllocAlg() {
		return alg;
	}


	/**
	 * Clear the world
	 *
	 * @param ressourceAllocationAlgorithm The ressource allocation algorithm to use
	 */
	public void init(int ressourceAllocationAlgorithm) {

		// In function of the ressource algorithm asked
		switch (ressourceAllocationAlgorithm) {

			// RoundRobin
			case RR_ALLOCATION_ALGORITHM:
				alg = new RoundRobin();
				break;

			// MaxSNR
			case MAX_SNR_ALLOCATION_ALGORITHM:
				alg = new MaxSNR();
				break;

			// Error
			default:
				System.err.println("[AccessPoint] Unknown ressource allocation algorithm");
				System.exit(-1);
				break;
		}

		// Remove the access points
		aps.clear();

		// Remove the calculations
		calculations.clear();

		// Reinitialize the attributes
		time = 0;
		nbAccessPoints = 0;
	}


	/**
	 * Launch a new simulation with the number of users on this world
	 * New version using iterators on each URs
	 *
	 * @param nbUsers The number of users
	 */
	public void bootstrap(int nbUsers) {

		// Reinitialize the time
		time = 0;

		// Clear the calculations array
		calculations.clear();

		// Add the different calculations
		calculations.add(new CalculThroughput(this));
		calculations.add(new CalculLatency(this));
		calculations.add(new CalculPercentageURUsed(this));

		// Get iterators on each URs
		List<List<UR>> urs = new ArrayList<List<UR>>();
		List<UR> to_compare = new ArrayList<UR>();
		UR current;

		// Initialize each access points
		for (AccessPoint ap : aps) {

			// Reinitialize the state of the access point
			ap.init(nbUsers, alg);

			// Put their AP iterators into the list
			urs.add(ap.getUr());
		}

		// Loop on the time
		while (time < MAX_TIME) {

			// Create packets for each user
			for (AccessPoint ap : aps) ap.userCreatePacket();

			// Clear and manage each ur list
			for (int i = 0; i < AccessPoint.NB_UR; ++i) {

				// For each UR list
				for (List<UR> ur_list : urs) {
					current = ur_list.get(i);

					current.clearUR();

					current = alg.allocateSingleUR(current);

					to_compare.add(current);
				}


				interferenceBetweenAP(to_compare);


				for (List<UR> ur_list : urs) {
					current = ur_list.get(i);

					if (current.getUser() != null) current.getUser().checkPacket();
				}
			}

			// Do the calculations
			for (Calculation c : calculations) c.execute();

			// Increment the time
			++time;
		}

		// In the end, finalize the calculation
		for (Calculation c : calculations) c.finalize(nbUsers);
	}



	private void interferenceBetweenAP(List<UR> urs) {

		User current, comparison;
		for (int i = 0; i < urs.size(); ++i) {

			if (i > 0) {

				current = urs.get(i).getUser();
				comparison = urs.get(i - 1).getUser();

				if ((current != null) && (comparison != null)) {

					if ((!current.isNear()) && (!comparison.isNear())) {
						current.interferenceDetected();
						comparison.interferenceDetected();
					}
				}
			}
		}
	}


}
