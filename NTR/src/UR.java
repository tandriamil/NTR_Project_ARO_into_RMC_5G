
public class UR {
	
	private AccessPoint accessPoint;
	private User user;
	
	public UR(AccessPoint accessPoint) {
		this.accessPoint = accessPoint;
	}
	
	public void affectURToUser(User user) {
		this.user = user;
	}
}
