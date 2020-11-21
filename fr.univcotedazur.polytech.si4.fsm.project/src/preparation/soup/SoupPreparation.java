package preparation.soup;

import java.util.HashMap;

import drink.Drink;
import drink.Option;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.soup.SoupStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.soup.ISoupStatemachine.SCInterfaceListener;
import preparation.DrinkSize;
import preparation.Preparation;
import preparation.soup.SoupPreparationControllerInterfaceImplementation;

public class SoupPreparation extends Preparation{
	SoupStatemachine soupFSM;
	public SoupPreparation(DrinkFactoryMachine drinkFactory) {
		super(1, DrinkSize.MEDIUM,60);
		soupFSM= new SoupStatemachine();
		soupFSM.getSCInterface().getListeners().add(new SoupPreparationControllerInterfaceImplementation(this,drinkFactory));
		TimerService timer = new TimerService();
		soupFSM.setTimer(timer);
		soupFSM.init();
		soupFSM.enter();
	}
	

	public void prepare(int sugarNumber,DrinkSize drinkSize,int temperature,HashMap<Option,Boolean> options) {
		this.sugarNumber=sugarNumber;
		this.drinkSize=drinkSize;
		this.temperature=temperature;
		soupFSM.setOptionBread(options.get(Option.BREAD_CROUTONS));
		soupFSM.setTimePoorDrink(timeToPoorDrinkInMs());
		soupFSM.raisePrepare();
		System.out.println("Raise Prepapare launch");
	}
	
	public void heatingWater() {
		try {
			Thread.sleep(this.timeToHeatingWaterinMS());
			soupFSM.raiseIsHot();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void poorSpice() {
		try {
			Thread.sleep(this.timeToPoorDrinkInMs());
			soupFSM.raiseSpiceFinishPoored();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	
}
