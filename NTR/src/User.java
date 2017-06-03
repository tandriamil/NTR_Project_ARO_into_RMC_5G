import java.util.LinkedList;
import java.util.Deque;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;



public class User {

	// Parameters
	private int id;
	private boolean near;
	private AccessPoint accessPoint;
	private List<Packet> packet_send;
	private Deque<Packet> buffer;
	private int debitMoy;
	private int debitCurrent;


	/**
	 * Constructor of user
	 * @param id : the id of this user
	 * @param accessPoint : the access point
	 * @param near : if user is near or not
	 */
	public User(int id, AccessPoint accessPoint, boolean near) {
		this.id = id;
		this.accessPoint = accessPoint;
		this.near = near;
		this.packet_send = new ArrayList<Packet>();;
		buffer = new LinkedList<Packet>();
		if(near) {
			debitMoy = 6;
		}
		else {
			debitMoy = 3;
		}
	}


	/**
	 * Check if an user is near
	 * @return boolean : true if he is near, else false
	 */
	public boolean isNear() {
		return near;
	}


	/**
	 * Create a packet (not at each call), and add it in the user buffer
	 */
	public void createPacket() {
		if(this.randomBoolean()){
			Packet packet = new Packet(this, accessPoint.getTime());
			buffer.add(packet);
		}
	}


	/**
	 * Check the packet : set the nb bits left, set packet end send, ...
	 */
	public void checkPacket() {
		Packet packet = this.getCurrentPacket();
		if(packet != null) {
			int bitsLeft = packet.getNbBitsLeft() - debitCurrent;
			if(bitsLeft <= 0){
				packet.setNbBitsLeft(0);
				this.packetTerminated();
			}
			else {
				packet.setNbBitsLeft(bitsLeft);
			}
		}
	}


	/**
	 * Get the first packet in buffer
	 * @return Packet : the first packet
	 */
	public Packet getCurrentPacket() {
		return buffer.peek();
	}


	/**
	 * Get the list of packet send
	 * @return List<Packet> : the list of packet send
	 */
 	public List<Packet> getPacket_send() {
		return this.packet_send;
	}


	/**
	 * Set packet terminated
	 */
	public void packetTerminated() {
		getCurrentPacket().setEndSend(accessPoint.getTime());
		this.packet_send.add(getCurrentPacket());
		buffer.removeFirst();
	}


	public void removePacket(){
		this.packet_send.remove(getCurrentPacket());
	}


	public void calculateDebit() {
		debitCurrent = (int)(2*(Math.random()*((debitMoy*2)/2+1)));
		//System.out.println("debitCurrent pour utilisateur " + near + " = " + debitCurrent);
	}


	public int getDebitCurrent(){
		return this.debitCurrent;
	}


	public int getSNR() {
		return this.debitCurrent;
	}


	public int getId() {
		return this.id;
	}


	public void interferenceDetected() {
		debitCurrent = debitCurrent / 2;
	}


	private boolean randomBoolean() {
		return (Math.random() < 0.25);
	}
}
