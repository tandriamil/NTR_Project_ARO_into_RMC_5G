import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccessPoint {
	private UR ur;
	private List<User> users;
	private World world;
	private int nb_Paket;
	
	public AccessPoint(World world) {
		ur = new UR(this);
		users = new ArrayList<User>();
		this.world = world;
		this.nb_Paket = 0;
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
	
	public void aLLocation(){

	}
}
