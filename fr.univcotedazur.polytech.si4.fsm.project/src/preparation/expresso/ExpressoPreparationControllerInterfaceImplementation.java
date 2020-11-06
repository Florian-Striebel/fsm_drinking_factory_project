package preparation.expresso;

import java.util.List;

import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.expresso.IExpressoStatemachine.SCInterfaceListener;

public class ExpressoPreparationControllerInterfaceImplementation implements SCInterfaceListener {
	
	
	ExpressoPreparation expresso;
	DrinkFactoryMachine factory;
	public ExpressoPreparationControllerInterfaceImplementation(ExpressoPreparation expressoPreparation,DrinkFactoryMachine drinkFactory) {
		this.expresso=expressoPreparation;
		this.factory=drinkFactory;
	}


	@Override
	public void onPlaceCupRaised() {
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
		System.out.println("Im in");
	}

	@Override
	public void onHeatingRaised() {
		expresso.heatingWater();
	}

	@Override
	public void onPooringSugarRaised() {
		expresso.poorSugar();
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
	}

	@Override
	public void onPooringDrinkRaised() {
		expresso.poorDrink();
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);

	}

	@Override
	public void onGrindingCoffeeRaised() {		
		//factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
		
	}

	@Override
	public void onGroundingCoffeeRaised() {
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
		
	}


	@Override
	public void onPreparationFinishedRaised() {

		System.out.println("finish Drink");
		factory.theFSM.raisePreparationFinished();
		factory.getProgressBar().setValue(100);		
	}




}
