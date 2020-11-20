package preparation.tea;

import drink.Drink;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.tea.TeaStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;
import preparation.poorIngredient.PoorIngredient;

public class TeaPreparation extends Preparation{
	TeaStatemachine teaFSM;
	DrinkFactoryMachine factory;
	PoorIngredient poorIngredient;

	public TeaPreparation(DrinkFactoryMachine fact) {	
	super(1, DrinkSize.MEDIUM,60);
	poorIngredient = new PoorIngredient(fact);
	teaFSM= new TeaStatemachine();
	teaFSM.getSCInterface().getListeners().add(new TeaPreparationControllerInterfaceImplementation(this,fact));
	TimerService timer = new TimerService();
	teaFSM.setTimer(timer);
	teaFSM.init();
	teaFSM.enter();
	teaFSM.setPoorI(poorIngredient.getPoorIngredientFSM());
	}
	
	
	public void prepare(int sugarSize, DrinkSize drinkSize, int temperature) {
		this.sugarNumber=sugarSize;
		this.drinkSize=drinkSize;
		this.temperature=temperature;
		teaFSM.raisePrepare();
	}
	
	public void heatingWater() {
		try {
			Thread.sleep(this.timeToHeatingWaterinMS());
			teaFSM.raiseIsHot();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
