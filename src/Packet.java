/**
 * Packet class to represent the packet that users have to send
 */
public class Packet {

	// Constants
	public static final int PACKET_SIZE = 100;  // Initial packet size in bits

	// Arguments
	private int creation;
	private int beginSend;
	private int endSend;
	private int nbBitsLeft;
	private User user;


	/**
	 * Constructor for the packets
	 *
	 * @param user The owner of this packet
	 * @param time The current creation time of this packet
	 */
	public Packet(User user, int time) {
		this.creation = time;
		this.nbBitsLeft = PACKET_SIZE;
		this.user = user;
	}


	/**
	 * Get the creation time of this packet
	 *
	 * @return The creation time
	 */
	public int getCreation() {
		return this.creation;
	}


	/**
	 * Get the beginning send time of this packet
	 *
	 * @return The creation time
	 */
	public int getBeginSend() {
		return this.beginSend;
	}


	/**
	 * Get the ending send time of this packet
	 *
	 * @return The ending send time
	 */
	public int getEndSend(){
		return this.endSend;
	}


	/**
	 * Get the number of bits left to send
	 *
	 * @return The number of bits left to send
	 */
	public int getNbBitsLeft(){
		return this.nbBitsLeft;
	}


	/**
	 * Set the beginning send time of this packet
	 *
	 * @param time The creation time
	 */
	public void setBeginSend(int time) {
		this.beginSend = time;
	}


	/**
	 * Get the ending send time of this packet
	 *
	 * @param time The ending send time
	 */
	public void setEndSend(int time) {
		this.endSend = time;
	}


	/**
	 * Set the number of bits left to send
	 *
	 * @param nb The number of bits left to send
	 */
	public void setNbBitsLeft(int nb) {
		this.nbBitsLeft = nb;
	}
}
