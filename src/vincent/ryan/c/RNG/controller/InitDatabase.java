package vincent.ryan.c.RNG.controller;

import java.util.Scanner;

import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;


public class InitDatabase {
	public static void init(Scanner keyboard) {
		System.out.print("Which database (0=fake, 1=derby): ");
		int which = Integer.parseInt(keyboard.nextLine());
		if (which == 0) {
			//DatabaseProvider.setInstance(new FakeDatabase());
			System.out.println("fake database does not meet expectations");
		} else if (which == 1) {
			DatabaseProvider.setInstance(new DerbyDatabase());
		} else {
			throw new IllegalArgumentException("Invalid choice: " + which);
		}
	}
}
