import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccessPoint {

	// Constants
	public static final int NB_UR = 128;
	
	// Prameters
	private List<UR> ur;
	private List<User> users;
	private World world;
	private int nb_Paket;


	/**
	 * Constructor for the access point
	 *
	 * @param world The world where this AP exists
	 */
	public AccessPoint(World world) {
		ur = new ArrayList<UR>();
		users = new ArrayList<User>();
		this.world = world;
		this.nb_Paket = 0;
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
	 * Create packets for each user
	 */
	public void userCreatePacket() {

		// Create a packet for each user
		// This function randomly create packets, not always
		for (User u : this.users) {
			u.createPacket();
		}
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
	 * @param alg The ressource allocation algorithm used here
	 */
	public void init(int nbUsers, Algorithm alg) {

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
