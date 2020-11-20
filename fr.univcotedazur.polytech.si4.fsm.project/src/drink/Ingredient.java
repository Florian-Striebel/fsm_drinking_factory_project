package drink;

public enum Ingredient {
	DOSETTECAFE(0),
	GRAINCAFE(1),
	SACHETTHE(2),
	DOSESOUPE(3);
	
	private int pos;
	private Ingredient(int pos) {
		this.pos=pos;
	}
	public int getPos() {
		return pos;
	}
}
