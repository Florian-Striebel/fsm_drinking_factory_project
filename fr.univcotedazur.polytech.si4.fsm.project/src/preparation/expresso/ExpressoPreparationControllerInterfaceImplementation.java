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
		factory.setPictureCup("./picts/gobeletPolluant.jpg");
	}



	@Override
	public void onPreparationFinishedRaised() {
		System.out.println("finish Drink");
		factory.theFSM.raisePreparationFinished();
	}


	@Override
	public void onAddingMilkRaised() {
		System.out.println("add milk");
	}



}
