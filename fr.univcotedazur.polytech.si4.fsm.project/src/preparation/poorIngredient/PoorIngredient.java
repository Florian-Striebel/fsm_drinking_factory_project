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
	DrinkFactoryMachine factory;

	public PoorIngredient(DrinkFactoryMachine drinkFactory) {
		super(1, DrinkSize.MEDIUM,60);
		factory = drinkFactory;
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
		poorIngredientFSM.setTimeSugar(this.timeToPoorSugarInMs());
		poorIngredientFSM.setMappleSyrup(options.get(Option.MAPLE_SYRUP));
		poorIngredientFSM.setIceCream(options.get(Option.ICE_CREAM));

	}
	
	public PoorIngredientStatemachine getPoorIngredientFSM() {
		return poorIngredientFSM;
	}
}

