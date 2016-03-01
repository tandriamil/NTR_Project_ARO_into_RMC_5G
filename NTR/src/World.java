import java.util.ArrayList;
import java.util.List;

/**
 * Class for the world in which we'll simulate the cells
 */
public class World {

	// Attributes
	private int time;
	private List<AccessPoint> aps;


	/**
	 * Constructor for the World
	 */
	public World() {

		// Initialize the attributes
		time = 0;
		aps = new ArrayList<AccessPoint>();

		// Add access points to the list
		aps.add(1, new AccessPoint(this));
		/*if (!)) {
			System.err.println("[ERROR] World: Can't add the access point number 1 to the list");
			System.exit(-1);
		}

		if (!aps.add(1, new AccessPoint())) {
			System.err.println("[ERROR] World: Can't add the access point number 2 to the list");
			System.exit(-1);
		}*/
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
	 * Accesor for the access points
	 * Care, the indexes begin to 1
	 *
	 * @param index The index of the wanted access point
	 *
	 * @return The access point associated to this index, null if none
	 */
	public AccessPoint getAccessPoint(int index) {
		return aps.get(index);
	}

}