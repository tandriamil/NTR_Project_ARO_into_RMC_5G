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
}
