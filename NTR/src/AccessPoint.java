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
	private Algorithm alg;
	private int nb_Paket;


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
	}


	public int getTime() {
		return world.getTime();
	}
    
    public List<UR> getUr(){
    	return this.ur;
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

		// Clear the ur first
		for (UR u : this.ur) u.clearUR();

		// Create a packet for each user
		// This function randomly create packets, not always
		for (User u : this.users) {
			u.createPacket();
			// Set the current debit for all users
			u.calculateDebit();
		}

		// Get the new UR allocation
		this.ur = alg.allocateUR(this.ur);
	}


	/**
	 * Display the URs
	 */
	public void displayUR() {
		for (UR u : this.ur) {
			System.out.println("\t[" + u.getId() + "] = User n°" + ((u.getUser() != null) ? u.getUser().getId() : "NULL"));
		}
	}


	/**
	 * Initialize the access point
	 *
	 * @param nbUsers The number of users for this access point
	 */
	public void init(int nbUsers) {

		// First, clear the two lists
		users.clear();
		ur.clear();

		// Initialize the UR list
		for (int i = 0; i < NB_UR; ++i) ur.add(new UR(i, this));

		// Add the correct number of users
		// The modulo is to put half of the users as near and the other half as far
		for (int i = 0; i < nbUsers; ++i) users.add(new User(i, this, (i%2 == 0)));

		// Init users list
		alg.init(users);
	}
}
