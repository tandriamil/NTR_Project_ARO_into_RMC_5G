import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccessPoint {
	private UR ur;
	private List<User> users;
	private World world;
	
	public AccessPoint(World world) {
		ur = new UR(this);
		users = new ArrayList<User>();
		this.world = world;
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

		// Add the correct number of users
		// The modulo is to put half of the users as near and the other half as far
		for (int i = 0; i < nbUsers; ++i) users.add(new User(this, (i%2 == 0)));
	}
}
