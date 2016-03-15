import java.util.List;
import java.util.Iterator;

public class MaxSNR implements Algorithm {

	public List<UR> allocateUR(List<User> users, List<UR> urs) {
		User user;
		UR ur;

		Iterator<UR> it_ur = urs.iterator();
		while(it_ur.hasNext()) {
			ur = it_ur.next();
			user = getUserWithMaxSNR(users);

			ur.affectURToUser(user);
			user.checkPacket();
		}

		return urs;

	}


	private User getUserWithMaxSNR(List<User> users) {
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
