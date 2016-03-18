import java.util.List;

public interface Algorithm {

	public List<UR> allocateUR(List<UR> urs);

	public void init(List<User> users);

}