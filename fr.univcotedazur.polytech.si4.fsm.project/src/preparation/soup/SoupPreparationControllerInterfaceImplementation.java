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

		factory.setPictureCup("./picts/gobeletPolluant.jpg");
	}

	@Override
	public void onPreparationFinishedRaised() {
		// TODO Auto-generated method stub

		System.out.println("finish Drink");
		factory.theFSM.raisePreparationFinished();
	}


	@Override
	public void onAddingBreadRaised() {
		// TODO Auto-generated method stub
		System.out.println("Adding Bread");

	}



}
