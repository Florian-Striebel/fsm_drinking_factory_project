package preparation.coffee;

import fr.univcotedazur.polytech.si4.fsm.project.TimerService;
import fr.univcotedazur.polytech.si4.fsm.project.coffee.CoffeeStatemachine;
import preparation.DrinkSize;
import preparation.Preparation;



public class CoffeePreparation extends Preparation{
	CoffeeStatemachine coffeeFsm;
	
	public CoffeePreparation(int sugarNumber,DrinkSize drinkSize,int temperature) {
		super(sugarNumber, drinkSize,temperature);
		coffeeFsm=new CoffeeStatemachine();
		TimerService timer = new TimerService();
		coffeeFsm.setTimer(timer);
		coffeeFsm.init();
		coffeeFsm.enter();
	}
}
