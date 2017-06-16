/**
 * Interface for the classes which will perform calculations
 */
public interface Calculation {

	/**
	 * The function to execute calculations on a given world
	 */
	public void execute();


	/**
	 * The function to finalize calculations
	 * Write the results in the file
	 */
	public void finalize(int nb_Users);
}
