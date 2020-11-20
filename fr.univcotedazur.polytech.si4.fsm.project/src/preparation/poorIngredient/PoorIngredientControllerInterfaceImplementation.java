package preparation.poorIngredient;
import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.pooringredient.PoorIngredientStatemachine;


public class PoorIngredientControllerInterfaceImplementation {
	PoorIngredientStatemachine poorIngredientFSM;
	PoorIngredient poorIngredient;
	DrinkFactoryMachine factory;
	
	public PoorIngredientControllerInterfaceImplementation( PoorIngredient poorIngredient,PoorIngredientStatemachine poorIngredientFSM, DrinkFactoryMachine factory) {
		this.poorIngredientFSM=poorIngredientFSM;
		this.factory=factory;
		this.poorIngredient = poorIngredient;
	}


}
