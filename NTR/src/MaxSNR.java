import java.util.List;
import java.util.Iterator;

public class MaxSNR implements Algorithm {

	// Constants
	public static final String NAME = "RR";

	private List<User> users;

	public void init(List<User> users) {
		this.users = users;
	}

	public List<UR> allocateUR(List<UR> urs) {
		User user;
		UR ur;

		Iterator<UR> it_ur = urs.iterator();
		while(it_ur.hasNext()) {
			ur = it_ur.next();
			user = getUserWithMaxSNR(users);

			if(user != null){
				ur.affectURToUser(user);
				user.checkPacket();
			}
		}

		return urs;

	}


	private User getUserWithMaxSNR(List<User> users) {
		User user, userWithMaxSNR = null;
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			user = it.next();
			
			// Set the current debit for all users
			user.calculateDebit();

			if((userWithMaxSNR == null || user.getSNR() > userWithMaxSNR.getSNR()) && user.getCurrentPacket() != null)
				userWithMaxSNR = user;
		}

		return userWithMaxSNR;
	}

	public String getName() {
		return NAME;
	}
}
