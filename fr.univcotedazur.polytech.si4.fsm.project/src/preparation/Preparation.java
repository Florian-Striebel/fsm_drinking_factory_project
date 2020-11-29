package preparation;

import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;

public class Preparation {
	private final int normalTemperature= 16;
	protected int sugarNumber;
	protected DrinkSize drinkSize;
	protected int temperature;
	protected DrinkFactoryMachine drinkFactory;

	
	public Preparation(int sugarNumber, DrinkSize drinkSize, int temprature,DrinkFactoryMachine drinkFactory) {
		this.drinkSize=drinkSize;
		this.sugarNumber=sugarNumber;
		this.temperature= temprature;
		this.drinkFactory = drinkFactory;
	}
	
	public int timeToPoorSugarOrSpicesInMs(){
		return 100*sugarNumber;//100ms pour 1 sucre
	}
	
	public int timeToPoorDrinkInMs() {
		return drinkSize.cl*300;//300ms pour 1cl 
	}
	
	protected int timeToHeatingWaterinMS() {
		return (temperature-normalTemperature)*300;
	}
}
