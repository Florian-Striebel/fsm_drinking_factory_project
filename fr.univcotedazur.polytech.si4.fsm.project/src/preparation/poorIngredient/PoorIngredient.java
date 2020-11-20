package preparation.poorIngredient;

import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.pooringredient.PoorIngredientStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;

public class PoorIngredient extends Preparation{
	PoorIngredientStatemachine poorIngredientFSM;
	DrinkFactoryMachine factory;

	public PoorIngredient(DrinkFactoryMachine drinkFactory) {
		super(1, DrinkSize.MEDIUM,60);
		factory = drinkFactory;
		poorIngredientFSM=new PoorIngredientStatemachine();
		TimerService timer = new TimerService();
		poorIngredientFSM.setTimer(timer);
		poorIngredientFSM.init();		
	}
	public void prepare(int sugarNumber,DrinkSize drinkSize) {
		this.sugarNumber=sugarNumber;
		this.drinkSize = drinkSize;
		poorIngredientFSM.setTimeDrink(this.timeToPoorDrinkInMs());
		poorIngredientFSM.setTimeSugar(this.timeToPoorSugarInMs());
	}
	
	public PoorIngredientStatemachine getPoorIngredientFSM() {
		return poorIngredientFSM;
	}
}

