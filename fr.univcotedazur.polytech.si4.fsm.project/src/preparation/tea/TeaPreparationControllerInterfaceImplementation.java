package preparation.tea;

import java.util.List;

import fr.univcotedazur.polytech.si4.fsm.project.DrinkFactoryMachine;
import fr.univcotedazur.polytech.si4.fsm.project.tea.ITeaStatemachine.SCInterfaceListener;

public class TeaPreparationControllerInterfaceImplementation implements SCInterfaceListener{
	TeaPreparation tea;
	DrinkFactoryMachine factory;
	
	public TeaPreparationControllerInterfaceImplementation(TeaPreparation teaPreparation, DrinkFactoryMachine fact) {
		this.tea = teaPreparation;
		this.factory = fact;
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
