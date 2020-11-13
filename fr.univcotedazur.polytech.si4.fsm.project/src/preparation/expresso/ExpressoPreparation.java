package preparation.expresso;

import drink.Drink;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.expresso.ExpressoStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.expresso.IExpressoStatemachine.SCInterfaceListener;
import preparation.DrinkSize;
import preparation.Preparation;
import preparation.coffee.CoffeePreparationControllerInterfaceImplementation;

public class ExpressoPreparation extends Preparation{
	ExpressoStatemachine expressoFSM;
	public ExpressoPreparation(DrinkFactoryMachine drinkFactory) {
		super(1, DrinkSize.MEDIUM,60);
		expressoFSM= new ExpressoStatemachine();
		expressoFSM.getSCInterface().getListeners().add(new ExpressoPreparationControllerInterfaceImplementation(this,drinkFactory));
		TimerService timer = new TimerService();
		expressoFSM.setTimer(timer);
		expressoFSM.init();
		expressoFSM.enter();
	}
	
	
	public void heatingWater() {
		try {
			Thread.sleep(this.timeToHeatingWaterinMS());
			expressoFSM.raiseIsHot();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void poorDrink() {
		try {
			Thread.sleep(this.timeToPoorDrinkInMs());
			expressoFSM.raiseDrinkFinishPoored();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void poorSugar() {
		try {
			Thread.sleep(this.timeToPoorDrinkInMs());
			expressoFSM.raiseSugarFinishPoored();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void prepare(int sugarSize, DrinkSize drinkSize, int temperature) {
		this.sugarNumber=sugarSize;
		this.drinkSize=drinkSize;
		this.temperature=temperature;
		expressoFSM.raisePrepare();
	}
	
}
