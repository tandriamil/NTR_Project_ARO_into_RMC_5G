/**
 * Main class to execute the program
 */
public class Executable {

	/**
	 * Main function to launch the program
	 */
	public static void main(String[] args) {

		// Create a new World
		World my_world = new World();

		// Add an access point
		if (!my_world.addAccessPoint()) {
			System.err.println("[ERROR] World: Can't add the access point number 0 to the list");
			System.exit(-1);
		}

	}
}