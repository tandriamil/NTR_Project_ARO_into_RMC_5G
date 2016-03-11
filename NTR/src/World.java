import java.util.ArrayList;
import java.util.List;

/**
 * Class for the world in which we'll simulate the cells
 */
public class World {

	// Attributes
	private int time;
	private int nb_access_points;
	private List<AccessPoint> aps;


	/**
	 * Constructor for the World
	 */
	public World() {

		// Initialize the attributes
		time = 0;
		nb_access_points = 0;
		aps = new ArrayList<AccessPoint>();
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
		return nb_access_points;
	}


	/**
	 * Method to add access points
	 *
	 * @return true if everything is right
	 */
	public boolean addAccessPoint() {

		// Adds the access point
		aps.add(new AccessPoint(this));

		// Increment the number of nb_access_points
		++nb_access_points;

		// Check value returned
		return (getAccessPoint(nb_access_points-1) != null);
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

}