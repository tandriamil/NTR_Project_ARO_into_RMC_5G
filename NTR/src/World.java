import java.util.ArrayList;
import java.util.List;

/**
 * Class for the world in which we'll simulate the cells
 */
public class World {

	// Constants
	public static final int MAX_TIME = 10000;

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
	 * @param ressourceAllocationAlgorithm The ressource allocation algorithm that we want to use here
	 *
	 * @return true if everything is right
	 */
	public boolean addAccessPoint(int ressourceAllocationAlgorithm) {

		// Adds the access point
		aps.add(new AccessPoint(this, ressourceAllocationAlgorithm));

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
			ap.init(nbUsers);

			// Put their AP iterators into the list
			urs.add(ap.getUR().iterator());
		}

		// Loop on the time
		while (time < MAX_TIME) {

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

					// Check packet
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