import java.util.List;
import java.util.Iterator;

public class RoundRobin implements Algorithm {

	// Constants
	public static final String NAME = "RR";

	// Parameters
	private List<User> users;
	private Iterator<User> it;

	/**
	 * Initialize parameters
	 * @param users : List of users
	 */
	public void init(List<User> users) {
		this.users = users;
		this.it = this.users.iterator();
	}

	/*public List<UR> allocateUR(List<UR> urs) {
		UR ur;
		User user;
		int cpt = 0;
		Iterator<UR> it_ur = urs.iterator();

		boolean found = false;

		while(it_ur.hasNext()) {
			calculateDebit();

			cpt = 0;
			ur = it_ur.next();
			user = null;

			found = false;

			while(!found && cpt < 2) {
				if(it.hasNext()) {
					user = it.next();
					if(user.getCurrentPacket() != null){
						found = true;
					}
					else {
						user = null;
					}
				}
				else {
					cpt ++;
					it = users.iterator();
				}
			}

			if(user != null) {
				ur.affectURToUser(user);
				user.checkPacket();
			}

			if(!it.hasNext())
				it = users.iterator();
		}

		return urs;
	}*/

	/**
	 * Allocate an user to an UR
	 * @param ur : the ur to allocate
	 * @return UR : the ur allocated
	 */
	public UR allocateSingleUR(UR ur) {
		User user = null;

		int cpt = 0;

		boolean found = false;

		// Caluclate new current debit for all users
		calculateDebit();

		// While any user found if an user without empty buffer exists
		while(!found && cpt < 2) {
			// If there is a next user in the list
			if(it.hasNext()) {
				user = it.next();

				// If user have a packet in his buffer
				if(user.getCurrentPacket() != null){
					found = true;
				}
				else {
					user = null;
				}
			}
			else {
				cpt ++;

				// Restart to the head of the list
				it = users.iterator();
			}
		}

		// If we have found an user, allocate him to the current ur
		if(user != null) {
			ur.affectURToUser(user);
		}

		if(!it.hasNext())
			it = users.iterator();

		return ur;
	}

	/**
	 * Calculate the debit for all users
	 */
	private void calculateDebit() {
		Iterator<User> it = users.iterator();
		User user;
		while(it.hasNext()) {
			user = it.next();
			// Set the current debit for this user
			user.calculateDebit();
		}
	}

	/**
	 * Get the name of this Algorithm
	 */
	public String getName() {
		return NAME;
	}
}
