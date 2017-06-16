import java.util.List;


/**
 * Interface for the algorithm used here
 */
public interface Algorithm {


	//public List<UR> allocateUR(List<UR> urs);

	/**
	 * Allocate an user to an UR
	 * @param ur : the ur to allocate
	 * @return UR : the ur allocated
	 */
	public UR allocateSingleUR(UR ur);

	/**
	 * Initialize parameters
	 * @param users : List of users
	 */
	public void init(List<User> users);

	/**
	 * Get the name of this Algorithm
	 */
	public String getName();

}
