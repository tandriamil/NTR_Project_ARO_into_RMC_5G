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
		if((int)(Math.random() * 1000)%2 == 0){
			Packet packet = new Packet(this, accessPoint.getTime());
			buffer.add(packet);
		}
	}
	
	public Packet getCurrentPacket() {
		return buffer.peek();
	}
	
	public void packetTerminated() {
		getCurrentPacket().setEndSend(accessPoint.getTime());
		buffer.remove();
	}
}
