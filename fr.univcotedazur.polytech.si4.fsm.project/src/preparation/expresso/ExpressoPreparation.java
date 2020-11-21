package preparation.expresso;

import java.util.HashMap;

import drink.Drink;
import drink.Option;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.OptionPanel;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.expresso.ExpressoStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.expresso.IExpressoStatemachine.SCInterfaceListener;
import preparation.DrinkSize;
import preparation.Preparation;
import preparation.coffee.CoffeePreparationControllerInterfaceImplementation;
import preparation.poorIngredient.PoorIngredient;

public class ExpressoPreparation extends Preparation{
	ExpressoStatemachine expressoFSM;
	PoorIngredient poorIngredient;

	public ExpressoPreparation(DrinkFactoryMachine drinkFactory) {
		super(1, DrinkSize.MEDIUM,60);
		poorIngredient = new PoorIngredient(drinkFactory);
		expressoFSM= new ExpressoStatemachine();
		expressoFSM.getSCInterface().getListeners().add(new ExpressoPreparationControllerInterfaceImplementation(this,drinkFactory));
		TimerService timer = new TimerService();
		expressoFSM.setTimer(timer);
		expressoFSM.init();
		expressoFSM.enter();
		expressoFSM.setPoorI(poorIngredient.getPoorIngredientFSM());

	}
	
	
	public void heatingWater() {
		try {
			Thread.sleep(this.timeToHeatingWaterinMS());
			expressoFSM.raiseIsHot();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void prepare(int sugarSize, DrinkSize drinkSize, int temperature,HashMap<Option,Boolean> options) {
		poorIngredient.prepare(sugarNumber, drinkSize,options);
		this.temperature=temperature;
		expressoFSM.setMilk(options.get(Option.MILK));
		expressoFSM.raisePrepare();
	}
	
}
