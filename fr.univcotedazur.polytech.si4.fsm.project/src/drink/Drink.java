package drink;

public enum Drink {
	COFFE("coffee",0.35f),
	EXPRESSO("Expresso", 0.50f),
	TEA("Tea", 0.40f),
	SOUP("Soup", 0.75f);

	private String name;
	private float price;
	Drink(String n,float p){
		this.name= n;
		this.price=p;
	}
	
	public float getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
}
