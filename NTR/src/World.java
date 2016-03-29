import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Class for the world in which we'll simulate the cells
 */
public class World {

	// Constants
	public static final int MAX_TIME = 10000;
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

		// Add the different calculations
		calculations.add(new CalculThroughput(this));
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
		return (getAccessPoint(nbAccessPoints-1) != null);
	}


	/**
	 * Accesor for the access points
	 * Begins to 0
	 *
	 * @param index The index of the wanted access point
	 *
	 * @return The access point associated to this index, null if none
	 */
	public AccessPoint getAccessPoint(int index) {
		return aps.get(index);
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

		// Get iterators on each URs
		List<Iterator<UR>> it_urs = new ArrayList<Iterator<UR>>();

		// Initialize each access points
		for (AccessPoint ap : aps) {

			// Reinitialize the state of the access point
			ap.init(nbUsers, alg);

			// Put their AP iterators into the list
			it_urs.add(ap.getUr().iterator());
		}

		// Loop on the time
		while (time < MAX_TIME) {

			// Create packets for each user
			for (AccessPoint ap : aps) ap.userCreatePacket();

			// Clear and manage each ur list
			UR current;
			for (Iterator<UR> ur : it_urs) {

				// For each ur in this list
				while (ur.hasNext()) {

					// Get it
					current = ur.next();

					// Clear it
					current.clearUR();

					// Allocate it
					current = alg.allocateSingleUR(current);
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


}