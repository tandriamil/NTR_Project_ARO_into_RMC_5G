import java.util.List;

public interface Algorithm {

	public List<UR> allocateUR(List<UR> urs);

	public UR allocateSingleUR(UR ur);

	public void init(List<User> users);

	public String getName();

}