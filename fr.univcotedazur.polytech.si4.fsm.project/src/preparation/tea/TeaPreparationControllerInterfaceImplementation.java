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
	public void onPlaceTeaBagRaised() {
		
	}

	@Override
	public void onPlaceCupRaised() {
		factory.setPictureCup("./picts/gobeletPolluant.jpg");
	}

	@Override
	public void onHeatingRaised() {
		tea.heatingWater();
	}
	
	@Override
	public void onPreparationFinishedRaised() {
		System.out.println("finish Drink");
		factory.theFSM.raisePreparationFinished();
		factory.getProgressBar().setValue(100);	
		
	}

	@Override
	public void onBrewingRaised() {
		
	}

	@Override
	public void onDropTeaBagRaised() {
		
	}


}
