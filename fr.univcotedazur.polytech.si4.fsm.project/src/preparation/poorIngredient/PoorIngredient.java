package preparation.poorIngredient;

import java.util.HashMap;

import drink.Option;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.pooringredient.PoorIngredientStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;
import preparation.coffee.CoffeePreparationControllerInterfaceImplementation;

public class PoorIngredient extends Preparation{
	PoorIngredientStatemachine poorIngredientFSM;
	public PoorIngredient(DrinkFactoryMachine drinkFactory) {
		super(1, DrinkSize.MEDIUM,60,drinkFactory);
		poorIngredientFSM=new PoorIngredientStatemachine();
		poorIngredientFSM.getSCInterface().getListeners().add(new PoorIngredientControllerInterfaceImplementation());

		TimerService timer = new TimerService();
		poorIngredientFSM.setTimer(timer);
		poorIngredientFSM.init();		
	}
	public void prepare(int sugarNumber,DrinkSize drinkSize,HashMap<Option,Boolean> options) {
		this.sugarNumber=sugarNumber;
		this.drinkSize = drinkSize;
		poorIngredientFSM.setTimeDrink(this.timeToPoorDrinkInMs());
		poorIngredientFSM.setTimeSugar(this.timeToPoorSugarOrSpicesInMs());
		poorIngredientFSM.setMappleSyrup(options.get(Option.MAPLE_SYRUP));
		poorIngredientFSM.setMappleTime(Option.MAPLE_SYRUP.getTime());
		poorIngredientFSM.setIceCream(options.get(Option.ICE_CREAM));
		poorIngredientFSM.setIceCreamTime(Option.ICE_CREAM.getTime());


	}
	
	public PoorIngredientStatemachine getPoorIngredientFSM() {
		return poorIngredientFSM;
	}
	
	public int getTime() {
		int time = Math.max(this.timeToPoorDrinkInMs(),poorIngredientFSM.getMappleSyrup()?Option.MAPLE_SYRUP.getTime():timeToPoorSugarOrSpicesInMs());
		time += poorIngredientFSM.getIceCream()?Option.ICE_CREAM.getTime():0;
		return time;
	}
}

