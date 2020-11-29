package drink;

public enum Option {
	BREAD_CROUTONS("Croutons de pain",0.30f,Ingredient.CROUTONS,1000),
	MILK("Nuage de lait",0.10f,Ingredient.DOSELAIT,2000),
	MAPLE_SYRUP("Sirop d'érable",0.10f,Ingredient.DOSESIROPDERABLE,2000),
	ICE_CREAM("Glace vanille mixée",0.60f,Ingredient.DOSEGLACE,4000);
	
	
	private String option;
	private float price;
	private Ingredient ingredient;
	private int time;
	Option(String o,float r,Ingredient i,int t){
		this.option= o;
		this.price=r;
		this.ingredient = i;
		this.time = t;
	}
	
	public float getPrice() {
		return price;
	}
	public String getOption() {
		return option;
	}
	
	public Ingredient getIngredient()
	{
		return ingredient;
	}
	public int getTime() {
		return time;
	}
}
