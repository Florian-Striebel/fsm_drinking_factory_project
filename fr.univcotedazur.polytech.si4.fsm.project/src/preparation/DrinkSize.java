package preparation;

public enum DrinkSize {
	SHORT(10),
	MEDIUM(25),
	LONG(50);
	
	final int cl;
	DrinkSize(int val) {
		this.cl=val;
	}
}
