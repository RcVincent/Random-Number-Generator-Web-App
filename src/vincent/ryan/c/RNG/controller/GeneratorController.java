package vincent.ryan.c.RNG.controller;

import vincent.ryan.c.RNG.model.RandomNumberGenerator;
import vincent.ryan.c.RNG.model.User;

public class GeneratorController {
	private RandomNumberGenerator model; 
	
	public void setModel (RandomNumberGenerator model) {
		this.model = model;
	}
	
	public Double start() {
		double r = model.generate();
		return r;
	}
	
	public boolean authenticate(User u, String password){
		boolean real = false;
		if(u.getPassword().equals(password)) {
			real = true;
		}
		return real;
	}
}
