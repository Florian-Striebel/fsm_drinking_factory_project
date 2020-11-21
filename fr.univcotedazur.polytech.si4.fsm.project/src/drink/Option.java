package drink;

public enum Option {
	BREAD_CROUTONS("Croutons de pain",0.30f),
	MILK("Nuage de lait",0.10f),
	MAPLE_SYRUP("Sirop d'érable",0.10f),
	ICE_CREAM("Glace vanille mixée",0.60f);
	
	
	private String option;
	private float price;
	Option(String o,float r){
		this.option= o;
		this.price=r;
	}
	
	public float getPrice() {
		return price;
	}
	public String getOption() {
		return option;
	}
}
