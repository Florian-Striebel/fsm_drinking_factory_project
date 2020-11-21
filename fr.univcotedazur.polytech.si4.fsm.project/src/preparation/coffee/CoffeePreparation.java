package preparation.coffee;

import java.util.HashMap;

import drink.Option;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.coffee.CoffeeStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.pooringredient.PoorIngredientStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;
import preparation.poorIngredient.PoorIngredient;



public class CoffeePreparation extends Preparation{
	CoffeeStatemachine coffeeFsm;
	PoorIngredient poorIngredient;
	
	public CoffeePreparation(DrinkFactoryMachine drinkFactory) {
		super(1, DrinkSize.MEDIUM,60);
		TimerService timer = new TimerService();
		poorIngredient = new PoorIngredient(drinkFactory);
		coffeeFsm=new CoffeeStatemachine();
		coffeeFsm.getSCInterface().getListeners().add(new CoffeePreparationControllerInterfaceImplementation(this,drinkFactory));
		coffeeFsm.setTimer(timer);
		coffeeFsm.setPoorI(poorIngredient.getPoorIngredientFSM());
		coffeeFsm.init();
		coffeeFsm.enter();
		System.out.println("enter coffee fsm");
	}
	public void prepare(int sugarNumber,DrinkSize drinkSize,int temperature,HashMap<Option,Boolean> options) {
		this.temperature=temperature;
		poorIngredient.prepare(sugarNumber, drinkSize,options);
		coffeeFsm.setMilk(options.get(Option.MILK));
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

}

