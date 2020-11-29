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
		System.out.println("place pod");
	}

	@Override
	public void onPlaceCupRaised() {
		System.out.println("place cup");
		factory.setPictureCup("./picts/gobeletPolluant.jpg");
	}

	@Override
	public void onHeatingRaised() {
		System.out.println("heatting");
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
