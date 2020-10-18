package preparation.tea;

import drink.Drink;
import fr.univcotedazur.polytech.si4.fsm.project.tea.TeaStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;

public class TeaPreparation extends Preparation{
	TeaStatemachine teaFSM;
	
	public TeaPreparation(int sugarNumber, DrinkSize drinkSize, int temprature) {
		super(sugarNumber, drinkSize, temprature);

		teaFSM= new TeaStatemachine();
		teaFSM.init();
		teaFSM.enter();
	}
}
