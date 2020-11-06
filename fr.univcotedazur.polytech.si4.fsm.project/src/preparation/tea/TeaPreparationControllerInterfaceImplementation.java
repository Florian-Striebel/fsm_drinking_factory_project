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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPlaceCupRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHeatingRaised() {
		// TODO Auto-generated method stub
		tea.heatingWater();
	}

	@Override
	public void onPooringSugarRaised() {
		// TODO Auto-generated method stub
		tea.poorSugar();
	}

	@Override
	public void onPooringDrinkRaised() {
		// TODO Auto-generated method stub
		tea.poorDrink();
	}

	@Override
	public void onPreparationFinishedRaised() {
		// TODO Auto-generated method stub
		System.out.println("finish Drink");
		factory.theFSM.raisePreparationFinished();
		factory.getProgressBar().setValue(100);	
		
	}

	@Override
	public void onBrewingRaised() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDropTeaBagRaised() {
		// TODO Auto-generated method stub
		
	}


}
