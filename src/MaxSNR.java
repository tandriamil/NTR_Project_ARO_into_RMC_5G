import java.util.List;
import java.util.Iterator;

public class MaxSNR implements Algorithm {

	// Constants
	public static final String NAME = "MAX_SNR";

	// Parameters
	private List<User> users;

	/**
	 * Initialize parameters
	 * @param users : List of users
	 */
	public void init(List<User> users) {
		this.users = users;
	}

	// public List<UR> allocateUR(List<UR> urs) {
	// 	User user;
	// 	UR ur;

	// 	Iterator<UR> it_ur = urs.iterator();
	// 	while(it_ur.hasNext()) {
	// 		ur = it_ur.next();
	// 		user = getUserWithMaxSNR();

	// 		if(user != null){
	// 			ur.affectURToUser(user);
	// 			user.checkPacket();
	// 		}
	// 	}

	// 	return urs;

	// }

	/**
	 * Allocate an user to an UR
	 * @param ur : the ur to allocate
	 * @return UR : the ur allocated
	 */
	public UR allocateSingleUR(UR ur) {
		// Get user with the max SNR
		User user = getUserWithMaxSNR();

		// If we have found an user, allocate him to the current ur
		if(user != null){
			ur.affectURToUser(user);
		}

		return ur;
	}

	/**
	 * Get the user with the highest SNR
	 * @return User : the user with the highest SNR
	 */
	private User getUserWithMaxSNR() {
		User user, userWithMaxSNR = null;
		Iterator<User> it = users.iterator();

		// While there is an user
		while(it.hasNext()) {
			user = it.next();

			// Set the current debit for all users
			user.calculateDebit();

			// If current user have bigger SNR than userWithMaxSNR
			if((userWithMaxSNR == null || user.getSNR() > userWithMaxSNR.getSNR()) && user.getCurrentPacket() != null)
				userWithMaxSNR = user;
		}

		return userWithMaxSNR;
	}

	/**
	 * Get the name of this Algorithm
	 */
	public String getName() {
		return NAME;
	}
}
