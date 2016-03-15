/**
 * Main class to execute the program
 */
public class Executable {

	// Constants
	public static final int MAX_USERS = 16;


	/**
	 * Main function to launch the program
	 */
	public static void main(String[] args) {

		/* ######################### Initialisation ######################### */

		// Variables used here
		int nb_users;

		// Create a new World
		World world = new World();



		/* ######################### One access point - RR ######################### */

		System.out.println("Tests with one access point and RR allocation algorithm");

		// Add an access point
		if (!world.addAccessPoint(AccessPoint.RR_ALLOCATION_ALGORITHM)) {
			System.err.println("[ERROR] World: Can't add the access point number 1 to the list");
			System.exit(-1);
		}

		// Change the number of users dynamically
		for (nb_users = 1; nb_users < MAX_USERS; ++nb_users) {

			// Launch the simulation on the world with this number of users
			world.bootstrap(nb_users);
		}



		/* ######################### One access point - MaxSNR ######################### */
		// Clear the world
		world.clear();

		System.out.println("\tTest with one access point and MaxSNR allocation algorithm");

		// Add an access point
		if (!world.addAccessPoint(AccessPoint.MAX_SNR_ALLOCATION_ALGORITHM)) {
			System.err.println("[ERROR] World: Can't add the access point number 1 to the list");
			System.exit(-1);
		}

		// Change the number of users dynamically
		for (nb_users = 1; nb_users < MAX_USERS; ++nb_users) {

			// Launch the simulation on the world with this number of users
			world.bootstrap(nb_users);
		}



		/* ######################### Two access points - RR ######################### */
		// Clear the world
		/*world.clear();

		System.out.println("Tests with two access points and RR allocation algorithm");

		// Add two access points
		if (!world.addAccessPoint(World.RR_ALLOCATION_ALGORITHM)) {
			System.err.println("[ERROR] World: Can't add the access point number 1 to the list");
			System.exit(-1);
		}
		if (!world.addAccessPoint(World.RR_ALLOCATION_ALGORITHM)) {
			System.err.println("[ERROR] World: Can't add the access point number 2 to the list");
			System.exit(-1);
		}*/



		/* ######################### Two access points - MaxSNR ######################### */
		// Clear the world
		/*world.clear();

		System.out.println("Tests with two access points and MaxSNR allocation algorithm");

		// Add two access points
		if (!world.addAccessPoint(World.MAX_SNR_ALLOCATION_ALGORITHM)) {
			System.err.println("[ERROR] World: Can't add the access point number 1 to the list");
			System.exit(-1);
		}
		if (!world.addAccessPoint(World.MAX_SNR_ALLOCATION_ALGORITHM)) {
			System.err.println("[ERROR] World: Can't add the access point number 2 to the list");
			System.exit(-1);
		}*/

	}
}