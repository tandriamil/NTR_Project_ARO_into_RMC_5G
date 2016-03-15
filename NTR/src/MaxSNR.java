import java.util.List;
import java.util.Iterator;

public class MaxSNR {

	private static final int MAX_TIME = 100;

	private List<User> users;
	private List<UR> ur;

	public MaxSNR(List<User> users) {
		this.users = users;
	}

	public List<UR> allocateUR() {
		User user;
		UR ur;
		int cpt = 0;
		for(int i = 0; i < MAX_TIME; i++) {
			Iterator<UR> it_ur = this.ur.iterator();
			while(it_ur.hasNext()) {
				ur = it_ur.next();
				user = getUserWithMaxSNR();

				ur.affectURToUser(user);
				user.checkPacket();
			}
		}
		return this.ur;

	}

	public User getUserWithMaxSNR() {
		User user, userWithMaxSNR = null;
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			user = it.next();
			if((userWithMaxSNR == null || user.getSNR() > userWithMaxSNR.getSNR()) && user.getCurrentPacket() == null)
				userWithMaxSNR = user;
		}

		return userWithMaxSNR;
	}
}
