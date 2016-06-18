package vincent.ryan.c.RNG.model;

import java.util.Random;

public class RandomNumberGenerator {
	private int generatedNumber;
	
	public RandomNumberGenerator() {
	}
	
	public int getNumber() {
		return generatedNumber;
	}
	
	public void setNumber(int n) {
		this.generatedNumber = n;
	}
	
	public double generate(){
		Random rand = new Random(); 
		generatedNumber = rand.nextInt(10); 
		return generatedNumber;
	}
	
}
