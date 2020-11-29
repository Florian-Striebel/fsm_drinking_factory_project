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
		super(1, DrinkSize.MEDIUM,60,drinkFactory);
		poorIngredient = new PoorIngredient(drinkFactory);
		expressoFSM= new ExpressoStatemachine();
		expressoFSM.getSCInterface().getListeners().add(new ExpressoPreparationControllerInterfaceImplementation(this,drinkFactory));
		TimerService timer = new TimerService();
		expressoFSM.setTimer(timer);
		expressoFSM.setPoorI(poorIngredient.getPoorIngredientFSM());
		expressoFSM.init();
		expressoFSM.enter();
	}


	public void prepare(int sugarSize, DrinkSize drinkSize, int temperature,HashMap<Option,Boolean> options,boolean userCup) {
		this.temperature=temperature;
		poorIngredient.prepare(sugarSize, drinkSize,options);
		expressoFSM.setMilk(options.get(Option.MILK));
		expressoFSM.setMilkTime(Option.MILK.getTime());
		expressoFSM.setTimeToHeating(this.timeToHeatingWaterinMS());
		expressoFSM.setUserCup(userCup);
		setBarTime(options, userCup);

	}
	
	private void setBarTime(HashMap<Option,Boolean> options,boolean userCup) {
		int time = Math.max(3000+(Math.max((userCup?0:2000), 1000)), timeToHeatingWaterinMS());
		time += poorIngredient.getTime() + (options.get(Option.MILK)?Option.MILK.getTime():0);
		drinkFactory.theFSM.setTimeToUpdateBar(time/100);
		drinkFactory.theFSM.raiseStartBar();
		expressoFSM.raisePrepare();

	}
}
