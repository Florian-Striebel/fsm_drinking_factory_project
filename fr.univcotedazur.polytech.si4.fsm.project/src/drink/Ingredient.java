package drink;

public enum Ingredient {
	DOSETTECAFE(0),
	DOSEGRAINCAFE(1),
	SACHETTHE(2),
	DOSESOUPE(3),
	CROUTONS(4),
	DOSELAIT(5),
	DOSESIROPDERABLE(6),
	DOSEGLACE(7);
	
	private int pos;
	private Ingredient(int pos) {
		this.pos=pos;
	}
	public int getPos() {
		return pos;
	}
	
}
