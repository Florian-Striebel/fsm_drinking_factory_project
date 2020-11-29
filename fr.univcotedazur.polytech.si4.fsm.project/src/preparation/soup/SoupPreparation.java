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
		super(1, DrinkSize.MEDIUM,60,drinkFactory);
		soupFSM= new SoupStatemachine();
		soupFSM.getSCInterface().getListeners().add(new SoupPreparationControllerInterfaceImplementation(this,drinkFactory));
		TimerService timer = new TimerService();
		soupFSM.setTimer(timer);
		soupFSM.init();
		soupFSM.enter();
	}
	

	public void prepare(int sugarNumber,DrinkSize drinkSize,int temperature,HashMap<Option,Boolean> options,boolean userCup) {
		this.sugarNumber=sugarNumber;
		this.drinkSize=drinkSize;
		this.temperature=temperature;
		soupFSM.setOptionBread(options.get(Option.BREAD_CROUTONS));
		soupFSM.setTimePoorDrink(timeToPoorDrinkInMs());
		soupFSM.setTimeToHeating(this.timeToHeatingWaterinMS());
		soupFSM.setTimeToPooringSpices(this.timeToPoorSugarOrSpicesInMs());
		soupFSM.setBreadTime(Option.BREAD_CROUTONS.getTime());
		soupFSM.setUserCup(userCup);
		setBarTime(options, userCup);
		System.out.println("Raise Prepapare launch"+userCup);
	}
	private void setBarTime(HashMap<Option,Boolean> options,boolean userCup) {
		int time = Math.max(Math.max(2000, timeToPoorSugarOrSpicesInMs())+(userCup?0:2000), timeToHeatingWaterinMS());
		time += Math.max(timeToPoorDrinkInMs(),options.get(Option.BREAD_CROUTONS)?Option.BREAD_CROUTONS.getTime()+3000:0);
		drinkFactory.theFSM.setTimeToUpdateBar(time/100);
		drinkFactory.theFSM.raiseStartBar();
		soupFSM.raisePrepare();

	}
}
