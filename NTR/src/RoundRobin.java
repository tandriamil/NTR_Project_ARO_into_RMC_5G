import java.util.List;
import java.util.Iterator;

public class RoundRobin implements Algorithm {

	private List<User> users;
	private Iterator<User> it;

	public void init(List<User> users) {
		this.users = users;
		this.it = this.users.iterator();
	}

	public List<UR> allocateUR(List<UR> urs) {
		UR ur;
		User user;
		int cpt = 0;
		Iterator<UR> it_ur = urs.iterator();
		
		boolean found = false;

		while(it_ur.hasNext()) {
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
	}
}
