package preparation;

public class Preparation {
	private final int normalTemperature= 16;
	protected int sugarNumber;
	protected DrinkSize drinkSize;
	protected int temperature;
	
	public Preparation(int sugarNumber, DrinkSize drinkSize, int temprature) {
		this.drinkSize=drinkSize;
		this.sugarNumber=sugarNumber;
		this.temperature= temprature;
	}
	
	protected double timeToPoorSugarInMs(){
		return 10*sugarNumber;//100ms pour 1 sucre
	}
	
	protected long timeToPoorDrinkInMs() {
		return drinkSize.cl*300;//300ms pour 1cl 
	}
	
	protected long timeToHeatingWaterinMS() {
		return (normalTemperature-temperature)*300;
	}
	
	
	
}
