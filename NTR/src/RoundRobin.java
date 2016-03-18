import java.util.List;
import java.util.Iterator;

public class RoundRobin implements Algorithm {

	public List<UR> allocateUR(List<User> users, List<UR> urs) {
		UR ur;
		User user;
		int cpt = 0;
		Iterator<User> it = users.iterator();
		Iterator<UR> it_ur = urs.iterator();
		// while(it.hasNext() && it_ur.hasNext()){
		// 	do {
		// 		// There is an user 
		// 		if(!it.hasNext()) {
		// 			it = users.iterator();
		// 			cpt++;
		// 		}
		// 		user = it.next();
		// 	} while(user.getCurrentPacket() == null && cpt == 1);

		// 	if(user.getCurrentPacket() != null) {
		// 		ur = it_ur.next();
		// 		ur.affectURToUser(user);
		// 		user.checkPacket();
		// 	}
		// }

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
