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
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
	}

	@Override
	public void onPlaceCupRaised() {
		System.out.println("place cup");
		factory.getProgressBar().setValue(factory.getProgressBar().getValue()+10);
		factory.setPictureCup("./picts/gobeletPolluant.jpg");
	}

	@Override
	public void onHeatingRaised() {
		coffee.heatingWater();
		System.out.println("hot");
	}

	@Override
	public void onPreparationFinishedRaised() {
		// TODO Auto-generated method stub
		System.out.println("finish Drink");
		factory.theFSM.raisePreparationFinished();
		factory.getProgressBar().setValue(100);
	}

}
