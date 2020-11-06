package preparation.coffee;

import java.util.List;

import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.coffee.ICoffeeStatemachine.SCInterfaceListener;

public class CoffeePreparationControllerInterfaceImplementation  implements SCInterfaceListener{
	CoffeePreparation coffee;
	DrinkFactoryMachine factory;
	
	public CoffeePreparationControllerInterfaceImplementation(CoffeePreparation coffee, DrinkFactoryMachine factory) {
		this.coffee=coffee;
		this.factory=factory;
	}

	@Override
	public void onPlacePodRaised() {
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
	}

	@Override
	public void onPlaceCupRaised() {
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
	}

	@Override
	public void onHeatingRaised() {
		coffee.heatingWater();
	}

	@Override
	public void onPooringSugarRaised() {

		coffee.poorSugar();
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
	}

	@Override
	public void onPooringDrinkRaised() {
		System.out.println("pooring Drink");
		coffee.poorDrink();
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
	}

	@Override
	public void onPreparationFinishedRaised() {
		// TODO Auto-generated method stub
		System.out.println("finish Drink");
		factory.getProgressBar().setValue(100);
		factory.theFSM.raiseFinished();
	}

}
