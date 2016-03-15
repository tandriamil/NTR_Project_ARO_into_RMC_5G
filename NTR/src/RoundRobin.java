import java.util.List;
import java.util.Iterator;

public class RoundRobin {

	private static final int MAX_TIME = 100;

	private List<User> users;
	private List<UR> ur;

	public RoundRobin(List<User> users) {
		this.users = users;
	}

	public List<UR> allocateUR() {
		User user;
		UR ur;
		int cpt = 0;
		for(int i = 0; i < MAX_TIME; i++) {
			Iterator<User> it = users.iterator();
			Iterator<UR> it_ur = this.ur.iterator();
			while(it.hasNext() && it_ur.hasNext()){
				do {
					// There is an user 
					if(!it.hasNext()) {
						it = users.iterator();
						cpt++;
					}
					user = it.next();
				} while(user.getCurrentPacket() == null && cpt == 1);

				if(user.getCurrentPacket() == null)
					return;

				ur = it_ur.next();
				ur.affectURToUser(user);
				user.checkPacket();
			}
		}
		return ur;
	}
}
