import java.util.ArrayList;
import java.util.List;

/**
 * Class for the world in which we'll simulate the cells
 */
public class World {

	// Constants
	public static final int RR_ALLOCATION_ALGORITHM = 1;
	public static final int MAX_SNR_ALLOCATION_ALGORITHM = 2;
	public static final int MAX_TIME = 200000;

	// Attributes
	private int time;
	private int nbAccessPoints;
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
	 * @param ressourceAllocationAlgorithm The ressource allocation algorithm that we want to use here
	 *
	 * @return true if everything is right
	 */
	public boolean addAccessPoint(int ressourceAllocationAlgorithm) {

		// TODO: In function of the ressourceAllocationAlgorithm passed
		// Put the correct class for the access point

		// Adds the access point
		aps.add(new AccessPoint(this));

		// Increment the number of nbAccessPoints
		++nbAccessPoints;

		// Check value returned
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
	 * Clear the world
	 */
	public void clear() {

		// Remove the access points
		aps.clear();

		// Reinitialize the attributes
		time = 0;
		nbAccessPoints = 0;
	}


	/**
	 * Launch a new simulation with the number of users on this world
	 *
	 * @param nbUsers The number of users
	 */
	public void bootstrap(int nbUsers) {

		// Reinitialize the time
		time = 0;

		// Put the number of users for each access points
		for (AccessPoint ap : aps) {

			// Reinitialize the state of the access point
			ap.init(nbUsers);

			// Loop on the time
			while (time < MAX_TIME) {

				// Switch to the next state
				ap.nextState();

				// Do the calculations
				for (Calculation c : calculations) c.execute();

				// Increment the time
				++time;
			}

			// In the end, finalize the calculation
			for (Calculation c : calculations) c.finalize();

		}
	}


}