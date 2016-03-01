import java.util.PriorityQueue;


public class User {
	private boolean near;
	private AccessPoint accessPoint;
	private PriorityQueue<Packet> buffer;
	
	public User(AccessPoint accessPoint, boolean near) {
		this.accessPoint = accessPoint;
		this.near = near;
		buffer = new PriorityQueue<Packet>();
	}
	
	public void createPacket() {
		Packet packet = new Packet(this);
		buffer.add(packet);
	}
	
	public Packet getCurrentPacket() {
		return buffer.peek();
	}
	
	public void packetTerminated(int time) {
		getCurrentPacket().setEndSend(time);
		buffer.remove();
	}
}
