package preparation;

public class Preparation {
	private final int normalTemperature= 16;
	private int sugarNumber;
	private DrinkSize drinkSize;
	private int temperature;
	
	public Preparation(int sugarNumber, DrinkSize drinkSize, int temprature) {
		this.drinkSize=drinkSize;
		this.sugarNumber=sugarNumber;
		this.temperature= temprature;
	}
	
	double timeToPoorSugarInMs(){
		return 10*sugarNumber;//10ms pour 1 sucre
	}
	
	double timeToPoorDrinkInMs() {
		return drinkSize.cl*10;//10ms pour 1cl 
	}
	
	double timeToHeatingWaterinMS() {
		return (normalTemperature-temperature)*100;
	}
	
}
