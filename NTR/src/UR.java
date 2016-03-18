
public class UR {
	
	private AccessPoint accessPoint;
	private User user;
	private int id;
	
	public UR(int id, AccessPoint accessPoint) {
		this.accessPoint = accessPoint;
		this.id = id;
	}
	
	public void affectURToUser(User user) {
		this.user = user;
	}

	public int getId() {
		return this.id;
	}

	public User getUser() {
		return this.user;
	}

	public void clearUR() {
		this.user = null;
	}
}
