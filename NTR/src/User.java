import java.util.LinkedList;
import java.util.Deque;
import java.util.Random;


public class User {
	private int id;
	private boolean near;
	private AccessPoint accessPoint;
	private Deque<Packet> buffer;
	private Random numberGenerator;
	private int debitMoy;
	private int debitCurrent;
	
	public User(int id, AccessPoint accessPoint, boolean near) {
		this.id = id;
		this.accessPoint = accessPoint;
		this.near = near;
		buffer = new LinkedList<Packet>();
		if(near) {
			debitMoy = 6;
		}
		else {
			debitMoy = 3;
		}

		this.numberGenerator = new Random();
	}
	
	public void createPacket() {

		if(this.numberGenerator.nextInt()%2 == 0){
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
		buffer.removeFirst();
	}

	public void calculateDebit() {
		debitCurrent = 2*(int)(Math.random()*((debitMoy*2)/2+1));
	}

	public int getDebitCurrent(){
		return this.debitCurrent;
	}

	public int getSNR() {
		return 0;
	}

	public int getId() {
		return this.id;
	}

	public void seedNumberGenerator() {
		// Set the seed for the random number generator
		this.numberGenerator.setSeed(System.currentTimeMillis());
	}
}
