package preparation.coffee;

import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryControllerInterfaceImplementation;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.coffee.CoffeeStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;



public class CoffeePreparation extends Preparation{
	CoffeeStatemachine coffeeFsm;
	
	public CoffeePreparation(DrinkFactoryMachine drinkFactory) {
		super(1, DrinkSize.MEDIUM,60);
		coffeeFsm=new CoffeeStatemachine();
		
		coffeeFsm.getSCInterface().getListeners().add(new CoffeePreparationControllerInterfaceImplementation(this,drinkFactory));
		TimerService timer = new TimerService();
		coffeeFsm.setTimer(timer);
		coffeeFsm.init();
		coffeeFsm.enter();
		System.out.println("enter coffee fsm");
	}
	public void prepare(int sugarNumber,DrinkSize drinkSize,int temperature) {
		this.sugarNumber=sugarNumber;
		this.drinkSize=drinkSize;
		this.temperature=temperature;
		coffeeFsm.raisePrepare();
		System.out.println("Raise Prepapare launch");
	}
	
	public void heatingWater() {
		try {
			Thread.sleep(this.timeToHeatingWaterinMS());
			coffeeFsm.raiseIsHot();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void poorDrink() {
		try {
			Thread.sleep(this.timeToPoorDrinkInMs());
			coffeeFsm.raiseDrinkFinishPoored();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void poorSugar() {
		try {
			Thread.sleep(this.timeToPoorDrinkInMs());
			coffeeFsm.raiseSugarFinishPoored();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

