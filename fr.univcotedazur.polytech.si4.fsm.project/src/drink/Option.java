package drink;

public enum Option {
	BREAD_CROUTONS("Croutons de pain",0.30f,Ingredient.CROUTONS),
	MILK("Nuage de lait",0.10f,Ingredient.DOSELAIT),
	MAPLE_SYRUP("Sirop d'érable",0.10f,Ingredient.DOSESIROPDERABLE),
	ICE_CREAM("Glace vanille mixée",0.60f,Ingredient.DOSEGLACE);
	
	
	private String option;
	private float price;
	private Ingredient ingredient;
	
	Option(String o,float r,Ingredient i){
		this.option= o;
		this.price=r;
		this.ingredient = i;
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
}
