package preparation.tea;

import java.util.List;

import fr.univcotedazur.polytech.si4.fsm.project.coffee.ICoffeeStatemachine.SCInterface;
import fr.univcotedazur.polytech.si4.fsm.project.coffee.ICoffeeStatemachine.SCInterfaceListener;

public class TeaPreparationControllerInterfaceImplementation implements SCInterface{



	@Override
	public void raiseIsHot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raiseSugarFinishPoored() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raiseDrinkFinishPoored() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raiseDrinkPickedUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRaisedPlacePod() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRaisedPlaceCup() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRaisedHeating() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRaisedPooringSugar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRaisedPooringDrink() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SCInterfaceListener> getListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void raisePrepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRaisedPreparationFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
