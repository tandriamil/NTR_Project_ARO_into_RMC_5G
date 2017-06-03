
public class UR {

	// Parameters
	private AccessPoint accessPoint;
	private User user;
	private int id;

	/**
	 * Constructor of UR
	 * @param id : the id of this UR
	 * @param accessPoint : the access point
	 */
	public UR(int id, AccessPoint accessPoint) {
		this.accessPoint = accessPoint;
		this.id = id;
	}


	/**
	 * Affect an user to this UR
	 * @param user : the user to affect
	 */
	public void affectURToUser(User user) {
		this.user = user;
	}


	/**
	 * Get the UR id
	 * @return int : the id
	 */
	public int getId() {
		return this.id;
	}


	/**
	 * Get the user affects to this UR
	 * @return User : the user affects to this this UR
	 */
	public User getUser() {
		return this.user;
	}


	/**
	 * Set the user affects to this UR to null
	 */
	public void clearUR() {
		this.user = null;
	}
}
