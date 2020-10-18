package preparation.expresso;

import drink.Drink;
import fr.univcotedazur.polytech.si4.fsm.project.expresso.ExpressoStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;

public class ExpressoPreparation extends Preparation{
	ExpressoStatemachine expressoFSM;
	public ExpressoPreparation(int sugarNumber, DrinkSize drinkSize, int temprature) {
		super(sugarNumber, drinkSize, temprature);
		expressoFSM= new ExpressoStatemachine();
		expressoFSM.init();
		expressoFSM.enter();
	}
}
