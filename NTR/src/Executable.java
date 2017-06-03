/**
 * Main class to execute the program
 */
public class Executable {

	// Constants
	private static final int MIN_USERS = 2;
	public static final int MAX_USERS = 193;


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
		// Init the world
		world.init(World.RR_ALLOCATION_ALGORITHM);

		System.out.println("Tests with one access point and RR allocation algorithm");

		// Add an access point
		if (!world.addAccessPoint()) {
			System.err.println("[ERROR] World: Can't add the access point number 1 to the list");
			System.exit(-1);
		}

		// Change the number of users dynamically
		for (nb_users = MIN_USERS; nb_users < MAX_USERS; nb_users += 2) {

			// Launch the simulation on the world with this number of users
			world.bootstrap(nb_users);
		}



		/* ######################### One access point - MaxSNR ######################### */
		// Init the world
		world.init(World.MAX_SNR_ALLOCATION_ALGORITHM);

		System.out.println("Test with one access point and MaxSNR allocation algorithm");

		// Add an access point
		if (!world.addAccessPoint()) {
			System.err.println("[ERROR] World: Can't add the access point number 1 to the list");
			System.exit(-1);
		}

		// Change the number of users dynamically
		for (nb_users = MIN_USERS; nb_users < MAX_USERS; nb_users += 2) {

			// Launch the simulation on the world with this number of users
			world.bootstrap(nb_users);
		}



		/* ######################### Two access points - RR ######################### */
		// Init the world
		world.init(World.RR_ALLOCATION_ALGORITHM);

		System.out.println("Tests with two access points and RR allocation algorithm");

		// Add two access points
		if (!world.addAccessPoint()) {
			System.err.println("[ERROR] World: Can't add the access point number 1 to the list");
			System.exit(-1);
		}
		if (!world.addAccessPoint()) {
			System.err.println("[ERROR] World: Can't add the access point number 2 to the list");
			System.exit(-1);
		}

		// Change the number of users dynamically
		for (nb_users = MIN_USERS; nb_users < MAX_USERS; nb_users += 2) {

			// Launch the simulation on the world with this number of users
			world.bootstrap(nb_users);
		}



		/* ######################### Two access points - MaxSNR ######################### */
		// Init the world
		world.init(World.MAX_SNR_ALLOCATION_ALGORITHM);

		System.out.println("Tests with two access points and MaxSNR allocation algorithm");

		// Add two access points
		if (!world.addAccessPoint()) {
			System.err.println("[ERROR] World: Can't add the access point number 1 to the list");
			System.exit(-1);
		}
		if (!world.addAccessPoint()) {
			System.err.println("[ERROR] World: Can't add the access point number 2 to the list");
			System.exit(-1);
		}

		// Change the number of users dynamically
		for (nb_users = MIN_USERS; nb_users < MAX_USERS; nb_users += 2) {

			// Launch the simulation on the world with this number of users
			world.bootstrap(nb_users);
		}

	}
}
