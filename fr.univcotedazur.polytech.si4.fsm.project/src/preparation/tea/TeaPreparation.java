package preparation.tea;

import drink.Drink;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.tea.TeaStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;

public class TeaPreparation extends Preparation{
	TeaStatemachine teaFSM;
	DrinkFactoryMachine factory;


	public TeaPreparation(DrinkFactoryMachine fact) {	
	super(1, DrinkSize.MEDIUM,60);
	teaFSM= new TeaStatemachine();
	teaFSM.getSCInterface().getListeners().add(new TeaPreparationControllerInterfaceImplementation(this,fact));
	TimerService timer = new TimerService();
	teaFSM.setTimer(timer);
	teaFSM.init();
	teaFSM.enter();
	}
	
	
	
	public void heatingWater() {
		try {
			Thread.sleep(this.timeToHeatingWaterinMS());
			teaFSM.raiseIsHot();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void poorDrink() {
		try {
			Thread.sleep(this.timeToPoorDrinkInMs());
			teaFSM.raiseDrinkFinishPoored();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void poorSugar() {
		try {
			Thread.sleep(this.timeToPoorDrinkInMs());
			teaFSM.raiseSugarFinishPoored();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
