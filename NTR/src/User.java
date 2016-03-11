import java.util.PriorityQueue;


public class User {
	private int id;
	private boolean near;
	private AccessPoint accessPoint;
	private PriorityQueue<Packet> buffer;
	private int debitMoy;
	private int debitCurrent;
	
	public User(int id, AccessPoint accessPoint, boolean near) {
		this.id = id;
		this.accessPoint = accessPoint;
		this.near = near;
		buffer = new PriorityQueue<Packet>();
		if(near) {
			debitMoy = 6;
		}
		else {
			debitMoy = 3;
		}
	}
	
	public void createPacket() {
		if((int)(Math.random() * 1000)%2 == 0){
			Packet packet = new Packet(this, accessPoint.getTime());
			buffer.add(packet);
		}
	}

	public void checkPacket() {
		Packet packet = this.getCurrentPacket();
		int bitsLeft = packet.getNbBitsLeft() - debitCurrent;
		if(bitsLeft <= 0){
			bitsLeft = 0;
			this.packetTerminated();
		}
	}
	
	public Packet getCurrentPacket() {
		return buffer.peek();
	}
	
	public void packetTerminated() {
		getCurrentPacket().setEndSend(accessPoint.getTime());
		buffer.remove();
	}

	public void calculateDebit() {
		debitCurrent = 2*(int)(Math.random()*((debitMoy*2)/2+1));
	}

	public int getSNR() {
		return 0;
	}
}
