package drink;

public enum Drink {
	COFFE("café",0.35f),
	EXPRESSO("expresso", 0.50f),
	TEA("thé", 0.40f),
	SOUP("soupe", 0.75f);

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
