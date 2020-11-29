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
		super(1, DrinkSize.MEDIUM,60,drinkFactory);
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
	public void prepare(int sugarNumber,DrinkSize drinkSize,int temperature,HashMap<Option,Boolean> options,boolean userCup) {
		this.temperature=temperature;
		poorIngredient.prepare(sugarNumber, drinkSize,options);
		coffeeFsm.setMilk(options.get(Option.MILK));
		coffeeFsm.setMilkTime(Option.MILK.getTime());
		coffeeFsm.setUserCup(userCup);
		coffeeFsm.setTimeToHeating(this.timeToHeatingWaterinMS());
		System.out.println("Raise Prepapare launch");
		setBarTime(options,userCup);
	}
	
	
	
	private void setBarTime(HashMap<Option,Boolean> options,boolean userCup) {
		int time = Math.max(1000+(userCup?0:2000), timeToHeatingWaterinMS());
		time += poorIngredient.getTime() + (options.get(Option.MILK)?Option.MILK.getTime():0);
		drinkFactory.theFSM.setTimeToUpdateBar(time/100);
		drinkFactory.theFSM.raiseStartBar();
		coffeeFsm.raisePrepare();

	}
}

