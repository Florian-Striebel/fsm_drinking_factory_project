/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.expresso;

import fr.univcotedazur.polytech.si4.fsm.project.IStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.ITimerCallback;
import fr.univcotedazur.polytech.si4.fsm.project.pooringredient.PoorIngredientStatemachine;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public interface IExpressoStatemachine extends ITimerCallback,IStatemachine {
	public interface SCInterface {
	
		public void raiseIsHot();
		
		public void raiseDrinkPickedUp();
		
		public void raisePrepare();
		
		public boolean isRaisedPreparationFinished();
		
		public boolean isRaisedGrindingCoffee();
		
		public boolean isRaisedGroundingCoffee();
		
		public boolean isRaisedPlaceCup();
		
		public boolean isRaisedHeating();
		
		public boolean isRaisedAddingMilk();
		
		public PoorIngredientStatemachine getPoorI();
		
		public void setPoorI(PoorIngredientStatemachine value);
		
		public boolean getMilk();
		
		public void setMilk(boolean value);
		
		public boolean getUserCup();
		
		public void setUserCup(boolean value);
		
	public List<SCInterfaceListener> getListeners();
	}
	
	public interface SCInterfaceListener {
	
		public void onPreparationFinishedRaised();
		public void onGrindingCoffeeRaised();
		public void onGroundingCoffeeRaised();
		public void onPlaceCupRaised();
		public void onHeatingRaised();
		public void onAddingMilkRaised();
		}
	
	public SCInterface getSCInterface();
	
}
