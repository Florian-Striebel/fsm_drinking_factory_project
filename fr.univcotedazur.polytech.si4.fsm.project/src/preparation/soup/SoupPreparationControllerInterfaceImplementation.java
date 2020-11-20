package preparation.soup;

import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.soup.ISoupStatemachine.SCInterfaceListener;
import preparation.soup.SoupPreparation;

public class SoupPreparationControllerInterfaceImplementation implements SCInterfaceListener {


	SoupPreparation soup;
	DrinkFactoryMachine factory;
	public SoupPreparationControllerInterfaceImplementation(SoupPreparation soupPreparation,DrinkFactoryMachine drinkFactory) {
		this.soup=soupPreparation;
		this.factory=drinkFactory;
	}

	@Override
	public void onPlaceCupRaised() {
		// TODO Auto-generated method stub

		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
		System.out.println("Im in");
	}

	@Override
	public void onHeatingRaised() {
		// TODO Auto-generated method stub
		soup.heatingWater();

	}

	

	@Override
	public void onPooringDrinkRaised() {
		// TODO Auto-generated method stub
		soup.poorDrink();
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
	}

	@Override
	public void onPreparationFinishedRaised() {
		// TODO Auto-generated method stub

		System.out.println("finish Drink");
		factory.theFSM.raisePreparationFinished();
		factory.getProgressBar().setValue(100);	
		
	}

	@Override
	public void onPooringSpiceRaised() {
		// TODO Auto-generated method stub
		soup.poorSpice();
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
		
	}

	@Override
	public void onAddingBreadRaised() {
		// TODO Auto-generated method stub
		
	}


}
