package preparation.poorIngredient;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.pooringredient.IPoorIngredientStatemachine.SCInterfaceListener;
import fr.univcotedazur.polytech.si4.fsm.project.pooringredient.PoorIngredientStatemachine;


public class PoorIngredientControllerInterfaceImplementation implements SCInterfaceListener {

	
	public PoorIngredientControllerInterfaceImplementation() {
	
	}

	@Override
	public void onPooringSyrupRaised() {
		System.out.println("add Syrup");
	}

	@Override
	public void onDoAddMixIcecreamRaised() {
		System.out.println("add iceCream");
		
	}


}
