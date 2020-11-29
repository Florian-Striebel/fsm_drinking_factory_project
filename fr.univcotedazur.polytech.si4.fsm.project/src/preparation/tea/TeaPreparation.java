package preparation.tea;

import java.util.HashMap;

import drink.Drink;
import drink.Option;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.tea.TeaStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;
import preparation.poorIngredient.PoorIngredient;

public class TeaPreparation extends Preparation{
	TeaStatemachine teaFSM;
	PoorIngredient poorIngredient;

	public TeaPreparation(DrinkFactoryMachine fact) {	
	super(1, DrinkSize.MEDIUM,60,fact);
	poorIngredient = new PoorIngredient(fact);
	teaFSM= new TeaStatemachine();
	teaFSM.getSCInterface().getListeners().add(new TeaPreparationControllerInterfaceImplementation(this,fact));
	TimerService timer = new TimerService();
	teaFSM.setTimer(timer);
	teaFSM.setPoorI(poorIngredient.getPoorIngredientFSM());
	teaFSM.init();
	teaFSM.enter();
	}
	
	
	public void prepare(int sugarSize, DrinkSize drinkSize, int temperature,HashMap<Option,Boolean> options,boolean userCup) {
		this.temperature=temperature;
		poorIngredient.prepare(sugarSize, drinkSize,options);
		teaFSM.setMilk(options.get(Option.MILK));
		teaFSM.setMilkTime(Option.MILK.getTime());
		teaFSM.setTimeToHeating(this.timeToHeatingWaterinMS());
		teaFSM.setUserCup(userCup);
		setBarTime(options, userCup);
	}
	
	private void setBarTime(HashMap<Option,Boolean> options,boolean userCup) {
		int time = Math.max(1000+(userCup?0:2000), timeToHeatingWaterinMS());
		time += poorIngredient.getTime() +5000+1000+ (options.get(Option.MILK)?Option.MILK.getTime():0);
		drinkFactory.theFSM.setTimeToUpdateBar(time/100);
		drinkFactory.theFSM.raiseStartBar();
		teaFSM.raisePrepare();

	}
}
