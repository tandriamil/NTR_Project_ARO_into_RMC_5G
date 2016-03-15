import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccessPoint {

	// Constants
	public static final int RR_ALLOCATION_ALGORITHM = 1;
	public static final int MAX_SNR_ALLOCATION_ALGORITHM = 2;
	public static final int NB_UR = 128;
	
	// Prameters
	private List<UR> ur;
	private List<User> users;
	private World world;
	private int nb_Paket;
	private int res_alloc_alg;  // Check world constants


	/**
	 * Constructor for the access point
	 *
	 * @param world The world where this AP exists
	 * @param ressourceAllocationAlgorithm The ressource allocation algorithm to use
	 */
	public AccessPoint(World world, int ressourceAllocationAlgorithm) {
		ur = new ArrayList<UR>();
		users = new ArrayList<User>();
		this.world = world;
		this.nb_Paket = 0;
		this.res_alloc_alg = ressourceAllocationAlgorithm;
	}


	public int getTime() {
		return world.getTime();
	}


	/**
	 * Ask all clients to send packet
	 */
	public void userGeneratePacket() {
		Iterator<User> it = users.iterator();
		
		while(it.hasNext()) {
			it.next().createPacket();
		}
	}


	/**
	 * Compute the next state of the access point and its users
	 */
	public void nextState() {

		// TODO
	}


	/**
	 * Initialize the access point
	 *
	 * @param nbUsers The number of users for this access point
	 */
	public void init(int nbUsers) {

		// First, clear the list
		users.clear();

		// Initialize the UR list
		for (int i = 0; i < NB_UR; ++i) ur.add(new UR(this));

		// Add the correct number of users
		// The modulo is to put half of the users as near and the other half as far
		for (int i = 0; i < nbUsers; ++i) users.add(new User(i, this, (i%2 == 0)));
	}
}
